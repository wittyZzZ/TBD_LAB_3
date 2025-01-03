// Seleccionar la base de datos
use ("tbd_lab_3");

db.categoria.insertMany([
    {nombre: "Tecnología" },
    {nombre: "Electrodomésticos" },
    {nombre: "Muebles para Oficina" },
    {nombre: "Accesorios para Vehículos" }
]);

const categoriaTecnologia = db.categoria.findOne({ nombre: "Tecnología" });
const categoriaElectrodomesticos = db.categoria.findOne({ nombre: "Electrodomésticos" });
const categoriaMuebles = db.categoria.findOne({ nombre: "Muebles para Oficina" });
const categoriaVehiculos = db.categoria.findOne({ nombre: "Accesorios para Vehículos" });
db.producto.insertMany([
    {
        nombre: "Mouse Silencioso RGB",
        descripcion: "Mouse inalámbrico con botones silenciosos y luces RGB",
        precio: 4500,
        stock: 12,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Teclado DELL",
        descripcion: "Teclado inalámbrico DELL distribución Latinoamericana con pad numérico",
        precio: 23000,
        stock: 5,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Pendrive 32 GB",
        descripcion: "Este dispositivo posee una gran velocidad para transferir datos",
        precio: 6500,
        stock: 20,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Batería portátil",
        descripcion: "Esta batería te permite llevar contigo sin preocupaciones tus dispositivos por un largo período de tiempo",
        precio: 500000,
        stock: 4,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Computador Gamer Ryzen 5500",
        descripcion: "Computador diseñado para videojuegos de altos requisitos junto con una NVIDIA 3060",
        precio: 720000,
        stock: 1,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Smartphone Samsung Galaxy A52",
        descripcion: "Teléfono inteligente con pantalla AMOLED y cámara de alta resolución",
        precio: 280000,
        stock: 10,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Audífonos Bluetooth Sony WH-1000XM4",
        descripcion: "Audífonos inalámbricos con cancelación de ruido y sonido de alta fidelidad",
        precio: 150000,
        stock: 8,
        estado: "Disponible",
        id_categoria: categoriaTecnologia._id
    },
    {
        nombre: "Refrigerador LG InstaView",
        descripcion: "Refrigerador de alta capacidad con panel táctil y eficiencia energética clase A++",
        precio: 900000,
        stock: 3,
        estado: "Disponible",
        id_categoria: categoriaElectrodomesticos._id
    },
    {
        nombre: "Horno Microondas Panasonic",
        descripcion: "Horno microondas con múltiples configuraciones y grill integrado",
        precio: 120000,
        stock: 7,
        estado: "Disponible",
        id_categoria: categoriaElectrodomesticos._id
    },
    {
        nombre: "Escritorio Ejecutivo",
        descripcion: "Escritorio de madera con acabado premium y amplio espacio de almacenamiento",
        precio: 180000,
        stock: 5,
        estado: "Disponible",
        id_categoria: categoriaMuebles._id
    },
    {
        nombre: "Silla Ergonómica para Oficina",
        descripcion: "Silla ergonómica con soporte lumbar ajustable y reposabrazos",
        precio: 85000,
        stock: 10,
        estado: "Disponible",
        id_categoria: categoriaMuebles._id
    },
    {
        nombre: "Cargador Rápido para Auto",
        descripcion: "Cargador rápido USB con soporte para carga dual",
        precio: 12000,
        stock: 15,
        estado: "Disponible",
        id_categoria: categoriaVehiculos._id
    }
]);

