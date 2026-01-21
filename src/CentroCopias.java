public class CentroCopias {

    private int maquinasDeCopiado;
    private boolean tiempoTerminado;
    private int[] cantidadDeCopias;

    //Constructor.
    public CentroCopias(int estudiantes, int maquinasDeCopiado) {
        this.maquinasDeCopiado = maquinasDeCopiado; // Hacemos un contador porque al ser dos maquinas me es mas sencillo que usar Booleanos.
        this.tiempoTerminado = false;
        this.cantidadDeCopias = new int[6];
    }

    //miramos si estan todas las maquinas utilizandose y si es así se tienen que esperar
    public synchronized void solicitoMaquina (int idEstudiantil) throws InterruptedException {
        while(maquinasDeCopiado <= 0){
            wait();
        }
        maquinasDeCopiado--;
        System.out.println("el estudiante " + idEstudiantil + " esta usando impresora");
    }

    //Cuando se quede libre una maquina podrá ser utilizada por otro estudiante y se notifica
    public synchronized void dejoMaquina (int idEstudiantil){
        maquinasDeCopiado++;
        System.out.println("el estudiante " + idEstudiantil + " dejo de usar la impresora y se retira a estudiar");
        notifyAll();
    }
    //Guardamos las copias al Estudiante en cuestion
    public synchronized void setCantidadDeCopias(int idEstudiantil, int copias) {
        cantidadDeCopias[idEstudiantil]=copias;
    }
    //Retornamos la cantidad de copias de cada estudiante
    public synchronized int[] getCantidadDeCopias(){
        return cantidadDeCopias;
    }

    //Avisamos al programa que termina el tiempo(20Segundos)
    public synchronized void setTiempoTerminado(boolean tiempoTerminado) {
        this.tiempoTerminado = tiempoTerminado;
    }
    //Avisamos a los estudiantes desde el centro de copias que temina el tiempo(20Segundos)
    public synchronized boolean getTiempoTerminado() {
        return tiempoTerminado;
    }
}