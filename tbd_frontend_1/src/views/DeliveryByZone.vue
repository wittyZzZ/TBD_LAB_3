<template>

    <v-container class="mt-15">

        <div class="mt-5 text-h4 font-weight-bold">Repartidores con Envíos en Zona de Reparto</div>
        <v-divider class="mt-5"></v-divider>
        <Map class="mt-10" ref="map" />

        <v-row class="my-4 d-flex justify-start">
            <v-col cols="6" md="6">
                <!-- Selector de Zona de Reparto -->
                <v-select
                v-model="selectedZoneId"
                :items="zones"
                item-title="nombre"
                item-value="id_zona"
                label="Seleccionar zona de reparto"
                outlined
                />
            </v-col>
            
            <v-col cols="4" class="ml-15">
                <!-- Botón -->
                <v-btn
                color="primary"
                block
                size="large"
                @click="fetchDeliveriesInPolygon"
                >
                Mostrar ordenes en zona de reparto
                </v-btn>
            </v-col>

            <v-divider class="mt-3"></v-divider>

            <v-col v-if="deliverys.length > 0" cols="12" md="3">

                <div class="my-5 text-h5 font-weight-bold">Repartidores</div>

                <v-table>
                    <thead>
                    <tr>
                        <th class="text-left">
                        ID de Repartidor
                        </th>
                        <th class="text-left">
                        Nombre
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr
                        v-for="delivery in deliverys"
                        :key="delivery.id_repartidor"
                    >
                        <td>{{ delivery.id_repartidor }}</td>
                        <td>{{ delivery.nombre }}</td>

                    </tr>
                    </tbody>
                </v-table>
                
            </v-col>
        </v-row>
       
    </v-container>

</template>

<script>
import Map from "../components/Map.vue";
import zonarepartoService from "@/services/zonareparto.service";

export default {
    components: {
        Map
    },

    data() {
        return {
            zones: [],
            deliverys: [],
            selectedZoneId: null,
            selectedZone: [],
        }
    },

    methods: {
        // Parsea coordenadas de poligono para Leaflet
        parsePolygon(wkt) {
            // Elimina el prefijo "POLYGON((" y el sufijo "))"
            const coordinates = wkt
                .replace("POLYGON((", "")
                .replace("))", "")
                .split(",") // Divide las coordenadas por coma
                .map(pair => {
                    const [lon, lat] = pair.trim().split(" ").map(Number); // Divide por espacio y convierte a números
                    return [parseFloat(lat), parseFloat(lon)]; // Leaflet usa formato [lat, lon]
                });
            return coordinates;
        },

        // Obtiene órdenes alrededor de la tienda seleccionada
        fetchDeliveriesInPolygon() {
            if (!this.selectedZoneId) {
                alert("Por favor, selecciona una zona de reparto.");
                return;
            }

            // Encuentra la tienda seleccionada en el arreglo de tiendas
            this.selectedZone = this.zones.find(
                (zone) => zone.id_zona === this.selectedZoneId
            );

            // Limpia los marcadores y circulos existentes en el mapa
            this.$refs.map.clearMarkers();
            this.$refs.map.circleGroup.clearLayers();
            this.$refs.map.polygonGroup.clearLayers(); // Limpia cualquier polígono existente

            // Convierte el polígono WKT a coordenadas
            const coordinates = this.parsePolygon(this.selectedZone.poligono);

            console.log("PARSE DE POLIGONO", coordinates);

            // Dibuja el poligon
            this.$refs.map.putPolygon(coordinates);

            // Centra el mapa en la zona seleccionada
            const [lat, lon] = coordinates[0];
            this.$refs.map.map.setView([lat, lon], 14);

            // Se obtienen los deliverys que estan en la zona de reparto
            zonarepartoService
                .getDeliveriesInPolygon(this.selectedZoneId)
                .then((response) => {
                    console.log("DELIVERYS ENCONTRADOS: ",response.data);
                    this.deliverys = response.data; // Asigna los deliveries    
                    // Marca las ubicaciones de las órdenes en el mapa
                    this.deliverys.forEach((delivery) => {
                        // Marca la ubicación de la orden en el mapa usando las coordenadas del cliente
                        this.$refs.map.putMarker(
                            `Orden ${delivery.id_orden} entregada por Repartidor ${delivery.id_repartidor}`,
                            delivery.latitude,
                            delivery.longitude,
                            "order" // Usa el ícono específico para las órdenes
                        );
                    });
                })
                .catch((error) => {
                console.error("Error al obtener los deliveries", error); // Maneja errores
                });

        },
    },

    mounted() {

        // Se obtienen las zonas de reparto
        zonarepartoService.getAll()
        .then(response => {
            console.log("ZONAS OBTENIDAS: ",response.data);
            this.zones = response.data; // Asignamos los datos de la respuesta
        })
        .catch(error => {
            console.error('Error al obtener las zonas de reparto:', error); // Manejamos errores
        });
    
    }
}
</script>

<style>

</style>