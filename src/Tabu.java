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

        while (iteraciones < 10000) {
            ++iteraciones;
            direccion = Math.abs(rd.nextInt(2));

            int idTransmisor = datos.getTransmisores().get(rd.nextInt(size)).getId();
            int rango = datos.getTransmisores().get(idTransmisor).getRango();
            ArrayList<Integer> frecuencias = datos.getFrecuencias().get(rango).getFrecuencias();
            int posFrRandom = datos.getFrecuencias().get(rango).getFrecuencias().get(rd.nextInt(frecuencias.size()));

            ArrayList<CosteFrecuencia> costesfrecuencias = new ArrayList<>();

            boolean tope = false;

            if (direccion == 0) {
                for (int i = posFrRandom; costesfrecuencias.size() < 20 && !tope; --i) {
                    int nuevaFrecuencia = datos.getFrecuencias().get(rango).getFrecuencias().get(i);
                    int puntuacion = mejorSolucion.recalcular(datos,idTransmisor, nuevaFrecuencia, mejorSolucion);

                    if(puntuacion < mejorSolucion.getPuntuacion()){
                        mejorSolucion.getFrecuenciasAsignadas().get(idTransmisor).setFrecuencia(nuevaFrecuencia);
                        mejorSolucion.setPuntuacion(puntuacion);

                        CeldaTabu ct = new CeldaTabu(idTransmisor);
                        ct.aniadirFrecuencia(nuevaFrecuencia);

                        listaTabu.put(idTransmisor, ct);
                    }

                    if(i == 0) {
                        tope = true;
                    }
                }
            } else {

            }

        }
    }



}
