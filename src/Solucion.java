import java.util.ArrayList;
import java.util.Map;

/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion {
    private int puntuacion=0;
    private Map<Integer,FrecAsignada> frecuenciasAsignadas;

    public void anadeFrecuencia(FrecAsignada var1){
        frecuenciasAsignadas.put(var1.getId(),var1);
    }
    public void compruebaRestriccion(Map<Integer,Restriccion> restricciones){
        for(Restriccion rs:restricciones.values()){
            int tx= frecuenciasAsignadas.get(rs.getId()).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia();
            int tolerancia = Math.abs(tx-rx);
            if(tolerancia< rs.getTolerancia()){
                puntuacion+=rs.getPenalizacion();
            }
        }
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Map<Integer,FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }

}