db.cliente.insertMany([
    {
        username: "user_1",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle 123, Santiago Centro",
              coordinates: [-70.6450, -33.4550]
          }
      ],
        email: "carlos.mendoza@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1234",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6450, -33.4550] }
    },
    {
        username: "user_2",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Central 45, Providencia",
              coordinates: [-70.6410, -33.4520]
          }
      ],
        email: "lucia.fernandez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-5678",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6410, -33.4520] }
    },
    {
        username: "user_3",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Central 45, Providencia",
              coordinates: [-70.5750, -33.4150]
          },
          {
            id_direccion: ObjectId(),
            direccion: "Av. Cristóbal Colón",
            coordinates: [-70.73422, -34.1722]
          }
      ],
        email: "andres.castillo@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8765",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5750, -33.4150] }
    },
    {
        username: "user_4",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Los Alamos 10, Ñuñoa",
              coordinates: [-70.6150, -33.4630]
          }
      ],
        email: "maria.lopez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-4321",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6150, -33.4630] }
    },
    {
        username: "user_5",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Primavera 21, Ñuñoa",
              coordinates: [-70.6180, -33.4660]
          }
      ],
        email: "jorge.martinez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2468",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6180, -33.4660] }
    },
    {
        username: "user_6",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. La Esperanza 99, Maipú",
              coordinates: [-70.7550, -33.5020]
          }
      ],
        email: "ana.ruiz@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1357",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.7550, -33.5020] }
    },
    {
        username: "user_7",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Las Palmas 7, Maipú",
              coordinates: [-70.7570, -33.5070]
          }
      ],
        email: "pedro.gonzalez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9753",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7570, -33.5070] }
    },
    {
        username: "user_8",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle San Juan 88, Renca",
              coordinates: [-70.7300, -33.4000]
          }
      ],
        email: "laura.gil@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3579",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.7300, -33.4000] }
    },
    {
        username: "user_9",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Norte 66, Pudahuel",
              coordinates: [-70.8000, -33.4300]
          }
      ],
        email: "sofia.duarte@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9632",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.8000, -33.4300] }
    },
    {
        username: "user_10",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle del Sol 41, La Florida",
              coordinates: [-70.5900, -33.5400]
          }
      ],
        email: "ramon.perez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-4682",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5900, -33.5400] }
    },
    {
        username: "user_11",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Oriente 23, La Florida",
              coordinates: [-70.5950, -33.5350]
          }
      ],
        email: "carmen.vazquez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7524",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.5950, -33.5350] }
    },
    {
        username: "user_12",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Pino 12, Peñalolén",
              coordinates: [-70.5500, -33.4900]
          }
      ],
        email: "diego.sanchez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-6142",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5500, -33.4900] }
    },
    {
        username: "user_13",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Libertad 14, Santiago Centro",
              coordinates: [-70.6500, -33.4510]
          }
      ],
        email: "paula.reyes@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8237",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6500, -33.4510] }
    },
    {
        username: "user_14",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Nueva 33, Providencia",
              coordinates: [-70.6380, -33.4390]
          }
      ],
        email: "alberto.morales@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7481",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6380, -33.4390] }
    },
    {
        username: "user_15",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Los Pinos 54, Ñuñoa",
              coordinates: [-70.6200, -33.4700]
          }
      ],
        email: "marta.salinas@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2153",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6200, -33.4700] }
    },
    {
        username: "user_16",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Cedros 98, Santiago Centro",
              coordinates: [-70.6520, -33.4525]
          }
      ],
        email: "luis.rojas@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9425",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6520, -33.4525] }
    },
    {
        username: "user_17",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Amanecer 60, Renca",
              coordinates: [-70.7280, -33.4020]
          }
      ],
        email: "gloria.vega@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7893",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7280, -33.4020] }
    },
    {
        username: "user_18",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Luna 67, Ñuñoa",
              coordinates: [-70.6170, -33.4640]
          }
      ],
        email: "tomas.rivera@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1568",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6170, -33.4640] }
    },
    {
        username: "user_19",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Av. Principal 5, Maipú",
              coordinates: [-70.7500, -33.5000]
          }
      ],
        email: "isabel.romero@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8947",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7500, -33.5000] }
    },
    {
        username: "user_20",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Real 19, Peñalolén",
              coordinates: [-70.5450, -33.4920]
          }
      ],
        email: "vicente.navarro@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3256",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5450, -33.4920] }
    },
    {
        username: "user_21",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "24 Nte., Viña del Mar",
              coordinates: [-71.54226306197091, -33.002067847491986]
          }
      ],
        email: "vicente.peralta@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3256",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.54226306197091, -33.002067847491986] }
    },
    //Region valparaiso
    {
        username: "user_22",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Lima 202, Viña del Mar",
              coordinates: [-71.540, -33.025]
          }
      ],
        email: "user.vina1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1111",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.540, -33.025] }
    },
    {
        username: "user_23",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Miraflores 101, San Antonio",
              coordinates: [-71.618, -33.585]
          }
      ],
        email: "user.san1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2222",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.618, -33.585] }
    },
    //Region Biobio
    {
        username: "user_24",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Freire 404, Concepción",
              coordinates: [-73.045, -36.820]
          }
      ],
        email: "user.conce1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3333",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-73.045, -36.820] }
    },
    {
        username: "user_25",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Independencia 306, Chillán",
              coordinates: [-72.100, -36.610]
          }
      ],
        email: "user.chillan1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3333",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.100, -36.610] }
    },
    // Region La Araucania
    {
        username: "user_26",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Alemania 1201, Temuco",
              coordinates: [-72.590, -38.730]
          }
      ],
        email: "user.temuco1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-5555",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.590, -38.730] }
    },
    // Region Los Lagos
    {
        username: "user_27",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Chinquihue 321, Puerto Montt",
              coordinates: [-72.940, -41.465]
          }
      ],
        email: "user.pm1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-6666",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.940, -41.465] }
    },
    // Region Antofagasta
    {
        username: "user_28",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Baquedano 101, Antofagasta",
              coordinates: [-70.398, -23.648]
          }
      ],
        email: "user.antofa1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7777",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.398, -23.648] }
    },
    // Region Tarapaca
    {
        username: "user_29",
        direccion: [
          {
              id_direccion: ObjectId(),
              direccion: "Calle Tarapacá 202, Iquique",
              coordinates: [-70.140, -20.210]
          }
      ],
        email: "user.iquique1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8888",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.140, -20.210] }
    },
]);

