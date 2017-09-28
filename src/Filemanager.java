import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Filemanager {

    public static void leeTransmisores(String nombreFichero) throws IOException{
        File archivo;
        FileReader reader = null;
        BufferedReader bf;

        try {
            archivo= new File(nombreFichero);
            reader = new FileReader(archivo);
            bf = new BufferedReader(reader);
            String lectura;
            String [] datos;
            lectura= bf.readLine();
            datos = lectura.split("\\s+");
            System.out.print(datos);

        }catch (Exception e){}

    }
    public static void leeFrecuencias(String nombreFichero) throws IOException{
        File archivo;
        FileReader reader = null;
        BufferedReader bf;

        try {
            archivo= new File(nombreFichero);
            reader = new FileReader(archivo);
            bf = new BufferedReader(reader);
            String lectura;
            String [] datos;
            lectura= bf.readLine();
            datos = lectura.split("\\s+");
            System.out.print(datos);

        }catch (Exception e){}


    }
    public static void leeRestricciones(String nombreFichero) throws IOException{}


}
