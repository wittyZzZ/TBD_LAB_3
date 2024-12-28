<template>
  <div class="mx-6 mt-15">
    <h1 class="mb-5 ml-5 pt-6">Productos</h1>
    <v-container fluid>
      <v-row>
        <v-col v-for="producto in productos" :key="producto.id_producto" cols="12" sm="6" md="4" lg="3">
          
          <v-card color="blue-grey-lighten-2" class="product-card pt-10">

            <v-img src="../assets/product.png" class="product-image mb-3"></v-img>

            <v-card-title class="text-h5 text-center">
              {{ producto.nombre }}
            </v-card-title>

            <v-card-subtitle class="text-h5 text-center">
              ${{ producto.precio }}
            </v-card-subtitle>

            <v-card-subtitle class="text-center mb-3">
              <p v-if="producto.estado === 'Disponible'">
              <strong>Stock:</strong> {{ producto.stock }}
              </p>
              <p v-else>
                {{ producto.estado }}
                </p>
            </v-card-subtitle>

            <v-card-subtitle class="text-center mb-3">
              Categoría:{{ categorias[producto.id_categoria] || "N/A" }}
            </v-card-subtitle>

            <v-btn class="mb-2" color="primary" outlined :to="{name: 'DetailsProduct', params: {id: producto.id_producto}}">
              Detalles
            </v-btn>

            <v-btn class="mb-2" color="primary" v-if="producto.estado === 'Disponible'" @click="agregarAlCarrito(producto)">
              Agregar al carrito
            </v-btn>
            
          </v-card>

        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { inject } from "vue";
import productoService from "@/services/producto.service";
import categoriaService from "@/services/categoria.service";

export default {
  inject: ["agregarAlCarrito"],
  
  data() {
    return {
      productos: [],
      categorias: {},
    };
  },
  
  async mounted() {
    await productoService
      .getAll()
      .then((response) => {
        this.productos = response.data;
      })
      .catch((error) => {
        console.error("Error al obtener los productos:", error);
      });

      await categoriaService.getAll().then((response) => {
        const categoriasArray = response.data
        this.categorias = {};
        for (const categoria of categoriasArray) {
          this.categorias[categoria.id_categoria] = categoria.nombre;
        }
      });

  },

  methods: {
    goToDetailsProduct(id) {
      this.$router.push({ path: `/detailsproduct`, query: { id } });
    },
  },
};
</script>

<style scoped>
.product-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  transition: transform 0.3s ease;
}
.product-card:hover {
  transform: scale(1.03);
}
.product-image {
  border-radius: 8px;
  width: 100%;
  max-width: 150px;
  height: auto;
}
h1 {
  font-weight: bold;
  font-size: 2.5rem;
  color: #333;
  text-transform: uppercase;
}
.text-center {
  text-align: center;
}

</style>