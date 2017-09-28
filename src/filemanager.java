import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

public class filemanager {
    public static void LeeNodos(String nombreFichero) throws IOException{
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
            datos = lectura.split(" ");


        }catch (Exception e){}

    }
    public static void LeeFrecuencias(String nombreFichero) throws IOException{}
    public static void LeePuntuaciones(String nombreFichero) throws IOException{}


}
