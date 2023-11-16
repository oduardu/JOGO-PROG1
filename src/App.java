import java.util.Scanner;

import model.Baralho;
import model.Jogador;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = new Baralho();
        baralho.createBaralho();

        System.out.println("JOGO NOME ???????");
        System.out.println("Informe a quantidade de jogadores:");
        int numJogadores = scanner.nextInt();
        Jogador[] jogadores = new Jogador[numJogadores];
        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Informe o nome do jogador " + (i+1) + ":");
            String nomeJogador = scanner.next();
            jogadores[i] = new Jogador(nomeJogador);
        }
        System.out.println("Jogadores criados com sucesso!");

        scanner.close();
    }
}
