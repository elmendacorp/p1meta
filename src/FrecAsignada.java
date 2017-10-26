/**
 * Clase para representar la asignacion de un transmisor y una frecuencia de su espectro
 */
public class FrecAsignada {
    private int id;
    private int frecuencia;

    public FrecAsignada(int trxId, int fr) {
        id = trxId;
        frecuencia = fr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
