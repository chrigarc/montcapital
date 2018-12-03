# PROTOCOLO BÁSICO

* El cliente es quien comienza el intercambio de mensajes
* Los únicos mensajes permitidos son VERSION, CLOSE, HELLO, donde:
    * VERSION retornar la versión del programa
    * HELLO retorna un saludo de bienvenida
    * CLOSE retorna un mensaje de cerrar conexión y la finaliza
    * En cualquier otro caso envia mensaje de que no entiende
