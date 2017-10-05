import java.util.Random;

public class BusquedaLocal {
    private long time;
    private Solucion solucionActual;
    private int nodoInicio;
    private int direccion; // 1 derecha, 0 izquierda
    private Random rd;
    public BusquedaLocal(Solucion solucion, int semilla){
        solucionActual = new Solucion(solucion);
        rd= new Random();
        rd.setSeed(semilla);
        nodoInicio= Math.abs(rd.nextInt()%solucionActual.tamanio());
        direccion= Math.abs(rd.nextInt()%2);
    }
    public void generaSoluciones(Filemanager datos, int iteraciones){
        time= System.currentTimeMillis();
        int contador=0;
        Solucion solucionModificada;
        solucionActual.compruebaRestriccion(datos.getRestricciones());
        try {
            while (contador < iteraciones) {
                solucionModificada = new Solucion(solucionActual);
                int intentos = 0;
                int frecuenciasNodo = datos.getTransmisores().get(datos.getTransmisores().get(nodoInicio).getId()).getRango();
                int numFrecuencias = datos.getFrecuencias().get(frecuenciasNodo).tamanio();
                while (intentos < numFrecuencias && contador < iteraciones && solucionModificada.getPuntuacion()>=solucionActual.getPuntuacion()) {
                    int nuevaFre=datos.getFrecuencias().get(frecuenciasNodo).getFrecuencias().get(Math.abs(rd.nextInt() % numFrecuencias));
                    solucionModificada.getFrecuenciasAsignadas().get(datos.getTransmisores().get(nodoInicio).getId()).setFrecuencia(nuevaFre);
                    //solucionModificada.compruebaRestriccion(datos.getRestricciones());
                    solucionModificada.recalcular(solucionActual.getFrecuenciasAsignadas().get(nodoInicio).getFrecuencia(),datos,nodoInicio);
                    ++intentos;
                    ++contador;
                    if (solucionModificada.getPuntuacion()<solucionActual.getPuntuacion()) {
                        solucionActual=new Solucion(solucionModificada);
                    }
                    //System.out.println("Iteracion " + contador + " puntuacion " + solucionModificada.getPuntuacion()+" Solucion Actual "+solucionActual.getPuntuacion() + "Intentos: " + intentos);
                }



                if (direccion == 1) {
                    if (nodoInicio == solucionActual.tamanio()-1) {
                        nodoInicio = 0;
                    }else {
                        ++nodoInicio;
                    }
                } else {
                    if (nodoInicio == 0) {
                        nodoInicio = solucionActual.tamanio()-2;
                    } else {
                        nodoInicio--;
                    }
                }
            }
        }catch(Exception e){
            System.err.println("Error Busqueda local"+e+nodoInicio);
            }


        time=System.currentTimeMillis()-time;
    }

    public Solucion getSolucionActual() {
        return solucionActual;
    }
    public void getResultados() {
        System.out.println("Solucion Busqueda Local "+ solucionActual.getPuntuacion()+" Tiempo ejecucion "+time+" ms");
        for(FrecAsignada fr:solucionActual.getFrecuenciasAsignadas()){
            //System.out.println(fr.getId()+"\t"+fr.getFrecuencia());
        }
    }
}
