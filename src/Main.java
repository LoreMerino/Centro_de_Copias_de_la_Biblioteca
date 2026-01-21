import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Thread> hilos = new ArrayList<>();// Guardamos cada estudiante
        int[] cantidadDeCopias;
        int alumnos = 5;
        int maquinas = 2;

        CentroCopias centroCopias = new CentroCopias(alumnos, maquinas);

        //Creamos, guardamos y ejecutamos el hilo de cada estudiante
        for(int i = 1; i <= 5; i++) {
            Estudiante estudiante = new Estudiante(i, centroCopias);

            Thread hilo = new Thread(estudiante);

            hilos.add(hilo);

            hilo.start();
        }

        Tiempo tiempo = new Tiempo (20000,centroCopias); // Para que termine en 20 segundos
        Thread hiloT = new Thread(tiempo);
        hiloT.start();

        //recorremos los hilos para asegurarnos que todos terminan antes de imprimir el número de copias
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Llamamos a GetCantidadDeCopias para que nos devuelva una lista de todas las copias
        // Las recorremos e imprimimos el numero de copias que hicimos
        cantidadDeCopias= centroCopias.getCantidadDeCopias();
        for(int i = 1; i <= 5; i++) {
            System.out.println("el estudiante: " + i + " Realizo un total de " + cantidadDeCopias[i] + " copias.");
        }
    }
}
