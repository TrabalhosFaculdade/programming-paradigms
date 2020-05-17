package br.mackenzie.paradigmas.prova;

import java.util.Arrays;
import java.util.Scanner;

public class Cliente {

    private Scanner scanner;
    private Container container;

    private static final int[] OPCOES_VALIDAS_MENU_PRINCIPAL = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

    public Cliente() {
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        inicializacao();
        menuPrincipal();
    }

    private void inicializacao() {

        try {
            imprimir("Bem-vindo!");
            int tamanho = pedirInteiro("Por favor, informe o tamanho do container: ");

            container = new Container(tamanho);

        } catch (Exception e) {
            imprimir("Inicilização de container comprometida. %s", e.getMessage());
            System.exit(0);
        }
    }

    private void menuPrincipal() {

        principal:
        while (true) {

            exibirOpcoes();
            int selecionada = pedirInteiroEntreOpcoes(
                    "Selecione uma opção:",
                    OPCOES_VALIDAS_MENU_PRINCIPAL);

            try {

                switch (selecionada) {
                    case 1:
                        imprimir("Adicionar elemento no final da fila.");
                        operacaoAdicaoPonto();
                        break;
                    case 2:
                        imprimir("Adicionar elemento em posição específica da fila.");
                        operacaoAdicaoPontoPosicao();
                        break;
                    case 3:
                        imprimir("Primeira ocorrência de elemento na coleção");
                        operacaoEncontrarPonto();
                        break;
                    case 4:
                        imprimir("Remover ponto da coleção");
                        operacaoRemoverPonto();
                        break;
                    case 5:
                        imprimir("Calcular maior distância entre todos os pontos da coleção");
                        operacaoMaiorDistancia();
                        break;
                    case 6:
                        imprimir("Pontos contidos em círculo");
                        operacaoPontosContidosEmCirculo();
                        break;
                    case 7:
                        imprimir("Imprimir todos os pontos da coleção");
                        operacaoImprimirContainer();
                        break;
                    case 0:
                        imprimir("Saindo...");
                        break principal;

                    default:
                        throw new IllegalArgumentException("Erro: opção seleciona inválida");

                }
            } catch (Exception e) {
                imprimir("%s", e.getMessage());
            }
        }

    }

    private void exibirOpcoes() {
        imprimir("\n");
        imprimir("1 - Adicionar elemento no final");
        imprimir("2 - Adicionar elemento em posição específica");
        imprimir("3 - Primeira ocorrência de elemento em coleção");
        imprimir("4 - Remover elemento de coleção");
        imprimir("5 - Calcular maior distância entre todos os pontos da coleção");
        imprimir("6 - Pontos contidos em círculo");
        imprimir("7 - Imprimir coleção de pontos atual");
        imprimir("0 - Sair");
    }

    private int pedirInteiro(String mensagem) {

        imprimir(mensagem);

        while (true) {

            String input = scanner.next();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                imprimir("Por favor, informe um número inteiro: ");
            }
        }
    }

    private double pedirDouble(String mensagem) {

        imprimir(mensagem);

        while (true) {

            String input = scanner.next();

            try {
                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                imprimir("Por favor, informe um número inteiro: ");
            }
        }
    }

    private int pedirInteiroEntreOpcoes(String mensagem, int[] validos) {

        while (true) {

            int selecionado = pedirInteiro(mensagem);

            if (estaPresente(validos, selecionado)) {
                return selecionado;
            }

            imprimir("Valor '%d' não está entre as opções válidas. ", selecionado);
        }

    }

    private void operacaoAdicaoPonto() {

        int x = pedirInteiro("Coordenadas ponto. X:");
        int y = pedirInteiro("Coordenadas ponto. Y:");

        container.adiciona(new Ponto(x, y));
    }

    private void operacaoAdicaoPontoPosicao() {

        int x = pedirInteiro("Coordenadas ponto. X: ");
        int y = pedirInteiro("Coordenadas ponto. Y: ");
        int posicao = pedirInteiro("Posição dentro do container: ");

        container.adiciona(new Ponto(x, y), posicao);
    }

    private void operacaoEncontrarPonto() {

        int x = pedirInteiro("Coordenadas ponto. X: ");
        int y = pedirInteiro("Coordenadas ponto. Y: ");
        Ponto aSerEncontrado = new Ponto(x,y);

        int posicao = container.posicao(aSerEncontrado);
        if (posicao == Container.ELEMENTO_NAO_EXISTENTE) {
            imprimir("Elemento não encontrado com as coordenadas %d e %d", x, y);
        } else {
            imprimir("Elemento encontrado na posição %d", posicao);
        }
    }

    private void operacaoRemoverPonto() {

        int posicao = pedirInteiro("Posição de ponto a ser removido: ");
        Ponto removido = container.remover(posicao);

        if (removido == null) {
            imprimir("Posição não possuía nenhum elemento para ser removido");
        } else {
            imprimir("Elemento removido: [%d,%d]", removido.getX(), removido.getY());
        }
    }

    private void operacaoMaiorDistancia() {
        double resultado = container.distanciaMaxima();
        imprimir("Maior distância entre todos os pontos no container: %f", resultado);
    }


    private void operacaoPontosContidosEmCirculo() {

        int x = pedirInteiro("Coordenadas centro. X: ");
        int y = pedirInteiro("Coordenadas centro. Y: ");

        double raio = pedirDouble("Raio do círculo:");
        Ponto centro = new Ponto (x, y);

        Container resultado = container.elementosDentroDeCirculo(centro, raio);
        imprimirPontosContainer(resultado);
    }


    private void operacaoImprimirContainer() {
        imprimirPontosContainer(container);
    }

    private static void imprimir(String message) {
        System.out.println(message);
    }

    private static void imprimir(String formato, Object... argumentos) {
        System.out.printf(formato + "\n", argumentos);
    }

    private void imprimirPontosContainer (Container container) {
        imprimir(container.toString());
    }

    private boolean estaPresente(int[] elementos, int valor) {
        int res = Arrays.binarySearch(elementos, valor);
        return res >= 0; //vai retornar um indice maior do que 0 se estiver dentro da colecao
    }

}
