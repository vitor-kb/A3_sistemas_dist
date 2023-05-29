package Cpu.Server;
import java.io.*;
import java.net.*;

public class JokenpoServerCpu {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado. Aguardando conexão...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            int playerWins = 0;
            int cpuWins = 0;

            while (playerWins < 3 && cpuWins < 3) {
                // Recebe a escolha do jogador
                String playerChoice = in.readLine();
                System.out.println("Escolha do jogador: " + playerChoice);

                // Gera a escolha da CPU
                String cpuChoice = generateCpuChoice();
                System.out.println("Escolha da CPU: " + cpuChoice);

                // Determina o vencedor
                String roundResult = determineWinner(playerChoice, cpuChoice);
                if (roundResult.equals("player")) {
                    playerWins++;
                    out.println("Você venceu esta rodada!");
                } else if (roundResult.equals("cpu")) {
                    cpuWins++;
                    out.println("A CPU venceu esta rodada!");
                } else {
                    out.println("Empate! Tente novamente.");
                }
            }

            // Envia o resultado final para o cliente
            if (playerWins == 3) {
                out.println("Você venceu o jogo!");
            } else {
                out.println("A CPU venceu o jogo!");
            }

            // Fecha as conexões
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateCpuChoice() {
        // Gera um número aleatório entre 0 e 2
        int randomNum = (int) (Math.random() * 3);

        // Retorna a escolha correspondente
        switch (randomNum) {
            case 0:
                return "Pedra";
            case 1:
                return "Papel";
            case 2:
                return "Tesoura";
            default:
                return "";
        }
    }

    private static String determineWinner(String playerChoice, String cpuChoice) {
        // Lógica para determinar o vencedor
        if (playerChoice.equals(cpuChoice)) {
            return "draw";
        } else if (playerChoice.equals("Pedra")) {
            if (cpuChoice.equals("Tesoura")) {
                return "player";
            } else {
                return "cpu";
            }
        } else if (playerChoice.equals("Papel")) {
            if (cpuChoice.equals("Pedra")) {
                return "player";
            } else {
                return "cpu";
            }
        } else if (playerChoice.equals("Tesoura")) {
            if (cpuChoice.equals("Papel")) {
                return "player";
            } else {
                return "cpu";
            }
        }
        return "";
    }
}
