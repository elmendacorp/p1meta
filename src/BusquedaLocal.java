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
        int contador=0;
        while(contador<iteraciones){
            Solucion solucionModificada=solucionActual;
            int intentos=0;
            int frecuenciasNodo= datos.getTransmisores().get(nodoInicio).getRango();
            int numFrecuencias= datos.getFrecuencias().get(frecuenciasNodo).tamanio();
            while(intentos<datos.getFrecuencias().get(frecuenciasNodo).tamanio()&&contador<iteraciones&&solucionModificada.getPuntuacion()>=solucionActual.getPuntuacion()){
                solucionModificada.getFrecuenciasAsignadas().get(nodoInicio).setFrecuencia(datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().get(Math.abs(rd.nextInt()%numFrecuencias)));
                solucionActual.recalcular(solucionModificada,datos.getRestricciones(),nodoInicio);
                ++intentos;
                ++contador;
                System.out.println("Iteracion "+contador+" puntuacion "+solucionModificada.getPuntuacion()+"Intentos: "+intentos);
            }
            if(solucionModificada.getPuntuacion()<solucionActual.getPuntuacion()){
                solucionActual=solucionModificada;
            }

            if(direccion==1){
                ++nodoInicio;
                if(nodoInicio==solucionActual.tamanio()){nodoInicio=0;}
            }else{
                --nodoInicio;
                if (nodoInicio==-1){nodoInicio=solucionActual.tamanio()-1;}
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
