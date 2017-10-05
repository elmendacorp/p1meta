import com.google.common.collect.Multimap;

import java.util.ArrayList;


/**
 * Esta clase almacena una solucion valida o no para el problema
 */
public class Solucion implements Cloneable {
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

    public void recalcular(int frecuenciaOriginal, Filemanager datos, int posicion){
        int fr1,fr2;
        for(Restriccion rs:datos.getRestricciones().get(posicion+1)){
            fr1=Math.abs(frecuenciaOriginal-frecuenciasAsignadas.get(rs.getId_restriccion()-1).getFrecuencia());
            fr2=Math.abs(frecuenciasAsignadas.get(rs.getId()-1).getFrecuencia()-frecuenciasAsignadas.get(rs.getId_restriccion()-1).getFrecuencia());
            System.out.println( frecuenciaOriginal+" "+fr1+" "+fr2);
            if(fr1<rs.getTolerancia()&&fr2>rs.getTolerancia()){
                setPuntuacion(puntuacion-rs.getPenalizacion());
            }else if(fr1>rs.getTolerancia()&&fr2<rs.getTolerancia()){
                setPuntuacion(puntuacion+rs.getPenalizacion());
            }
        }

    }

    public Solucion(Solucion var1){
        puntuacion=var1.puntuacion;
        frecuenciasAsignadas= new ArrayList<>(var1.frecuenciasAsignadas);
    }

    private void setPuntuacion(int puntos){puntuacion=puntos;}

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