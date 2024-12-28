<template>

    <v-container class="mt-15">

        <div class="mt-5 text-h4 font-weight-bold">Clientes Dentro de Radio de Tienda</div>
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
                <v-btn color="primary" block size="large" @click="fetchNearbyClients">
                    Mostrar clientes en radio
                </v-btn>
            </v-col>

            <v-divider class="mt-3"></v-divider>

            <v-col v-if="clients.length > 0" cols="12">

                <div class="my-5 text-h5 font-weight-bold">Clientes</div>

                <v-table>
                    <thead>
                    <tr>
                        <th class="text-left">
                        ID de Cliente
                        </th>
                        <th class="text-left">
                        Nombre
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
                    <tr
                        v-for="client in clients"
                        :key="client.id_cliente"
                    >
                        <td>{{ client.id_cliente }}</td>
                        <td>{{ client.username }}</td>
                        <td>{{ client.latitude }}</td>
                        <td>{{ client.longitude }}</td>

                    </tr>
                    </tbody>
                </v-table>
                
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import Map from "../components/Map.vue";
import deliveryService from "@/services/delivery.service";
import tiendaService from "@/services/tienda.service";
import clienteService from '../services/cliente.service';


export default {
    components: { Map },
    data() {
        return {
            stores: [], // Lista de tiendas
            selectedStoreId: 1,
            radius: 30, // Radio en kilómetros
            clients: [], // Clientes obtenidos
            selectedStore: [],
        };
    },
    
    methods: {
        async fetchNearbyClients() {

            // Encuentra la tienda seleccionada
            const selectedStore = this.stores.find(store => store.id_tienda === this.selectedStoreId);


            if (!selectedStore) {
                alert("Tienda no encontrada.");
                return;
            }

            if (!this.selectedStoreId || this.radius <= 0) {
                alert("Por favor, selecciona una tienda y un radio válido.");
                return;
            }

            try {
                this.$refs.map.clearMarkers();
                this.$refs.map.putMarker(selectedStore.nombre, selectedStore.latitude, selectedStore.longitude, "store");
                this.$refs.map.putCircle(selectedStore.latitude, selectedStore.longitude, this.radius * 1000);

                this.$refs.map.map.setView(
                    [selectedStore.latitude, selectedStore.longitude],
                    14 // Nivel de zoom (puedes ajustarlo según lo necesites)
                );
                // Llamada al backend para obtener clientes cercanos
                const response = await clienteService.getInRadius(this.selectedStoreId, this.radius);

                // Muestra los clientes obtenidos
                this.clients = response.data;

                this.clients.forEach(client => {
                    this.$refs.map.putMarker(client.username, client.latitude, client.longitude, "client");
                });
            } catch (error) {
                console.error("Error al obtener clientes:", error);
                alert("Error al obtener clientes. Por favor, intenta nuevamente.");
            }
        },
    },
   
    
    async mounted() {
        try {
            // Cargar las tiendas desde el backend
            const response = await tiendaService.getAll();
            this.stores = response.data;

            // Opcional: Centrar el mapa en la tienda principal por defecto
        } catch (error) {
            console.error("Error al obtener las tiendas:", error);
            alert("Error al obtener las tiendas. Por favor, intenta nuevamente.");
        }
    }
   
    
};
</script>
