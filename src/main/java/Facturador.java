import java.util.ArrayList;

public class Facturador {
    private final ArrayList<Llamada> llamadasNoLocales;
    private ArrayList<Llamada> llamadasLocales;
    //Asumo que el abono mensual basico es el mismo para cualquier factura
    private int abonoMensualBasico = 150;

    public Facturador(){
        llamadasLocales = new ArrayList<>();
        llamadasNoLocales = new ArrayList<>();
    }

    public double generarFactura( ) {
        double consumoLocal = consumoLocal();
        double consumoNoLocal = consumoNoLocal();

        mostrarPorPantalla(consumoLocal, consumoNoLocal);

        return abonoMensualBasico + consumoLocal + consumoNoLocal;
    }

    private double consumoNoLocal() {
        double consumo = 0;
        for (Llamada Llamada:llamadasNoLocales) {
            consumo += Llamada.calcularConsumo();
        }
        return consumo;
    }

    private double consumoLocal() {
        double consumo = 0;
        for (Llamada Llamada:llamadasLocales) {
            consumo += Llamada.calcularConsumo();
        }
        return consumo;
    }

    public void agregarLlamadaLocal(Llamada llamada) {
        llamadasLocales.add(llamada);
    }

    public void agregarLlamadaNoLocal(Llamada llamada) {
        llamadasNoLocales.add(llamada);
    }

    //Esto es solo para mostrar en pantalla para este ejercicio
    private void mostrarPorPantalla(double consumoLocal, double consumoNoLocal) {
        System.out.println("Factura Mensual:");
        System.out.println("Abono Mensual Basico: " + abonoMensualBasico);
        System.out.println("Consumo de llamadas locales: " + consumoLocal);
        System.out.println("Consumo de llamadas Nacionales e Internacionales: " + consumoNoLocal);
    }
}
