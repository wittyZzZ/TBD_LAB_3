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
        direccion: "Calle 123, Santiago Centro",
        email: "carlos.mendoza@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1234",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6450, -33.4550] }
    },
    {
        username: "user_2",
        direccion: "Av. Central 45, Providencia",
        email: "lucia.fernandez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-5678",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6410, -33.4520] }
    },
    {
        username: "user_3",
        direccion: "Calle Los Robles 85, Las Condes",
        email: "andres.castillo@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8765",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5750, -33.4150] }
    },
    {
        username: "user_4",
        direccion: "Av. Los Alamos 10, Ñuñoa",
        email: "maria.lopez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-4321",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6150, -33.4630] }
    },
    {
        username: "user_5",
        direccion: "Calle Primavera 21, Ñuñoa",
        email: "jorge.martinez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2468",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6180, -33.4660] }
    },
    {
        username: "user_6",
        direccion: "Av. La Esperanza 99, Maipú",
        email: "ana.ruiz@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1357",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.7550, -33.5020] }
    },
    {
        username: "user_7",
        direccion: "Calle Las Palmas 7, Maipú",
        email: "pedro.gonzalez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9753",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7570, -33.5070] }
    },
    {
        username: "user_8",
        direccion: "Calle San Juan 88, Renca",
        email: "laura.gil@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3579",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.7300, -33.4000] }
    },
    {
        username: "user_9",
        direccion: "Av. Norte 66, Pudahuel",
        email: "sofia.duarte@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9632",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.8000, -33.4300] }
    },
    {
        username: "user_10",
        direccion: "Calle del Sol 41, La Florida",
        email: "ramon.perez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-4682",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5900, -33.5400] }
    },
    {
        username: "user_11",
        direccion: "Av. Oriente 23, La Florida",
        email: "carmen.vazquez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7524",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.5950, -33.5350] }
    },
    {
        username: "user_12",
        direccion: "Calle Pino 12, Peñalolén",
        email: "diego.sanchez@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-6142",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5500, -33.4900] }
    },
    {
        username: "user_13",
        direccion: "Av. Libertad 14, Santiago Centro",
        email: "paula.reyes@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8237",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6500, -33.4510] }
    },
    {
        username: "user_14",
        direccion: "Calle Nueva 33, Providencia",
        email: "alberto.morales@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7481",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6380, -33.4390] }
    },
    {
        username: "user_15",
        direccion: "Av. Los Pinos 54, Ñuñoa",
        email: "marta.salinas@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2153",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.6200, -33.4700] }
    },
    {
        username: "user_16",
        direccion: "Calle Cedros 98, Santiago Centro",
        email: "luis.rojas@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-9425",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6520, -33.4525] }
    },
    {
        username: "user_17",
        direccion: "Av. Amanecer 60, Renca",
        email: "gloria.vega@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7893",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7280, -33.4020] }
    },
    {
        username: "user_18",
        direccion: "Calle Luna 67, Ñuñoa",
        email: "tomas.rivera@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1568",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.6170, -33.4640] }
    },
    {
        username: "user_19",
        direccion: "Av. Principal 5, Maipú",
        email: "isabel.romero@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8947",
        rol: "ADMIN",
        coordenadas: { type: "Point", coordinates: [-70.7500, -33.5000] }
    },
    {
        username: "user_20",
        direccion: "Calle Real 19, Peñalolén",
        email: "vicente.navarro@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3256",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.5450, -33.4920] }
    },
    {
        username: "user_21",
        direccion: "24 Nte., Viña del Mar",
        email: "vicente.peralta@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3256",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.54226306197091, -33.002067847491986] }
    },
    //Region valparaiso
    {
        username: "user_22",
        direccion: "Calle Lima 202, Viña del Mar",
        email: "user.vina1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-1111",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.540, -33.025] }
    },
    {
        username: "user_23",
        direccion: "Calle Miraflores 101, San Antonio",
        email: "user.san1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-2222",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-71.618, -33.585] }
    },
    //Region Biobio
    {
        username: "user_24",
        direccion: "Calle Freire 404, Concepción",
        email: "user.conce1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3333",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-73.045, -36.820] }
    },
    {
        username: "user_25",
        direccion: "Calle Independencia 306, Chillán",
        email: "user.chillan1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-3333",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.100, -36.610] }
    },
    // Region La Araucania
    {
        username: "user_26",
        direccion: "Calle Alemania 1201, Temuco",
        email: "user.temuco1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-5555",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.590, -38.730] }
    },
    // Region Los Lagos
    {
        username: "user_27",
        direccion: "Calle Chinquihue 321, Puerto Montt",
        email: "user.pm1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-6666",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-72.940, -41.465] }
    },
    // Region Antofagasta
    {
        username: "user_28",
        direccion: "Calle Baquedano 101, Antofagasta",
        email: "user.antofa1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-7777",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.398, -23.648] }
    },
    // Region Tarapaca
    {
        username: "user_29",
        direccion: "Calle Tarapacá 202, Iquique",
        email: "user.iquique1@mail.com",
        contrasena: "$2a$10$dlbYCOz.DNvH98Aqjm4Yk.wQ6ReUh9yBjrcTLPkNHRDdhE8IIzgS.",
        telefono: "555-8888",
        rol: "USER",
        coordenadas: { type: "Point", coordinates: [-70.140, -20.210] }
    },
]);