package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Batalla extends Thread {
    private Socket player1Socket;
    private Socket player2Socket;

    private Combate c = null;

    private Socket primero;
    private ObjectInputStream primeroIn;
    private ObjectOutputStream primeroOut;

    private Socket segundo;
    private ObjectInputStream segundoIn;
    private ObjectOutputStream segundoOut;
    public Batalla(Socket player1Socket, Socket player2Socket) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;

    }

    @Override
    public void run() {
        try (
                ObjectOutputStream player1Output = new ObjectOutputStream(player1Socket.getOutputStream());
                ObjectInputStream player1Input = new ObjectInputStream(player1Socket.getInputStream());
                ObjectOutputStream player2Output = new ObjectOutputStream(player2Socket.getOutputStream());
                ObjectInputStream player2Input = new ObjectInputStream(player2Socket.getInputStream())
        ) {
            List<Pokemon> pokemons = new ListaPokemon().getPokemons();
            player1Output.writeObject(pokemons);
            Pokemon p1 = (Pokemon) player1Input.readObject();
            player2Output.writeObject(pokemons);
            Pokemon p2 = (Pokemon) player2Input.readObject();
            c = new Combate(p1,p2);

            if(c.p1.getVelocidad()>c.p2.getVelocidad()){
                this.primero=player1Socket;
                this.segundo=player2Socket;
                primeroIn = player1Input;
                primeroOut = player1Output;
                segundoIn=player2Input;
                segundoOut=player2Output;
            }else {
                this.primero=player2Socket;
                this.segundo=player1Socket;
                primeroIn = player2Input;
                primeroOut = player2Output;
                segundoIn=player1Input;
                segundoOut=player1Output;
            }
            while (true){
                primeroOut.writeObject(c);
                segundoOut.writeObject(c);

                primeroOut.writeObject(c);
                c = (Combate) primeroIn.readObject();
                if(c.p2.getVida()<=0){
                    break;
                }

                segundoOut.writeObject(c);
                c = (Combate) segundoIn.readObject();
                if(c.p1.getVida()<=0){
                    break;
                }




            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                player1Socket.close();
                player2Socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

