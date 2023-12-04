import java.util.Scanner;

import model.Baralho;
import model.Tabuleiro;

import model.Jogador;
import service.Utils;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int numJogadores = 0;

        System.out.println("Informe a quantidade de jogadores:");
        int tentativas = 3;

        while (tentativas > 0) {
            try {
                numJogadores = scanner.nextInt();
                if (numJogadores >= 3 && numJogadores <= 6) {
                    break;
                } else {
                    System.out.println("Valor escolher entre 3 a 6 jogadores.");
                }
            } catch (Exception error) {
                System.out.println("Por favor digite apenas números.");
                scanner.nextLine();
                tentativas--;

                if (tentativas == 0) {
                    System.out.println("Número de tentativas excedido. Encerrando o programa.");
                    System.exit(1);
                }
            }
        }

        Jogador[] jogadores = new Jogador[numJogadores];

        Baralho baralho = new Baralho();
        baralho.createBaralho();
        Tabuleiro tabuleiro = new Tabuleiro(baralho, numJogadores);

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Informe o nome do jogador " + (i+1) + ":");
            String nomeJogador = scanner.next();
            jogadores[i] = new Jogador(nomeJogador);
            jogadores[i].getCartasIniciais(baralho);
        }

        System.out.println("Jogadores criados com sucesso!");
        Utils.limparTela();

        while(jogadores[jogadores.length-1].getCartasDoJogador() != 0){
            Utils.limparTela();
            tabuleiro.pontuacoesJogadores(jogadores);
            tabuleiro.exibirTabuleiro();
            for (Jogador jogador: jogadores) {
                System.out.println("Jogador " +  jogador.getNome() + " escolha o número da carta que deseja jogar:");
                jogador.jogarCarta(tabuleiro);
            }
            tabuleiro.validaRodada();
        }
        Utils.limparTela();
        tabuleiro.exibirTabuleiro();
        tabuleiro.pontuacoesFinalJogadores(jogadores);

        scanner.close();
    }
}
