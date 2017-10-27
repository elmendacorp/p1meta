
import java.util.*;

public class Tabu2 {
    private Solucion mejorSolucion;
    private Solucion modificada;
    private Filemanager datos;
    private Map<Integer, CeldaTabu> listaTabu;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;

    public Tabu2(Filemanager data, Solucion greedy, int semilla) {
        mejorSolucion = new Solucion(greedy);
        modificada = new Solucion(greedy);
        datos = data;
        listaTabu = new HashMap<>();
        rd = new Random();
        rd.setSeed(semilla);
        direccion = rd.nextInt(2);
    }

    public void generaSolucion(int maxIteraciones, int maxSinMejora) {
        direccion = rd.nextInt(2);
        Iterator<FrecAsignada> listItera=modificada.getFrecuenciasAsignadas().values().iterator();
        int iteraciones = 0;
        int iterSinMejora = 0;
        int idTransmisor = 0;
        int rango = 0;
        int posFrRandom = 0;
        ArrayList<CosteFrecuencia> listaFrecuencias = new ArrayList<>();

        while (iteraciones < maxIteraciones) {

            if(listItera.hasNext()){
                listItera.next();
            }else{
                listItera= modificada.getFrecuenciasAsignadas().values().iterator();
            }
            //Comprueba que no has llegado al estancamiento

            if(iterSinMejora>=maxSinMejora){
                iterSinMejora=0;
                for(FrecAsignada fr: modificada.getFrecuenciasAsignadas().values()){
                    if(listaTabu.containsKey(fr.getId())){
                        int mejor=0;
                        int idMejor=0;
                        for(FrecuenciaApariciones fa:listaTabu.get(fr.getId()).getFrecuenciasNodo().values()){
                            if(fa.getNumApariciones()>mejor){
                                mejor=fa.getNumApariciones();
                                idMejor=fa.getIdFrecuencia();
                            }
                        }
                        modificada.getFrecuenciasAsignadas().get(fr.getId()).setFrecuencia(idMejor);
                    }
                }
            }

            // calcula las 20 frecuencias asociadas a un transmisor

            // calcula el coste por cada frecuencia

            // coge la mejor y decide si vas a meterla en las soluciones

            // añade la frecuencia a la estructuda de celtatabu para mantener los transmisores con las mejores frecuencias y sus apariciones



        }
    }

}
