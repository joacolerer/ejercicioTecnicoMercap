public class Llamada {

    private ModoConsumo modoConsumo;

    public Llamada(ModoConsumoLocal calculadorDiaHorarioLocal) {
        this.modoConsumo = calculadorDiaHorarioLocal;
    }

    public Llamada(ModoConsumoNoLocal calculadorDiaHorarioNoLocal) {
        this.modoConsumo = calculadorDiaHorarioNoLocal;
    }

    public double calcularConsumo() {
        return modoConsumo.calcularConsumo();
    }
}
