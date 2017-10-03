/**
 * Clase que almacena las restriccion de un transmisor sobre otro y la penalizacion sobre la misma
 */
public class Restriccion {
    private int id;
    private int id_restriccion;
    private int tolerancia;
    private int penalizacion;

    public Restriccion(int var1, int var2, int var3, int var4){
        id=var1;
        id_restriccion=var2;
        tolerancia=var3;
        penalizacion=var4;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_restriccion() {
        return id_restriccion;
    }

    public void setId_restriccion(int id_restriccion) {
        this.id_restriccion = id_restriccion;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }
}
