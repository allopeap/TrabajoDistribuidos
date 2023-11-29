package src;

import java.util.ArrayList;
import java.util.List;

public class ListaPokemon {
    private List<Pokemon> pokemons = new ArrayList<>();

    ListaPokemon(){

        Ataque a1 = new Ataque("Ascuas","fuego",100,20);
        Ataque a2 = new Ataque("Arañazo","normal",100,30);
        Ataque a3 = new Ataque("Placaje","normal",100,40);
        Ataque a4 = new Ataque("Giro fuego","fuego",100,50);
        java.util.List<Ataque> l = new ArrayList<>();
        l.add(a1);
        l.add(a2);
        l.add(a3);
        l.add(a4);
        Pokemon p = new Pokemon("Charmander","fuego",100,30,24,33,l);

        Ataque a5 = new Ataque("Absorber","planta",100,20);
        Ataque a6 = new Ataque("Arañazo","normal",100,30);
        Ataque a7 = new Ataque("Placaje","normal",100,40);
        Ataque a8 = new Ataque("Latigo cepa","planta",100,50);
        java.util.List<Ataque> l2 = new ArrayList<>();
        l2.add(a5);
        l2.add(a6);
        l2.add(a7);
        l2.add(a8);
        Pokemon p2 = new Pokemon("Bulbasaur","planta",100,25,31,20,l2);

        Pokemon p3 = new Pokemon("Vuplix","fuego",100,28,20,35,l);

        pokemons.add(p);
        pokemons.add(p2);
        pokemons.add(p3);

    }

    public List<Pokemon> getPokemons(){
        return this.pokemons;
    }

}
