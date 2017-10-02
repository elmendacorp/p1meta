import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Filemanager {

    private List<Pair<Integer, Integer>> transmisores;
    private List<Pair<Integer, List<Integer>>> frecuencias;
    private List<Restriccion> restricciones;





    public Filemanager(String path) {
        transmisores = new ArrayList<>();
        leeTransmisores(path + "var.txt");

        frecuencias = new ArrayList<>();
        leeFrecuencias(path + "dom.txt");

        restricciones = new ArrayList<>();
        leeRestricciones(path + "ctr.txt");

    }

    public List<Pair<Integer, Integer>> getTransmisores(){
        return transmisores;
    }

    public List<Pair<Integer, List<Integer>>> getFrecuencias(){
        return frecuencias;
    }

    public List<Restriccion> getRestricciones() {
        return restricciones;
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
            System.err.println("Fallo al leer el fichero de transmisores: " + e);
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
            System.err.println("Fallo al leer el fichero de frecuencias: " + e);
        }
    }

    private void leeRestricciones(String rutaFichero) {
        Restriccion mirestriccion;
        try{
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();

            while (strLine != null && strLine.length() != 1) {

                String[] cadena;
                cadena = strLine.split("\\s+");
                if (cadena[3] == ">") {
                    mirestriccion = new Restriccion(Integer.parseInt(cadena[1]),Integer.parseInt(cadena[2]),Integer.parseInt(cadena[5]),Integer.parseInt(cadena[6]));
                    restricciones.add(mirestriccion);
                }
                strLine = br.readLine();
            }

            br.close();
        }catch(Exception e){
            System.err.println("Error al leer el archivo de restrcciones");
        }
    }

}
