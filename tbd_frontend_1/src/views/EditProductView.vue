<template>
  <v-container class="mt-12">
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="producto.nombre"
            label="Nombre"
            required
          ></v-text-field>
          <v-textarea
            v-model="producto.descripcion"
            label="Descripción"
            required
          ></v-textarea>
          <v-text-field
            v-model="producto.precio"
            label="Precio"
            type="number"
            required
          ></v-text-field>
          <v-text-field
            v-model="producto.stock"
            label="Stock"
            type="number"
            required
          ></v-text-field>
          <v-select
            v-model="producto.estado"
            :items="['Disponible', 'Agotado']"
            label="Estado"
            required
          ></v-select>
          <v-btn color="primary" @click="updateProduct">
            Guardar Cambios
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import productoService from "@/services/producto.service";

  export default {
    name: "EditProductView",
    data() {
      return {
        producto: {
          id_producto: null,
          nombre: "",
          descripcion: "",
          precio: null,
          stock: null,
          estado: "",
        },
        valid: false,
      };
    },

    async created() {
      const id = this.$route.query.id;

      if (id) {
        try {
          const response = await productoService.getById(id);
          console.log("Producto obtenido:", response.data);
          this.producto = response.data;
        } catch (error) {
          console.error("Error al obtener el producto:", error);
        }
      } else {
        console.error("ID del producto no proporcionado.");
      }
    },

    methods: {
      async updateProduct() {
        try {
          const response = await productoService.update(this.producto.id_producto, this.producto);
          console.log("Producto actualizado:", response.data);

          this.producto = response.data;

          this.$router.push({ path: `/detailsproduct/${this.producto.id_producto}` });
        } catch (error) {
          console.error("Error al actualizar el producto:", error);
        }
      },
    },
  };
</script>

<style scoped>
.mt-12 {
  margin-top: 12px;
}
</style>