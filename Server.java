import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Servidor iniciado. Aguardando conexões...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Novo cliente conectado: " + clientSocket);

                    // Inicie uma nova thread para lidar com o cliente
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clientHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Lógica de processamento do cliente
            // Você pode adicionar a lógica do jogo aqui
            String playerName = reader.readLine();
            Player player = new Player(playerName);
            writer.println("Bem-vindo, " + playerName + "! Você é um herói em busca de aventuras.");

            while (true) {
                String request = reader.readLine();
                if (request.equals("quit")) {
                    writer.println("Sessão encerrada.");
                    break;
                }

                // Verifica o comando do jogador
                if (request.equals("level")) {
                    player.levelUp();
                    writer.println("Parabéns! Você subiu para o nível " + player.getLevel() + ".");
                } else if (request.equals("skills")) {
                    writer.println("Habilidades disponíveis: " + player.getSkills());
                } else {
                    writer.println("Comando inválido. Tente novamente.");
                }
            }
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
