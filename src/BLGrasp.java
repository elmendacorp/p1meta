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
    private int nodosSinCambio=0;
    private int contador = 0;
    int intentosMejora = 0;

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
            int intentos = 0;
            int frecuenciasNodo = datos.getTransmisores().get(actual.getId()).getRango();
            int numFrecuencias = datos.getFrecuencias().get(frecuenciasNodo).tamanio();

            while (intentos < numFrecuencias && contador < iteraciones && nodosSinCambio< solucionActual.getFrecuenciasAsignadas().size()) {
                ++intentos;
                ++contador;
                int nuevaFre = datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().get(Math.abs(rd.nextInt(numFrecuencias)));
                int nuevaPuntuacion = solucionActual.recalcular(datos, actual.getId(), nuevaFre, solucionActual);
                if (solucionActual.getPuntuacion() > nuevaPuntuacion) {
                    intentosMejora = 0;
                    nodosSinCambio=0;
                    solucionActual.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(nuevaFre);
                    solucionActual.setPuntuacion(nuevaPuntuacion);
                    actual.setFrecuencia(nuevaFre);
                    intentos = numFrecuencias;
                } else {
                    ++intentosMejora;
                    ++nodosSinCambio;
                }

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
        System.out.println(solucionActual.getPuntuacion() + " " + time / 1000000 + " ms"+ " Nodos sin cambio: "+nodosSinCambio + " IntentosMejora: "+intentosMejora);
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }
}
