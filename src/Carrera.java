public class Carrera {
    Corredor[] corredores;
    static volatile int longitud;
    static volatile boolean carreraEnCurso = true;
    static volatile Corredor ganador;

    public Carrera(Corredor[] corredores, int longitud) {
        this.corredores = corredores;
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }

}
