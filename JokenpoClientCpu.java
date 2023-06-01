import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JokenpoClientCpu {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            int playerWins = 0;
            int cpuWins = 0;

            while (playerWins < 3 && cpuWins < 3) {
                System.out.println("Placar: Jogador " + playerWins + " - CPU " + cpuWins);
                System.out.print("Escolha (Pedra, Papel ou Tesoura), escreva exatamente conforme orientado: ");
                String playerChoice = scanner.nextLine();

                // Envia a escolha para o servidor
                out.println(playerChoice);

                // Recebe a resposta do servidor
                String serverResponse = in.readLine();
                System.out.println(serverResponse);

                if (serverResponse.contains("venceu esta rodada")) {
                    playerWins++;
                } else if (serverResponse.contains("CPU venceu esta rodada")) {
                    cpuWins++;
                }
            }

            // Recebe o resultado final do servidor
            String gameResult = in.readLine();
            System.out.println(gameResult);

            // Fecha as conexões
            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}