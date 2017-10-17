/**
 * Clase para representar una celda en la estructura GRASP
 */
public class CeldaGrasp {
    private int id;
    private int frecuencia;
    private int coste;
    private int posicion;
    private double probabilidad;

    public int getId() {
        return id;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public CeldaGrasp(int identificador, int fre, int cost, int pos, int prob) {
        frecuencia = fre;
        id = identificador;
        coste = cost;
        posicion = pos;
        probabilidad = prob;
    }
    public CeldaGrasp(CeldaGrasp cp) {
        frecuencia = cp.frecuencia;
        id = cp.id;
        coste = cp.coste;
        posicion = cp.posicion;
        probabilidad =cp.probabilidad;
    }
    public CeldaGrasp(){}
}
