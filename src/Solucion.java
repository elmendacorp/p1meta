import com.google.common.collect.Multimap;

import java.util.ArrayList;


/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion {
    private int puntuacion=0;
    private ArrayList<FrecAsignada> frecuenciasAsignadas;
    public Solucion(){
        frecuenciasAsignadas= new ArrayList<>();
    }

    public void anadeFrecuencia(FrecAsignada var1){
        frecuenciasAsignadas.add(var1);
    }

    public void compruebaRestriccion(Multimap<Integer,Restriccion> restricciones){
        puntuacion=0;
        for(Restriccion rs:restricciones.values()){
            int tx= frecuenciasAsignadas.get(rs.getId()-1).getFrecuencia();
            int rx = frecuenciasAsignadas.get(rs.getId_restriccion()-1).getFrecuencia();
            int tolerancia = Math.abs(tx-rx);
            if(tolerancia< rs.getTolerancia()){
                puntuacion+=rs.getPenalizacion();
            }
        }
    }
    //esta funcion es para evitar el recalcular todo el vector de soluciones para alterar la puntuacion

    public void recalcular(Solucion modificado, Multimap<Integer,Restriccion> restricciones, int posicion){
        int fr1,fr2;
        fr1=frecuenciasAsignadas.get(posicion).getFrecuencia();
        fr2=modificado.frecuenciasAsignadas.get(posicion).getFrecuencia();
        for(Restriccion rs:restricciones.get(posicion)){
            int fr3=frecuenciasAsignadas.get(rs.getId_restriccion()).getFrecuencia();
            if(Math.abs(fr1-fr3)>rs.getTolerancia()&&Math.abs(fr2-fr3)<=rs.getTolerancia()){
                modificado.setPuntuacion((-1*rs.getPenalizacion()));
            }
        }

    }

    private void setPuntuacion(int puntos){puntuacion+=puntos;}

    public int getPuntuacion() {
        return puntuacion;
    }

    public ArrayList<FrecAsignada> getFrecuenciasAsignadas() {
        return frecuenciasAsignadas;
    }

    public int tamanio(){
        return frecuenciasAsignadas.size();
    }

}