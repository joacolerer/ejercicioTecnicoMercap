public class Llamada {

    private CalculadorDiaHorario calculadorDiaHorario;

    public Llamada(CalculadorDiaHorarioLocal calculadorDiaHorarioLocal) {
        this.calculadorDiaHorario = calculadorDiaHorarioLocal;
    }

    public Llamada(CalculadorDiaHorarioNoLocal calculadorDiaHorarioNoLocal) {
        this.calculadorDiaHorario = calculadorDiaHorarioNoLocal;
    }

    public double calcularConsumo() {
        return calculadorDiaHorario.calcularConsumo();
    }
}
