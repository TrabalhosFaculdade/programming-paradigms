package br.mackenzie.paradigmas.prova;

import static java.util.Objects.requireNonNull;

public class Ponto {

    private int x;
    private int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanciaPara (Ponto outro) {
        requireNonNull(outro);
        return Math.sqrt(Math.pow(getX() - outro.getX(), 2) + Math.pow(getY() - outro.getY(), 2));
    }

    /**
     * Manter a propriedade de classe imutavel do Ponto
     * para operações que retornem pontos, apenas para consulta
     * Importancia descria aqui: https://dzone.com/articles/the-importance-of-immutability-in-java
     * */
    public Ponto copia () {
        return new Ponto(this.getX(), this.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ponto ponto = (Ponto) o;

        if (x != ponto.x) return false;
        return y == ponto.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Ponto{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
