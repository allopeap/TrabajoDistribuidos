package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jugador {

    private static final int SERVER_PORT = 8080;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    Combate c;

    private boolean turno = true;

    private Pokemon chosenPokemon; // El Pokémon elegido por el jugador

    public Jugador() {

        try {
            System.out.println("Esperando rival");
            socket = new Socket(InetAddress.getLocalHost(), SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            int tipoPartida=0;
            Scanner scan = new Scanner(System.in);
            do{
                System.out.println("Deseas jugar en:");
                System.out.println("1. Interfaz Grafica (funcionamiento con posibles bugs)");
                System.out.println("2. Consola");


                tipoPartida = scan.nextInt();

            }while (tipoPartida!=1 && tipoPartida!=2);
            List<Pokemon> availablePokemons = (List<Pokemon>) in.readObject();

            chosenPokemon = choosePokemon(availablePokemons);

            // Envía el Pokémon elegido al servidor
            out.writeObject(chosenPokemon);
            out.flush();

            c = (Combate) in.readObject();


            switch (tipoPartida){
                case 1:{

                    // Configurar la interfaz gráfica

                    PokemonGameGUI pkgui = setupGUI();
                    while (true){
                        double d = in.readDouble();
                        if(d==0){
                            break;
                        }
                        if(pkgui.jugador==1){
                            c.p1.setVida(d);
                            pkgui.c.p1.setVida(d);
                        }else{
                            c.p2.setVida(d);
                            pkgui.c.p2.setVida(d);
                        }

                        boolean b = true;
                        SwingUtilities.invokeLater(() -> pkgui.invertirVisibilidad(true));

                        while (b){
                            if(pkgui.isVisible()){
                                TimeUnit.SECONDS.sleep(1);
                            }else b = false;
                        }
                        c.p1.setVida(pkgui.c.p1.getVida());
                        c.p2.setVida(pkgui.c.p2.getVida());
                        if(pkgui.jugador==1){
                            out.writeDouble(c.p2.getVida());
                        }else{
                            out.writeDouble(c.p1.getVida());
                        }
                        out.flush();

                    }
                    break;
                }
                case 2:{
                    while (true){
                        System.out.println("Esperando a tu turno");

                        double d = in.readDouble();

                        if(d==0){
                            System.out.println("Has perdido");
                            break;
                        }
                        if(c.p1.equals(chosenPokemon)){
                            c.p1.setVida(d);
                        }else {
                            c.p2.setVida(d);
                        }
                        if(c.p1.equals(chosenPokemon)){
                            System.out.println("Tú: " + c.p1.toString() );
                            System.out.println("Rival: " + c.p2.toString() );
                        }else {
                            System.out.println("Tú: " + c.p2.toString() );
                            System.out.println("Rival: " + c.p1.toString() );
                        }
                        int ataque = 0;
                        do{
                            System.out.println("Tú turno, elige ataque:");
                            System.out.println("1. " +  chosenPokemon.getAtaques().get(0).getNombre() + "| 2. " +
                                    chosenPokemon.getAtaques().get(1).getNombre()+ "| 3. " +
                                    chosenPokemon.getAtaques().get(2).getNombre() + "| 4. " +
                                    chosenPokemon.getAtaques().get(3).getNombre()  );
                            ataque = scan.nextInt();

                        }while (ataque!=1 && ataque!=2 && ataque!=3 && ataque!=4);
                        if(c.p1.equals(chosenPokemon)){
                            c.p2.recibirDano(c.p1.atacar(ataque-1));
                            if(c.p2.getVida()<=0){
                                System.out.println("Has ganado");
                            }
                            out.writeDouble(c.p2.getVida());
                        }else {
                            c.p1.recibirDano(c.p2.atacar(ataque-1));
                            if(c.p1.getVida()<=0){
                                System.out.println("Has ganado");
                            }
                            out.writeDouble(c.p1.getVida());
                        }
                        out.flush();

                    }
                    break;
                }

            }
            System.out.println("Partida acabada");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private PokemonGameGUI setupGUI() {
        int i =2;
        if(c.p1.equals(chosenPokemon)){
            i=1;
        }
        PokemonGameGUI pkg = new PokemonGameGUI(c,i);
        pkg.setVisible(true);
        return pkg;
    }

    private Pokemon choosePokemon(List<Pokemon> availablePokemons) {

        int j = 0;
        int i = 1;
        while (j<availablePokemons.size()){
            if(i==4){
                System.out.print("\r\n");
                i=1;
            }
                System.out.print(j+1+ ": " + availablePokemons.get(j).getNombre()+"  ");
            j++;
            i++;
        }
        System.out.println("\r\nIntroduce el numero del pokemon que quieras: ");
        Scanner s = new Scanner(System.in);
        return (availablePokemons.get(s.nextInt()-1));
    }



    public static void main(String[] args) {
        new Jugador();
    }


}
