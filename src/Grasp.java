import java.util.Random;

public class Grasp {
    private long time;
    private Solucion solucionActual;
    private Random rd;

    public Grasp(Filemanager datos,int semilla){
        solucionActual= new Solucion();
        rd = new Random();
        rd.setSeed(semilla);
    }

}
