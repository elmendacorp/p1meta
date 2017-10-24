import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tabu {

    private Solucion mejorSolucion;
    private Solucion modificada;
    private Filemanager datos;
    private Map<Integer, CeldaTabu> listaTabu;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;

    public Tabu(Filemanager data, Solucion greedy, int semilla) {
        mejorSolucion = new Solucion(greedy);
        modificada = new Solucion(greedy);
        datos = data;
        listaTabu = new HashMap<>();
        rd = new Random();
        rd.setSeed(semilla);
        direccion = Math.abs(rd.nextInt(2));
    }

    public void generaSolucion() {
        int frecInicio = 0;
        int iteraciones = 0;
        int size = datos.getTransmisores().size();

        while (iteraciones < 10000) {
            ++iteraciones;
            direccion = Math.abs(rd.nextInt(2));

            int idTransmisor = datos.getTransmisores().get(rd.nextInt(size)).getId();
            int rango = datos.getTransmisores().get(idTransmisor).getRango();
            ArrayList<Integer> frecuencias = datos.getFrecuencias().get(rango).getFrecuencias();
            int frRandom = datos.getFrecuencias().get(rd.nextInt(frecuencias.size())).getId();

            ArrayList<CosteFrecuencia> costesfrecuencias = new ArrayList<>();
            int frecRandomInicial = frInicial(rango, frRandom);

            boolean tope = false;

            while (costesfrecuencias.size() != 20 && tope == false) {
                if (direccion == 0) {
                    //izquierda

                } else {

                }
            }

        }
    }

    private int frInicial(int rango, int frecInicial) {

        for (int i = 0; i < datos.getFrecuencias().get(rango).getFrecuencias().size(); ++i) {
            if (datos.getFrecuencias().get(rango).getFrecuencias().get(i) == frecInicial) {
                return i;
            }
        }
        return 0;
    }


}
