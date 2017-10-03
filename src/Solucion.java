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
    public void compruebaRestriccion(){
        //TODO: funcion que conprueba las restricciones de cada nodo y comprueba que el asociado no emite ne la misma banda y calcula la penalizacion
    }
}
