public class FrecuenciaApariciones {
    private int idFrecuencia;
    private int numApariciones;

    public FrecuenciaApariciones(int var1){
        idFrecuencia=var1;
        numApariciones=1;
    }

    public int getIdFrecuencia() {
        return idFrecuencia;
    }

    public void setIdFrecuencia(int idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
    }

    public int getNumApariciones() {
        return numApariciones;
    }

    public void setNumApariciones(int numApariciones) {
        this.numApariciones = numApariciones;
    }
}
