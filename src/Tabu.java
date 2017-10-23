import java.util.*;

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
            direccion = Math.abs(rd.nextInt(2));
            ++iteraciones;

            int idTransmisor = datos.getTransmisores().get(rd.nextInt() % size).getId();
            int rango = datos.getTransmisores().get(idTransmisor).getRango();

            int frecInicial =  Math.abs(rd.nextInt(datos.getFrecuencias().get(rango).getFrecuencias().size()));

            for(int i=0; i < datos.getFrecuencias().get(rango).getFrecuencias().size(); ++i){
                if(datos.getFrecuencias().get(rango).getFrecuencias().get(i) == frecInicial){
                    //sacar el nodo inicial, ponerlo en otra funcion
                }
            }


            if(direccion == 0){
                //izquierda


            }else{

            }

        }
    }

}
