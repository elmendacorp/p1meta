import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Filemanager {

    private ArrayList<Transmisor> transmisores;
    private ArrayList<RangoFrec> frecuencias;
    private ArrayList<Restriccion> restricciones;

    public Filemanager(String path) {
        transmisores = new ArrayList<>();
        leeTransmisores(path + "var.txt");

        frecuencias = new ArrayList<>();
        leeFrecuencias(path + "dom.txt");

        restricciones = new ArrayList<>();
        leeRestricciones(path + "ctr.txt");

    }

    public ArrayList<Transmisor> getTransmisores() {
        return transmisores;
    }

    public ArrayList<RangoFrec> getFrecuencias() {
        return frecuencias;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    /**
     * Funcion que parsea el fichero de conexiones de los nodos y los almacena
     *
     * @param rutaFichero ruta del archivo con los datos
     */
    private void leeTransmisores(String rutaFichero) {
        try {
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();

            while (strLine != null && strLine.length() > 2) {

                String[] cadena;

                cadena = strLine.split("\\s+");

                Integer transmisor;
                Integer banda;

                if (cadena.length > 2) {
                    transmisor = Integer.parseInt(cadena[1]);
                    banda = Integer.parseInt(cadena[2]);
                } else {
                    transmisor = Integer.parseInt(cadena[0]);
                    banda = Integer.parseInt(cadena[1]);
                }

                Transmisor t = new Transmisor(transmisor, banda);
                transmisores.add(t);

                strLine = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Fallo al leer el fichero de transmisores: " + e);
        }
    }

    /**
     * Funcion que parsea el fichero de frecuencias y las almacena
     *
     * @param rutaFichero ruta de archivo con los datos
     */
    private void leeFrecuencias(String rutaFichero) {
        try {
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine = br.readLine();

            while (strLine != null && strLine.length() > 2) {

                String[] cadena;
                cadena = strLine.split("\\s+");

                RangoFrec rf = new RangoFrec(Integer.parseInt(cadena[1]));

                for (int i = 2; i < cadena.length; ++i) {
                    rf.aniadeFrecuencia(Integer.parseInt(cadena[i]));
                }

                frecuencias.add(rf);

                strLine = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Fallo al leer el fichero de frecuencias: " + e);
        }
    }

    /**
     * Funcion que parsea el archivo de restricciones y lo almacena
     *
     * @param rutaFichero ruta del archivo con los datos
     */
    private void leeRestricciones(String rutaFichero) {
        Restriccion mirestriccion;
        try {
            FileInputStream fstream = new FileInputStream(rutaFichero);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();

            while (strLine != null && strLine.length() > 2) {

                String[] cadena;
                cadena = strLine.split("\\s+");
                if (cadena[4].toString().equals(">") && cadena.length==7) {
                    mirestriccion = new Restriccion(
                            Integer.parseInt(cadena[1]),
                            Integer.parseInt(cadena[2]),
                            Integer.parseInt(cadena[5]),
                            Integer.parseInt(cadena[6]));

                    restricciones.add(mirestriccion);
                }else if(cadena[3].toString().equals(">") && cadena.length==6) {
                    mirestriccion = new Restriccion(
                            Integer.parseInt(cadena[0]),
                            Integer.parseInt(cadena[1]),
                            Integer.parseInt(cadena[4]),
                            Integer.parseInt(cadena[5]));

                    restricciones.add(mirestriccion);
                }
                strLine = br.readLine();
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de restricciones");
        }
    }

    /**
     * Funcion para comprobar que la lectura de archivo se ha realizado correctamente
     */
    public void imprimeDatos(){
        System.out.println("Transmisor \t Frecuencias");
        for (Transmisor tr:transmisores) {
            System.out.println(tr.getId()+"\t->"+ frecuencias.get(tr.getRango()).getFrecuencias());
        }
        System.out.println("\n\n"+"Tx\tTr\tTolerancia\tPenalizacion");
        for (Restriccion rs:restricciones){
            System.out.println(rs.getId()+"\t"+rs.getId_restriccion()+"\t > "+rs.getTolerancia()+"\t"+rs.getPenalizacion());
        }
    }

}
