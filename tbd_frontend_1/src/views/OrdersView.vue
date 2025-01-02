<template>
    <v-container class="mt-15">
      <v-card>
        <v-card-title>Gestión de Órdenes</v-card-title>
        <v-card-subtitle>Visualiza, crea y edita órdenes</v-card-subtitle>
        
        <!-- Tabla de órdenes -->
        <v-data-table
          :items="ordenes"
          :headers="headers"
          item-value="id_orden"
          class="elevation-1"
          dense
        >
        <template #item.id_repartidor="{ item }">
          {{ getRepartidorName(item.id_repartidor) }}
        </template>
          <template #top>
            <v-toolbar flat>
              <v-toolbar-title>Órdenes</v-toolbar-title>
              <v-spacer></v-spacer>

            </v-toolbar>
          </template>
          <template #item.acciones="{ item }">
            <v-btn @click="editOrden(item)" icon color="blue">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="deleteOrden(item)" icon color="red">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-card>
  
      <!-- Diálogo para formulario -->
      <v-dialog v-model="dialog" max-width="500px">
        <v-card>
          <v-card-title>
            <span class="text-h6">{{ formTitle }}</span>
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="form.id_orden"
                label="ID Orden"
                type="number"
                readonly
                v-if="form.id_orden"
              ></v-text-field>
              <v-text-field
                v-model="form.id_cliente"
                label="ID Cliente"
                type="number"
                required
              ></v-text-field>
              <v-menu
                v-model="datePicker"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template #activator="{ props }">
                  <v-text-field
                    v-model="form.fecha_orden"
                    label="Fecha de Orden"
                    readonly
                    v-bind="props"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="form.fecha_orden"
                  no-title
                  scrollable
                ></v-date-picker>
              </v-menu>
              <v-select
                v-model="form.estado"
                :items="['Enviada', 'Completada', 'Pagada']"
                label="Estado"
                required
              ></v-select>
              <v-text-field
                v-model="form.total"
                label="Total"
                type="number"
                step="0.01"
                required
                prefix="$"
              ></v-text-field>
              <v-select
                v-model="form.id_repartidor"
                :items="repartidores"
                item-title="nombre"
                item-value="id_repartidor"
                label="Repartidor"
                required
              ></v-select>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="red" @click="closeDialog()">Cancelar</v-btn>
            <v-btn text color="green" @click="saveOrden()">Guardar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-container>
  </template>
  
  <script>
  import ordenService from '@/services/orden.service';
  import deliveryService from '@/services/delivery.service';

  export default {
    data() {
      return {
        ordenes: [],
        repartidores: [],
        headers: [
          { title: "ID Orden", value: "id_orden" },
          { title: "Fecha", value: "fecha_orden" },
          { title: "Estado", value: "estado" },
          { title: "ID Cliente", value: "id_cliente" },
          { title: "Total", value: "total" },
          { title: "Repartidor", value: "id_repartidor" },
          { title: "Acciones", value: "acciones", sortable: false },
        ],
        dialog: false,
        form: {
          id_orden: null,
          fecha_orden: null,
          estado: "",
          id_cliente: null,
          total: null,
          id_repartidor: null,
          id_tienda: null,
        },
        formTitle: "",
        valid: false,
        datePicker: false,
      };
    },
    async created() {
      await this.fetchOrdenes();
      await this.fetchRepartidores();
    },
 
    methods: {
      getRepartidorName(id) {
        const rep = this.repartidores.find(r => r.id_repartidor === id);
        return rep ? rep.nombre : 'Sin asignar';
      },
      async fetchOrdenes() {
        try {
          const response = await ordenService.getAll();
          this.ordenes = response.data;
        } catch (error) {
          console.error("Error al obtener las órdenes:", error);
        }
      },
      async fetchRepartidores(){
        try{
          const response = await deliveryService.getAll();
          this.repartidores = response.data;
        }
        catch(error){
          console.error("Error al obtener repartidores:", error);
        }
      },
      openDialog() {
        this.form = {
          id_orden: null,
          fecha_orden: null,
          estado: "",
          id_cliente: null,
          total: null,
        };
        this.formTitle = "Nueva Orden";
        this.dialog = true;
      },
      editOrden(item) {
        this.form = { ...item };
        this.formTitle = "Editar Orden";
        this.dialog = true;
      },
      async saveOrden() {
        if (!this.$refs.form.validate()) return;
  
        try {
          if (this.form.id_orden) {
            // Actualizar orden existente
            await ordenService.update(this.form.id_orden, this.form);
          } else {
            // Crear nueva orden
            await ordenService.create(this.form);
          }
          this.fetchOrdenes(); // Refrescar la lista
          this.closeDialog();
        } catch (error) {
          console.error("Error al guardar la orden:", error);
        }
      },
      async deleteOrden(item) {
        try {
          await ordenService.remove(item.id_orden);
          this.fetchOrdenes(); // Refrescar la lista
        } catch (error) {
          console.error("Error al eliminar la orden:", error);
        }
      },
      closeDialog() {
        this.dialog = false;
      },
    },
  
  };
  </script>
  