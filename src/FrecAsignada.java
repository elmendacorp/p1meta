public class FrecAsignada {
    private int id;
    private int frecuencia;

    public FrecAsignada(int var1, int var2) {
        //TODO: borrar id puesto que donde se usa esta clase, el integer del hashmap es el mismo
        id = var1;
        frecuencia = var2;
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
