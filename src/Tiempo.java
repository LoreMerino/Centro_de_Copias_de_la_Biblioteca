public class Tiempo implements Runnable {
    private int tiempo;
    private CentroCopias centroCopias;

    //constructor
    public Tiempo(int tiempo, CentroCopias centroCopias) {
        this.tiempo = tiempo;
        this.centroCopias=centroCopias;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(tiempo);
            centroCopias.setTiempoTerminado(true);// True = Final del tiempo.  (En este caso los 20 Segundos)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