db.cliente.createIndex({coordenadas: "2dsphere"});

// Insertar datos en la colección "TIENDA"
db.tienda.insertMany([
    {
      nombre: "Tienda Costanera Center",
      direccion: "Av. Andres Bello 2425, Providencia, Santiago",
      coordenadas: { type: "Point", coordinates: [-70.6056, -33.417] },
    },
    {
      nombre: "Tienda La Reina",
      direccion: "Av. Blest Gana 5962, La Reina, Santiago",
      coordenadas: { type: "Point", coordinates: [-70.5685, -33.4553] },
    },
    {
      nombre: "Tienda Las Nieves",
      direccion: "Los Mallines 1996, Puente Alto, Santiago",
      coordenadas: {
        type: "Point", coordinates: [-70.57602256706053, -33.59415557033961],
      },
    },
    {
      nombre: "Tienda Maipú",
      direccion: "Rafael Frontaura 5-99, Maipú, Santiago",
      coordenadas: {
        type: "Point", coordinates: [-70.75585819880487, -33.514533912551755],
      },
    },
    {
      nombre: "TecTec - Tecnología y Computación",
      direccion: "Oficina Contable - Ventas Solo en Nuestra Web - Moneda 812, Santiago, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.64745903544836, -33.44157280323984],
      },
    },
    {
      nombre: "ABCDIN Estado",
      direccion: "Paseo Estado 73, 8320256 Santiago, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.64899653193504, -33.44174084107714],
      },
    },
    {
      nombre: "Tecnodetodo",
      direccion: "Av. Domingo Sta. María 3584, 8640722 Renca, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.69452693082134, -33.40700876892923],
      },
    },
    {
      nombre: "Techcenter",
      direccion: "San Diego 335, 8320000 Santiago, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.65089521869716, -33.4484501686001],
      },
    },
    {
      nombre: "Tecnozona",
      direccion: "Francisco Bilbao 1344, Local 103, 7500000 Providencia, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.6096002299189, -33.437859593913444],
      },
    },
    {
      nombre: "Informatica y electronica Lufraja - Punto Blue express",
      direccion: "Alberdi 01781, 8500020 Santiago, Quinta Normal, Región Metropolitana",
      coordenadas: {
        type: "Point", coordinates: [-70.70295864279399, -33.42567739504398],
      },
    },
    {
      nombre: "Tienda Viña Centro",
      direccion: "Av. Valparaíso 101, Viña del Mar, Valparaíso",
      coordenadas: { type: "Point", coordinates: [-71.543, -33.02] },
    },
    {
      nombre: "Tienda San Antonio",
      direccion: "Calle Blanco 505, San Antonio, Valparaíso",
      coordenadas: { type: "Point", coordinates: [-71.621, -33.59] },
    },
    {
      nombre: "Tienda Concepción Centro",
      direccion: "Av. Paicaví 1234, Concepción, Biobío",
      coordenadas: { type: "Point", coordinates: [-73.048, -36.826] },
    },
    {
      nombre: "Tienda Chillán",
      direccion: "Calle Libertad 345, Chillán, Biobío",
      coordenadas: { type: "Point", coordinates: [-72.105, -36.606] },
    },
    {
      nombre: "Tienda Temuco Centro",
      direccion: "Av. Alemania 789, Temuco, La Araucanía",
      coordenadas: { type: "Point", coordinates: [-72.593, -38.735] },
    },
    {
      nombre: "Tienda Puerto Montt",
      direccion: "Calle Antonio Varas 123, Puerto Montt, Los Lagos",
      coordenadas: { type: "Point", coordinates: [-72.936, -41.468] },
    },
    {
      nombre: "Tienda Antofagasta Centro",
      direccion: "Av. Brasil 456, Antofagasta, Antofagasta",
      coordenadas: { type: "Point", coordinates: [-70.4, -23.65] },
    },
    {
      nombre: "Tienda Iquique",
      direccion: "Av. Arturo Prat 789, Iquique, Tarapacá",
      coordenadas: { type: "Point", coordinates: [-70.142, -20.215] },
    },
]);
db.tienda.createIndex({coordenadas: "2dsphere"});


  // Insertar datos en la colección "REPARTIDOR"
