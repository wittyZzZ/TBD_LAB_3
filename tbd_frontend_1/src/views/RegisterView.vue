<template>
  <v-container class="register-container" fluid>
    <v-row justify="center">
      <v-col cols="12" md="4">
        <v-card class="pa-6">
          <v-card-title class="text-h5">Registrarse</v-card-title>
          <v-card-subtitle class="v-card-sub">
            <span>¿Tienes una cuenta?</span>
          </v-card-subtitle>
          <v-btn color="primary" class="ml-1" :to="{ name: 'Login' }"
            >Iniciar Sesión</v-btn
          >
          <v-card-text>
            <!-- Mostrar mensaje de error solo si errorMessage tiene un valor -->
            <v-alert v-if="errorMessage" type="error" class="mb-4" dismissible>
              {{ errorMessage }}
            </v-alert>

            <v-form @submit.prevent="handleSubmit">
              <v-text-field
                v-model="emailx"
                label="Correo"
                placeholder="tu@ejemplo.com"
                prepend-inner-icon="mdi-email"
                outlined
                dense
                :rules="[rules.required]"
              ></v-text-field>
              <v-text-field
                v-model="usernamex"
                label="Nombre"
                placeholder="Ingresa tu nombre"
                prepend-inner-icon="mdi-account"
                outlined
                dense
                :rules="[rules.required]"
              ></v-text-field>

              <v-autocomplete
                v-model="direccionx"
                :items="addressSuggestions"
                label="Buscar Dirección"
                placeholder="Ingresa una dirección"
                item-title="text"
                item-key="id"
                :filter="() => true"
                @update:search="fetchAddressSuggestions"
                outlined
                dense
              >
                <template #no-data>
                  <div class="ml-4">No hay resultados disponibles.</div>
                </template>
              </v-autocomplete>

              <v-text-field
                v-model="telefonox"
                label="Teléfono"
                placeholder="Ingresa tu número telefónico"
                prepend-inner-icon="mdi-phone"
                outlined
                dense
                :rules="[rules.required]"
              ></v-text-field>
              <v-text-field
                v-model="passwordx"
                :rules="[rules.required]"
                label="Contraseña"
                placeholder="Ingresa tu contraseña"
                prepend-inner-icon="mdi-lock"
                outlined
                dense
                :type="show1 ? 'text' : 'password'"
                :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append-inner="show1 = !show1"
              ></v-text-field>
              <v-text-field
                v-model="confirmPassword"
                :rules="[rules.required, rules.passwordMatch]"
                label="Confirmar contraseña"
                placeholder="Confirma tu contraseña"
                prepend-inner-icon="mdi-lock-check"
                outlined
                dense
                :type="show2 ? 'text' : 'password'"
                :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                @click:append-inner="show2 = !show2"
              ></v-text-field>
              <v-btn class="mt-1" type="submit" color="primary"
                >Registrarse</v-btn
              >
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
  
<script>
import authService from "../services/auth.service";
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      emailx: "",
      passwordx: "",
      confirmPassword: "",
      usernamex: "",
      direccionx: "",
      telefonox: "",
      show1: false,
      show2: false,
      errorMessage: "",
      rules: {
        required: (value) => !!value || "Requerido.",
        passwordMatch: (value) =>
          value === this.passwordx ||
          "La contraseña no coincide con la anterior.",
      },
      addressSuggestions: [], // Lista de sugerencias
      selectedAddress: null, // Dirección seleccionada
      selectedCoordinates: null, // Coordenadas seleccionadas
    };
  },
  watch: {
    // Observa cambios en la dirección seleccionada
    direccionx(newAddress) {
      if (newAddress) {
        const selectedSuggestion = this.addressSuggestions.find(
          (item) => item.text === newAddress
        );
        if (selectedSuggestion) {
          this.selectedCoordinates = selectedSuggestion.coordinates;
        } else {
          this.selectedCoordinates = null; // Reinicia si no hay coincidencia
        }
      }
    },
  },

  methods: {
    handleSubmit() {
      if (
        (this.usernamex !== "") &
        (this.passwordx !== "") &
        (this.direccionx !== "") &
        (this.telefonox !== "") &
        (this.emailx !== "")
      ) {
        
        const registerDto = {
          username: this.usernamex,
          email: this.emailx,
          password: this.passwordx,
          telefono: this.telefonox,
          direccion: this.direccionx,
          coordinates: this.selectedCoordinates,
          rol: "ADMIN",
        };

        // Se intenta register
        authService
          .register(registerDto)
          .then((response) => {
            console.log("Se registró", response.data);
            this.errorMessage = ""; // Limpia el mensaje de error si el login es exitoso
            this.$router.push({ name: 'Login' }); // Redirigir a Home después de un register exitoso
          })
          .catch(() => {
            // Mostrar mensaje de error en pantalla
            this.errorMessage = "Este usuario ya existe";
          });
        
      } else {
        this.errorMessage = "Complete el formulario";
      }
    },
    // Llamada a la API de Mapbox para obtener sugerencias
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
              access_token:
                "pk.eyJ1Ijoid2l0dHl6IiwiYSI6ImNtNWZ6NDkzNTAzMXUya3E1dmNqemNjaDkifQ.rQ-tUBE3AoJRQuL3_tSMxQ",
              autocomplete: true,
              limit: 5,
              country: "CL", // Filtra por Chile
            },
          }
        );

        // Mapea las sugerencias
        this.addressSuggestions = response.data.features.map(
          (feature, index) => ({
            text: feature.place_name, // Texto que se muestra
            coordinates: feature.geometry.coordinates, // Coordenadas asociadas
            id: index,
          })
        );
      } catch (error) {
        console.error("Error al obtener sugerencias:", error);
      }
    },
  },
};
</script>
  
  <style scoped>
.register-container {
  height: 100vh;
  display: flex;
  align-items: center;
}

.v-card {
  border-radius: 12px;
}

.v-card-title {
  font-weight: bold;
}

.v-card-sub {
  display: inline;
}
</style>
  
    