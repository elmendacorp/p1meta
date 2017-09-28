import java.io.IOException;

public class Main {
    public static void main(String [ ] args){
        System.out.println("-----------------------------------");
        Filemanager fmanager = new Filemanager();
        try {
            fmanager.leeTransmisores("/archivos_guion/instancias/Ejemplo/var.txt");
        } catch (IOException e) {
            System.out.println("Reventando por doquier");
            e.printStackTrace();
        }


    }
}
