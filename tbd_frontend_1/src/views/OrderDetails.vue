<template>
    <v-container class="mt-15">

        <p class="my-10 text-h4 font-weight-bold"> Detalles de Orden N°{{ this.id_orden }}</p>


        <v-table>
            <thead>
                <tr>
                    <th class="text-left font-weight-bold">
                    ID Detalle
                    </th>
                    <th class="text-left font-weight-bold">
                    ID Producto
                    </th>
                    <th class="text-left font-weight-bold">
                    Precio Unitario
                    </th>
                    <th class="text-left font-weight-bold">
                    Cantidad
                    </th>
                </tr>
            </thead>

            <tbody>
            <tr
                v-for="detail in this.details"
                :key="detail.id_detalle"
            >
                <td>{{ detail.id_detalle}}</td>
                <td>{{ detail.id_producto }}</td>
                <td>$ {{ detail.precio_unitario}}</td>
                <td>{{ detail.cantidad }} unidades</td>
            </tr>
            </tbody>
        </v-table>


    </v-container>
  
</template>

<script>

import detalleOrdenService from '@/services/detalleOrden.service';

export default {

    data() {
        return {
            id_orden: null,
            details: [],
        }
    },

    mounted() {

        this.id_orden = this.$route.query.id;

        if (this.id_orden) {

            // Se obtienen los detalles de la orden
            // detalleOrdenService.getByOrderId(this.id_orden)
            // .then(response => {
            //     console.log("Detalles obtenidos: ",response.data);
            //     this.details = response.data; // Asignamos los datos de la respuesta
            // })
            // .catch(error => {
            //     console.error('Error al obtener los detalles:', error); // Manejamos errores
            // });

            this.details = [
                { id_detalle: 1, id_orden: 101, id_producto: 201, cantidad: 2, precio_unitario: 15.99 },
                { id_detalle: 2, id_orden: 101, id_producto: 202, cantidad: 1, precio_unitario: 45.50 },
                { id_detalle: 3, id_orden: 101, id_producto: 203, cantidad: 4, precio_unitario: 9.75 },
                { id_detalle: 4, id_orden: 101, id_producto: 204, cantidad: 1, precio_unitario: 120.00 },
                { id_detalle: 5, id_orden: 101, id_producto: 205, cantidad: 3, precio_unitario: 8.25 },
                { id_detalle: 6, id_orden: 101, id_producto: 206, cantidad: 2, precio_unitario: 60.00 },
                { id_detalle: 7, id_orden: 101, id_producto: 207, cantidad: 5, precio_unitario: 12.99 },
                { id_detalle: 8, id_orden: 101, id_producto: 208, cantidad: 1, precio_unitario: 35.75 },
                { id_detalle: 9, id_orden: 101, id_producto: 209, cantidad: 2, precio_unitario: 50.00 },
                { id_detalle: 10, id_orden: 101, id_producto: 210, cantidad: 1, precio_unitario: 85.25 },
                { id_detalle: 11, id_orden: 101, id_producto: 211, cantidad: 6, precio_unitario: 10.50 },
                { id_detalle: 12, id_orden: 101, id_producto: 212, cantidad: 2, precio_unitario: 7.75 },
                { id_detalle: 13, id_orden: 101, id_producto: 213, cantidad: 3, precio_unitario: 14.99 },
                { id_detalle: 14, id_orden: 101, id_producto: 214, cantidad: 1, precio_unitario: 99.99 },
                { id_detalle: 15, id_orden: 101, id_producto: 215, cantidad: 2, precio_unitario: 45.00 }
            ];
        }
    }, 
    
    methods: {
        
    }

}
</script>

<style>

</style>