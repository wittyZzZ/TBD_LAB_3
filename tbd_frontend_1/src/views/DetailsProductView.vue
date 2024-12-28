<template>
  <v-container class="mt-12">
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card>
          <v-card-title>{{ producto.nombre }}</v-card-title>
          <v-card-subtitle>Precio: ${{ producto.precio }}</v-card-subtitle>
          <v-card-text>
            <p><strong>Descripción:</strong> {{ producto.descripcion }}</p>
            <p v-if="producto.estado === 'Disponible'">
              <strong>Stock:</strong> {{ producto.stock }}
            </p>
            <p><strong>Estado:</strong> {{ producto.estado }}</p>
          </v-card-text>
          <v-card-actions>
            <!-- Botón para ir a la vista de edición -->
            <v-btn color="primary" @click="goToEditProduct(producto.id_producto)">
              Editar
            </v-btn>
            <v-btn color="error" @click="deleteProduct">
              Borrar
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
  

<script>
  import productoService from "@/services/producto.service";
  export default {
  name: "DetailsProduct",
  props: ['id'],
  data() {
    return {
      producto: []
    };
  },

  mounted() {
    console.log("ESTA ES LA ID",this.id);
    productoService.getById(this.id)
      .then(response => {
        this.producto = response.data;
      })
      .catch(error => {
        console.error('Error al obtener el producto:', error); // Manejamos errores
      })
  },

  
  methods: {
    goToEditProduct(id) {
      this.$router.push({ path: "/editproduct", query: { id } });
    },
    
    async deleteProduct() {
      if (confirm("¿Estás seguro de que deseas eliminar este producto?")) {
        try {
          await productoService.remove(this.producto.id_producto);
          alert("Producto eliminado correctamente.");
          this.$router.push("/products"); // Redirige a la lista de productos
        } catch (error) {
          console.error("Error al eliminar el producto:", error);
          alert("No se pudo eliminar el producto.");
        }
      }
    },
    // async created() {
    //   const id = this.$route.query.id;
  
    //   if (id) {
    //     try {
    //       const response = await productoService.getById(id);
    //       this.producto = response.data;
    //     } catch (error) {
    //       console.error("Error al obtener el producto:", error);
    //       this.producto = null;
    //     }
    //   } else {
    //     console.error("ID del producto no proporcionado.");
    //     this.producto = null;
    //   }
    // },
  
    /* created() {
      // Obtener el ID del producto desde el query
      const id = this.$route.query.id;
  
      // Simular una solicitud para obtener los datos del producto
      // En un proyecto real, realizarías una llamada a la API aquí
      this.fetchProduct(id);
    }, */

    /* fetchProduct(id) {
      // Simulación de datos del producto
      const productos = [
        {
          id_producto: 1,
          nombre: "Producto A",
          descripcion: "Este es el producto A",
          precio: 100.0,
          stock: 20,
          estado: "Disponible",
        },
        {
          id_producto: 2,
          nombre: "Producto B",
          descripcion: "Este es el producto B",
          precio: 150.0,
          stock: 10,
          estado: "Agotado",
        },
      ];

      // Buscar el producto por ID
      const productoEncontrado = productos.find(
        (producto) => producto.id_producto === parseInt(id)
      );

      if (productoEncontrado) {
        this.producto = productoEncontrado;
      } else {
        this.producto = { nombre: "Producto no encontrado" };
      }
    }, */
  },
};
</script>

<style scoped>
.mt-12 {
  margin-top: 12px;
}
</style>