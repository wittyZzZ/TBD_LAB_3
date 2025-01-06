<template>
    <v-navigation-drawer :value="drawer" @input="$emit('update:drawer', $event)" app temporary>
      <v-list>
        <v-item-group>
          <v-list-item v-for="item in menuItems" :key="item.text" :to="item.route">
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-title>
              {{ item.text }}
            </v-list-item-title>
          </v-list-item>
        </v-item-group>
      </v-list>
    </v-navigation-drawer>
</template>
  
  <script>
  export default {
    props: ['drawer'],
    data() {
      return {
        userId: null, 
        menuItems: [
          { text: "Inicio", icon: "mdi-home", route: "/" },
          { text: "Productos", icon: "mdi-package-variant", route: "/products" },
          //{ text: "Categorias", icon: "mdi-checkbox-multiple-blank", route: ""},
          { text: "Ordenes", icon: "mdi-order-bool-descending-variant", route: "/orders"},
          { text: "Clientes Registrados", icon: "mdi-account", route: "/clientlist"},
          { text: "Ubicación de Clientes", icon: "mdi-map-marker-multiple", route: "/clientslocation"},
          { text: "Perfil", icon: "mdi-account", route: "" },
          { text: "Ordenes en radio de tienda", icon: "mdi-account", route: "/orderstore"},
          { text: "Repartidores en zona de reparto", icon: "mdi-account", route: "/deliveryzone"},
          { text: "Repartidores en 30 KM", icon: "mdi-map-marker-multiple", route: "delivery30"},
          { text: "Clientes en radio de tienda", icon: "mdi-map-marker-multiple", route: "/clientsradius"},
          // Agrega más opciones de menú según tus necesidades
        ],
      };
    },
    mounted() {
      // Obtener el usuario desde el localStorage
      const user = JSON.parse(localStorage.getItem("user"));
      if (user) {
        this.userId = user.id_cliente;
        // Actualizar dinámicamente la ruta del perfil
        this.menuItems.find((item) => item.text === "Perfil").route = `/client/${this.userId}`;
      } else {
        console.error("No se encontró el usuario en el localStorage");
      }
  },
};
</script>
  
<!--   <style scoped>
  /* Opcional: personaliza el estilo del menú lateral aquí */
  </style> -->
  