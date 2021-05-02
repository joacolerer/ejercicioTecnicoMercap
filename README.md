# ejercicioTecnicoMercap
Ejercicio tecnico de Mercap

Aclaraciones y asunciones:

Asumo que el abono mensual basico es fijo y no se puede cambiar.
Asumo que ingresan datos validados, para no lidiar con validacion.
Asumo que no habra casos bordes extremos (por Ej, una llamada de 500 horas) ya que tendria que perder mucho tiempo pensando logica que creo que no es pertinente para este ejercicio.


Acerca de mi resolucion:
Aplique TDD para ir iterativamente creando los nuevos objetos y metodos.
Tambien realice algunas pruebas unitarias para corroborar el calculo de minutos y la facturacion.

Modelo:
El modelo se basa en un facturador, el cual contiene dos listas de llamadas. Contiene dos listas para poder calcular solo el consumo de llamadas locales por un lado y por otro lado el consumo de las no locales

Si bien en el problema tenemos distitnos tipos de llamadas, como llamada local, o llamada internaciona/nacional, decidi abstraer ese comportamiento ya que no influye en como es el objeto llamada. Ademas si en un futuro se quiere cambiar la manera de calcular el consumo se puede hacerlo sin interferir en la clase Llamada.

El unico cambio que tiene los distintos tipos de llamada es como se calcula el consumo, este mismo esta abstraido a la interfaz ModoConsumo, del cual implementan dos clases, ModoConsumoLocal y ModoConsumoNoLocal. Como la manera de calcular el consumo de llamadas internacionales o nacionales es el mismo, las llamadas no locales van a tener asignado un modoConsumoNoLocal, sin diferenciarlas. 

El problema que pude observar al abstraer el comportamiento del consumo es que el objeto Llamada queda anemico, es decir, no tiene uso mas que contener a otra clase y hacer un llamado a un metodo, pero teniendo en cuenta que mi objetivo era aislar el comportamiento para hacer mas comodo cambiar el codigo sin interferir en otras clases me parece que vale la pena. Tambien tengo en cuenta que se le puede agregar mas responsabilidad a la llamda sin cambiar como se consigue su consumo.
