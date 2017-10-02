import java.util.ArrayList;

public class RangoFrec {
    private int id;
    private ArrayList<Integer> frecuencias;

    public RangoFrec(int _id) {
        id = _id;
        frecuencias = new ArrayList<>();
    }

    public void aniadeFrecuencia(int valor) {
        frecuencias.add(valor);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getFrecuencias() {
        return frecuencias;
    }
}
