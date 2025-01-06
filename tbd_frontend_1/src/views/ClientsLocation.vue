<template>
    <v-container class="mt-15">
        <div class="mt-5 text-h4 font-weight-bold">Ubicación de Clientes</div>
        <v-divider class="mt-5"></v-divider>

        <Map class="mt-10" ref="map" />

        <v-row class="my-4 d-flex justify-start">
            <v-col cols="6" md="6">
                <!-- Selector de Cliente -->
                <v-select
                    v-model="selectedClientId"
                    :items="clients"
                    item-title="username"
                    item-value="id_cliente"
                    label="Seleccionar Cliente"
                    outlined
                />
            </v-col>

            <v-col cols="4" class="ml-15">
                <!-- Botón -->
                <v-btn
                    color="primary"
                    block
                    size="large"
                    @click="markClientOnMap"
                >
                    Mostrar ubicación del cliente
                </v-btn>
            </v-col>
        </v-row>

        <v-divider class="mt-3"></v-divider>

        <!-- Tabla de clientes -->
        <v-col v-if="clients.length > 0" cols="12" md="8">
            <div class="my-5 text-h5 font-weight-bold">Lista de Clientes</div>

            <v-table>
                <thead>
                    <tr>
                        <th class="text-left">ID Cliente</th>
                        <th class="text-left">Nombre</th>
                        <th class="text-left">Dirección</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="client in clients" :key="client.id_cliente">
                        <td>{{ client.id_cliente }}</td>
                        <td>{{ client.username }}</td>
                        <td>
                            <ul>
                                <li v-for="address in client.direccion" :key="address.id_direccion">
                                    {{ address.direccion }}
                                </li>
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </v-table>
        </v-col>
    </v-container>
</template>

<script>
import Map from "../components/Map.vue";
import clienteService from "@/services/cliente.service";

export default {
    components: {
        Map,
    },

    data() {
        return {
            clients: [],
            selectedClientId: null,
        };
    },

    methods: {
        // Marca en el mapa la ubicación del cliente seleccionado
        markClientOnMap() {
            if (!this.selectedClientId) {
                alert("Por favor, selecciona un cliente.");
                return;
            }

            // Encuentra el cliente seleccionado
            const selectedClient = this.clients.find(
                (client) => client.id_cliente === this.selectedClientId
            );

            if (selectedClient && selectedClient.direccion.length > 0) {
                // Limpia los marcadores existentes
                this.$refs.map.clearMarkers();

                // Marca las ubicaciones de las direcciones del cliente en el mapa
                selectedClient.direccion.forEach((address) => {
                    if (address.coordinates && address.coordinates.length === 2) {
                        const [lon, lat] = address.coordinates; // [longitud, latitud]
                        this.$refs.map.putMarker(
                            `Cliente: ${selectedClient.username}, Dirección: ${address.direccion}`,
                            lat,
                            lon,
                            "client" // Usa un ícono específico para clientes
                        );

                        // Centra el mapa en la primera dirección
                        this.$refs.map.map.setView([lat, lon], 14);
                    }
                });
            } else {
                alert("El cliente seleccionado no tiene direcciones válidas.");
            }
        },
    },

    mounted() {
        // Se obtienen los clientes
        clienteService
            .getAll()
            .then((response) => {
                console.log("CLIENTES OBTENIDOS: ", response.data);
                this.clients = response.data; // Asignamos los datos de la respuesta
            })
            .catch((error) => {
                console.error("Error al obtener los clientes:", error); // Manejamos errores
            });
    },
};
</script>

<style></style>
