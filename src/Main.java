/**
 * @Author Rafael Martinez Rubio
 * @Mail rmr00034@red.ujaen.es
 * @Author Francisco Jesus Ruiz Lopez
 * @Mail fjrl0016@red.ujaen.es
 */

public class Main {

    public static final int SEMILLA1 = 12345678;
    public static final int SEMILLA2 = 23456781;
    public static final int SEMILLA3 = 34567812;
    public static final int SEMILLA4 = 45678123;
    public static final int SEMILLA5 = 56781234;

    public static void main(String[] args) {

        Filemanager filemanager1 = new Filemanager("./archivos_guion/instancias/graph05/");
        Filemanager filemanager2 = new Filemanager("./archivos_guion/instancias/graph06/");
        Filemanager filemanager3 = new Filemanager("./archivos_guion/instancias/graph07/");
        Filemanager filemanager4 = new Filemanager("./archivos_guion/instancias/graph11/");
        Filemanager filemanager5 = new Filemanager("./archivos_guion/instancias/graph12/");
        Filemanager filemanager6 = new Filemanager("./archivos_guion/instancias/graph13/");
        Filemanager filemanager7 = new Filemanager("./archivos_guion/instancias/scen06/");
        Filemanager filemanager8 = new Filemanager("./archivos_guion/instancias/scen07/");
        Filemanager filemanager9 = new Filemanager("./archivos_guion/instancias/scen08/");
        Filemanager filemanager10 = new Filemanager("./archivos_guion/instancias/scen09/");
        Filemanager filemanager11 = new Filemanager("./archivos_guion/instancias/scen10/");

        //filemanager6.imprimeDatos();

        Filemanager elegido = filemanager4;

        Greedy miGreedy = new Greedy(elegido, SEMILLA1);
        miGreedy.getSolucion().compruebaRestriccion(elegido.getRestricciones());
        miGreedy.getResultados();
        BusquedaLocal miBusqueda = new BusquedaLocal(miGreedy.getSolucion(), SEMILLA1);
        miBusqueda.generaSoluciones(elegido, 10000);
        miBusqueda.getResultados();

    }
}
