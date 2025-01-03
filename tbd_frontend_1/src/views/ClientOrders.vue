<template>
    <v-container class="mt-15">

        <p class="my-10 text-h4 font-weight-bold"> Ordenes de {{this.cliente.username}} </p>


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
            cliente: [],
            orders: [],
        }
    },

    mounted() {

        this.id_cliente = this.$route.query.id;

        if (this.id_cliente) {

            // Se obtiene al cliente
            clienteService.getById(this.id_cliente)
            .then(response => {
                console.log("Cliente obtenido: ",response.data);
                this.cliente = response.data; // Asignamos los datos de la respuesta
            })
            .catch(error => {
                console.error('Error al obtener al cliente:', error); // Manejamos errores
            });

            // Se obtienen las ordenes del cliente
            ordenService.getByClientId(this.id_cliente)
            .then(response => {
                console.log("Ordenes obtenidas: ",response.data);
                this.orders = response.data; // Asignamos los datos de la respuesta
            })
            .catch(error => {
                console.error('Error al obtener las ordenes:', error); // Manejamos errores
            });
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