<template>
    <v-app-bar app>
      <v-app-bar-nav-icon @click.stop="toggleDrawer" />
      <v-toolbar-title>E-Commerce</v-toolbar-title>
  
      <v-spacer></v-spacer>
  
      <v-btn text to="/" :exact="true">Inicio</v-btn>
      <v-btn text to="/products">Productos</v-btn>
      
      <!-- <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn text v-bind="attrs" v-on="on">
          Categorías
          <v-icon right>mdi-menu-down</v-icon> 
        </v-btn>
      </template>

      <v-list>
        <v-list-item
          v-for="(categoria, index) in categorias"
          :key="index"
          @click="filtrarPorCategoria(categoria)"
        >
          <v-list-item-title>{{ categoria.nombre }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
 -->
    <!-- <v-btn icon :to="{ name: 'Cart' }">
        <v-badge :content="cartCount" color="red" overlap>
            <v-icon>mdi-cart</v-icon>
        </v-badge>
    </v-btn> -->
    </v-app-bar>
    <Sidemenu v-model="drawer" />
</template>

<script>
import Sidemenu from "./Sidemenu.vue";

export default {
  name: "Navbar",
  components: { 
    Sidemenu 
    },
    data() {
    return {
      drawer: false,
    };
    },

  methods: {
    toggleDrawer() {
      this.drawer = !this.drawer;
    },
  },
};
</script>