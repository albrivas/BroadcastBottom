# Broadcast BottomNavigationView

Consiste en una pequeña aplicación que muestra el número de notificaciones push llegadas en tiempo real

La aplicación consta de las siguientes partes:
- Google Firebase para la recepción de Notificaciones Push
- Broadcast Receiver para actualizar en tiempo real el número de notificaciones recibidas
- Patron de arquitectura MVVM con DataBinding y ViewBinding
- Jetpack Navigation para la BottomNavigationView
- Koin para la inyeccion de dependencias
- BadgeDrawable para mostrar el número de notificaciones llegadas


<h2>¿Como hacer que funcione?</h2>

Crea un proyecto en Firebase y pega el json de configuracion de Cloud Messaging generado en la aplicación. Una vez hecho eso ejecuta la aplicación y obten el device_token desde la consola, buscando en el logcat Device_Token en la opción Debug. Al mandar una notificación push añade ese device token y llegará sin problemas a la aplicación
