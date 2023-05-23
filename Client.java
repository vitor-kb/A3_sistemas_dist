import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conexão estabelecida com o servidor.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Lógica de processamento do cliente
            // Você pode adicionar a lógica do jogo aqui
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite seu nome de jogador: ");
            String playerName = scanner.nextLine();
            writer.println(playerName);

            System.out.println("Essa é a história do herói: " + playerName);
            String hist = "Você usou K10, acordou lombradasso, e agora não sabe onde está\n";
            String hist2 = "Ao acordar, você se depara com alguns poucos itens a sua frente: uma espada, uma adaga e um escudo\n";
            String hist3 = "Qual você escolhe?\n";
            TextoLento.exibirTextoLentamente(hist, 50);
            TextoLento.exibirTextoLentamente(hist2, 50);
            TextoLento.exibirTextoLentamente(hist3, 50);
                
            
            // Recebe a mensagem de boas-vindas do servidor
            String response = reader.readLine();
            System.out.println(response);
            while (true) {
                System.out.print("Comando (level/skills/quit): ");
                String command = scanner.nextLine();
                writer.println(command);

                // Recebe a resposta do servidor
                response = reader.readLine();
                System.out.println(response);

                if (command.equals("quit")) {
                    break;
                }
            }
            
            reader.close();
            writer.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
