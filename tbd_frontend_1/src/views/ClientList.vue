<template>
    <v-container class="mt-15">

        <p class="my-10 text-h4 font-weight-bold"> Clientes Registrados </p>
     

        <v-table>
            <thead>
                <tr>
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
                <td>{{ client.username }}</td>
                <td>{{ client.email}}</td>
                <td>{{ client.direccion[0].direccion}}</td>
                <td>
                    <v-btn @click="viewClientOrders(client.id_cliente)" color="primary"> Ver Ordenes </v-btn>
                    <v-btn @click="editClient(client.id_cliente)" color="green">Editar</v-btn>

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
        clienteService.getAll()
        .then(response => {
            console.log("Clientes obtenidos: ",response.data);
            this.clients = response.data; // Asignamos los datos de la respuesta
        })
        .catch(error => {
            console.error('Error al obtener los clientes:', error); // Manejamos errores
        });
    
    },

    methods: {
        viewClientOrders(id_cliente) {
            this.$router.push({ name: "ClientOrders", query: { id: id_cliente } });
        },
        editClient(id_cliente) {
            this.$router.push({ path: `/client/${id_cliente}` }); // Redirigir al formulario de edición
        },
    }

}
</script>

<style>

</style>