import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class Tabu {

    private Solucion mejorSolucion;
    private Solucion modificada;
    private Filemanager datos;
    private Map<Integer, CeldaTabu> listaTabu;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;
    private float time;

    public Tabu(Filemanager data, Solucion greedy, int semilla) {
        mejorSolucion = new Solucion(greedy);
        modificada = new Solucion(greedy);
        datos = data;
        listaTabu = new HashMap<>();
        rd = new Random();
        rd.setSeed(semilla);
        direccion = rd.nextInt(2);
        time = System.nanoTime();
    }

    public void generaSolucion(int maxIteraciones, int maxSinMejora) {
        time = System.nanoTime();
        Iterator<FrecAsignada> listItera = modificada.getFrecuenciasAsignadas().values().iterator();
        direccion = rd.nextInt(2);
        int iteraciones = 0;
        int iterSinMejora = 0;
        int rango;
        int posFrRandom;
        ArrayList<CosteFrecuencia> listaFrecuencias = new ArrayList<>();

        while (iteraciones < maxIteraciones) {

            // Comprueba que no has llegado al estancamiento
            // Si has llegado realiza la reinicializacion copiando lo que tengas en la estuctura de celta tabu
            if (!listItera.hasNext()) {
                listItera = modificada.getFrecuenciasAsignadas().values().iterator();
            }

            // Comprueba que no has llegado al estancamiento
            // Si llega al estancamiento, nuestra solucion ahora estara compuesta por las frecuencias mas frecuentes
            if (iterSinMejora >= maxSinMejora) {
                iterSinMejora = 0;
                for (FrecAsignada fr : modificada.getFrecuenciasAsignadas().values()) {
                    if (listaTabu.containsKey(fr.getId())) {
                        int mejor = 0;
                        int idMejor = 0;
                        for (FrecuenciaApariciones fa : listaTabu.get(fr.getId()).getFrecuenciasNodo().values()) {
                            if (fa.getNumApariciones() > mejor) {
                                mejor = fa.getNumApariciones();
                                idMejor = fa.getIdFrecuencia();
                            }
                        }
                        modificada.getFrecuenciasAsignadas().get(fr.getId()).setFrecuencia(idMejor);
                    }
                }
            }
            FrecAsignada actual = listItera.next();

            direccion = rd.nextInt(2);
            rango = datos.getTransmisores().get(actual.getId()).getRango();
            posFrRandom = rd.nextInt(datos.getFrecuencias().get(rango).getFrecuencias().size());

            // Calcula las 20 frecuencias asociadas a un transmisor
            // Calcula el coste por cada frecuencia
            listaFrecuencias = calculaVecinos(rango, posFrRandom, actual.getId());
            iteraciones += listaFrecuencias.size();

            // Coge la mejor
            CosteFrecuencia mejorFrecuencia = mejorCosteFrecuencia(listaFrecuencias);

            // Decide si vas a meterla en las soluciones
            // Añade la frecuencia a la estructura de celtatabu para mantener los transmisores con las mejores frecuencias y sus apariciones
            if (listaTabu.containsKey(actual.getId())) {
                listaTabu.get(actual.getId()).aniadirFrecuencia(mejorFrecuencia.getFrecuencia());
                modificada.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(mejorFrecuencia.getFrecuencia());
                modificada.calculaRestriccion(datos.getRestricciones());
            } else {
                CeldaTabu cd = new CeldaTabu(actual.getId());
                cd.aniadirFrecuencia(mejorFrecuencia.getFrecuencia());
                listaTabu.put(actual.getId(), cd);
                modificada.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(mejorFrecuencia.getFrecuencia());
                modificada.calculaRestriccion(datos.getRestricciones());
            }

            // Si al movernos a este vecino, mejoramos la solucion global, ahora esa sera nuestra solucion global
            if (modificada.getPuntuacion() < mejorSolucion.getPuntuacion()) {
                mejorSolucion = new Solucion(modificada);
            } else {
                iterSinMejora += listaFrecuencias.size();
            }
        }
        time = System.nanoTime() - time;
    }

    /**
     * Funcion para devolver la frecuencia con menor coste en la solucion actual.
     *
     * @param rango      ArrayList que contiene las frecuencias con los costes a recorrer.
     * @param posInicial Posicion aleatoria de una frecuencia por la que se empezar
     * @param idTrx      Id del transmisor actual
     * @return ArrayList que contiene los vecinos y sus puntuaciones
     */
    private ArrayList<CosteFrecuencia> calculaVecinos(int rango, int posInicial, int idTrx) {
        ArrayList<CosteFrecuencia> finalList = new ArrayList<>();
        ArrayList<Integer> frecuencias = datos.getFrecuencias().get(rango).getFrecuencias();

        if (frecuencias.size() <= 20) {
            for (Integer fr : frecuencias) {
                int puntuacion = modificada.recalcularTabu(datos, idTrx, fr, modificada);
                finalList.add(new CosteFrecuencia(fr, puntuacion));
            }

        } else {

            if (direccion == 0) {
                for (int i = posInicial; finalList.size() < 20; --i) {
                    if (i == 0) {
                        i = frecuencias.size() - 1;
                    }
                    int puntuacion = modificada.recalcularTabu(datos, idTrx, frecuencias.get(i), modificada);
                    finalList.add(new CosteFrecuencia(frecuencias.get(i), puntuacion));
                }
            } else {
                for (int i = posInicial; finalList.size() < 20; ++i) {
                    if (i == frecuencias.size() - 1) {
                        i = 0;
                    }
                    int puntuacion = modificada.recalcularTabu(datos, idTrx, frecuencias.get(i), modificada);
                    finalList.add(new CosteFrecuencia(frecuencias.get(i), puntuacion));

                }
            }
        }
        return finalList;
    }

    /**
     * Funcion para devolver la frecuencia con menor coste en la solucion actual.
     *
     * @param listaFr ArrayList que contiene las frecuencias con los costes a recorrer.
     * @return Un objeto CosteFrecuencia que contiene la frecuencia con la mejor puntuacion.
     */
    private CosteFrecuencia mejorCosteFrecuencia(ArrayList<CosteFrecuencia> listaFr) {
        int mejorFr = listaFr.get(0).getFrecuencia();
        int mejorCoste = listaFr.get(0).getCoste();

        for (CosteFrecuencia cf : listaFr) {
            if (cf.getCoste() < mejorCoste) {
                mejorFr = cf.getFrecuencia();
                mejorCoste = cf.getCoste();
            }
        }
        CosteFrecuencia cf = new CosteFrecuencia(mejorFr, mejorCoste);
        return cf;
    }

    /**
     * Funcion para mostrar los resultados de la ejecucion con los tiempos.
     */
    public void getResultados() {
        System.out.println("Tabu: " + mejorSolucion.getPuntuacion() + " " + time / 1000000 + " ms");
        for (FrecAsignada fr : mejorSolucion.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }


}
