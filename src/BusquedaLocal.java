import java.util.Random;

public class BusquedaLocal {
    private long time;
    private Solucion solucionActual;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;
    public BusquedaLocal(Solucion solucion, int semilla){
        solucionActual=solucion;
        solucionActual.getPuntuacion();
        rd= new Random();
        rd.setSeed(semilla);
        nodoInicio= Math.abs(rd.nextInt()%solucionActual.tamanio());
        direccion= Math.abs(rd.nextInt()%2);
    }
    public void generaSoluciones(Filemanager datos, int iteraciones){
        time= System.currentTimeMillis();
        int i=0;
        Solucion solucionInicial=solucionActual;
        while(i<iteraciones){
            int frecuenciasNodo= datos.getTransmisores().get(nodoInicio).getRango();
            for(int j:datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias()){
                    solucionInicial.getFrecuenciasAsignadas().get(nodoInicio).setFrecuencia(j);
                    ++i;
                }


            if(direccion==1){
                ++nodoInicio;
                if(nodoInicio==solucionActual.tamanio()){nodoInicio=0;}
            }else{
                --nodoInicio;
                if (nodoInicio==-1){nodoInicio=solucionActual.tamanio()-1;}
            }
            if(solucionInicial.getPuntuacion()<solucionActual.getPuntuacion()){
                solucionActual=solucionInicial;
            }
        }
        time=System.currentTimeMillis()-time;
    }

    public Solucion getSolucionActual() {
        return solucionActual;
    }
    public void getResultados() {
        System.out.println("Solucion Greedy "+ solucionActual.getPuntuacion()+" Tiempo ejecucion "+time+" ms");
        for(FrecAsignada fr:solucionActual.getFrecuenciasAsignadas().values()){
            System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }
}
