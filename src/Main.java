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

    public static void main(String [ ] args){

        Filemanager filemanager = new Filemanager("./archivos_guion/instancias/scen08/");
        Greedy miGreedy = new Greedy(filemanager,SEMILLA1);
        miGreedy.getSolucion().compruebaRestriccion(filemanager.getRestricciones());
        miGreedy.getResultados();

    }
}
