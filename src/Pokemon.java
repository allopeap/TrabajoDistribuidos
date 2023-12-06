package src;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Pokemon implements Serializable {
    private String nombre;
    private String tipo;
    private double vida;
    private int ataque;
    private int defensa;
    private int velocidad;

    private List<Ataque> ataques;



    public  Pokemon(String nombre,String tipo,double vida,int ataque, int defensa,int velocidad,List<Ataque> ataques){
        this.nombre=nombre;
        this.tipo=tipo;
        this.vida=vida;
        this.ataque=ataque;
        this.defensa=defensa;
        this.velocidad=velocidad;
        this.ataques=ataques;


    }
    public double atacar(int at){
        Ataque a = this.ataques.get(at);
        double dano = this.ataque * a.getPotencia();
        if(this.tipo.equals(a.getTipo())){
            dano =  (dano*1.5);
        }
        Random r = new Random();
        int p = r.nextInt(100);
        if(p>a.getPrecision()){
            return 0;
        }
        dano = dano/100;
        return dano+this.ataque;

    }

    public boolean recibirDano(double d){
        if(d==0){return false;}
        double danio = d-this.defensa;
        this.vida = this.vida-danio;
        if(this.vida<=0){
            return true;
        }
        return false;
    }
    public String getNombre(){
        return this.nombre;
    }
    public  List<Ataque> getAtaques(){
        return  this.ataques;
    }

    public double getVida(){
        return this.vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public  int getVelocidad(){return this.velocidad;}

    public boolean equals(Pokemon p){
        if(p.getNombre().compareTo(this.nombre)==0){
            return true;
        }else return false;
    }
    public String toString(){
        return (this.nombre+ " Vida:" + this.vida);
    }
}
