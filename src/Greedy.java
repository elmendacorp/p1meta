import java.util.Random;

public class Greedy {
    private Solucion solucion;
    static Random aleatorio;
    public Greedy(Filemanager conjuntoDatos, int semilla){
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
    }

    public void getResultados() {
        System.out.println("Solucion Greedy "+ solucion.getPuntuacion());
        for(FrecAsignada fr:solucion.getFrecuenciasAsignadas()){
            System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }

    public Solucion getSolucion() {
        return solucion;
    }
}
