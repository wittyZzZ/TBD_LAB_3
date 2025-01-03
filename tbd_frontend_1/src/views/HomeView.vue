<script>
import { jwtDecode } from "jwt-decode";
import clienteService from "../services/cliente.service";
import Map from "../components/Map.vue";

export default {
  inject: ["logout"],
  components: {
    Map,
  },
  name: "Home",
  data() {
    return {
      users: [],
    };
  },
  methods: {},
  mounted() {

    // const user = JSON.parse(localStorage.getItem("user"));
    // console.log("HOME USER: ",user);
    
  },
};
</script>

<template>
  <v-container class="home-container" fluid>
    <v-row justify="center">
      <v-card class="text-center pa-6" elevation="8" max-width="400">
        <v-card-title class="text-h5">Bienvenido</v-card-title>
        <p>Ve a la pestaña "Productos" para empezar tu compra</p>
      </v-card>
    </v-row>
  </v-container>
</template>

<style scoped>
.home-container {
  height: 100vh;
  display: flex;
  align-items: center;
  background-image: url("../assets/fondoapp_2.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.map-container {
  width: 80%;
  min-width: 50%;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>


