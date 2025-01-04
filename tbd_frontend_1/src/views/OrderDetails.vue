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
                    Nombre Producto
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
                <td>{{ getProductName(detail.id_producto) }}</td>
                <td>$ {{ detail.precio_unitario}}</td>
                <td>{{ detail.cantidad }} unidades</td>
            </tr>
            </tbody>
        </v-table>


    </v-container>
  
</template>

<script>

import detalleOrdenService from '@/services/detalleOrden.service';
import productoService from '@/services/producto.service';

export default {

    data() {
        return {
            id_orden: null,
            details: [],
            products: [],
        }
    },

    mounted() {

        this.id_orden = this.$route.query.id;

        if (this.id_orden) {

            // Se obtienen los detalles de la orden
            detalleOrdenService.getByOrderId(this.id_orden)
            .then(response => {
                console.log("Detalles obtenidos: ",response.data);
                this.details = response.data; // Asignamos los datos de la respuesta
            })
            .catch(error => {
                console.error('Error al obtener los detalles:', error); // Manejamos errores
            });

            // Se obtienen los productos
            productoService.getAll()
            .then(response => {
                console.log("Productos obtenidos: ",response.data);
                this.products = response.data; // Asignamos los datos de la respuesta
            })
            .catch(error => {
                console.error('Error al obtener los productos:', error); // Manejamos errores
            });

        }
    }, 
    
    methods: {
        getProductName(productId) {
            const product = this.products.find(prod => prod.id_producto === productId);
            return product ? product.nombre : 'Producto no encontrado';
        }   
    }

}
</script>

<style>

</style>