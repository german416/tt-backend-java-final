# REQUISITOS

## BACKEND

### Productos
Cada producto deberá tener los siguientes atributos:
- ✅ id
- ✅ nombre
- descripción
- ✅ precio
- categoría
- imágen
- ✅ stock

✅ Se deben poder realizar las siguientes acciones sobre los productos:
- ✅ Create
- ✅ Read: all, by id
- ✅ Update
- ✅ Delete

**NOTA DE GER:** estimo que será necesario alguna forma de subir imágenes.

### Categorías
- Create
- Read: all, by id
- Update
- Delete

### Carrito de compras
- Create
- Read
- Update
- Delete

### Usuarios
- Create
- Read: all, by id
- Update
- Delete

## FRONTEND

### 0) Implementar template proporcionado por la cátedra
Enlaces del template: 
- [presentación online (github pages)](https://emilianospinoso.github.io/proyecto-final-ecommerce/)
- [repositorio (github)](https://github.com/emilianospinoso/proyecto-final-ecommerce)

### 1) Gestión de productos
- Crear un producto nuevo
- Listar los productos existentes
- Buscar producto por nombre o id, si se encuentra mostrar info completa.
- Actualizar producto (solo precio o stock, validar coherencia de datos)
- Eliminar producto por id (con confirmación)
- Alertar cuando el stock alcance niveles minimos

### 2) Gestión de pedidos
- Crear pedidos de compra por usuario
- Listar pedidos de compra
- Gestionar estado de pedidos de compra: pendiente, confirmado, enviado, entregado, cancelado, etc)
- Actualizar stock al confirmar pedido

### 3) Administración
...
