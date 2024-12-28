<script>
import Navbar from "./components/Navbar.vue";
import Sidemenu from "./components/Sidemenu.vue";
import productoService from "@/services/producto.service";

export default {
  name: "App",
  components: {
    Sidemenu,
  },

  data() {
    return {
      menuDrawer: false,
      cartDrawer: false,
      mostrarNavbar: true,
      mostrarCarrito: true,
      carrito: [], // Arreglo de arreglos [[producto, cantidad], ...]
    };
  },

  watch: {
    $route(to) {
      this.mostrarNavbar = !["Login", "Register","NotFound"].includes(to.name);
      // Ocultar el botón del carrito en PaymentView
      this.mostrarCarrito = to.name !== "PaymentView"; 
      // Cerrar el drawer del carrito al cambiar de vista
      this.cartDrawer = false;
    },
  },

  mounted() {
    this.mostrarNavbar = !["Login", "Register","NotFound"].includes(this.$route.name);
    // Cargar el carrito desde localStorage al iniciar
    const savedCarrito = JSON.parse(localStorage.getItem("carrito"));
    // Configurar la visibilidad inicial del botón del carrito
    this.mostrarCarrito = this.$route.name !== "PaymentView"; 
    if (savedCarrito) {
      this.carrito = savedCarrito;
    }
  },

  methods: {
    toggleMenuDrawer() {
      this.menuDrawer = !this.menuDrawer;
    },

    toggleCartDrawer() {
      //Actualiza el carro al abrirlo
      this.actualizarCarrito();
      this.cartDrawer = !this.cartDrawer;
    },

    logout() {
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
      // Vacía el carrito y elimina el valor de localStorage
      this.carrito = [];
      localStorage.removeItem("carrito");
    },

    agregarAlCarrito(producto) {
      const index = this.carrito.findIndex(
        ([p]) => p.id_producto === producto.id_producto
      );
      if (index !== -1) {
        // Producto ya existe en el carrito
        const [, cantidad] = this.carrito[index];
        if (cantidad < producto.stock) {
          this.carrito[index][1]++; // Incrementar cantidad
        } else {
          alert(
            "No se puede agregar más, se ha alcanzado el stock disponible."
          );
        }
      } else {
        // Producto no existe en el carrito
        this.carrito.push([producto, 1]);
      }
      // Guardar el carrito actualizado en localStorage
      localStorage.setItem("carrito", JSON.stringify(this.carrito));
      // Abrir el drawer
      this.cartDrawer = true;
    },

    eliminarDelCarrito(productoID) {
      const index = this.carrito.findIndex(
        ([p]) => p.id_producto === productoID
      );

      if (index !== -1) {
        const [, cantidad] = this.carrito[index];
        if (cantidad > 1) {
          this.carrito[index][1]--; // Reducir cantidad
        } else {
          this.carrito.splice(index, 1); // Eliminar producto del carrito
        }
      }
      // Guardar el carrito actualizado en localStorage
      localStorage.setItem("carrito", JSON.stringify(this.carrito));
    },

    async actualizarCarrito() {
      try {
        // Obtener productos actualizados 
        const promises = this.carrito.map(async item => {
          const response = await productoService.getById(item[0].id_producto);
          return [response.data, item[1]];
        });
        // Asignar el resultado al carrito actualizado
        this.carrito = await Promise.all(promises);
      } catch (error) {
        console.error("Error al actualizar el carrito:", error);
      }
    },

    vaciarCarrito() {
      // Vacía el carrito y elimina el valor de localStorage
      this.carrito = [];
      localStorage.removeItem("carrito");
    },

    calcularSubtotal() {
      return this.carrito.reduce(
        (total, [producto, cantidad]) => total + producto.precio * cantidad,
        0
      );
    },

  },

  provide() {
    return {
      agregarAlCarrito: this.agregarAlCarrito,
      eliminarDelCarrito: this.eliminarDelCarrito,
      logout: this.logout,
      calcularSubtotal: this.calcularSubtotal,
      vaciarCarrito: this.vaciarCarrito,
    };
  },

};
</script>

<template>
  <v-app>
    <v-app-bar color="blue-grey-lighten-2" v-if="mostrarNavbar" app>
      <!-- Ícono del menú lateral -->
      <v-app-bar-nav-icon @click="toggleMenuDrawer" />

      <!-- Título de la tienda -->
      <v-toolbar-title>Mi Tienda</v-toolbar-title>

      <!-- Menú de navegación principal -->
   
      <v-btn text to="/">Inicio</v-btn>
      <v-btn text to="/products">Productos</v-btn>

      <!-- Carrito -->
      <v-btn v-if="mostrarCarrito" @click="toggleCartDrawer">
        Carrito
        <v-icon right>mdi-cart</v-icon>
      </v-btn>

      <!-- Cerrar sesión -->
      <v-btn text @click="logout">
        <v-icon left>mdi-logout</v-icon>
        Cerrar Sesión
      </v-btn>
    </v-app-bar>

    <Sidemenu v-model="menuDrawer" />

    <!-- ############################################  -->
    <!--                   CARRITO                     -->
    <!-- ############################################  -->

    <v-navigation-drawer
      v-model="cartDrawer"
      app
      location="right"
      width="350"
      color="blue-grey-lighten-2"
    >
      <v-list>
        <!-- Título del carrito -->
        <v-list-item>
          <v-list-item-title class="text-h5 font-weight-bold"
            >Carrito</v-list-item-title
          >
        </v-list-item>
        <v-divider></v-divider>

        <!-- Productos en el carrito -->
        <v-list-item
          v-for="([producto, cantidad], index) in carrito"
          :key="index"
          class="py-4"
        >
          <v-img
            src="./assets/product.png"
            max-height="80"
            max-width="80"
            class="mr-4 rounded"
          ></v-img>

          <v-list-item-content>
            <v-list-item-title class="text-h6 font-weight-bold">
              {{ producto.nombre }}
            </v-list-item-title>
            <v-list-item-subtitle class="text-caption grey--text">
              ${{ producto.precio }} x {{ cantidad }}
            </v-list-item-subtitle>
          </v-list-item-content>

          <v-btn
            color="amber-darken-4"
            @click="eliminarDelCarrito(producto.id_producto)"
          >
            Eliminar
          </v-btn>
        </v-list-item>
      </v-list>

      <!-- Subtotal y botón "Ir a pagar" -->
      <v-divider v-if="carrito.length"></v-divider>
      <v-container v-if="carrito.length" class="pt-4">
        <v-row justify="space-between">
          <v-col>
            <span class="text-h6 font-weight-bold">Subtotal:</span>
          </v-col>
          <v-col class="text-right">
            <span class="text-h6 font-weight-bold"
              >${{ calcularSubtotal() }}</span
            >
          </v-col>
        </v-row>
        <v-row>
          <v-btn color="success" block class="mt-4" :to="{name: 'PaymentView'}">
            Ir a pagar
          </v-btn>
        </v-row>
      </v-container>
    </v-navigation-drawer>

    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>
