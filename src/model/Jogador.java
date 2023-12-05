package model;

import java.util.Scanner;

public class Jogador {
    
    String name;
    int pontuacao = 0;
    Carta[] cartas;
    private Carta[] cartasColetadas = new Carta[109];
    private int posicaoCartasColetadas = 0;

    public Jogador() {
        this.name = "";
    }

    public Jogador(String name) {
        this.name = name;
    }

    public Jogador getJogador() {
        return this;
    }

    public String getNome() {
        return name;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public Carta[] getCarta() {
        return cartas;
    }

    public int getCartasDoJogador() {
        return cartas.length;
    }

    public void addPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }

    public void getCartasIniciais(Baralho baralho, int numeroCartas) {
        for (int i = 0; i < numeroCartas; i++) {
            Carta carta = baralho.retirarCarta();

            if (carta != null) {
                addCarta(carta);
            } else {
                System.out.println("Baralho vazio. Não foi possível obter mais cartas.");
                break;
            }
        }
    }

    public void getCartasIniciais(Baralho baralho) {
        getCartasIniciais(baralho, 12);
    }

    public void addCarta(Carta novaCarta) {
        if(cartas == null){
            cartas = new Carta[1];
            cartas[0] = novaCarta;
        } else {
            Carta[] novoArray = new Carta[cartas.length + 1];

            System.arraycopy(cartas, 0, novoArray, 0, cartas.length);
            novoArray[cartas.length] = novaCarta;

            cartas = novoArray;
        }
    }

    public void jogarCarta(Tabuleiro tabuleiro) {
        Scanner scanner = new Scanner(System.in);
        exibirCartas();
        while (true) {
            System.out.println("\nCarta escolhida: ");

            if (scanner.hasNextInt()) {
                int numeroEscolhido = scanner.nextInt();
                int posicao = encontrarPosicaoCarta(numeroEscolhido);

                if (posicao != -1) {
                    System.out.println("\nCarta " + numeroEscolhido + " jogada!\n");
                    tabuleiro.adicionarCartaSelecionada(cartas[posicao], getJogador());
                    cartas = removerCarta(cartas, posicao);
                    break;
                } else {
                    System.out.println("Carta não encontrada, tente novamente.");
                }
            } else {
                System.out.println("Por favor, digite apenas números inteiros.");
                scanner.next();
            }
        }
}

    private void exibirCartas() {
        System.out.println("\nCartas na mão do jogador:");
        System.out.println();
        for (Carta carta : cartas) {
            if (carta != null) {
                System.out.print("[ " + carta.numero + " ]");
            }
        }
        System.out.println();
    }

    private int encontrarPosicaoCarta(int numeroCarta) {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] != null && cartas[i].numero == numeroCarta) {
                return i;
            }
        }
        return -1;
    }

    private Carta[] removerCarta(Carta[] array, int posicao) {
        Carta[] novoArray = new Carta[array.length - 1];
        System.arraycopy(array, 0, novoArray, 0, posicao);
        System.arraycopy(array, posicao + 1, novoArray, posicao, array.length - posicao - 1);
        return novoArray;
    }

    public void adicionarCartasColetadas(Carta[] cartas) {
        System.arraycopy(cartas, 0, cartasColetadas, posicaoCartasColetadas, cartas.length);
        posicaoCartasColetadas += cartas.length;
    }

    public String getCartasColetadasAsString() {
        String cartasColetadasStr = "";

        for (Carta carta : cartasColetadas) {
            if (carta == null)
                continue;
            cartasColetadasStr += "[ " + carta.numero + " ] ";
        }

        return "Cartas Coletadas: " + cartasColetadasStr;
    }


    @Override
    public String toString() {
        return "Jogador " + name + " - Pontuacao:" + pontuacao;
    }
}
