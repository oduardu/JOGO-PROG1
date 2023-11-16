package model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<List<Cartas>> linhas;

    public Tabuleiro() {
        linhas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            linhas.add(new ArrayList<>());
        }
    }

    public void posicionarCarta(Cartas novaCarta, Jogador jogador) {
        List<Cartas> linhaMenorDiferenca = null;
        int menorDiferenca = Integer.MAX_VALUE;

        for (List<Cartas> linha : linhas) {
            if (!linha.isEmpty()) {
                Cartas ultimaCarta = linha.get(linha.size() - 1);
                int diferenca = novaCarta.getNumero() - ultimaCarta.getNumero();

                if (diferenca < menorDiferenca) {
                    menorDiferenca = diferenca;
                    linhaMenorDiferenca = linha;
                }
            }
        }

        if (linhaMenorDiferenca.size() == 5) {
            linhaMenorDiferenca.clear();
        }

        linhaMenorDiferenca.add(novaCarta);
    }
}