db.repartidor.insertMany([
    {
      nombre: "Tomás Soto",
      coordenadas: { type: "Point", coordinates: [-70.60836609, -33.50862745] },
    },
    {
      nombre: "Carlos Barra",
      coordenadas: {
        type: "Point", coordinates: [-70.6453284199841, -33.45860089018716],
      },
    },
    {
      nombre: "Benjamín Tapia",
      coordenadas: {
        type: "Point", coordinates: [-70.7103335299697, -33.50162765386649],
      },
    },
    {
      nombre: "Matías Silva",
      coordenadas: {
        type: "Point", coordinates: [-70.76772848894939, -33.44778595432466],
      },
    },
    {
      nombre: "Andrés Garrido",
      coordenadas: {
        type: "Point", coordinates: [-71.54492236676212, -33.02332421999087],
      },
    },
    {
      nombre: "Joaquín Valdés",
      coordenadas: { type: "Point", coordinates: [-71.538, -33.024] },
    },
    {
      nombre: "Francisca Araya",
      coordenadas: { type: "Point", coordinates: [-71.62, -33.59] },
    },
    {
      nombre: "Cristóbal Morales",
      coordenadas: { type: "Point", coordinates: [-73.043, -36.823] },
    },
    {
      nombre: "Daniela Sánchez",
      coordenadas: { type: "Point", coordinates: [-72.102, -36.609] },
    },
    {
      nombre: "Matías Herrera",
      coordenadas: { type: "Point", coordinates: [-72.594, -38.732] },
    },
    {
      nombre: "Sofía Pérez",
      coordenadas: { type: "Point", coordinates: [-72.938, -41.467] },
    },
    {
      nombre: "Felipe Gutiérrez",
      coordenadas: { type: "Point", coordinates: [-70.399, -23.652] },
    },
    {
      nombre: "Camila Rojas",
      coordenadas: { type: "Point", coordinates: [-70.141, -20.212] },
    },
]);
db.repartidor.createIndex({coordenadas: "2dsphere"});


