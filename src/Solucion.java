import com.google.common.collect.Multimap;

import java.util.HashMap;


/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion implements Cloneable {
    private int puntuacion = 0;
    private HashMap<Integer, FrecAsignada> frecuenciasAsignadas;

    public Solucion() {
        frecuenciasAsignadas = new HashMap<>();
    }

    public void anadeFrecuencia(FrecAsignada var1) {
        frecuenciasAsignadas.put(var1.getId(), var1);
    }

    /**
     * calcula la puntuacion de una solucion teniendo en cuenta todos los valores
     *
     * @param restricciones
     */
    public void calculaRestriccion(Multimap<Integer, Restriccion> restricciones) {
        puntuacion = 0;
        for (Restriccion rs : restricciones.values()) {
            int tx = frecuenciasAsignadas.get(rs.getId()).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia();
            int tolerancia = Math.abs(tx - rx);
            if (tolerancia < rs.getTolerancia()) {
                puntuacion += rs.getPenalizacion();
            }
        }
        puntuacion = puntuacion / 2;
    }
    //calcula la puntuacion a partir de un nodo y una solucion inicial
    //tener en cuenta que la puntuacion esta doble

    /**
     * calcula la puntuacion marginal de un transmisor en concreto
     *
     * @param datos
     * @param posicion
     * @param frecuencia
     * @param estadoOriginal
     * @return
     */
    public int recalcular(Filemanager datos, int posicion, int frecuencia, Solucion estadoOriginal) {
        int puntosOriginal = 0;
        int puntosModificado = 0;
        int frecuenciaOriginal = estadoOriginal.frecuenciasAsignadas.get(posicion).getFrecuencia();
        for (Restriccion rs : datos.getRestricciones().get(posicion)) {
            int frecuenciaRestringida = estadoOriginal.getFrecuenciasAsignadas().get(rs.getId_restriccion()).getFrecuencia();
            if (Math.abs(frecuencia - frecuenciaRestringida) <= rs.getTolerancia()) {
                puntosOriginal += rs.getPenalizacion();
            }
            if (Math.abs(frecuenciaOriginal - frecuenciaRestringida) <= rs.getTolerancia()) {
                puntosModificado += rs.getPenalizacion();
            }
        }
        //System.out.println("Posicion " + posicion+" real "+puntosOriginal+" Modificado "+puntosModificado);

        if (puntosModificado < puntosOriginal) {
            return estadoOriginal.getPuntuacion() - ((puntosOriginal - puntosModificado) / 2);
        } else {
            return estadoOriginal.getPuntuacion();
        }
    }

    /**
     * Funcion que en base a una solucion nos da los puntos de una frecuencia asociada a su nodo
     * @param datos
     * @param posicion
     * @param frecuencia
     * @param estadoOriginal
     * @return
     */
    public int puntosFrecuencia(Filemanager datos, int posicion, int frecuencia, Solucion estadoOriginal) {
        int puntosOriginal = 0;
        for (Restriccion rs : datos.getRestricciones().get(posicion)) {
            int frecuenciaRestringida = estadoOriginal.getFrecuenciasAsignadas().get(rs.getId_restriccion()).getFrecuencia();
            if (Math.abs(frecuencia - frecuenciaRestringida) <= rs.getTolerancia()) {
                puntosOriginal += rs.getPenalizacion();
            }
        }
        return puntosOriginal;
    }

    public Solucion(Solucion original){
        frecuenciasAsignadas= new HashMap<>();
        for(FrecAsignada fr:original.frecuenciasAsignadas.values()){
            frecuenciasAsignadas.put(fr.getId(),new FrecAsignada(fr.getId(),fr.getFrecuencia()));
        }
        puntuacion= original.getPuntuacion();
    }

    public void setPuntuacion(int puntos) {
        puntuacion = puntos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public HashMap<Integer, FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }


}