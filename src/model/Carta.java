package model;

public class Carta {
    public int numero;
    public Jogador dono;
    public int pontuacao;

    public Carta(int numero) {
        this.numero = numero;
        this.verificaPontuacao(numero);
    }

    private void verificaPontuacao(int num) {
        int pontos = 1;

        pontos += num % 10 == 5 ? 1 : 0;

        pontos += num % 10 == 0 ? 2 : 0;

        pontos += algarismosIguais(num) ? 5 : 0;

        this.pontuacao = pontos;
    }

    private boolean algarismosIguais(int num) {
        String cartaEmString = Integer.toString(num);

        if (cartaEmString.length() == 2) {
            return cartaEmString.charAt(0) == cartaEmString.charAt(1);
        } else if (cartaEmString.length() == 3) {
            return cartaEmString.charAt(0) == cartaEmString.charAt(1) || cartaEmString.charAt(1) == cartaEmString.charAt(2) || cartaEmString.charAt(0) == cartaEmString.charAt(2);
        }
        return false;
    }

}
