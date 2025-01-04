<template>
  <v-container style="margin-top: 100px;">
    <v-card>
      <v-card-title>Perfil del Cliente</v-card-title>
      <v-card-text>
        <!-- Información básica del cliente -->
        <v-list>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="font-weight-bold">Username:</v-list-item-title>
              <v-list-item-subtitle>{{ cliente.username }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="font-weight-bold">Email:</v-list-item-title>
              <v-list-item-subtitle>{{ cliente.email }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="font-weight-bold">Teléfono:</v-list-item-title>
              <v-list-item-subtitle>{{ cliente.telefono }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <!-- Dirección Principal -->
        <v-divider class="my-4"></v-divider>
        <h4>Dirección Principal</h4>
        <p>{{ direccionPrincipal }}</p>

        <!-- Lista de Direcciones -->
        <v-divider class="my-4"></v-divider>
        <h4>Lista de Direcciones</h4>
        <v-select
          v-model="direccionSeleccionada"
          :items="cliente.direccion"
          item-title="direccion"
          return-object
          label="Seleccionar Dirección"
        ></v-select>

        <!-- Acciones para Direcciones -->
        <v-row class="mt-3">
          <v-col>
            <v-btn color="primary" :disabled="!direccionSeleccionada" @click="setMainDireccion(direccionSeleccionada)">
              Establecer Principal
            </v-btn>
          </v-col>
          <v-col>
            <v-btn color="red" :disabled="!direccionSeleccionada" @click="eliminarDireccion(direccionSeleccionada)">
              Borrar Dirección
            </v-btn>
          </v-col>
        </v-row>

        <!-- Botón para Añadir Dirección -->
        <v-btn color="green" class="mt-4" @click="dialog = true">Añadir Nueva Dirección</v-btn>

        <!-- Diálogo para Añadir Dirección -->
        <v-dialog v-model="dialog" max-width="600px">
          <v-card>
            <v-card-title>Añadir Nueva Dirección</v-card-title>
            <v-card-text>
              <!-- Campo de Autocompletar -->
              <v-autocomplete
                v-model="nuevaDireccion"
                :items="addressSuggestions"
                label="Buscar Dirección"
                placeholder="Ejemplo: Calle 123, Ciudad XYZ"
                item-title="text"
                item-key="id"
                :filter="() => true"
                @update:search="fetchAddressSuggestions"
                @change="validateSelectedAddress"
                outlined
                dense
              ></v-autocomplete>
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" :disabled="!isAddressValid" @click="agregarDireccion">
                Guardar
              </v-btn>
              <v-btn text @click="dialog = false">Cancelar</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-card-text>
    </v-card>
  </v-container>
</template>

---

### **Script**

```javascript
<script>
import axios from "axios";
import clienteService from "@/services/cliente.service";

export default {
  data() {
    return {
      cliente: {
        id_cliente: "",
        username: "",
        direccion: [],
        email: "",
        telefono: "",
      },
      direccionPrincipal: "",
      direccionSeleccionada: null,
      dialog: false,
      nuevaDireccion: null,
      addressSuggestions: [],
      isAddressValid: false,
      selectedCoordinates: null,
    };
  },
  async mounted() {
    const id_cliente = this.$route.params.id || JSON.parse(localStorage.getItem("user")).id_cliente;

    try {
      const response = await clienteService.getById(id_cliente);
      this.cliente = response.data;

      // Establecer dirección principal
      this.direccionPrincipal = this.cliente.direccion.find(
        (dir) => dir.coordinates === this.cliente.coordenadas
      )?.direccion || "No definida";
    } catch (error) {
      console.error("Error al cargar el cliente:", error);
    }
  },
  methods: {
    // Llamada a Mapbox para sugerencias de direcciones
    async fetchAddressSuggestions(query) {
      if (!query) {
        this.addressSuggestions = [];
        return;
      }

      try {
        const response = await axios.get(
          `https://api.mapbox.com/geocoding/v5/mapbox.places/${query}.json`,
          {
            params: {
              access_token: "pk.eyJ1Ijoid2l0dHl6IiwiYSI6ImNtNWZ6NDkzNTAzMXUya3E1dmNqemNjaDkifQ.rQ-tUBE3AoJRQuL3_tSMxQ",
              autocomplete: true,
              limit: 5,
              country: "CL",
            },
          }
        );

        this.addressSuggestions = response.data.features.map((feature, index) => ({
          text: feature.place_name,
          coordinates: feature.geometry.coordinates,
          id: index,
        }));
      } catch (error) {
        console.error("Error al obtener sugerencias:", error);
      }
    },

    validateSelectedAddress() {
      const selectedSuggestion = this.addressSuggestions.find(
        (item) => item.text === this.nuevaDireccion
      );

      if (selectedSuggestion) {
        this.isAddressValid = true;
        this.selectedCoordinates = selectedSuggestion.coordinates;
      } else {
        this.isAddressValid = false;
        this.selectedCoordinates = null;
      }
    },

    async agregarDireccion() {
      if (!this.isAddressValid) {
        console.error("Dirección inválida");
        return;
      }

      const nuevaDireccion = {
        direccion: this.nuevaDireccion,
        coordinates: this.selectedCoordinates,
      };

      try {
        const response = await clienteService.addDireccion(this.cliente.id_cliente, nuevaDireccion);
        this.cliente = response.data;

        localStorage.setItem("user", JSON.stringify(this.cliente));
        this.dialog = false;
      } catch (error) {
        console.error("Error al agregar dirección:", error);
      }
    },

    async setMainDireccion() {
      if (!this.direccionSeleccionada) {
        console.error("No se ha seleccionado ninguna dirección.");
        return;
      }

      try {
        // Llamada al backend para establecer la dirección principal
        const id_direccion = this.direccionSeleccionada.id_direccion.toString();
        console.log("Direccion Id: ", id_direccion);
        const response = await clienteService.setMainDireccion(this.cliente.id_cliente, this.direccionSeleccionada);

        // Actualizar los datos del cliente en la vista
        this.cliente = response.data;

        // Actualizar la dirección principal con la dirección seleccionada
        this.direccionPrincipal = this.cliente.direccion.find(
          (dir) => dir.id_direccion === this.direccionSeleccionada
        ).direccion;

        console.log("Dirección principal actualizada correctamente:", this.direccionPrincipal);
      } catch (error) {
        console.error("Error al establecer dirección principal:", error);
      }
    },

    async eliminarDireccion() {
      if (!this.direccionSeleccionada) {
        console.error("No se ha seleccionado ninguna dirección.");
        return;
      }

      console.log("Dirección seleccionada:", this.direccionSeleccionada);
      const id_direccion = this.direccionSeleccionada.id_direccion.toString();
      console.log("Direccion Id: ",id_direccion);

      try {
        await clienteService.deleteDireccion(this.cliente.id_cliente, id_direccion);

        // Actualizar la lista de direcciones en el cliente
        this.cliente.direccion = this.cliente.direccion.filter(
          (dir) => dir.id_direccion !== id_direccion
        );

        // Actualizar el almacenamiento local
        localStorage.setItem("user", JSON.stringify(this.cliente));

        console.log("Dirección eliminada correctamente.");
        this.direccionSeleccionada = null; // Reiniciar la selección
      } catch (error) {
        console.error("Error al eliminar dirección:", error);
      }
    },
  },
};
</script>


  