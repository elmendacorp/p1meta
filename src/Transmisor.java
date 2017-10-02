public class Transmisor {
    private int id;
    private int rango;
    private int frecuencia;

    public Transmisor(int _id, int _rango){
        id = _id;
        rango = _rango;
        frecuencia = -1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
