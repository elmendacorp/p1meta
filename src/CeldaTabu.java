public class CeldaTabu {
    private int idTransmisor;
    private int numIntentos;

    public CeldaTabu(int id){
        idTransmisor = id;
        numIntentos = 0;
    }

    public void otroIntento(){
        ++numIntentos;
    }

    public int getIdTransmisor() {
        return idTransmisor;
    }

    public void setIdTransmisor(int idTransmisor) {
        this.idTransmisor = idTransmisor;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void reinicializarIntentos() {
        this.numIntentos = 1;
    }

}
