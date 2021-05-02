import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebaUnitariaCalculadorHorarioLocal {
    double costoMinutoHabil = 0.2;
    double costoMinutoNoHabil = 0.1;

    @Test
    public void testCalcularConsumoRango(){
        int dia = 1;
        LocalTime horaInicial = LocalTime.of(10, 30, 00);
        LocalTime horaFinal = LocalTime.of(10, 40, 00);
        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioLocal(dia, horaInicial, horaFinal);
        assertEquals(calculadorDiaHorarioLocal.calcularConsumo(), 10 * costoMinutoHabil);
    }

    @Test
    public void testCalcularConsumoRangoPasandoLas20(){
        int dia = 1;
        LocalTime horaInicial = LocalTime.of(19, 00, 00);
        LocalTime horaFinal = LocalTime.of(21, 00, 00);
        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioLocal(dia, horaInicial, horaFinal);
        assertEquals(calculadorDiaHorarioLocal.calcularConsumo(), 60 * costoMinutoHabil + 60 * costoMinutoNoHabil);
    }

    @Test
    public void testCalcularConsumoRangoPasandoLas8(){
        int dia = 1;
        LocalTime horaInicial = LocalTime.of(6, 00, 00);
        LocalTime horaFinal = LocalTime.of(9, 00, 00);
        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioLocal(dia, horaInicial, horaFinal);
        assertEquals(calculadorDiaHorarioLocal.calcularConsumo(), 60 * costoMinutoHabil + 120 * costoMinutoNoHabil);
    }

    @Test
    public void testCalcularConsumoRangoPasandoDeDia(){
        int dia = 6;
        LocalTime horaInicial = LocalTime.of(23, 00, 00);
        LocalTime horaFinal = LocalTime.of(1, 00, 00);
        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioLocal(dia, horaInicial, horaFinal);
        assertEquals(calculadorDiaHorarioLocal.calcularConsumo(), 120 * costoMinutoNoHabil);
    }

}