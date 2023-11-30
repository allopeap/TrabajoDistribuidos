package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Jugador {

    private static final int SERVER_PORT = 8080;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    Combate c;

    private Pokemon chosenPokemon; // El Pokémon elegido por el jugador

    public Jugador() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Interacción para elegir Pokémon (supongamos que tienes un método choosePokemon en tu interfaz)
            List<Pokemon> availablePokemons = (List<Pokemon>) in.readObject();
            chosenPokemon = choosePokemon(availablePokemons);

            // Envía el Pokémon elegido al servidor
            out.writeObject(chosenPokemon);

            c = (Combate) in.readObject();

            // Configurar la interfaz gráfica y manejar eventos
            PokemonGameGUI pkgui = setupGUI();

            while (true){
                pkgui.esperarTurno();
                c = (Combate) in.readObject();
                pkgui.setC(c);
                c = pkgui.turno();
                out.writeObject(c);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PokemonGameGUI setupGUI() {
        int i =2;
        if(c.p1.equals(chosenPokemon)){
            i=1;
        }
        PokemonGameGUI pkg = new PokemonGameGUI(c,i);
        return pkg;
    }

    private Pokemon choosePokemon(List<Pokemon> availablePokemons) {
            return availablePokemons.get(0);
    }



    public static void main(String[] args) {
        new Jugador();
    }
}
