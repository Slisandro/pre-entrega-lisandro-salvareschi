
# Pre-entrega

Simulación de un e-commerce por consola




## Features

### Autenticación

```
Para loguearse como administrador, utilizar:
    username: admin
    password: admin

Para loguearse como usuario, utilizar:
    username: user
    password: user
```

El e-commerce cuenta con autenticación básica para dos roles; administradores y usuario

* Como administrador voy a poder ver todas las ordenes de compra
* Como administrador voy a poder ver todos los usuarios
* Como administrador voy a poder ver, crear, editar, eliminar productos

* Como usuario voy a poder ver listado de productos 
* Como usuario voy a poder ver y agregar productos al carrito

### Clases

Se diseñaron varias clases

#### User 

Esta clase permite la creación de un usuario (nombre, usuario, contraseña y rol). También cuenta con un método para comparar contraseña 

#### UserService 

Esta clase contiene todos los métodos relacionado a autenticación y usuarios; como el inicio de sesión, el registro y el listado de usuarios

#### Product 

Esta clase permite la creación de un producto (nombre, precio y stock), con sus getter y setter

#### ProductService

Esta clase contiene todos los métodos relacionado a productos; como agregar producto, actualizar producto, eliminar producto, búsqueda por nombre (parcial o completo), obtener todos los productos y obtener producto por id


#### CartItem

Esta clase permite agregar un producto a un carrito, recibe como parametros un producto (class Product) y la cantidad. Los métodos para esta clase son; obtener producto, obtener cantidad, modificar cantidad y obtener subtotal

#### Cart 

Esta clase permite de un carrito, va a ser un arrayList de CartItems, es decir puede tener x cantidad de productos agregados. Los métodos son agregar un nuevo producto, obtener total (suma de los subtotales de CartItem) y obtener los productos agregados al carrito

#### CartService

Esta clase permite asociar un carrito (Cart) a un usuario, los métodos van a ser obtener todos los carritos (para el Admin), obtener carrito (este metodo recibe como parametro un User, si existe lo devuelve, sino lo crea), y por último, agregar producto que recibe como parametros un usuario User, un producto Product y la cantidad
