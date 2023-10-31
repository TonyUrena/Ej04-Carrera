import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        /////////////////////////////////////////////////////////////////////
        //
        //  He decidido no implementar la parte del ejercicio en la que
        //  hay que simulamos como los animales se resbalan.
        //  El motivo: al hacer que los animales se resbalen de forma
        //  aleatoria lo único que estamos consiguiendo es aleatorizar el
        //  resultado de la carrera. Para que la carrera sea realmente justa
        //  e influyan las prioridades de cada hilo, obligo a cada hilo a
        //  realizar la misma cantidad de operaciones para que el sistema
        //  operativo distribuya los recursos entre cada proceso.
        //
        ////////////////////////////////////////////////////////////////////


        Corredor[] corredores = {
                new Corredor("%",1,4, 4),
                new Corredor("&",2,4, 3),
                new Corredor("$",3,3, 2),
                new Corredor("€",4,2, 1),
                new Corredor("@",5,2, 2)
        };

        Carrera carrera = new Carrera(corredores, 100);

        Arrays.stream(carrera.corredores).forEach(c -> {
            c.lanzaHilosCorredor();
        });

        try {
            while(Carrera.carreraEnCurso) {
                Thread.sleep(1000);
                dibujaCarrera(carrera.longitud, corredores);
            }
            System.out.println("Ganador: " + Carrera.ganador.getTipoCorredor());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void dibujaCarrera(int longitudCarrera, Corredor[] corredores) {
        String bordePista = "━".repeat(longitudCarrera);
        System.out.println("\n".repeat(50)); // simula una limpieza de pantalla

        System.out.println("┏" + bordePista + "┓");
        Arrays.stream(corredores).forEach(c -> {
            System.out.println("┃" +
                    " ".repeat(c.getPosicionCorredor() - 1) + c.getTipoCorredor() +
                    ((c.getPosicionCorredor() != longitudCarrera) ?
                            " ".repeat((longitudCarrera - c.getPosicionCorredor() - 1)) + "░" : " "));
        });
        System.out.println("┗" + bordePista + "┛");
    }

}