// Insertar datos en la colección "ORDEN"

// **Primero, obtener las referencias a los clientes, repartidores y tiendas.**
const cliente1 = db.cliente.findOne({ username: "user_1" });
const cliente2 = db.cliente.findOne({ username: "user_2" });
const cliente3 = db.cliente.findOne({ username: "user_3" });
const cliente12 = db.cliente.findOne({ username: "user_12" });
const cliente21 = db.cliente.findOne({ username: "user_21" });

const repartidor1 = db.repartidor.findOne({ nombre: "Tomás Soto" });
const repartidor2 = db.repartidor.findOne({ nombre: "Carlos Barra" });
const repartidor3 = db.repartidor.findOne({ nombre: "Benjamín Tapia" });
const repartidor5 = db.repartidor.findOne({ nombre: "Andrés Garrido" });

const tienda1 = db.tienda.findOne({ nombre: "Tienda Costanera Center" });
const tienda2 = db.tienda.findOne({ nombre: "Tienda La Reina" });
const tienda5 = db.tienda.findOne({ nombre: "TecTec - Tecnología y Computación" });
const tienda6 = db.tienda.findOne({ nombre: "ABCDIN Estado" });

db.orden.insertMany([
    {
      fecha_orden: new Date(), // revisar
      estado: "Pagada",
      id_cliente: cliente1._id,
      total: 24000,
      id_tienda: tienda1._id,
    },
    {
      fecha_orden: new Date(),
      estado: "Enviada",
      id_cliente: cliente2._id,
      total: 30000,
      id_repartidor: repartidor1._id,
      id_tienda: tienda2._id,
    },
    {
      fecha_orden: new Date(),
      estado: "Enviada",
      id_cliente: cliente21._id,
      total: 21000,
      id_repartidor: repartidor5._id,
      id_tienda: tienda5._id,
    },
    {
      fecha_orden: new Date(),
      estado: "Enviada",
      id_cliente: cliente12._id,
      total: 3000,
      id_repartidor: repartidor3._id,
      id_tienda: tienda6._id,
    },
    {
      fecha_orden: new Date(),
      estado: "Completada",
      id_cliente: cliente3._id,
      total: 15000,
      id_repartidor: repartidor2._id,
      id_tienda: tienda1._id,
    },
]);

// Insertar datos en la colección "DETALLE_ORDEN"

// **Primero, obtener las referencias a las órdenes y productos.**

// Obtener las referencias a las órdenes
const orden1 = db.orden.findOne({ estado: "Pagada" });
const orden2 = db.orden.findOne({ estado: "Enviada", id_cliente: cliente2._id });
const orden3 = db.orden.findOne({ estado: "Enviada", id_cliente: cliente21._id });
const orden4 = db.orden.findOne({ estado: "Enviada", id_cliente: cliente12._id });
const orden5 = db.orden.findOne({ estado: "Completada" });

// Obtener las referencias a los productos
const producto1 = db.producto.findOne({ nombre: "Mouse Silencioso RGB" });
const producto2 = db.producto.findOne({ nombre: "Teclado DELL" });
const producto3 = db.producto.findOne({ nombre: "Pendrive 32 GB" });

db.detalle_orden.insertMany([
    {
      id_orden: orden1._id,
      id_producto: producto1._id,
      cantidad: 2,
      precio_unitario: producto1.precio,
    },
    {
      id_orden: orden2._id,
      id_producto: producto2._id,
      cantidad: 1,
      precio_unitario: producto2.precio,
    },
    {
      id_orden: orden3._id,
      id_producto: producto3._id,
      cantidad: 3,
      precio_unitario: producto3.precio,
    },
    {
      id_orden: orden4._id,
      id_producto: producto1._id,
      cantidad: 1,
      precio_unitario: producto1.precio,
    },
    {
      id_orden: orden5._id,
      id_producto: producto2._id,
      cantidad: 1,
      precio_unitario: producto2.precio,
    },
]);

