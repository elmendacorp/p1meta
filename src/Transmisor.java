public class Transmisor {
    private static int id;
    private static int frecuencias;
    public Transmisor(int valor, int frec){
        id = valor;
        frecuencias= frec;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Transmisor.id = id;
    }

    public static int getFrecuencias() {
        return frecuencias;
    }

    public static void setFrecuencias(int frecuencias) {
        Transmisor.frecuencias = frecuencias;
    }
}
