import java.util.Random;

/**
 * Generador de soluciones tipo GREEDY
 */
public class Greedy {
    private Solucion solucion;
    static Random aleatorio;
    private float time;

    /**
     * Constructor parametrizado
     *
     * @param conjuntoDatos conjunto de datos de trabajo
     * @param semilla       Semilla para la aleatorizacion
     */
    public Greedy(Filemanager conjuntoDatos, int semilla) {
        time = System.nanoTime();
        solucion = new Solucion();
        FrecAsignada fr;
        aleatorio = new Random();
        aleatorio.setSeed(semilla);
        for (Transmisor tx : conjuntoDatos.getTransmisores().values()) {
            int size = conjuntoDatos.getFrecuencias().get(tx.getRango()).tamanio();
            int frecuenciaAsignada = conjuntoDatos.getFrecuencias().get(tx.getRango()).getFrecuencias().get(Math.abs(aleatorio.nextInt() % size));
            fr = new FrecAsignada(tx.getId(), frecuenciaAsignada);
            solucion.anadeFrecuencia(fr);
        }
        time = System.nanoTime() - time;
    }

    /**
     * Funcion para mostrar los resultados de la ejecucion
     */
    public void getResultados() {
        System.out.println("Greedy: "+solucion.getPuntuacion() + " " + time / 1000000 + " ms");
        for (FrecAsignada fr : solucion.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

    public Solucion getSolucion() {
        return solucion;
    }
}