db.zona_reparto.insertMany([
    {
      nombre: "Zona Centro",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-70.68965994719805, -33.419622713280866],
            [-70.63343880436038, -33.436767034525424],
            [-70.60684528230406, -33.41658069751892],
            [-70.58021703839037, -33.48742592788876],
            [-70.69165479386703, -33.49488968909503],
            [-70.68965994719805, -33.419622713280866],
          ],
        ],
      },
    },
    {
      nombre: "Zona Puente Alto",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-70.55608391865024, -33.54342781519428],
            [-70.6196885668074, -33.54201144195442],
            [-70.59856793794873, -33.62695273425572],
            [-70.53423499229359, -33.6061296330255],
            [-70.55608391865024, -33.54342781519428],
          ],
        ],
      },
    },
    {
      nombre: "Zona Viña del Mar 1",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-71.54938886905275, -33.000659557745905],
            [-71.50974757352077, -33.006616685862916],
            [-71.50745616915475, -33.04504009861507],
            [-71.56817838485404, -33.023717172932265],
            [-71.54938886905275, -33.000659557745905],
          ],
        ],
      },
    },
    {
      nombre: "Zona Santiago",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-70.76233181623087, -33.383944888168216],
            [-70.66545818578015, -33.36756690642463],
            [-70.60721514415948, -33.38642613149192],
            [-70.54203392164673, -33.329007657048415],
            [-70.49355731556004, -33.33735851993195],
            [-70.53503729769275, -33.609990046732385],
            [-70.7064545773473, -33.644944725790786],
            [-70.86187957211999, -33.5750211910383],
            [-70.76233181623087, -33.383944888168216],
          ],
        ],
      },
    },
    {
      nombre: "Zona Viña del Mar 2",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-71.55, -33.01],
            [-71.53, -33.01],
            [-71.53, -33.03],
            [-71.55, -33.03],
            [-71.55, -33.01],
          ],
        ],
      },
    },
    {
      nombre: "Zona San Antonio",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-71.63, -33.58],
            [-71.61, -33.58],
            [-71.61, -33.6],
            [-71.63, -33.6],
            [-71.63, -33.58],
          ],
        ],
      },
    },
    {
      nombre: "Zona Concepción",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-73.05, -36.81],
            [-73.04, -36.81],
            [-73.04, -36.83],
            [-73.05, -36.83],
            [-73.05, -36.81],
          ],
        ],
      },
    },
    {
      nombre: "Zona Chillán",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-72.11, -36.6],
            [-72.1, -36.6],
            [-72.1, -36.62],
            [-72.11, -36.62],
            [-72.11, -36.6],
          ],
        ],
      },
    },
    {
      nombre: "Zona Temuco",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-72.6, -38.72],
            [-72.59, -38.72],
            [-72.59, -38.74],
            [-72.6, -38.74],
            [-72.6, -38.72],
          ],
        ],
      },
    },
    {
      nombre: "Zona Puerto Montt",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-72.95, -41.46],
            [-72.93, -41.46],
            [-72.93, -41.48],
            [-72.95, -41.48],
            [-72.95, -41.46],
          ],
        ],
      },
    },
    {
      nombre: "Zona Antofagasta",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-70.41, -23.64],
            [-70.39, -23.64],
            [-70.39, -23.66],
            [-70.41, -23.66],
            [-70.41, -23.64],
          ],
        ],
      },
    },
    {
      nombre: "Zona Iquique",
      poligono: {
        type: "Polygon",
        coordinates: [
          [
            [-70.15, -20.2],
            [-70.13, -20.2],
            [-70.13, -20.22],
            [-70.15, -20.22],
            [-70.15, -20.2],
          ],
        ],
      },
    },
]);