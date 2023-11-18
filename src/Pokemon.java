package src;

import java.util.List;
import java.util.Random;

public class Pokemon {
    private String nombre;
    private String tipo;
    private double Vida;
    private int ataque;
    private int defensa;
    private int velocidad;

    private List<Ataque> ataques;



    public double atacar(int at){
        Ataque a = this.ataques.get(at);
        double daño = this.ataque * a.getPotencia();
        if(this.tipo.equals(a.getTipo())){
            daño =  (daño*1.5);
        }
        Random r = new Random();
        int p = r.nextInt(100);
        if(p>a.getPrecision()){
            return 0;
        }
        return daño;

    }
}
