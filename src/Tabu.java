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
        int size = datos.getTransmisores().size();
        int iteraciones = 0;
        int iterSinMejora = 0;
        int rango = 0;
        int posFrRandom = 0;
        ArrayList<CosteFrecuencia> listaFrecuencias = new ArrayList<>();

        while (iteraciones < maxIteraciones) {

            //Comprueba que no has llegado al estancamiento
            // si has llegado realiza la reinicializacion copiando lo que tengas en la estuctura de celta tabu
            if (listItera.hasNext()) {
                listItera.next();
            } else {
                listItera = modificada.getFrecuenciasAsignadas().values().iterator();
            }
            //Comprueba que no has llegado al estancamiento

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

            ++iterSinMejora;
            if (!listItera.hasNext()) {
                listItera = modificada.getFrecuenciasAsignadas().values().iterator();
            }

            FrecAsignada actual = listItera.next();

            direccion = rd.nextInt(2);
            rango = datos.getTransmisores().get(actual.getId()).getRango();
            posFrRandom = rd.nextInt(datos.getFrecuencias().get(rango).getFrecuencias().size());
            listaFrecuencias = new ArrayList<>();


            // calcula las 20 frecuencias asociadas a un transmisor
            // calcula el coste por cada frecuencia
            listaFrecuencias = calculaVecinos(rango, posFrRandom, actual.getId());
            iteraciones += listaFrecuencias.size();

            // coge la mejor
            CosteFrecuencia mejorFrecuencia = mejorCosteFrecuencia(listaFrecuencias);

            //  Decide si vas a meterla en las soluciones
            // añade la frecuencia a la estructuda de celtatabu para mantener los transmisores con las mejores frecuencias y sus apariciones
            if (listaTabu.containsKey(actual.getId())) {
                if (listaTabu.get(actual.getId()).getIdTransmisor() == mejorFrecuencia.getFrecuencia()) {
                    listaTabu.get(actual.getId()).aniadirFrecuencia(mejorFrecuencia.getFrecuencia());
                } else {
                    modificada.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(mejorFrecuencia.getFrecuencia());
                    modificada.setPuntuacion(mejorFrecuencia.getCoste());
                }
            } else {
                CeldaTabu cd = new CeldaTabu(actual.getId());
                cd.aniadirFrecuencia(mejorFrecuencia.getFrecuencia());

                listaTabu.put(actual.getId(), cd);

                modificada.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(mejorFrecuencia.getFrecuencia());
                modificada.setPuntuacion(mejorFrecuencia.getCoste());

            }

            if (modificada.getPuntuacion() < mejorSolucion.getPuntuacion()) {
                mejorSolucion = new Solucion(modificada);
            }

        }
        time = System.nanoTime() - time;
    }

    private ArrayList<CosteFrecuencia> calculaVecinos(int rango, int posInicial, int idTrx) {
        ArrayList<CosteFrecuencia> finalList = new ArrayList<>();
        ArrayList<Integer> frecuencias = datos.getFrecuencias().get(rango).getFrecuencias();

        if (frecuencias.size() <= 20) {
            for (Integer fr : frecuencias) {
                int puntuacion = modificada.recalcular(datos, idTrx, fr, modificada);
                finalList.add(new CosteFrecuencia(fr, puntuacion));
            }

        } else {

            if (direccion == 0) {
                for (int i = posInicial; finalList.size() < 20; --i) {
                    if (i == 0) {
                        i = frecuencias.size() - 1;
                    }
                    int puntuacion = modificada.recalcular(datos, idTrx, frecuencias.get(i), modificada);
                    finalList.add(new CosteFrecuencia(frecuencias.get(i), puntuacion));
                }
            } else {
                for (int i = posInicial; finalList.size() < 20; ++i) {
                    if (i == frecuencias.size() - 1) {
                        i = 0;
                    }
                    int puntuacion = modificada.recalcular(datos, idTrx, frecuencias.get(i), modificada);
                    finalList.add(new CosteFrecuencia(frecuencias.get(i), puntuacion));

                }
            }
        }
        return finalList;
    }

    private CosteFrecuencia mejorCosteFrecuencia(ArrayList<CosteFrecuencia> listaFr) {
        int mejorFr = 0;
        int mejorCoste = 999999;

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
     * Funcion para mostrar los resultados de la ejecucion
     */
    public void getResultados() {
        System.out.println("Tabu: "+mejorSolucion.getPuntuacion() + " " + time / 1000000 + " ms");
        for (FrecAsignada fr : mejorSolucion.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

}
