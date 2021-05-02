import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ModoConsumoLocal implements ModoConsumo {
    private int dia;
    private LocalTime horarioInicial;
    private LocalTime horarioFinal;

    public ModoConsumoLocal(int dia, LocalTime horaInicial, LocalTime horaFinal) {
        this.dia = dia;
        this.horarioInicial = horaInicial;
        this.horarioFinal = horaFinal;
    }

    @Override
    public double calcularConsumo() {
        //Defino el valor del minuto
        double costoRangoHabilxMinuto = 0.2;
        double costoRangoInhabilxMinuto = 0.1;
        double consumoTotal = 0;

        int horaInicial = horarioInicial.getHour();
        int horaFinal = horarioFinal.getHour();
        //Hago calculo por si cambio de dia para calcular los minutos correctamente
        long minutosTotales = 0;
        if (horaFinal < horaInicial){
            minutosTotales += ChronoUnit.MINUTES.between(horarioInicial, LocalTime.of(23, 59, 00)) + 1;
            minutosTotales += ChronoUnit.MINUTES.between(LocalTime.of(00, 00, 00), horarioFinal);
        } else {
            minutosTotales = ChronoUnit.MINUTES.between(horarioInicial, horarioFinal);
        }

        //Si el dia es no habil simplemente retorno minutos * el costo del dia no habil (decidi excluir casos borde
        // donde se podria hacer una llamada de 80 horas y que se saltee el lunes. Me parecia que noe ra necesario tanto detalle para este ejercicio
        if (dia > 5){
            return minutosTotales * costoRangoInhabilxMinuto;
        }

        //En este while lo que voy haciendo preguntar si la cantidad de minutos que tengo hace que pase al siguiente rango,
        //en cuyo caso calculo el consumo hasta el rango y actualizo los minutos y las horas iniciales.
        while (minutosTotales != 0){
            //
            if (horaInicial >= 8 && horaInicial < 20) {

                if (horaFinal > 20) {
                    long minutosHasta = ChronoUnit.MINUTES.between(horarioInicial, LocalTime.of(20, 00, 00));
                    consumoTotal += minutosHasta * costoRangoHabilxMinuto;
                    minutosTotales -= minutosHasta;
                    horaInicial = 20;
                } else {
                    consumoTotal += costoRangoHabilxMinuto * minutosTotales;
                    return consumoTotal;
                }

            } else {

                if (horaFinal >= 8 && horaFinal < 20) {
                    long minutosHasta = ChronoUnit.MINUTES.between(horarioInicial, LocalTime.of(8, 00, 00));
                    minutosTotales -= minutosHasta;
                    horaInicial = 8;
                    consumoTotal += minutosHasta * costoRangoInhabilxMinuto;
                } else {
                    consumoTotal += costoRangoInhabilxMinuto * minutosTotales;
                    return consumoTotal;
                }
            }
        }

        return consumoTotal;
    }
}
