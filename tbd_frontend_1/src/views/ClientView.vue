<template>
  <v-container style="margin-top: 100px;">
    <v-card>
      <v-card-title>Editar Cliente</v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="formValid">
          <!-- Información básica del cliente -->
          <v-text-field v-model="cliente.username" label="Username"></v-text-field>
          <v-text-field v-model="cliente.email" label="Email"></v-text-field>
          <v-text-field v-model="cliente.telefono" label="Teléfono"></v-text-field>

          <!-- Lista de direcciones -->
          <v-divider class="my-4"></v-divider>
          <h4>Direcciones</h4>
          <v-container>
            <v-row v-for="(direccion, index) in cliente.direccion" :key="direccion.id_direccion">
              <v-col cols="12" md="9">
                <v-text-field
                  v-model="direccion.direccion"
                  label="Dirección Completa"
                  placeholder="Ejemplo: Calle 123, Ciudad XYZ"
                ></v-text-field>
              </v-col>
              <v-col cols="12" md="3">
                <v-btn color="red" icon @click="eliminarDireccion(index)">
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </v-col>
            </v-row>
            <v-btn color="green" @click="agregarDireccion">Añadir Dirección</v-btn>
          </v-container>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="guardarCambios">Guardar Cambios</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>
  
  <script>
  import clienteService from '@/services/cliente.service';
  export default {
    name: "ClientView",
    
    data() {
    return {
      formValid: false,
      cliente: {
        id_cliente: '',
        username: '',
        email: '',
        telefono: '',
        direccion: [],
      },
    };
  },
  methods: {
    handleError(message, error) {
      console.error(message, error);
    },
    agregarDireccion() {
      const nuevaDireccion = {
        id_direccion: new Date().getTime().toString(), // ID temporal para manejo local
        calle: '',
        ciudad: '',
      };
      this.cliente.direccion.push(nuevaDireccion);
    },
    async eliminarDireccion(index) {
      const direccionId = this.cliente.direccion[index].id_direccion;
      try {
        if (direccionId && direccionId !== '0') { // Solo elimina si la dirección existe en el backend
          await clienteService.deleteDireccion(this.cliente.id_cliente, direccionId);
        }
        this.cliente.direccion.splice(index, 1);
      } catch (error) {
        console.error("Error eliminando la dirección:", error);
      }
    },
    async guardarCambios() {
      if (this.$refs.form.validate()) {
        try {
          const clienteSinDirecciones = { ...this.cliente, direccion: undefined };
          await clienteService.update(this.cliente.id_cliente, clienteSinDirecciones);

          for (const direccion of this.cliente.direccion) {
            if (!direccion.id_direccion || direccion.id_direccion === '0') {
              // Si no tiene ID, agregar nueva dirección
              await clienteService.addDireccion(this.cliente.id_cliente, direccion);
            }
          }

          console.log("Cliente actualizado correctamente");
        } catch (error) {
          console.error("Error al guardar los cambios del cliente:", error);
        }
      }
    },
  },
  async mounted() {
    const id_cliente = this.$route.params.id; // Suponiendo que el ID está en la ruta
    if (!id_cliente) {
      this.handleError("ID de cliente no válido.");
      return;
    }

    try {
      const response = await clienteService.getById(id_cliente);
      this.cliente = response.data;
    } catch (error) {
      this.handleError("Error al cargar el cliente:", error);
    }
  },
};
</script>


<style scoped>
.my-4 {
  margin-top: 16px;
  margin-bottom: 16px;
}
</style>

  