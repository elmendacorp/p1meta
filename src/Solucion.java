import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion implements Cloneable {
    private int puntuacion=0;
    private HashMap<Integer,FrecAsignada> frecuenciasAsignadas;
    public Solucion(){
        frecuenciasAsignadas= new HashMap<>();
    }

    public void anadeFrecuencia(FrecAsignada var1){
        frecuenciasAsignadas.put(var1.getId(),var1);
    }

    public void compruebaRestriccion(Multimap<Integer,Restriccion> restricciones){
        puntuacion=0;
        for(Restriccion rs:restricciones.values()){
            int tx= frecuenciasAsignadas.get(rs.getId()).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia();
            int tolerancia = Math.abs(tx-rx);
            if(tolerancia< rs.getTolerancia()){
                puntuacion+=rs.getPenalizacion();
            }
        }
    }
    //esta funcion es para evitar el recalcular todo el vector de soluciones para alterar la puntuacion

    public void recalcular(int frecuenciaOriginal, Filemanager datos, int posicion){
        for(Restriccion rs:datos.getRestricciones().get(posicion)){
            int fr1=Math.abs(frecuenciaOriginal-frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia());
            int fr2=Math.abs(frecuenciasAsignadas.get(rs.getId()).getFrecuencia()-frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia());
            System.out.println( frecuenciaOriginal+" "+fr1+" "+fr2+ " tolerancia"+rs.getTolerancia());
            if((fr1<rs.getTolerancia())&&(fr2>rs.getTolerancia())&&fr2>fr1) {
                setPuntuacion(puntuacion - rs.getPenalizacion());
            }
        }

    }

    public Solucion(Solucion var1){
        puntuacion=var1.puntuacion;
        frecuenciasAsignadas= new HashMap<>(var1.frecuenciasAsignadas);
    }

    private void setPuntuacion(int puntos){puntuacion=puntos;}

    public int getPuntuacion() {
        return puntuacion;
    }

    public HashMap<Integer,FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }

    public int tamanio(){
        return frecuenciasAsignadas.size();
    }


}