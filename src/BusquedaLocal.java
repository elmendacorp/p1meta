import java.util.Random;

public class BusquedaLocal {
    private Solucion solucionActual;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;
    public BusquedaLocal(Solucion solucion, int semilla){
        solucionActual=solucion;
        rd= new Random();
        rd.setSeed(semilla);
        nodoInicio= rd.nextInt()%solucionActual.tamanio();
        direccion= rd.nextInt()%2;
    }
    public void generaSoluciones(Filemanager datos, int iteraciones){
        int i=0;
        Solucion solucionTmp=solucionActual;
        while(i<iteraciones){
            FrecAsignada fr= solucionActual.getFrecuenciasAsignadas().get(nodoInicio);
            for(Restriccion rs:datos.getRestricciones().get(fr.getId())){
                int nodo=rs.getId_restriccion();

            }





            if(direccion==1){
                ++nodoInicio;
                if(nodoInicio==solucionActual.tamanio()){nodoInicio=0;}
            }else{
                --nodoInicio;
                if (nodoInicio==-1){nodoInicio=solucionActual.tamanio()-1;}
            }
        }
    }
}
