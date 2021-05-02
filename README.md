# Ejercicio Tecnico Mercap

Aclaraciones y asunciones:

Asumo que el abono mensual básico es fijo y no se puede cambiar.
Asumo que ingresan datos validados, para no lidiar con validación.
Asumo que no habrá casos bordes extremos (por Ej. una llamada de 500 horas), ya que tendría que perder mucho tiempo pensando lógica que creo que no es pertinente para este ejercicio.


Acerca de mi resolución:
Aplique TDD para ir iterativamente creando los nuevos objetos y métodos.
También, realice algunas pruebas unitarias para corroborar el cálculo de minutos y la facturación.

Modelo:
El modelo se basa en un facturador, el cual contiene dos listas de llamadas. Contiene dos listas para poder calcular solo el consumo de llamadas locales por un lado y por otro lado el consumo de las no locales

Si bien en el problema tenemos distintos tipos de llamadas, como llamada local, o llamada internacional/nacional, decidí abstraer ese comportamiento, ya que no influye en como es el objeto llamada. Además si en un futuro se quiere cambiar la manera de calcular el consumo se puede hacerlo sin interferir en la clase Llamada.

El único cambio que tiene los distintos tipos de llamada es como se calcula el consumo, este mismo está abstraído a la interfaz ModoConsumo, del cual implementan dos clases, ModoConsumoLocal y ModoConsumoNoLocal. Como la manera de calcular el consumo de llamadas internacionales o nacionales es el mismo, las llamadas no locales van a tener asignado un modoConsumoNoLocal, sin diferenciarlas. 

El problema que pude observar al abstraer el comportamiento del consumo es que el objeto Llamada queda anémico, es decir, no tiene uso más que contener a otra clase y hacer un llamado a un método, pero teniendo en cuenta que mi objetivo era aislar el comportamiento para hacer más cómodo cambiar el código sin interferir en otras clases me parece que vale la pena. También tengo en cuenta que se le puede agregar más responsabilidad a la clase llamda sin cambiar como se consigue su consumo.
