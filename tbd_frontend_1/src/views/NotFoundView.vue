<template>
    <v-container class="text-center">
      <h1 class="display-1">404</h1>
      <p class="text-h5">Página no encontrada</p>
      <v-btn color="primary" to="/">Volver al inicio</v-btn>
    </v-container>
  </template>
  
  <script>
  export default {
    name: "NotFoundView",
  };
  </script>
  
  <style scoped>
  .text-center {
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  </style>