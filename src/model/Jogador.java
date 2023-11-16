package model;

public class Jogador {
    
    String name;
    int pontuacao = 0;
    Cartas[] cartas;
    Cartas[] cartasColetadas;
    
    public Jogador(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public Cartas[] getCartas() {
        return cartas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setCartas(Cartas[] cartas) {
        this.cartas = cartas;
    }

    public Cartas[] getCartasColetadas() {
        return cartasColetadas;
    }

    public void setCartasColetadas(Cartas[] cartasColetadas) {
        this.cartasColetadas = cartasColetadas;
    }
}
