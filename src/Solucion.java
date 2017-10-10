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

    public void compruebaRestriccion(Multimap<Integer, Restriccion> restricciones) {
        puntuacion = 0;
        for (Restriccion rs : restricciones.values()) {
            int tx = frecuenciasAsignadas.get(rs.getId()).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia();
            int tolerancia = Math.abs(tx - rx);
            if (tolerancia < rs.getTolerancia()) {
                puntuacion += rs.getPenalizacion();
            }
        }
    }
    //calcula la puntuacion a partir de un nodo y una solucion inicial

    public int recalcular(Filemanager datos, int posicion, int frecuencia, Solucion estadoOriginal) {

        return 0;
    }

    public Solucion(Solucion var1) {
        puntuacion = var1.puntuacion;
        frecuenciasAsignadas = new HashMap<>(var1.frecuenciasAsignadas);
    }

    private void setPuntuacion(int puntos) {
        puntuacion = puntos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public HashMap<Integer, FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }

    public int tamanio() {
        return frecuenciasAsignadas.size();
    }


}