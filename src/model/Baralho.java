package model;

import java.util.Arrays;

public class Baralho {

    private Carta[] cartas;

    public void createBaralho() {
        this.cartas = new Carta[109];
        for (int i = 0; i < 109; i++) {
            this.cartas[i] = new Carta(i + 1);
        }

        this.embaralhaCarta();
    }

    public void embaralhaCarta() {
        for (int i = 0; i < cartas.length; i++) {
            int randomIndex = (int) (Math.random() * cartas.length);
            Carta temp = cartas[i];
            cartas[i] = cartas[randomIndex];
            cartas[randomIndex] = temp;
        }
    }

    public Carta retirarCarta() {
        if (cartas.length > 0) {
            Carta cartaRetirada = cartas[cartas.length - 1];
            cartas = Arrays.copyOf(cartas, cartas.length - 1);
            return cartaRetirada;
        } else {
            return null;
        }
    }

    public void getTamanho() {
        System.out.println(cartas.length);
    }
}