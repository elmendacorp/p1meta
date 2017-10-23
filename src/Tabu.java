import java.util.HashMap;
import java.util.Random;
import java.util.Map;

public class Tabu {

    private Solucion mejorSolucion;
    private Filemanager datos;
    private Map<Integer,CeldaTabu> listaTabu;
    private int frecInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;

    public Tabu(Filemanager data, Solucion greedy, int semilla){
        mejorSolucion = greedy;
        datos = data;
        listaTabu = new HashMap<>();
        rd = new Random();
        rd.setSeed(semilla);
        direccion = Math.abs(rd.nextInt(2));
    }

    public void generaSolucion(){
        int iteraciones = 0;
        int size = datos.getTransmisores().size();
        while(iteraciones < 10000){
            ++iteraciones;

            int idTransmisor = datos.getTransmisores().get(rd.nextInt() % size).getId();
            int rango = datos.getTransmisores().get(idTransmisor).getRango();

            for(Integer fr : datos.getFrecuencias().get(rango).getFrecuencias()){

            }

        }
    }

}
