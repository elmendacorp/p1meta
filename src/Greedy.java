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
            int seed= Math.abs(aleatorio.nextInt());
            int size = conjuntoDatos.getFrecuencias().get(tx.getRango()).getFrecuencias().size();
            int indice = conjuntoDatos.getFrecuencias().get(tx.getRango()).getFrecuencias().get(seed%size);
            fr= new FrecAsignada(tx.getId(),indice);
            solucion.anadeFrecuencia(fr);
        }
        time=System.currentTimeMillis()-time;
    }

    public void getResultados() {
        System.out.println("Solucion Greedy "+ solucion.getPuntuacion()+" Tiempo ejecucion "+time+" ms");
        for(FrecAsignada fr:solucion.getFrecuenciasAsignadas().values()){
            System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

    public Solucion getSolucion() {
        return solucion;
    }
}
