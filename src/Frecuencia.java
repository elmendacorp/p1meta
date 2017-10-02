import java.util.ArrayList;

public class Frecuencia {
    private int id;
    private static ArrayList<Integer> frecuencias;
    public Frecuencia(int valor){
        id = valor;
        frecuencias= new ArrayList();
    }
    public static void anadeFrecuencia(int valor){
        frecuencias.add(valor);
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Integer> getFrecuencias() {
        return frecuencias;
    }
}
