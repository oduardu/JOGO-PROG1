package model;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tabuleiro {
    private Carta[][] tabuleiro;
    private Carta[] cartasSelecionadas;

    public Tabuleiro(Baralho baralho, int numJogadores) {
        tabuleiro = new Carta[5][5];
        cartasSelecionadas = new Carta[numJogadores];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    tabuleiro[i][j] = baralho.retirarCarta();
                }
            }
        }
    }

    public void adicionarCarta(int linha, Carta cartaAdd) {
        int ultimaColuna = encontrarUltimaColunaVazia(linha);

        if (verificarLinhaCheia(linha) || tabuleiro[linha][ultimaColuna - 1].numero > cartaAdd.numero) {
            coletarTodasAsCartas(cartaAdd.dono, linha);
            tabuleiro[linha][0] = cartaAdd;
        } else {
            tabuleiro[linha][ultimaColuna] = cartaAdd;
        }

    }

    private boolean verificarLinhaCheia(int linha) {
        for (int j = 0; j < 5; j++) {
            if (tabuleiro[linha][j] == null) {
                return false;
            }
        }
        return true;
    }

    private int encontrarUltimaColunaVazia(int linha) {
        for (int j = 0; j < 5; j++) {
            if (tabuleiro[linha][j] == null) {
                return j;
            }
        }
        return -1;
    }

    public void exibirTabuleiro() {
        System.out.println(StringUtils.center("TABULEIRO", 28));
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 5; j++) {
                Carta carta = tabuleiro[i][j];
                if (carta != null) {
                    System.out.print("[" + carta.numero + "] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.print("| ");
            System.out.println();
        }
    }

    public void coletarTodasAsCartas(Jogador jogador, int linhaNumero) {
        int pontuacaoLinha = this.pontuacoesLinha(linhaNumero);
        jogador.addPontuacao(pontuacaoLinha);
        Carta[] cartasLinha = Arrays.copyOf(tabuleiro[linhaNumero], tabuleiro[linhaNumero].length);
        jogador.adicionarCartasColetadas(cartasLinha);
        Arrays.fill(tabuleiro[linhaNumero], null);
    }

    private int pontuacoesLinha(int linhaNum) {
        int pontos = 0;

        for (int j = 0; j < 5; j++) {
            Carta carta = tabuleiro[linhaNum][j];
            if (carta != null) {
                pontos += carta.pontuacao;
            }
        }

        return pontos;
    }

    public void adicionarCartaSelecionada(Carta carta, Jogador jogador) {
        carta.dono = jogador;
        for (int i = 0; i < cartasSelecionadas.length; i++) {
            if (cartasSelecionadas[i] == null) {
                cartasSelecionadas[i] = carta;
                break;
            }
        }
        ordenarCartasSelecionadas();
    }

    private void ordenarCartasSelecionadas() {
        int n = cartasSelecionadas.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Carta c1 = cartasSelecionadas[j];
                Carta c2 = cartasSelecionadas[j + 1];

                if (c1 != null && c2 != null && c1.numero > c2.numero) {
                    Carta temp = c1;
                    cartasSelecionadas[j] = c2;
                    cartasSelecionadas[j + 1] = temp;
                }
            }
        }
    }

    public void validaRodada() {
        for (Carta carta : cartasSelecionadas) {
            int linha = encontrarLinhaParaCarta(carta);
            adicionarCarta(linha, carta);
        }
        Arrays.fill(cartasSelecionadas, null);
    }

    private int encontrarLinhaParaCarta(Carta cartaSelecionada) {
        int menorDiferenca = Integer.MAX_VALUE;
        int linhaCorreta = 0;
        boolean achouLinha = false;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] != null && tabuleiro[i][j].numero < cartaSelecionada.numero) {
                    int diferenca = Math.abs(tabuleiro[i][j].numero - cartaSelecionada.numero);
                    if (diferenca < menorDiferenca) {
                        menorDiferenca = diferenca;
                        linhaCorreta = i;
                        achouLinha = true;
                    }
                }
            }
        }

        if(!achouLinha) {
            linhaCorreta = getLinhaComMaiorNúmero(tabuleiro);
        }

        return linhaCorreta;
    }

    private static int getLinhaComMaiorNúmero(Carta[][] tabuleiro) {
        int largestNumber = Integer.MIN_VALUE, linha = -1;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = tabuleiro[i].length - 1; j >= 0; j--) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].numero > largestNumber) {
                        largestNumber = tabuleiro[i][j].numero;
                        linha = i;
                    }
                    break;
                }
            }
        }

        return linha;
    }

    public void pontuacoesJogadores(Jogador jogadores[]) {
        System.out.println("PONTUAÇÕES DOS JOGADORES:");
        System.out.println();
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.toString());
        }
        System.out.println();
    }

    public void pontuacoesFinalJogadores(Jogador jogadores[]) {
        Jogador ganhador = null;
        int menorPontuacao = Integer.MAX_VALUE;

        for (Jogador jogador : jogadores) {
            int pontuacao = jogador.pontuacao;
            if (pontuacao < menorPontuacao) {
                menorPontuacao = pontuacao;
                ganhador = jogador;
            }
        }

        // Verificar se há empate
        List<Jogador> ganhadoresEmpate = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            int pontuacao = jogador.pontuacao;
            if (pontuacao == menorPontuacao) {
                ganhadoresEmpate.add(jogador);
            }
        }

        System.out.println("\nRESULTADOS FINAIS:\n");

        if (ganhadoresEmpate.size() > 1) {
            System.out.println("Empate entre os seguintes jogadores:");
            for (Jogador empate : ganhadoresEmpate) {
                System.out.println(empate.toString());
            }
        } else {
            System.out.println("Ganhador com menor pontuação:");
            System.out.println(ganhador.toString());
        }

        System.out.println("\nRanking dos jogadores:\n");

        Arrays.sort(jogadores, Comparator.comparingInt(Jogador::getPontuacao));

        for (int i = 0 ; i < jogadores.length; i++) {
            System.out.println(jogadores[i].toString());
            System.out.println(jogadores[i].getCartasColetadasAsString());
        }
    }

}
