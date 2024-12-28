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
              <v-text-field
                v-model="direccionx"
                label="Dirección"
                placeholder="Ingresa tu dirección"
                prepend-inner-icon="mdi-map-marker"
                outlined
                dense
                :rules="[rules.required]"
              ></v-text-field>
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
import authService from '../services/auth.service';

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
      errorMessage: '',
      rules: {
        required: (value) => !!value || "Requerido.",
        passwordMatch: (value) =>
          value === this.passwordx ||
          "La contraseña no coincide con la anterior."
      },
    };
  },
  methods: {
    handleSubmit() {

      if (this.usernamex !== '' & this.passwordx !== '' & this.direccionx !== '' & this.telefonox !== '' & this.emailx !== '') {

        const username = this.usernamex;
        const password = this.passwordx;
        const rol = "ADMIN";
        const direccion = this.direccionx;
        const telefono = this.telefonox;
        const email = this.emailx;

        const registerDto = {username,email,password,telefono,direccion,rol}

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
  },
};
</script>
  
  <style scoped>
.register-container {
  height: 100vh;
  display: flex;
  align-items: center;
  /* background-image: url("../assets/yotsuba.jpg");
  background-size: cover;
  background-position: center; 
  background-repeat: no-repeat;  */
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
  
    