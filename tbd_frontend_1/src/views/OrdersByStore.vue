<template>

    <v-container class="mt-15">

        <div class="mt-5 text-h4 font-weight-bold">Ordenes Enviadas Alrededor de Almacén</div>
        <v-divider class="mt-5"></v-divider>
        <Map class="mt-10" ref="map" />

        <v-row class="my-4 d-flex justify-center">
            <v-col cols="12" md="6">
                <!-- Selector de Tienda -->
                <v-select
                    v-model="selectedStoreId"
                    :items="stores"
                    item-title="nombre"
                    item-value="id_tienda"
                    label="Seleccionar tienda"
                    outlined
                />
            </v-col>
            <v-col cols="12" md="6">
                <!-- Input para Radio -->
                <v-text-field
                    v-model="radius"
                    type="number"
                    label="Radio en KM"
                    outlined
                />
            </v-col>
            <v-col cols="4">
                <!-- Botón -->
                <v-btn color="primary" block size="large" @click="fetchOrdersAroundStore">
                    Mostrar órdenes alrededor de tienda
                </v-btn>
            </v-col>

            <v-divider class="mt-3"></v-divider>

            <v-col v-if="selectedStoreId" cols="12">

                <div class="my-5 text-h5 font-weight-bold">Tienda</div>

                <v-table>
                    <thead>
                    <tr>
                        <th class="text-left">
                        ID de Tienda
                        </th>
                        <th class="text-left">
                        Nombre
                        </th>
                        <th class="text-left">
                        Dirección
                        </th>
                        <th class="text-left">
                        Latitud
                        </th>
                        <th class="text-left">
                        Longitud
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{{ this.selectedStore.id_tienda }}</td>
                        <td>{{ this.selectedStore.nombre }}</td>
                        <td>{{ this.selectedStore.direccion }}</td>
                        <td>{{ this.selectedStore.latitude }}</td>
                        <td>{{ this.selectedStore.longitude }}</td>
                    </tr>
                    </tbody>
                </v-table>

            </v-col>

            <v-col v-if="orders.length > 0" cols="12">

                <v-divider class="mt-3"></v-divider>

                <div class="my-5 text-h5 font-weight-bold">Ordenes</div>

                <v-table>
                    <thead>
                    <tr>
                        <th class="text-left">
                        Número de Orden
                        </th>
                        <th class="text-left">
                        ID de Cliente
                        </th>
                        <th class="text-left">
                        Fecha de Orden
                        </th>
                        <th class="text-left">
                        Latitud
                        </th>
                        <th class="text-left">
                        Longitud
                        </th>
                        <th class="text-left">
                        Total
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr
                        v-for="order in orders"
                        :key="order.id_orden"
                    >
                        <td>{{ order.id_orden }}</td>
                        <td>{{ order.id_cliente }}</td>
                        <td>{{ order.fecha_orden }}</td>
                        <td>{{ order.latitude }}</td>
                        <td>{{ order.longitude }}</td>
                        <td>{{ "$"+order.total }}</td>
                    </tr>
                    </tbody>
                </v-table>
        
            </v-col>

            
            
        </v-row>
       
    </v-container>

</template>

<script>
import Map from "../components/Map.vue";
import tiendaService from "../services/tienda.service";
import ordenService from "@/services/orden.service";

export default {
    components: {
        Map
    },

    data() {
        return {
            stores: [],
            orders: [],
            selectedStoreId: null,
            selectedStore: [],
            radius: 0
        }
    },

    methods: {

        // Obtiene órdenes alrededor de la tienda seleccionada
        fetchOrdersAroundStore() {
            if (!this.selectedStoreId || this.radius <= 0) {
                alert("Por favor, selecciona una tienda y un radio válido.");
                return;
            }

            // Encuentra la tienda seleccionada en el arreglo de tiendas
            this.selectedStore = this.stores.find(
                (store) => store.id_tienda === this.selectedStoreId
            );

            // Limpia los marcadores y circulos existentes en el mapa
            this.$refs.map.clearMarkers();

            // Marca la tienda seleccionada en el mapa
            this.$refs.map.putMarker(
                this.selectedStore.nombre,
                this.selectedStore.latitude,
                this.selectedStore.longitude,
                "store"
            );

            // Centra el mapa en la tienda seleccionada y ajusta el zoom
            this.$refs.map.map.setView(
                [this.selectedStore.latitude, this.selectedStore.longitude],
                14 // Nivel de zoom (puedes ajustarlo según lo necesites)
            );
            
            // Dibuja el círculo alrededor de la tienda
            const radiusInMeters = this.radius * 1000; // Convierte el radio de KM a metros
            this.$refs.map.putCircle(
                this.selectedStore.latitude,
                this.selectedStore.longitude,
                radiusInMeters
            );

            // Se obtienen las ordenes enviadas desde la tienda indicada en el radio indicado 
            ordenService.getOrdersWithinRadius(this.selectedStoreId, this.radius)
                .then(response => {
                    console.log("Órdenes dentro del radio:", response.data);
                    this.orders = response.data; // Asigna las órdenes obtenidas
                    // Marca las ubicaciones de las órdenes en el mapa
                    this.orders.forEach((order) => {
                        // Marca la ubicación de la orden en el mapa usando las coordenadas del cliente
                        this.$refs.map.putMarker(
                        `Orden ${order.id_orden}`,
                        order.latitude,
                        order.longitude,
                        "order" // Usa el ícono específico para las órdenes
                        );
                    });
                })
                .catch(error => {
                    console.error("Error al obtener órdenes:", error);
                });
        },
    },

    mounted() {
        // Se obtienen las tiendas
        tiendaService.getAll()
        .then(response => {
            this.stores = response.data; // Asignamos los datos de la respuesta
        })
        .catch(error => {
            console.error('Error al obtener las tiendas:', error); // Manejamos errores
        });
    }
}
</script>

<style>

</style>