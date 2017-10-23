
import java.util.HashMap;
import java.util.Random;

public class Grasp {
    private float time;
    private Solucion solucionActual;
    private Random rd;
    private HashMap<Integer, CeldaGrasp> solucionTemporal;
    private Solucion solucionLocal;
    private Filemanager data;


    public Grasp(Filemanager datos, int semilla) {
        data = datos;
        solucionActual = new Solucion();
        rd = new Random();
        rd.setSeed(semilla);
        solucionTemporal = new HashMap<>();
        solucionLocal = new Solucion();
    }

    public void generaSolucion() {
        time = System.nanoTime();
        solucionActual.getFrecuenciasAsignadas().clear();
        int nodosAsegurados = 0;
        HashMap<Integer, CosteFrecuencia> frecuenciasProcesadas = new HashMap<>();
        while (nodosAsegurados < data.getTransmisores().size()) {
            //Reinicio del estado
            CeldaGrasp tmp = new CeldaGrasp();
            resetSolucionTemporal();
            solucionLocal.getFrecuenciasAsignadas().clear();
            solucionLocal.setPuntuacion(0);

            //introducimos 10 valores de nodos a la solucion greedy que vamos a computar
            while (solucionTemporal.size() < 10) {
                int rdn = rd.nextInt(data.getTransmisores().size());
                int nodo = (Integer) data.getTransmisores().keySet().toArray()[rdn];
                if (!solucionTemporal.containsKey(nodo)) {
                    int frecNodo = data.getTransmisores().get(nodo).getRango();
                    frecNodo = data.getFrecuencias().get(frecNodo).getFrecuencias().get(rd.nextInt(data.getFrecuencias().get(frecNodo).getFrecuencias().size()));
                    CeldaGrasp nueva = new CeldaGrasp(nodo, frecNodo, 0, 0, 0);
                    solucionTemporal.put(nodo, nueva);
                    solucionLocal.getFrecuenciasAsignadas().put(nodo, new FrecAsignada(nodo, frecNodo));
                }
            }

            //rellenar el resto con la dinamica del primero mejor si se 0 elegimos ese

            for (Transmisor t : data.getTransmisores().values()) {
                if (!solucionLocal.getFrecuenciasAsignadas().containsKey(t.getId())) {
                    frecuenciasProcesadas.clear();
                    int puntos = 0;
                    int nuevaFrecuencia = 0;
                    for (Integer fr : data.getFrecuencias().get(t.getRango()).getFrecuencias()) {
                        nuevaFrecuencia = fr;
                        puntos = puntosFrecuencia(t.getId(), fr);
                        if (puntos == 0) {
                            break;
                        } else if (!frecuenciasProcesadas.containsKey(fr)) {
                            frecuenciasProcesadas.put(fr, new CosteFrecuencia(fr, puntos));
                        }
                        puntos = puntosFrecuencia(t.getId(), fr);

                    }

                    if (puntos == 0) {
                        if (solucionLocal.getFrecuenciasAsignadas().containsKey(t.getId())) {
                            solucionLocal.getFrecuenciasAsignadas().get(t.getId()).setFrecuencia(nuevaFrecuencia);
                            solucionTemporal.get(t.getId()).setFrecuencia(nuevaFrecuencia);
                        } else {
                            solucionLocal.getFrecuenciasAsignadas().put(t.getId(), new FrecAsignada(t.getId(), nuevaFrecuencia));
                            solucionTemporal.put(t.getId(), new CeldaGrasp(t.getId(), nuevaFrecuencia, 0, 0, 0));
                        }
                    } else {
                        int coste = 100;
                        int elegido = 0;
                        for (CosteFrecuencia crt : frecuenciasProcesadas.values()) {
                            if (crt.getCoste() < coste) {
                                coste = crt.getCoste();
                                elegido = crt.getFrecuencia();
                            }
                        }
                        if (solucionLocal.getFrecuenciasAsignadas().containsKey(t.getId())) {
                            solucionLocal.getFrecuenciasAsignadas().get(t.getId()).setFrecuencia(elegido);
                            solucionTemporal.get(t.getId()).setFrecuencia(elegido);
                            solucionTemporal.get(t.getId()).setCoste(coste);
                        } else {
                            solucionLocal.getFrecuenciasAsignadas().put(t.getId(), new FrecAsignada(t.getId(), elegido));
                            solucionTemporal.put(t.getId(), new CeldaGrasp(t.getId(), elegido, coste, 0, 0));
                        }
                    }
                }

            }
            //con esto se calcula el coste verdadero de cada  nodo y no el marginal
            for (FrecAsignada fr : solucionLocal.getFrecuenciasAsignadas().values()) {
                solucionTemporal.get(fr.getId()).setCoste(puntosFrecuencia(fr.getId(), fr.getFrecuencia()));
            }

            for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
                solucionTemporal.remove(fr.getId());
            }

            //asignar posiciones en funcion al coste
            int asignados = 0;
            int posicionRelativa;
            int puntuacion = 0;
            double tope = 0.0;
            while (asignados < solucionTemporal.size()) {
                posicionRelativa = asignados + 1;
                for (CeldaGrasp cd : solucionTemporal.values()) {
                    if (cd.getCoste() == puntuacion) {
                        cd.setPosicion(posicionRelativa);
                        ++asignados;
                        tope += (1.0 / posicionRelativa);
                    }
                }
                ++puntuacion;
            }

            //calculo de la probabilidad en base al sesgo
            double pAcumulada = 0.0;
            for (CeldaGrasp cd : solucionTemporal.values()) {
                double prob = ((1.0 / cd.getPosicion()) / tope);
                pAcumulada += prob;
                cd.setProbabilidad(pAcumulada);
            }
            //buscamos el que tiene la probabilidad elegida en funcion de la acumulada
            double rand = rd.nextDouble();
            double acumulador = 0;
            for (CeldaGrasp cp : solucionTemporal.values()) {
                acumulador += cp.getProbabilidad();
                if (acumulador < rand) {
                } else {
                    tmp = new CeldaGrasp(cp);
                    break;
                }
            }
            //si no forma parte de la solucion final lo añadimos
            if (!solucionActual.getFrecuenciasAsignadas().containsKey(tmp.getId())) {
                solucionActual.getFrecuenciasAsignadas().put(tmp.getId(), new FrecAsignada(tmp.getId(), tmp.getFrecuencia()));
                ++nodosAsegurados;
                //System.out.println(nodosAsegurados+" "+ (System.nanoTime()-time)/1000000);
            }

        }
        time = System.nanoTime() - time;
    }

    public void resetSolucionTemporal() {
        for (CeldaGrasp tr : solucionTemporal.values()) {
            tr.setCoste(0);
            tr.setFrecuencia(0);
            tr.setPosicion(0);
            tr.setProbabilidad(0);
        }
    }

    public int puntosFrecuencia(int posicion, int frecuencia) {
        int puntosOriginal = 0;
        for (Restriccion rs : data.getRestricciones().get(posicion)) {
            if (solucionLocal.getFrecuenciasAsignadas().containsKey(rs.getId_restriccion())) {
                int frecuenciaRestringida = solucionLocal.getFrecuenciasAsignadas().get(rs.getId_restriccion()).getFrecuencia();
                if (Math.abs(frecuencia - frecuenciaRestringida) <= rs.getTolerancia()) {
                    puntosOriginal += rs.getPenalizacion();
                }
            }

        }
        return puntosOriginal;
    }

    public Solucion getSolucion() {
        return solucionActual;
    }

    public float getTime() {
        return time / 1000000;
    }

    /**
     * Funcion para mostrar los resultados
     */
    public void getResultados() {
        solucionActual.calculaRestriccion(data.getRestricciones());
        System.out.println(solucionActual.getPuntuacion() + " " + time / 1000000 + " ms");
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

}
