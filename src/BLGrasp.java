import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Clase para la resolucion mediante factorizacion y exploracion lineal de la busqueda local
 */
public class BLGrasp {
    private float time;
    private Solucion solucionActual;
    private LinkedList<FrecAsignada> vectorIteracion;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;
    private static int nodosSinCambio;
    private static int intentosMejora;
    private static int contador;

    /**
     * Constructor parametrizado
     *
     * @param solucion Solucion sobre la que se va a aplicar BL
     * @param semilla  Semilla para la aleatorizacion
     */
    public BLGrasp(Solucion solucion, int semilla) {
        solucionActual = solucion;
        vectorIteracion = new LinkedList<>();
        actualizaVector();
        rd = new Random();
        rd.setSeed(semilla);
        nodoInicio = Math.abs(rd.nextInt(vectorIteracion.size()));
        direccion = Math.abs(rd.nextInt(2));
        nodosSinCambio = 0;
        intentosMejora = 0;
        contador = 0;
    }

    /**
     * Busqueda local
     *
     * @param datos       conjunto de datos a usar
     * @param iteraciones numero de iteraciones maximo
     * @param limite      numero de intentos maximo sin mejora
     */
    public void generaSoluciones(Filemanager datos, int iteraciones, int limite) {
        time = System.nanoTime();
        intentosMejora = 0;
        nodosSinCambio = 0;
        Iterator<FrecAsignada> it;
        if (direccion == 0) {
            it = vectorIteracion.descendingIterator();
            for (int i = vectorIteracion.size(); i > nodoInicio; --i) {
                if (it.hasNext()) {
                    it.next();
                }
            }
        } else {
            it = vectorIteracion.iterator();
            for (int i = 0; i < nodoInicio; ++i) {
                if (it.hasNext()) {
                    it.next();
                }
            }
        }

        while (contador < iteraciones && intentosMejora < limite && nodosSinCambio < solucionActual.getFrecuenciasAsignadas().size()) {
            FrecAsignada actual = it.next();
            int frecuenciasNodo = datos.getTransmisores().get(actual.getId()).getRango();
            for (Integer fr : datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias()) {
                ++intentosMejora;
                if (contador > iteraciones || intentosMejora>limite) {
                    break;
                }
                int nuevaPuntuacion = solucionActual.recalcular(datos, actual.getId(), fr, solucionActual);
                if (solucionActual.getPuntuacion() > nuevaPuntuacion) {
                    solucionActual.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(fr);
                    solucionActual.setPuntuacion(nuevaPuntuacion);
                    actual.setFrecuencia(fr);
                }
                ++contador;
            }

            if (direccion == 0) {
                if (!it.hasNext()) {
                    it = vectorIteracion.descendingIterator();
                }
            } else if (direccion == 1) {
                if (!it.hasNext()) {
                    it = vectorIteracion.iterator();
                }
            }
        }
        time = System.nanoTime() - time;
    }

    /**
     * Metodo para reinicializar el vector temporal de iteracion
     */
    private void actualizaVector() {
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            vectorIteracion.add(fr);
        }
    }

    /**
     * Metodo para devolver los resultados
     */
    public void getResultados() {
        System.out.println(solucionActual.getPuntuacion() + " " + time / 1000000 + " ms" + " IntentosMejora: " + contador);
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

    public float getTime() {
        return time / 1000000;
    }

    public int getPuntuacion() {
        return solucionActual.getPuntuacion();
    }

    public int iteracionesConsumidas() {
        return contador;
    }
}
