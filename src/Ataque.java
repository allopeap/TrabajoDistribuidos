package src;

import javax.print.DocFlavor;

public class Ataque {
    private String nombre;
    private String tipo;
    private int precision;
    private int potencia;

    public Ataque(String nombre,String tipo, int precision, int potencia){
        this.nombre=nombre;
        this.tipo=tipo;
        this.precision=precision;
        this.potencia=potencia;
    }
    public int getPotencia() {
        return potencia;
    }

    public int getPrecision() {
        return precision;
    }

    public String getTipo() {
        return tipo;
    }
    public String getNombre(){
        return this.nombre;
    }
}
