package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Partida iniciada. Esperando conexiones...");

            while (true) {
                Socket player1Socket = serverSocket.accept();
                System.out.println("Jugador 1 conectado.");

                Socket player2Socket = serverSocket.accept();
                System.out.println("Jugador 2 conectado.");

                // Iniciar un nuevo hilo para manejar la batalla entre los dos jugadores
                new Batalla(player1Socket, player2Socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

