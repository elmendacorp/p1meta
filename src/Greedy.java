import java.util.Random;

public class Greedy {
    private Solucion solucion;
    static Random aleatorio;
    private long time;
    public Greedy(Filemanager conjuntoDatos, int semilla){
        time= System.currentTimeMillis();
        solucion= new Solucion();
        FrecAsignada fr;
        aleatorio= new Random();
        aleatorio.setSeed(semilla);
        for(Transmisor tx:conjuntoDatos.getTransmisores()){
            int size = conjuntoDatos.getFrecuencias().get(tx.getRango()).tamanio();
            int frecuenciaAsignada = conjuntoDatos.getFrecuencias().get(tx.getRango()).getFrecuencias().get(Math.abs(aleatorio.nextInt()%size));
            fr= new FrecAsignada(tx.getId(),frecuenciaAsignada);
            solucion.anadeFrecuencia(fr);
        }
        time=System.currentTimeMillis()-time;
    }

    public void getResultados() {
        System.out.println("Solucion Greedy "+ solucion.getPuntuacion()+" Tiempo ejecucion "+time+" ms");
        for(FrecAsignada fr:solucion.getFrecuenciasAsignadas().values()){
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

    public Solucion getSolucion() {
        return solucion;
    }
}
