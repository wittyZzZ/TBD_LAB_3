# Laboratorio 3 - Bases de datos Avanzadas
## Laboratorio desarrollado por el grupo n°8.
### Integrantes de grupo 8

* Arina Bilan
* Patricio Briceño
* Gonzalo Arévalo
* William Zubarzo
* Cristobal Flores

# Descripción del proyecto

El objetivo es diseñar y desarrollar un sistema para un E-commerce, donde se gestionen productos, órdenes de compra, clientes, inventario en tiempo real, zonas de reparto, tiendas o sucursales y repartidores. Garantizando que cuando un cliente realice una compra, el stock de productos se actualice automáticamente, y si el stock de un producto llega a 0, este se marque como “agotado”. Además, se debe asignar un repartidor al cliente según la tienda o sucursal. El sistema permitirá también realizar un seguimiento mediante consultas sobre la cercanía de clientes, repartos y pedidos respecto a una tienda específica. Asimismo, el sistema contará con funcionalidades como permitir a los clientes almacenar múltiples direcciones de envío en sus perfiles, guardar un historial completo de las órdenes de cada cliente (incluyendo los productos comprados y el estado de la entrega), y registrar la geolocalización de los clientes para mejorar el servicio de recomendaciones basadas en su ubicación.

# Recursos utilizados para el desarrollo del proyecto:

   ## Base de datos
   * MongoDB versión 8.0
   * Mongosh versión 2.3
   * MongoDB Compass versión 1.45 (opcional, para visualización y consultas)

   ## Backend
   * IntelliJ IDEA Ultimate
   * JDK versión 17
   * Postman

   ## Frontend
   * VUE versión 3
   * Node.js versión >=16
   * Visual Studio Code (VSC)

# Guía de Instalación y Ejecución
Esta guía ayudará a configurar el entorno de desarrollo para trabajar con IntelliJ IDEA, MongoDB y ejecutar un proyecto de Vue.js

## 1. Clonar el repositorio

git clone https://github.com/wittyZzZ/TBD_LAB_3

## 2. Instalación de Node.js

1. Descargar Node.js desde el sitio web oficial: [Descargar Node.js](https://nodejs.org/es/download/).

## 3. Instalación de IntelliJ IDEA

1. Descargar IntelliJ IDEA desde el sitio web oficial: [Descargar IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

2. Instalar IntelliJ IDEA siguiendo las instrucciones específicas para el sistema operativo.

## 4. Instalación de MongoDB y herramientas asociadas

### 4.1 Instalación de MongoDB

1. Descargar MongoDB desde el sitio web oficial: [Descargar MongoDB](https://www.mongodb.com/try/download/community).

2. Seguir las instrucciones de instalación para el sistema operativo.

### 4.2 Instalación de Mongosh

1. Descargar Mongosh desde el sitio web oficial: [Descargar Mongosh](https://www.mongodb.com/try/download/shell).

2. Instalar Mongosh siguiendo las instrucciones para el sistema operativo.

### 4.3 Instalación de MongoDB Compass

1. Descargar MongoDB Compass desde el sitio web oficial: [Descargar MongoDB Compass](https://www.mongodb.com/try/download/compass).

2. Instalar MongoDB Compass siguiendo las instrucciones para el sistema operativo.

## 5. Crear base de datos y cargar información

### 5.1 Crear base de datos y colecciones

1. Abrir una terminal en la carpeta donde se encuentran los scripts correspondientes

2. Ejecutar el siguiente comando para crear la base de datos y las colecciones con sus respectivas validaciones:

```bash
   mongosh "mongodb://localhost:27017/tbd_lab_3" dbCreate.js  
``` 

## 5.2 Cargar datos en las colecciones

1. Ejecutar el siguiente comando para cargar los datos iniciales en las colecciones:
```bash
mongosh "mongodb://localhost:27017/tbd_lab_3" loadData.js
```

## 5.3 Verificar la carga de datos

1. Abrir MongoDB Compass y conectarse a la base de datos en mongodb://localhost:27017/tbd_lab_3.

2. Verificar que las colecciones contengan los datos cargados correctamente.

## 5. Revisión de código y Ejecución de backend:

1. Abrir IntelliJ IDEA y seleccionar File -> Open, abrir carpeta "lab_1"

2. Ejecutar "mvn clean install" para instalar las dependencias necesarias descritas en el archivo pom.xml

3. Ejecutar directamente backend desde IntelliJ IDEA

## 6. Revisión de código y Ejecución de Frontend:

1. Abrir VSC -> open folder -> seleccionar tbd_frontend_1

2. Abrir una terminal en VSC y ejecutar los siguientes comandos:

   * Ejecutar "npm install" para instalar las dependencias necesarias descritas en el archivo package.json

   * Ejecutar "npm run serve" para iniciar el servidor de desarrollo de Vue.js.

3. Abrir el navegador web y navegar a la dirección http://localhost:5173/ para ver la aplicación de Vue.js en funcionamiento.

## 7. Funcionalidades especificas a desarrollar

1. Permitir a los clientes almacenar múltiples direcciones de envío en sus perfiles.

2. Guardar un historial completo de las órdenes de cada cliente, incluyendo los productos comprados y el estado de la entrega.

3. Registrar la geolocalización de los clientes para mejorar el servicio de recomendaciones basadas en su ubicación.

## 8. Login en aplicación web

* Existen 29 usuarios cargados en la base de datos, y para loguearse con uno de ellos el nombre de usuario es "user_i" (con i = {1,2,3,...,29} ) y la contraseña es "123" para todos

