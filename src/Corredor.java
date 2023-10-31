import java.util.Random;

public class Corredor{
    private String tipoCorredor;
    private int velocidadBase, probTurbo, probChoque, posicionCorredor = 1;

    public Corredor(String tipoCorredor, int velocidadBase, int probTurbo, int probChoque) {
        this.tipoCorredor = tipoCorredor;
        this.velocidadBase = velocidadBase;
        this.probTurbo = probTurbo;
        this.probChoque = probChoque;
    }

    public void lanzaHilosCorredor(){
        AvanzaCorredor avanza = new AvanzaCorredor(this);
        TropiezaCorredor tropieza = new TropiezaCorredor(this);

        avanza.start();
        tropieza.start();

    }

    private class AvanzaCorredor extends Thread{
        Corredor corredor;
        public AvanzaCorredor(Corredor corredor) {
            this.corredor = corredor;
        }

        @Override
        public void run(){
            super.run();
            try {
                while (posicionCorredor < Carrera.longitud) {
                    Thread.sleep(1000);
                    posicionCorredor = calculaProbabilidad(probTurbo) ?
                            posicionCorredor + velocidadBase * 2:
                            posicionCorredor + velocidadBase;

                    // Puesto que el corredor puede "salirse" de la carrera al avanzar
                    // más casillas de las que necesita para llegar al final, limitamos
                    // el valor máximo de su posición
                    if (posicionCorredor > Carrera.longitud) posicionCorredor = Carrera.longitud;
                }

                Carrera.carreraEnCurso = false;
                Carrera.ganador = corredor;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class TropiezaCorredor extends Thread{
        Corredor corredor;
        public TropiezaCorredor(Corredor corredor) {
            this.corredor = corredor;
        }

        @Override
        public void run(){
            super.run();
            try {
                Thread.sleep(calculaProbabilidad(probChoque) ? 2000 : 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean calculaProbabilidad(int probabilidad){
        Random random = new Random();
        return (random.nextInt(0, 5+1)) <= probabilidad;
    }
    public int getPosicionCorredor() {
        return posicionCorredor;
    }

    public void setPosicionCorredor(int posicionCorredor) {
        this.posicionCorredor = posicionCorredor;
    }

    public String getTipoCorredor() {
        return tipoCorredor;
    }

    public int getVelocidadBase() {
        return velocidadBase;
    }

    public int getProbTurbo() {
        return probTurbo;
    }

    public int getProbChoque() {
        return probChoque;
    }

    public void setProbChoque(int probChoque) {
        this.probChoque = probChoque;
    }
}
