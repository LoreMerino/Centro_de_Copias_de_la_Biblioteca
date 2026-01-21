class Estudiante implements Runnable {

    private CentroCopias centroCopias;
    private int idEstudiantil;
    private int numcopias;



    public Estudiante (int idEstudiantil, CentroCopias centroCopias){
        this.idEstudiantil = idEstudiantil;
        this.centroCopias = centroCopias;
        this.numcopias = 0;


    }
    @Override
    public void run() {
        //Estudia
        while (centroCopias.getTiempoTerminado() == false) {
            try {
                System.out.println(idEstudiantil + " Empieza a estudiar.");
                Thread.sleep((int) (Math.random() * 4000));// Simulamos un tiempo aleatorio para estudiar

                //va al centro de copias
                System.out.println(idEstudiantil + " va al centro de copias.");
                Thread.sleep(1000);//// Simulamos un tiempo para que vaya al centro de copia

                //solicito maquina
                centroCopias.solicitoMaquina(idEstudiantil);

                //hago copias y las guardamos
                Thread.sleep(1000); // Simulamos un tiempo para que saque copias
                numcopias++;

                //deja la maquina y vuelve a casa
                centroCopias.dejoMaquina(idEstudiantil);
                System.out.println(idEstudiantil + " vuelve a casa.");
                Thread.sleep(1000);// Simulamos un tiempo para que vuelva a casa
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        // le paso el total de numero de copias del estudiante
        centroCopias.setCantidadDeCopias(idEstudiantil, numcopias);

    }

}