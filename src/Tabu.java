import java.util.*;

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
        int idTransmisor = 0;
        int rango = 0;
        ArrayList<CosteFrecuencia> listaFrecuencias = new ArrayList<>();
        int posFrRandom = 0;

        while (iteraciones < 10000) {

            //Comprueba que no has llegado al estancamiento

            // si has llegado realiza la reinicializacion copiando lo que tengas en la estuctura de celta tabu

            ++iteraciones;
            direccion = Math.abs(rd.nextInt(2));
            listaFrecuencias.clear();

            idTransmisor = datos.getTransmisores().get(rd.nextInt(size)).getId();
            rango = datos.getTransmisores().get(idTransmisor).getRango();
            listaFrecuencias = copiaFrecuencias(rango);
            // calcula las 20 frecuencias asociadas a un transmisor

            // calcula el coste por cada frecuencia

            // coge la mejor y decide si vas a meterla en las soluciones

            // añade la frecuencia a la estructuda de celtatabu para mantener los transmisores con las mejores frecuencias y sus apariciones



            posFrRandom = rd.nextInt(frecuencias.size());

            ArrayList<CosteFrecuencia> costesfrecuencias = new ArrayList<>();

            if (direccion == 0) {

            } else {

            }

        }
    }

    private ArrayList<CosteFrecuencia> copiaFrecuencias(int rango, int posInicial, int direccion) {
        ArrayList<CosteFrecuencia> finalList = new ArrayList<>();
        ArrayList<Integer> frecuencias = datos.getFrecuencias().get(rango).getFrecuencias();

        if (frecuencias.size() <= 20) {
            for (Integer fr : datos.getFrecuencias().get(rango).getFrecuencias()) {
                finalList.add(new CosteFrecuencia(fr,0));
            }

        } else {

            if (direccion == 0) {
                for (int i = posInicial; finalList.size() < 20; --i) {
                    finalList.add(new CosteFrecuencia(frecuencias.get(i),0));
                    if (i == 0) {
                        i = frecuencias.size() - 1;
                    }
                }
            } else {
                for (int i = posInicial; finalList.size() < 20; ++i) {
                    finalList.add(new CosteFrecuencia(frecuencias.get(i),0));
                    if (i == frecuencias.size() - 1) {
                        i = 0;
                    }
                }
            }
        }
        return finalList;
    }
}
