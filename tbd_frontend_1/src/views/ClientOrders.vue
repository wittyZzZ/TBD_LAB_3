<template>
    <v-container class="mt-15">

        <p class="my-10 text-h4 font-weight-bold"> Ordenes de {{ this.client.username }}</p>


        <v-table>
            <thead>
                <tr>
                    <th class="text-left font-weight-bold">
                    ID Orden
                    </th>
                    <th class="text-left font-weight-bold">
                    Estado de Orden
                    </th>
                    <th class="text-left font-weight-bold">
                    Fecha
                    </th>
                    <th class="text-left font-weight-bold">
                    Total
                    </th>
                    <th class="text-left font-weight-bold">
                    Acciones
                    </th>
                </tr>
            </thead>

            <tbody>
            <tr
                v-for="order in this.orders"
                :key="order.id_orden"
            >
                <td>{{ order.id_orden}}</td>
                <td class="text-capitalize">{{ order.estado }}</td>
                <td>{{ order.fecha_orden}}</td>
                <td>$ {{ order.total }}</td>
                <td>
                    <v-btn @click="viewOrderDetails(order.id_orden)" color="primary"> Ver Detalle </v-btn>
                </td>
            </tr>
            </tbody>
        </v-table>


    </v-container>
  
</template>

<script>

import clienteService from '@/services/cliente.service';
import ordenService from '@/services/orden.service';

export default {

    data() {
        return {
            id_cliente: null,
            client: [],
            orders: [],
        }
    },

    mounted() {

        this.id_cliente = this.$route.query.id;

        if (this.id_cliente) {

            // Se obtiene al cliente
            // clienteService.getById(this.id_cliente)
            // .then(response => {
            //     console.log("Cliente obtenido: ",response.data);
            //     this.cliente = response.data; // Asignamos los datos de la respuesta
            // })
            // .catch(error => {
            //     console.error('Error al obtener al cliente:', error); // Manejamos errores
            // });

            this.client = { id_cliente: 1, 
                username: "koyo", 
                email: "correoxd@gmail.com", 
                direccion: "direccion generica xd", 
                latitude: -33.4510, longitude: -70.6510 };

            // Se obtienen las ordenes del cliente
            // ordenService.getByClientId(this.id_cliente)
            // .then(response => {
            //     console.log("Ordenes obtenidas: ",response.data);
            //     this.orders = response.data; // Asignamos los datos de la respuesta
            // })
            // .catch(error => {
            //     console.error('Error al obtener las ordenes:', error); // Manejamos errores
            // });

            this.orders = [
                { id_orden: 1, fecha_orden: "2024-12-12T10:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 50000 },
                { id_orden: 2, fecha_orden: "2024-12-12T11:00:00", estado: "completada", id_cliente: this.id_cliente, total: 75000 },
                { id_orden: 3, fecha_orden: "2024-12-12T12:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 40000 },
                { id_orden: 4, fecha_orden: "2024-12-12T13:00:00", estado: "completada", id_cliente: this.id_cliente, total: 60000 },
                { id_orden: 5, fecha_orden: "2024-12-12T14:00:00", estado: "cancelada", id_cliente: this.id_cliente, total: 35000 },
                { id_orden: 6, fecha_orden: "2024-12-12T15:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 80000 },
                { id_orden: 7, fecha_orden: "2024-12-12T16:00:00", estado: "completada", id_cliente: this.id_cliente, total: 45000 },
                { id_orden: 8, fecha_orden: "2024-12-12T17:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 70000 },
                { id_orden: 9, fecha_orden: "2024-12-12T18:00:00", estado: "completada", id_cliente: this.id_cliente, total: 65000 },
                { id_orden: 10, fecha_orden: "2024-12-12T19:00:00", estado: "cancelada", id_cliente: this.id_cliente, total: 50000 },
                { id_orden: 11, fecha_orden: "2024-12-12T20:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 60000 },
                { id_orden: 12, fecha_orden: "2024-12-12T21:00:00", estado: "completada", id_cliente: this.id_cliente, total: 90000 },
                { id_orden: 13, fecha_orden: "2024-12-12T22:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 50000 },
                { id_orden: 14, fecha_orden: "2024-12-12T23:00:00", estado: "completada", id_cliente: this.id_cliente, total: 55000 },
                { id_orden: 15, fecha_orden: "2024-12-12T24:00:00", estado: "pendiente", id_cliente: this.id_cliente, total: 75000 },
            ];
        }
    }, 
    
    methods: {
        viewOrderDetails(id_orden) {
            this.$router.push({ name: "OrderDetails", query: { id: id_orden } });
        }
    }

}
</script>

<style>

</style>