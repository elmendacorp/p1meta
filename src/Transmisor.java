/**
 * Clase que almacena un transmisor asi como el identificador del rango de frecuencias asociado
 */
public class Transmisor {
    private int id;
    private int rango;

    public Transmisor(int _id, int _rango) {
        id = _id;
        rango = _rango;
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
}
