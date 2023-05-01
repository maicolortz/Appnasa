# Aplicación de la NASA

Este proyecto es una aplicación móvil que permite a los usuarios registrarse, iniciar sesión y registrar asteroides que han sido descubiertos.

## Librerías utilizadas

- Volley: se utiliza para realizar peticiones HTTP a la API de la NASA.
- androidx.sqlite: es una biblioteca que proporciona una API simple para interactuar con bases de datos SQLite en el registro de usuarios y asteroides.
- Gson: se utiliza para el manejo de archivos json y poder trabajarlos como objetos java para el consumo de la Api de la NASA.

## Registro de usuarios y validaciones

Al abrir la aplicación, se muestra una pantalla de inicio de sesión donde los usuarios pueden ingresar sus credenciales o registrarse para obtener una nueva cuenta. Se utilizan las siguientes validaciones:

- El campo de correo electrónico debe ser un correo electrónico válido.
- La contraseña debe tener al menos 4 caracteres.
- El campo de nombre no puede estar vacío.
- El campo de apellido no puede estar vacío.
- El campo de identificación debe tener al menos 6 caracteres.

## Registro de asteroides

Después de Hacer el registro de usuario,automaticamente se registran los asteroides del dia 27 de abril del 2023. El asteroide tiene:

  - name: el nombre del asteroide.
  - absoluteMagnitudeH: la magnitud absoluta H del asteroide, que es una medida de su brillo.
  - estimatedDiameter: el diámetro estimado del asteroide en metros.
  - is_potentially_hazardous_asteroid: una cadena que indica si el asteroide es potencialmente peligroso para la Tierra. Puede tener el valor "true" o "false".

## Cómo usar la aplicación

### 1. Abre la aplicación.

![WhatsApp Image 2023-05-01 at 11 16 16 AM(1)](https://user-images.githubusercontent.com/107804493/235486272-f394090f-7108-48b9-bd4e-ad8e45a34822.jpeg)


###  2. Inicia sesión o crea una cuenta.

![WhatsApp Image 2023-05-01 at 11 16 16 AM](https://user-images.githubusercontent.com/107804493/235486397-82ca8a0c-5b07-43df-afcd-beefeb73fa5a.jpeg)

![WhatsApp Image 2023-05-01 at 11 16 15 AM(2)](https://user-images.githubusercontent.com/107804493/235486402-b79e0613-034a-408c-9156-c9e93ad26508.jpeg)


### 3. Si eres un usuario nuevo, se te llevará a la pantalla de registro. De lo contrario, tendras  en la parte inferior de la app, la opcion de registro de Usuario.

### 4. Si deseas ver los asteroides que has registrado, solo ingresa sesion y te apareceran la lista de asteroides registrados en el menú de la aplicación.
### 5. Para cerrar sesión, selecciona "Logout " en la seccion list of Asteroids.

![WhatsApp Image 2023-05-01 at 11 16 15 AM(1)](https://user-images.githubusercontent.com/107804493/235486650-a441b32f-9f92-43e0-9b3d-442e0b92462c.jpeg)

### 6. si deseas ver los detalles de algun asteroide, deberas dar clck en alguno de los asteroides listados

![WhatsApp Image 2023-05-01 at 11 16 15 AM](https://user-images.githubusercontent.com/107804493/235486849-d7ac8f0f-12da-4b72-8428-7bb09b7483d0.jpeg)



### ¡Disfruta de la aplicación de la NASA!
