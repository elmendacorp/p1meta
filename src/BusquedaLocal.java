import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class BusquedaLocal {
    private long time;
    private Solucion solucionActual;
    private LinkedList<FrecAsignada> vectorIteracion;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;

    public BusquedaLocal(Solucion solucion, int semilla) {
        solucionActual= solucion;
        vectorIteracion= new LinkedList<>();
        actualizaVector();
        rd = new Random();
        rd.setSeed(semilla);
        nodoInicio = Math.abs(rd.nextInt(vectorIteracion.size()));
        direccion = Math.abs(rd.nextInt(2));
    }

    public void generaSoluciones(Filemanager datos, int iteraciones) {
        time = System.currentTimeMillis();
        rellenaLista();
        int contador=0;
        Iterator<FrecAsignada> it;
        if(direccion==0){
            it= vectorIteracion.descendingIterator();
            for(int i= vectorIteracion.size();i>nodoInicio;--i){
                if(it.hasNext()){
                    it.next();
                }
            }
        }else{
            it= vectorIteracion.iterator();
            for(int i=0;i<nodoInicio;++i){
                if(it.hasNext()){
                    it.next();
                }
            }
        }

        while (contador<iteraciones){
            int intentos=0;
            int frecuenciasNodo = datos.getTransmisores().get(vectorIteracion.get(nodoInicio).getId()).getRango();
            int numFrecuencias = datos.getFrecuencias().get(frecuenciasNodo).tamanio();
            FrecAsignada actual=it.next();
            while (intentos < numFrecuencias && contador < iteraciones){
                ++intentos;
                ++iteraciones;
                int nuevaFre = datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().get(Math.abs(rd.nextInt(numFrecuencias)));
                if(solucionActual.getPuntuacion()>solucionActual.recalcular(datos,actual.getId(),nuevaFre,solucionActual)){
                    solucionActual.getFrecuenciasAsignadas().get(actual.getId()).setFrecuencia(nuevaFre);
                }
                
            }
        }
        time = System.currentTimeMillis() - time;
    }

    private void rellenaLista(){
        vectorIteracion.clear();
        for(FrecAsignada fr:solucionActual.getFrecuenciasAsignadas().values()){
            vectorIteracion.add(fr);
        }
    }

    public Solucion getSolucionActual() {
        return solucionActual;
    }
    private void actualizaVector(){
        vectorIteracion.clear();
        for(FrecAsignada fr:solucionActual.getFrecuenciasAsignadas().values()){
            vectorIteracion.add(fr);
        }
    }

    public void getResultados() {
        System.out.println("Solucion Busqueda Local " + solucionActual.getPuntuacion() + " Tiempo ejecucion " + time + " ms");
        for (FrecAsignada fr : solucionActual.getFrecuenciasAsignadas().values()) {
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }
}
