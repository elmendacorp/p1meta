import java.util.Random;

public class BusquedaLocal {
    private Solucion solucionActual;
    private int nodoInicio;
    private int direccion; // 1 derecha, -1 izquierda
    private Random rd;
    public BusquedaLocal(Solucion solucion, int semilla){
        solucionActual=solucion;
        rd= new Random();
        rd.setSeed(semilla);
    }
    public void generaSoluciones(int iteraciones){
        int i=0;
        while(i<iteraciones){

        }
    }
}
