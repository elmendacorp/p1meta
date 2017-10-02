import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Filemanager {

    private List<Pair<Integer, Integer>> transmisores;
    private List<Pair<Integer, List<Integer>>> frecuencias;

    public Filemanager(String path) {
        transmisores = new ArrayList<>();
        leeTransmisores(path + "var.txt");

        frecuencias = new ArrayList<>();
        leeFrecuencias(path + "dom.txt");

    }

    public List<Pair<Integer, Integer>> getTransmisores(){
        return transmisores;
    }

    public List<Pair<Integer, List<Integer>>> getFrecuencias(){
        return frecuencias;
    }

    private void leeTransmisores(String rutaFichero) {
        try {
            FileInputStream fstream = new FileInputStream(rutaFichero);
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
        } catch (Exception e) {
            System.out.println("Fallo al leer el fichero de transmisores: " + e);
        }
    }

    private void leeFrecuencias(String rutaFichero) {
        try {
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();

            while (strLine != null && strLine.length() != 1) {

                String[] cadena;
                cadena = strLine.split("\\s+");

                List<Integer> aux = new ArrayList<>();

                for (int i = 2; i < cadena.length; ++i) {
                    aux.add(Integer.parseInt(cadena[i]));
                }

                Pair<Integer, List<Integer>> frec = new Pair<>(Integer.parseInt(cadena[1]), aux);
                frecuencias.add(frec);

                strLine = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Fallo al leer el fichero de frecuencias: " + e);
        }
    }

    private void leeRestricciones(String rutaFichero) {
        try{
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();

            while (strLine != null && strLine.length() != 1) {

                String[] cadena;
                cadena = strLine.split("\\s+");

                List<Integer> aux = new ArrayList<>();


                strLine = br.readLine();
            }

            br.close();
        }catch(Exception e){}
    }

}
