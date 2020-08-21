# Broadcast BottomNavigationView

Consiste en una pequeña aplicación que muestra el número de notificaciones push llegadas en tiempo real

La aplicación consta de las siguientes partes:
- Google Firebase para la recepción de Notificaciones Push
- Broadcast Receiver para actualizar en tiempo real el número de notificaciones recibidas
- Patron de arquitectura MVVM con DataBinding y ViewBinding
- Jetpack Navigation para la BottomNavigationView
- Koin para la inyeccion de dependencias
- BadgeDrawable para mostrar el número de notificaciones llegadas
