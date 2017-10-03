import java.util.ArrayList;

/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion {
    private int puntuacion=0;
    private ArrayList<FrecAsignada> frecuenciasAsignadas;

    public Solucion(){
        frecuenciasAsignadas= new ArrayList<FrecAsignada>();
    }

    public void anadeFrecuencia(FrecAsignada var1){
        frecuenciasAsignadas.add(var1);
    }
    public void compruebaRestriccion(ArrayList<Restriccion> restricciones){
        for(Restriccion rs:restricciones){
            int tx= frecuenciasAsignadas.get(rs.getId()-1).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()-1).getFrecuencia();
            int toletancia = Math.abs(tx-rx);
            if(toletancia< rs.getTolerancia()){
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

    public ArrayList<FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }

    public void setFrecuenciasAsignadas(ArrayList<FrecAsignada> frecuenciasAsignadas) {
        this.frecuenciasAsignadas = frecuenciasAsignadas;
    }
}