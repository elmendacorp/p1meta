import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Filemanager {

    private List<Pair<Integer,Integer>> transmisores;

    public Filemanager(){
        transmisores = new ArrayList<>();
    }

    public void leeTransmisores(String nombreFichero){

        try {
            FileInputStream fstream = new FileInputStream(nombreFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();

            while (strLine != null && strLine.length() != 1) {

                String[] cadena;

                cadena = strLine.split("\\s+");
                Integer transmisor = Integer.parseInt(cadena[1]);
                Integer banda = Integer.parseInt(cadena[2]);

                Pair<Integer, Integer> par = new Pair<>(transmisor, banda);
                transmisores.add(par);

                strLine = br.readLine();
            }

            br.close();
        }catch (Exception e){
            System.out.println ("Fallo al leer el fichero de transmisores: " + e);
        }

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
