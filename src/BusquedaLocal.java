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
            int numFrecuencias= datos.getFrecuencias().get(frecuenciasNodo).tamanio();
            int frecuenciaActual= solucionActual.getFrecuenciasAsignadas().get(nodoInicio).getFrecuencia();
            int indiceFrecuencia = datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().indexOf(frecuenciaActual);
            while(solucionInicial.getPuntuacion()>=solucionActual.getPuntuacion()&&i<iteraciones){
                if(direccion==1) {
                    ++indiceFrecuencia;
                    if (indiceFrecuencia == numFrecuencias) { indiceFrecuencia=0;
                    }
                }else{
                    --indiceFrecuencia;
                    if(indiceFrecuencia<0){indiceFrecuencia=numFrecuencias-1;}
                }
                frecuenciaActual= datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().get(indiceFrecuencia);
                solucionInicial.getFrecuenciasAsignadas().get(nodoInicio).setFrecuencia(frecuenciaActual);
                solucionInicial.compruebaRestriccion(datos.getRestricciones());
                ++i;
            }

            if(direccion==1){
                ++nodoInicio;
                if(nodoInicio==solucionActual.tamanio()){nodoInicio=0;}
            }else{
                --nodoInicio;
                if (nodoInicio==-1){nodoInicio=solucionActual.tamanio()-1;}
            }
            solucionActual=solucionInicial;
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
