package model;

public class Baralho {
    
    Cartas[] cartas;

    public void createBaralho()
    {
        Cartas[] baralho = new Cartas[109];
        for (int i = 0; i < 109; i++) {
            baralho[i] = new Cartas(i+1);
        }
        
        baralho = this.embaralhaCartas(baralho);

        this.cartas = baralho;
    }

    public Cartas[] getCartas() {
        return cartas;
    }

    public Cartas[] embaralhaCartas(Cartas[] cartas) {
        
        for (int i = 0; i < cartas.length; i++) {
            int randomIndex = (int) (Math.random() * cartas.length);
            Cartas temp = cartas[i];
            cartas[i] = cartas[randomIndex];
            cartas[randomIndex] = temp;
        }

        return cartas;
    }
}
