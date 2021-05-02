import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CalculadorDiaHorarioNoLocal implements CalculadorDiaHorario {

    private final double costo;
    private final LocalTime horarioInicial;
    private final LocalTime horarioFinal;

    public CalculadorDiaHorarioNoLocal(double costo, LocalTime horarioInicial, LocalTime horarioFinal) {
        this.costo = costo;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
    }

    @Override
    public double calcularConsumo() {
        long minutos = 0;
        if (horarioFinal.getHour() < horarioInicial.getHour()){
            minutos += ChronoUnit.MINUTES.between(horarioInicial, LocalTime.of(23, 59, 00)) + 1;
            minutos += ChronoUnit.MINUTES.between(LocalTime.of(00, 00, 00), horarioFinal);
        } else {
            minutos = ChronoUnit.MINUTES.between(horarioInicial, horarioFinal);
        }
        return costo * minutos;
    }
}
