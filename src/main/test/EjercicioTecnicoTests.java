import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EjercicioTecnicoTests {

    double costoMinuto = 0.2;
    double costoMinutoDiaNoHabil = 0.1;
    double costoLocalidad = 0.6;
    double costoPais = 0.3;

    @Test
    public void testCrearFacturadorSinLlamadasYGenerarFactura(){
        Facturador facturador = new Facturador();
        assertEquals(facturador.generarFactura(),150);
    }

    @Test
    public void testGenerarFacturaCon1Llamada(){
        LocalTime horaInicial = LocalTime.of(10, 30, 00);
        LocalTime horaFinal = LocalTime.of(10, 40, 00);

        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioLocal(1, horaInicial, horaFinal);
        Llamada llamada1 = new Llamada(calculadorDiaHorarioLocal);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaLocal(llamada1);

        assertEquals(facturador.generarFactura(),150 + (10 * costoMinuto));
    }

    @Test
    public void testGenerarFacturaConVariasLlamadasLocales(){
        LocalTime horaInicial1 = LocalTime.of(10, 30, 00);
        LocalTime horaFinal1 = LocalTime.of(10, 40, 00);

        //diferencia de 2 horas y medio = 150 minutos
        LocalTime horaInicial2 = LocalTime.of(20, 30, 00);
        LocalTime horaFinal2 = LocalTime.of(23, 00, 00);

        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal1 = new CalculadorDiaHorarioLocal(1, horaInicial1, horaFinal1);
        CalculadorDiaHorarioLocal calculadorDiaHorarioLocal2 = new CalculadorDiaHorarioLocal(6, horaInicial2, horaFinal2);

        Llamada llamada1 = new Llamada(calculadorDiaHorarioLocal1);
        Llamada llamada2 = new Llamada(calculadorDiaHorarioLocal2);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaLocal(llamada1);
        facturador.agregarLlamadaLocal(llamada2);

        assertEquals(facturador.generarFactura(),150 + (10 * costoMinuto) + (150 * costoMinutoDiaNoHabil));
    }

    @Test
    public void testGenerarFacturaCon1LlamadaInternacional(){
        double costoPais = 0.3;
        LocalTime horaInicial = LocalTime.of(10, 30, 00);
        LocalTime horaFinal = LocalTime.of(10, 40, 00);

        CalculadorDiaHorarioNoLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioNoLocal(costoPais, horaInicial, horaFinal);
        Llamada llamada1 = new Llamada(calculadorDiaHorarioLocal);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaNoLocal(llamada1);

        assertEquals(facturador.generarFactura(),150 + (10 * costoPais));
    }

    @Test
    public void testGenerarFacturaCon2LlamadasInternacionales(){
        LocalTime horaInicial = LocalTime.of(10, 30, 00);
        LocalTime horaFinal = LocalTime.of(10, 40, 00);
        LocalTime horaFinal2 = LocalTime.of(11, 30, 00);
        LocalTime horaFinal3 = LocalTime.of(14, 30, 00);

        CalculadorDiaHorarioNoLocal calculador1 = new CalculadorDiaHorarioNoLocal(costoPais, horaInicial, horaFinal);
        CalculadorDiaHorarioNoLocal calculador2 = new CalculadorDiaHorarioNoLocal(0.5, horaInicial, horaFinal2);
        CalculadorDiaHorarioNoLocal calculador3 = new CalculadorDiaHorarioNoLocal(0.6, horaInicial, horaFinal3);

        Llamada llamada1 = new Llamada(calculador1);
        Llamada llamada2 = new Llamada(calculador2);
        Llamada llamada3 = new Llamada(calculador3);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaNoLocal(llamada1);
        facturador.agregarLlamadaNoLocal(llamada2);
        facturador.agregarLlamadaNoLocal(llamada3);

        assertEquals(facturador.generarFactura(),150 + (10 * costoPais) + (0.5 * 60) + (0.6 * 240));
    }

    @Test
    public void testGenerarFacturaCon1LlamadaNacional(){
        double costoLocalidad = 0.6;
        LocalTime horaInicial = LocalTime.of(10, 30, 00);
        LocalTime horaFinal = LocalTime.of(12, 30, 00);

        CalculadorDiaHorarioNoLocal calculadorDiaHorarioLocal = new CalculadorDiaHorarioNoLocal(costoLocalidad, horaInicial, horaFinal);
        Llamada llamada1 = new Llamada(calculadorDiaHorarioLocal);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaNoLocal(llamada1);

        assertEquals(facturador.generarFactura(),150 + (120 * costoLocalidad));
    }

    @Test
    public void testGenerarFacturaCon1LlamadaDeCadaTipo(){
        LocalTime horaInicial = LocalTime.of(10, 00, 00);
        LocalTime horaFinal = LocalTime.of(12, 00, 00);

        LocalTime horaInicial2 = LocalTime.of(8, 00, 00);
        LocalTime horaFinal2 = LocalTime.of(12, 00, 00);

        LocalTime horaInicial3 = LocalTime.of(21, 00, 00);
        LocalTime horaFinal3 = LocalTime.of(01, 00, 00);

        CalculadorDiaHorarioLocal calculadorLocal = new CalculadorDiaHorarioLocal(6, horaInicial, horaFinal);
        CalculadorDiaHorarioNoLocal calculadorNoLocal2 = new CalculadorDiaHorarioNoLocal(costoLocalidad, horaInicial2, horaFinal2);
        CalculadorDiaHorarioNoLocal calculadorNoLocal3 = new CalculadorDiaHorarioNoLocal(costoPais, horaInicial3, horaFinal3);

        Llamada llamada1 = new Llamada(calculadorLocal);
        Llamada llamada2 = new Llamada(calculadorNoLocal2);
        Llamada llamada3 = new Llamada(calculadorNoLocal3);

        Facturador facturador = new Facturador();
        facturador.agregarLlamadaNoLocal(llamada3);

        assertEquals(facturador.generarFactura(),150 + (240 * costoPais));
    }
}
