
import java.util.HashMap;

public class CeldaTabu {
    private int idTransmisor;
    private HashMap<Integer,FrecuenciaApariciones> frecuencias;

    public CeldaTabu(int id){
        idTransmisor = id;
        frecuencias= new HashMap<>();
    }

    public int getIdTransmisor() {
        return idTransmisor;
    }

    public void setIdTransmisor(int idTransmisor) {
        this.idTransmisor = idTransmisor;
    }

    public void aniadirFrecuencia(int frecuencia){
        if(frecuencias.containsKey(frecuencia)){
            frecuencias.get(frecuencia).setNumApariciones(frecuencias.get(frecuencia).getNumApariciones()+1);
        }else{

            frecuencias.put(idTransmisor,new FrecuenciaApariciones(frecuencia));
        }
    }

    public HashMap<Integer,FrecuenciaApariciones> getFrecuenciasNodo(int id_nodo){
        return frecuencias;
    }

}
