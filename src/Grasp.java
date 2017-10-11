import java.util.ArrayList;
import java.util.Random;

public class Grasp {
    private float time;
    private Solucion solucionActual;
    private Random rd;
    private ArrayList<CeldaGrasp> solucionTemporal;
    private Filemanager data;


    public Grasp(Filemanager datos,int semilla){
        data=datos;
        solucionActual= new Solucion();
        rd = new Random();
        rd.setSeed(semilla);
    }
    public void generaSolucion(){
        int nodosAsegurados=0;
        while(nodosAsegurados<solucionActual.getFrecuenciasAsignadas().size()){
            resetSolucionTemporal();
            //escoger los nodos al azar

            //rellenar el resto con la dinamica del primero mejor si se 0 elegimos ese

            //vector de costes a partir de la solucion propuesta

            //asignar posiciones en funcion al coste

            //calculo de la probabilidad en base al sesgo


            ++nodosAsegurados;
        }
    }
    public void resetSolucionTemporal(){
        solucionTemporal= new ArrayList<>();
        for(Transmisor tr:data.getTransmisores().values()){
            solucionTemporal.add(new CeldaGrasp(tr.getId(),0,0,0,0));
        }
    }

}
