import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * clase de busqueda local por defecto
 */
public class BusquedaLocal {
    private float time;
    private Solucion solucionActual;
    private LinkedList<FrecAsignada> vectorIteracion;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;

    /**
     * Constructor parametrizado
     *
     * @param solucion Solucion de la que parte el problema
     * @param semilla  Semilla para la aleatorizacion
     */
    public BusquedaLocal(Solucion solucion, int semilla) {
        solucionActual = solucion;
        vectorIteracion = new LinkedList<>();
        actualizaVector();
        rd = new Random();
        rd.setSeed(semilla);
        nodoInicio = Math.abs(rd.nextInt(vectorIteracion.size()));
        direccion = Math.abs(rd.nextInt(2));
    }

    /**
     * @param datos       Conjunto de datos de trabajo
     * @param iteraciones Limite de iteraciones del problema
     */
    public void generaSoluciones(Filemanager datos, int iteraciones) {
        time = System.nanoTime();
        int contador = 0;
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

        while (contador < iteraciones) {
            FrecAsignada actual = it.next();
            int rangoTransmisor = datos.getTransmisores().get(actual.getId()).getRango();
            for(Integer fr:datos.getFrecuencias().get(rangoTransmisor).getFrecuencias()) {
                ++contador;
                if(contador>iteraciones){
                    break;
                }
                int nuevaPuntuacion = solucionActual.recalcular(datos, actual.getId(), fr, solucionActual);
                if (solucionActual.getPuntuacion() > nuevaPuntuacion) {
                    solucionActual.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(fr);
                    solucionActual.setPuntuacion(nuevaPuntuacion);
                    actual.setFrecuencia(fr);
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
     * Inicializacion del vector temporal donde testearemos los cambios
     */
    private void actualizaVector() {
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            vectorIteracion.add(fr);
        }
    }

    /**
     * Funcion para mostrar los resultados
     */
    public void getResultados() {
        System.out.println("Busqueda Local: "+solucionActual.getPuntuacion() + " " + time / 1000000 + " ms");
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }
}
