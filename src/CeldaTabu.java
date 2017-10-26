import java.util.ArrayList;

public class CeldaTabu {
    private int idTransmisor;
    private ArrayList<Integer> frecuencias;

    public CeldaTabu(int id){
        idTransmisor = id;
        frecuencias= new ArrayList<>();
    }

    public int getIdTransmisor() {
        return idTransmisor;
    }

    public void setIdTransmisor(int idTransmisor) {
        this.idTransmisor = idTransmisor;
    }

    public void aniadirFrecuencia(int frecuencia){
        frecuencias.add(frecuencia);
    }

}
