<template>
  <v-container class="payment-container py-16">
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card class="pa-6">
          <v-card-title class="text-h5 text-center"
            >Detalle de la Compra</v-card-title
          >
          <v-divider class="my-4"></v-divider>

          <!-- Lista de productos -->
          <v-list>
            <v-list-item
              v-for="([producto, cantidad], index) in cart"
              :key="index"
              class="mb-4"
            >
              <v-list-item-content>
                <div>
                  <strong>{{ producto.nombre }}</strong>
                </div>
                <div>Cantidad: {{ cantidad }}</div>
                <div>Precio Unitario: ${{ producto.precio }}</div>
                <div>Subtotal: ${{ producto.precio * cantidad }}</div>
              </v-list-item-content>
            </v-list-item>
          </v-list>

          <v-divider class="my-4"></v-divider>

          <!-- Total -->
          <div class="d-flex justify-end align-center">
            <span class="text-h6 mr-4">Total:</span>
            <span class="text-h5 font-weight-bold">${{ this.orden.total }}</span>
          </div>

          <div class="mt-5">
            <v-select
                  v-model="orden.id_tienda"
                  :items="stores"
                  item-title="nombre"
                  item-value="id_tienda"
                  label="Seleccionar tienda de origen"
                  outlined
                  />
          </div>


          <!-- Botón de pagar -->
          <v-card-actions class="d-flex justify-center mt-4">
            <v-btn color="success" @click="pay" block>
              <v-icon left>mdi-credit-card-check</v-icon>
              Pagar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
  

<script>
import { jwtDecode } from "jwt-decode";
import ordenService from "@/services/orden.service";
import detalleOrdenService from "@/services/detalleOrden.service";
import tiendaService from "@/services/tienda.service";

export default {
  inject: ["calcularSubtotal","vaciarCarrito"],

  name: "PaymentView",
  data() {
    return {
      cart: [], // Carrito de compras cargado desde localStorage
      orden: {
        id_cliente: null,
        total: null,
        id_tienda: 1,
        },
      stores: [],
    };
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

    const savedCart = JSON.parse(localStorage.getItem("carrito"));
    if (savedCart) {
      this.cart = savedCart;
      this.orden.total = this.calcularSubtotal();
    } else {
      this.$router.push({ name: "Home" });
    }
    const token = localStorage.getItem("token");
    if (token) {
      const decodedToken = jwtDecode(token);
      this.orden.id_cliente = decodedToken.id_cliente; // Almacena el id del cliente logueado
    }
  },

  methods: {
    async pay() {

      console.log("ID TIENDA DE ORDEN",this.orden.id_tienda);
      console.log("TIENDAS",this.stores);

      if (this.cart.length === 0) {
        alert("El carrito está vacío. Agrega productos antes de pagar.");
        return;
      } 

      // Servicio de orden.service para enviar carrito

      try {
          console.log("Recibe para crear:", this.orden);

          const response = await ordenService.create(this.orden);
          console.log("Orden creada:", response.data);
          const ordenCreada = response.data;


          // Preparar el carrito para enviar al backend
          const carrito = this.cart.map(([producto, cantidad]) => ({
            id_producto: producto.id_producto,
            cantidad: cantidad,
            precio_unitario: producto.precio,
          }));

          const payload = {
            id_orden: ordenCreada.id_orden,
            carrito: carrito,
          };
          
          //Se crean detalles
          const responseDetalles = await detalleOrdenService.create(payload);
          console.log("Detalles de la orden creados:", responseDetalles.data);

          alert(`¡Pago exitoso! El monto total es $${this.orden.total}`);
          this.vaciarCarrito();
          // Redirigir a la página principal
          this.$router.push({ name: "Home" });
        } catch (error) {
          console.error("No se pudo realizar la transacción:", error);
        }
    },
  },
};
</script>
  