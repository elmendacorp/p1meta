import java.util.ArrayList;

public class RangoFrec {
    private int id;
    private ArrayList<Integer> frecuencias;

    public RangoFrec(int _id, ArrayList<Integer> _frecuencias) {
        id = _id;
        frecuencias = new ArrayList<>(_frecuencias);
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
