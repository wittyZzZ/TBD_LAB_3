// Se usa .js para que VS muestre la sintaxis
// Seleccionar la base de datos
use("tbd_lab_3");

// Crear la colección "CATEGORIA" con validación
db.createCollection("categoria", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["nombre"],
        properties: {
          nombre: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          }
        }
      }
    }
  });
  
  // Crear la colección "PRODUCTO" con validación
  db.createCollection("producto", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["nombre", "precio", "stock", "id_categoria"],
        properties: {
          nombre: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          descripcion: {
            bsonType: "string",
            description: "Debe ser una cadena (opcional)"
          },
          precio: {
            bsonType: "int",
            description: "Debe ser un número entero y es obligatorio"
          },
          stock: {
            bsonType: "int",
            description: "Debe ser un entero y es obligatorio"
          },
          estado: {
            bsonType: "string",
            description: "Debe ser una cadena (opcional)"
          },
          id_categoria: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a CATEGORIA y es obligatorio"
          }
        }
      }
    }
  });
  
  //Creacion de cliente con validación
  db.createCollection("cliente", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: ["username", "direccion", "email", "contrasena", "telefono", "rol", "coordenadas"],
            properties: {
                username: { bsonType: "string", description: "Debe ser un string y es requerido" },
                direccion: {
                    bsonType: "array",
                    description: "Debe ser un array de objetos",
                    items: {
                        bsonType: "object",
                        required: ["direccion", "coordinates"],
                        properties: {
                            id_direccion: { bsonType: "objectId", description: "Debe ser un ObjectId" },
                            direccion: { bsonType: "string", description: "Debe ser un string y es requerido" },
                            coordinates: {
                                bsonType: "array",
                                minItems: 2,
                                maxItems: 2,
                                items: { bsonType: "double" },
                                description: "Debe ser un array con exactamente dos elementos (latitud y longitud)"
                            }
                        }
                    }
                },
                email: { bsonType: "string", pattern: "@.*\\.", description: "Debe ser un email válido" },
                contrasena: { bsonType: "string", description: "Debe ser un string y es requerido" },
                telefono: { bsonType: "string", description: "Debe ser un string y es requerido" },
                rol: { enum: ["USER", "ADMIN"], description: "Debe ser USER o ADMIN" },
                coordenadas: {
                    bsonType: "object",
                    required: ["type", "coordinates"],
                    properties: {
                        type: { enum: ["Point"], description: "Debe ser 'Point'" },
                        coordinates: {
                            bsonType: "array",
                            minItems: 2,
                            maxItems: 2,
                            items: { bsonType: "double" },
                            description: "Debe ser un array con exactamente dos elementos (latitud y longitud)"
                        }
                    }
                }
            }
        }
    }
});
  
  // Crear la colección "TIENDA" con validación
  db.createCollection("tienda", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["nombre", "direccion"],
        properties: {
          nombre: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          direccion: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          coordenadas: {
            bsonType: "object",
            description: "Debe ser un objeto de tipo Point (opcional)"
          }
        }
      }
    }
  });
  
  // Crear la colección "REPARTIDOR" con validación
  db.createCollection("repartidor", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["nombre"],
        properties: {
          nombre: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          coordenadas: {
            bsonType: "object",
            description: "Debe ser un objeto de tipo Point (opcional)"
          }
        }
      }
    }
  });
  
  // Crear la colección "ORDEN" con validación
  db.createCollection("orden", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["fecha_orden", "estado", "id_cliente", "total"],
        properties: {
          fecha_orden: {
            bsonType: "date",
            description: "Debe ser una cadena y es obligatorio"
          },
          estado: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          id_cliente: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a CLIENTE y es obligatorio"
          },
          total: {
            bsonType: "int",
            description: "Debe ser un número entero y es obligatorio"
          },
          id_repartidor: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a REPARTIDOR (opcional)"
          },
          id_tienda: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a TIENDA (opcional)"
          }
        }
      }
    }
  });
  
  // Crear la colección "DETALLE_ORDEN" con validación
  db.createCollection("detalle_orden", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["id_orden", "id_producto", "cantidad", "precio_unitario"],
        properties: {
          id_orden: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a ORDEN y es obligatorio"
          },
          id_producto: {
            bsonType: "objectId",
            description: "Debe ser una referencia válida a PRODUCTO y es obligatorio"
          },
          cantidad: {
            bsonType: "int",
            description: "Debe ser un entero y es obligatorio"
          },
          precio_unitario: {
            bsonType: "int",
            description: "Debe ser un número decimal y es obligatorio"
          }
        }
      }
    }
  });
  
  // Crear la colección "ZONA_REPARTO" con validación
  db.createCollection("zona_reparto", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["nombre", "poligono"],
        properties: {
          nombre: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          poligono: {
            bsonType: "object",
            description: "Debe ser un objeto de tipo Polygon y es obligatorio"
          }
        }
      }
    }
  });
  
  // Crear la colección "audit_log" con validación
  db.createCollection("audit_log", {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["tabla", "operacion", "datos", "usuario"],
        properties: {
          tabla: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          operacion: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          datos: {
            bsonType: "object",
            description: "Debe ser un objeto JSON y es obligatorio"
          },
          usuario: {
            bsonType: "string",
            description: "Debe ser una cadena y es obligatorio"
          },
          fecha: {
            bsonType: "date",
            description: "Debe ser una fecha (opcional)"
          }
        }
      }
    }
  });