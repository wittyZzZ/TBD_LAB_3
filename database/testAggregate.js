use("tbd_lab_3");

//Ejecutar db.producto_con_categoria.find(); para hacer tabla de nombre, descripción, precio nombre de categoría y stock
db.createView(
    "producto_con_categoria",
    "producto",
    [
        {
            $lookup: {
                from: "categoria",            // Nombre de la colección 'categoria'
                localField: "id_categoria",   // Campo en 'producto' que referencia a 'categoria'
                foreignField: "_id",           // Campo en 'categoria' que actúa como referencia
                as: "categoria_info"           // Alias para los datos combinados
            }
        },
        {
            $unwind: "$categoria_info"      // Descomprime el array para obtener un documento por categoría
        },
        {
            $project: {
                _id: 0,                      // Excluye el campo _id de los resultados
                nombre: 1,                   // Muestra el nombre del producto
                descripcion: 1,              // Muestra la descripción del producto
                precio: 1,                   // Muestra el precio del producto
                stock: 1,
                categoria: "$categoria_info.nombre" // Muestra el nombre de la categoría
            }
        }
    ]
);