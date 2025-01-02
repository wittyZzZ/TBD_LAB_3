<template>
    <v-container class="mt-15">

        <p class="my-10 text-h4 font-weight-bold"> Clientes Registrados </p>
     

        <v-table>
            <thead>
                <tr>
                    <th class="text-left font-weight-bold">
                    ID Cliente
                    </th>
                    <th class="text-left font-weight-bold">
                    Nombre de Usuario
                    </th>
                    <th class="text-left font-weight-bold">
                    Correo
                    </th>
                    <th class="text-left font-weight-bold">
                    Dirección Principal
                    </th>
                    <th class="text-left font-weight-bold">
                    Acciones
                    </th>
                </tr>
            </thead>

            <tbody>
            <tr
                v-for="client in this.clients"
                :key="client.id_cliente"
            >
                <td>{{ client.id_cliente}}</td>
                <td>{{ client.username }}</td>
                <td>{{ client.email}}</td>
                <td>{{ client.direccion }}</td>
                <td>
                    <v-btn @click="viewClientOrders(client.id_cliente)" color="primary"> Ver Ordenes </v-btn>

                </td>
            </tr>
            </tbody>
        </v-table>


    </v-container>
  
</template>

<script>

import clienteService from '@/services/cliente.service';

export default {
    data() {
        return {
            clients: [],
        }
    }, 

    mounted() {

        // Se obtienen los clientes
        // clienteService.getAll()
        // .then(response => {
        //     console.log("Clientes obtenidos: ",response.data);
        //     this.clients = response.data; // Asignamos los datos de la respuesta
        // })
        // .catch(error => {
        //     console.error('Error al obtener los clientes:', error); // Manejamos errores
        // });

        this.clients = [
            { id_cliente: 1, username: "Cliente 1", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.4510, longitude: -70.6510 },
            { id_cliente: 2, username: "Cliente 2", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.4495, longitude: -70.6485 },
            { id_cliente: 3, username: "Cliente 3", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -23.6505, longitude: -70.3995 },
            { id_cliente: 4, username: "Cliente 4", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -23.6485, longitude: -70.4015 },
            { id_cliente: 5, username: "Cliente 5", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -36.8190, longitude: -73.0490 },
            { id_cliente: 6, username: "Cliente 6", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -36.8210, longitude: -73.0510 },
            { id_cliente: 7, username: "Cliente 7", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -29.8990, longitude: -70.9010 },
            { id_cliente: 8, username: "Cliente 8", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -29.9015, longitude: -70.8990 },
            { id_cliente: 9, username: "Cliente 9", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.0465, longitude: -71.6195 },
            { id_cliente: 10, username: "Cliente 10", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.0485, longitude: -71.6215 },
            { id_cliente: 11, username: "Cliente 11", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.4512, longitude: -70.6505 },
            { id_cliente: 12, username: "Cliente 12", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.4498, longitude: -70.6498 },
            { id_cliente: 13, username: "Cliente 13", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -23.6510, longitude: -70.3985 },
            { id_cliente: 14, username: "Cliente 14", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -23.6490, longitude: -70.4005 },
            { id_cliente: 15, username: "Cliente 15", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -36.8195, longitude: -73.0485 },
            { id_cliente: 16, username: "Cliente 16", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -36.8215, longitude: -73.0505 },
            { id_cliente: 17, username: "Cliente 17", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -29.9005, longitude: -70.8995 },
            { id_cliente: 18, username: "Cliente 18", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -29.8985, longitude: -70.9015 },
            { id_cliente: 19, username: "Cliente 19", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.0475, longitude: -71.6205 },
            { id_cliente: 20, username: "Cliente 20", email: "correoxd@gmail.com", direccion: "direccion generica xd", latitude: -33.0490, longitude: -71.6185 },
        ];
    
    },

    methods: {
        viewClientOrders(id_cliente) {
            this.$router.push({ name: "ClientOrders", query: { id: id_cliente } });
        }
    }

}
</script>

<style>

</style>