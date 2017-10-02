public class Transmisor {
    private int id;
    private RangoFrec rango;
    private int frecuencia;

    public Transmisor(int _id, RangoFrec _rango){
        id = _id;
        rango = _rango;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RangoFrec getRango() {
        return rango;
    }

    public void setRango(RangoFrec rango) {
        this.rango = rango;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}
