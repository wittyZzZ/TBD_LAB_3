<template>
    <v-container class="mt-15">
      <h1 class="mt-15">Prueba de Autocompletar Direcciones</h1>
  
      <!-- Campo de búsqueda con autocompletar -->
      <v-autocomplete
        v-model="selectedAddress"
        :items="addressSuggestions"
        label="Buscar Dirección"
        placeholder="Ingresa una dirección"
        item-title="text"
        item-key="id"
        :filter="() => true"
        @update:search="fetchAddressSuggestions"
        outlined
        dense
      ></v-autocomplete>
  
      <!-- Coordenadas seleccionadas -->
      <div v-if="selectedCoordinates">
        <h3>Coordenadas Seleccionadas:</h3>
        <p>Latitud: {{ selectedCoordinates[1] }}</p>
        <p>Longitud: {{ selectedCoordinates[0] }}</p>
      </div>
    </v-container>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {

    data() {
      return {
        addressSuggestions: [], // Lista de sugerencias
        selectedAddress: null, // Dirección seleccionada
        selectedCoordinates: null, // Coordenadas seleccionadas
      };
    },

    watch: {
      // Observa cambios en la dirección seleccionada
      selectedAddress(newAddress) {
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
                access_token: "pk.eyJ1Ijoid2l0dHl6IiwiYSI6ImNtNWZ6NDkzNTAzMXUya3E1dmNqemNjaDkifQ.rQ-tUBE3AoJRQuL3_tSMxQ",
                autocomplete: true,
                limit: 5,
                country: "CL", // Filtra por Chile
              },
            }
          );
  
          // Mapea las sugerencias
          this.addressSuggestions = response.data.features.map((feature, index) => ({
            text: feature.place_name, // Texto que se muestra
            coordinates: feature.geometry.coordinates, // Coordenadas asociadas
            id: index
          }));
        } catch (error) {
          console.error("Error al obtener sugerencias:", error);
        }
      },
    },
  };
  </script>
  
  