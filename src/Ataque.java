package src;

import javax.print.DocFlavor;
import java.io.Serializable;

public class Ataque implements Serializable {
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
