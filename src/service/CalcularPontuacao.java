package service;

import model.Cartas;
import model.Jogador;

public class CalcularPontuacao {

    public int calculaMonteJogador(Jogador jogador) {
        int pontuacao = 0;
        Cartas[] cartas = jogador.getCartas();

        for (int i = 0; i < cartas.length; i++) {
            pontuacao += calcularValorCarta(cartas[i]);
        }

        return pontuacao;
    }

    public int calcularValorCarta(Cartas carta) {
        int valorBase = 1;
        int numeroCarta = carta.getNumero();

        if (numeroCarta % 10 == 5) {
            valorBase += 1;
        }

        if (numeroCarta % 10 == 0) {
            valorBase += 2;
        }

        if (numeroCarta / 10 == numeroCarta % 10) {
            valorBase += 4;
        }

        return valorBase;
    }
}
