package src;

import java.io.Serializable;

public class Combate implements Serializable {
    Pokemon p1;
    Pokemon p2;


    public Combate(Pokemon p1, Pokemon p2){
        this.p1=p1;
        this.p2=p2;
    }
}
