import java.util.HashMap;
import java.util.Random;

public class Grasp {
    private float time;
    private Solucion solucionActual;
    private Random rd;
    private HashMap<Integer,CeldaGrasp> solucionTemporal;
    private Filemanager data;


    public Grasp(Filemanager datos,int semilla){
        data=datos;
        solucionActual= new Solucion();
        rd = new Random();
        rd.setSeed(semilla);
        solucionTemporal= new HashMap<Integer, CeldaGrasp>();
    }
    public void generaSolucion(){
        int nodosAsegurados=0;
        while(nodosAsegurados<solucionActual.getFrecuenciasAsignadas().size()){
            resetSolucionTemporal();
            //introducimos 10 valores de nodos a la solucion greedy que vamos a computar
            while(solucionTemporal.size()<10){
                int rdn=rd.nextInt(data.getTransmisores().size());
                int nodo= (Integer) data.getTransmisores().keySet().toArray()[rdn];
                if(!solucionTemporal.containsKey(nodo)){
                    int frecNodo= data.getTransmisores().get(nodo).getRango();
                    frecNodo=data.getFrecuencias().get(frecNodo).getFrecuencias().get(rd.nextInt(data.getFrecuencias().get(frecNodo).getFrecuencias().size()));
                    CeldaGrasp nueva = new CeldaGrasp(nodo,frecNodo,0,0,0);
                    solucionTemporal.put(nodo,nueva);
                }
            }
            //rellenar el resto con la dinamica del primero mejor si se 0 elegimos ese
            for(Transmisor t:data.getTransmisores().values()){

            }
            //vector de costes a partir de la solucion propuesta

            //asignar posiciones en funcion al coste

            //calculo de la probabilidad en base al sesgo


            ++nodosAsegurados;
        }
    }
    public void resetSolucionTemporal(){
        solucionTemporal.clear();
        for(Transmisor tr:data.getTransmisores().values()){
            solucionTemporal.put(tr.getId(),new CeldaGrasp(tr.getId(),0,0,0,0));
        }
    }

}
