# Broadcast BottomNavigationView

Consiste en una pequeña aplicación que muestra el número de notificaciones push llegadas en tiempo real

La aplicación consta de las siguientes partes:

<b>Arquitectura</b>
- Patron de arquitectura MVVM con DataBinding y ViewBinding
- Clean-Architecture (UsesCases, Repository, DataSources)
- Koin para la inyeccion de dependencias

<b>Componentes de la aplicación:</b>
- Broadcast Receiver para actualizar en tiempo real el número de notificaciones recibidas
- Jetpack Navigation para el flujo del login y el BottomNavigation
- Jetpack DataStore para almacenar información del ususario 
- BadgeDrawable para mostrar el número de notificaciones llegadas
- Glide para cargar la imagen del perfil

<b>Google Firebase:</b>
  - Authentication: para realizar el proceso de inicio de sesion con email/password, Google o Facebook. Posibilidad de recuperación de la contraseña o de crear una     nueva cuenta
  - FireStore: para almacenar la información del usuario
  - Storage: para almacenar la imagen de perfil que el ususario selecciona de su galeria en la pantalla de perfil
  - Cloud Messaging: para la recepción de notificaciones push

<h2>¿Como hacer que funcione?</h2>

Crea un proyecto en Firebase y pega el json de configuracion de Cloud Messaging generado en la aplicación. 
Una vez hecho eso ejecuta la aplicación y obten el device_token desde la consola, buscando en el logcat Device_Token en la opción Debug. 
Al mandar una notificación push añade ese device token y llegará sin problemas a la aplicación

