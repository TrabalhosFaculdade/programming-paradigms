package br.mackenzie.paradigmas.prova;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class Container {

    private Ponto[] pontos;
    private int index;

    public static final int ELEMENTO_NAO_EXISTENTE = -1;

    public Container (int size) {

        if (size < 0) {
            throw new IllegalArgumentException(
                    "Erro: tentantiva de criação de um " +
                    "container usando tamanho negativo de elementos");
        }

        this.pontos = new Ponto[size];
        this.index = 0;
    }

    /**
     * 1 Adição de elemento no final da coleção
     * */
    public void adiciona (Ponto ponto) {

        requireNonNull(ponto);
        requireNonFullContainer();

        pontos[index] = ponto;
        index++;
    }

    /**
     * 2 Adição de elemento em posição informada
     * */
    public void adiciona (Ponto ponto, int posicao) {

        requireNonNull(ponto);
        requireNonFullContainer();
        requireValidPositionAdding(posicao);

        if (posicao == index) {
            pontos[index] = ponto;
        } else {
            //posicao ja tomada, precisamos liberar espaco
            deslocarParaDireita(posicao, index);
            pontos[posicao] = ponto;
        }

        index++;
    }

    /**
     * 3 Índice da primeira ocorrencia de um elemento
     * */
    public int posicao (Ponto ponto) {

        requireNonNull(ponto);

        for (int i = 0; i < pontos.length; i++) {
            if (pontos[i].equals(ponto)) {
                return i;
            }
        }

        return ELEMENTO_NAO_EXISTENTE;
    }

    /**
     * 4 Remoção de um elemento na posição informada,
     * e retornando tal elemento.
     *
     * Elemento retornado vai ser nulo, se posição
     * informada nao tiver nenhum elemento
     * */
    public Ponto remover (int posicao) {

        requireValidPositionRemoving(posicao);

        if (posicao == index - 1) {
            return realizarRemovao(posicao);
        }

        Ponto sendoRemovido = realizarRemovao(posicao);
        deslocarParaEsquerda(index, posicao);

        return sendoRemovido;
    }

    /**
     * Nullifica a posicao informada; diminui o index;
     * retorna elemento removido.
     * Assumindo que validações já foram feitas
     * */
    private Ponto realizarRemovao (int posicao) {

        Ponto sendoRemovido = pontos[posicao];
        pontos[posicao] = null;
        index--;

        return sendoRemovido;
    }

    /**
     * 5 distancia maxima entre dois elementos
     * entre todos os pontos dentro do container
     * */
    public double distanciaMaxima () {

        if (pontos.length < 2) {
            throw new IllegalStateException(
                    "Erro: tentativa de calculo de distancia maxima, " +
                    "mas lista de pontos contém menos de 2 elementos");
        }

        double maior = -1;

        for (Ponto primeiro : pontos) {

            if (primeiro == null) {
                break;
            }

            for (Ponto segundo : pontos) {

                if (segundo == null) {
                    break;
                }

                if (primeiro == segundo) {
                    //atencao: nao é uma comparação com equals,
                    //estamos comparando igualidade de instancias
                    //poderíamos ter problema com container que contém
                    //uma mesma instancia de um ponto em multiplas posições da lista
                    continue;
                }

                double atual = primeiro.distanciaPara(segundo);
                maior = Math.max(atual, maior);
            }
        }

        if (maior == -1) {

            //poderia acontecer em cenarios como:
            //um container que tem dois elementos,
            //mas esses elementos sao a mesma instancia
            //de objeto em posicoes diferentes
            throw new IllegalStateException(
                    "Error ao tentar calcular maior " +
                    "distancia entre pontos do container: " +
                    "nao existem elementos para comparação");
        }

        return maior;
    }

    /**
     * 6 todos os elementos dentro do container que estão dentro
     * do círculo informado a partir dos argumentos
     * */
    public Container elementosDentroDeCirculo (Ponto centro, double raio) {

        requireNonNull(centro);
        requireNonNegative(raio);

        //assumindo que o container resultado vai ter o tamanho do container atual
        Container pontosDentro = new Container(pontos.length);

        for (Ponto ponto : pontos) {

            if (ponto == null) {
                break;
            }

            if (ponto.distanciaPara(centro) <= raio) {
                pontosDentro.adiciona(ponto.copia());
            }
        }

        return pontosDentro;
    }

    /**
     *
     * Assumindo que a posição "de" é a que nós queremos desocupar
     * E a posicao "para" está livre
     * */
    private void deslocarParaDireita(int de, int para) {

        if (de >= para) {
            throw new IllegalArgumentException(
                    "Erro ao realizar deslocamento para direita. " +
                    "Posicao 'de' maior ou igual a posicao 'para'");
        }

        for (int i = para; i >= de; i--) {
            pontos[i] = elementoAnterior(i);
        }
    }

    private void deslocarParaEsquerda(int de, int para) {

        if (de <= para) {
            throw new IllegalArgumentException(
                    "Erro ao realizar deslocamento para " +
                    "esquerda. Posicao 'de' menor ou igual a posicao 'para'");
        }

        for (int i = para; i <= de; i++) {
            pontos[i] = proximoElemento(i);
        }
    }

    /**
     * Função de utilidade para validar se um
     * container já está cheio antes da adição
     * de um novo elemento. Similar ao método
     * {@link java.util.Objects#requireNonNull(Object)}
     * que verifica se um dado elemento é nulo e lança
     * uma exceção em caso positivo
     *
     * @throws IllegalArgumentException se o vetor de
     * pontos já estiver cheio
     * */
    private void requireNonFullContainer () {

        if (index == pontos.length) {
            throw new IllegalStateException(
                    "Erro: tentativa de adicionar " +
                    "um ponto em um container já cheio");
        }
    }

    /**
     * Similar ao método {@link Container#requireNonFullContainer()}
     * Verifica se podemos adicionarmos um elemento
     * dentro de uma posição informada
     *
     * Posições válidas: a posição de qualquer outro elemento ja adicionado
     * ou a última posição (index)
     * */
    private void requireValidPositionAdding(int posicao) {

        if (posicao > index || posicao < 0) {
            throw new IllegalArgumentException(
                    "Erro: tentativa de adicionar um elemento " +
                    "em um posicao nao continua dentro do container");
        }
    }

    /**
     * Similar ao método {@link Container#requireValidPositionAdding(int)},
     * mas para a realização da operação de remoção
     * */
    private void requireValidPositionRemoving (int posicao) {

        if (posicao < 0 || posicao >= index) {
            throw new IllegalArgumentException(
                    "Erro: tentativa de remoção de um elemento " +
                    "em um posição não ocupada ou inválida");
        }
    }

    private void requireNonNegative (double valor) {

        if (valor < 0) {
            throw new IllegalArgumentException(
                    "Erro: valor passado como raio do círculo " +
                    "precisa ser maior ou igual a zero.");
        }
    }

    @Override
    public String toString() {
        return "Container{" +
                "pontos=" + Arrays.toString(pontos) +
                ", index=" + index +
                '}';
    }

    private Ponto proximoElemento (int posicao) {

        if (posicao + 1 >= pontos.length) {
            return null;
        }

        return pontos[posicao + 1];
    }

    private Ponto elementoAnterior (int posicao) {

        if ((posicao - 1) < 0) {
            return null;
        }

        return pontos[posicao - 1];
    }
}
