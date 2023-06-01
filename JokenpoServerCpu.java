import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JokenpoServerCpu {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite a porta:");
            int porta = scanner.nextInt();
            ServerSocket serverSocket = new ServerSocket(porta/*12345*/);
            System.out.println("Servidor iniciado. Aguardando conexão...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            int playerWins = 0;
            int cpuWins = 0;

            while (playerWins < 3 && cpuWins < 3) {
                // Recebe a escolha do jogador
                String playerChoice = "";
                while (playerChoice.equals("")) {
                    playerChoice = in.readLine();
                }
                System.out.println("Escolha do jogador: " + playerChoice);

                String cpuChoice = generateCpuChoice();
                System.out.println("Escolha da CPU: " + cpuChoice);

                String roundResult = determineWinner(playerChoice, cpuChoice);
                if (roundResult.equals("player")) {
                    out.println("Voce venceu esta rodada!");
                    playerWins++;
                } else if (roundResult.equals("cpu")) {
                    out.println("A CPU venceu esta rodada!");
                    cpuWins++;
                } else {
                    out.println("Empate! Tente novamente.");
                }
                if (playerWins == 2 && cpuWins == 1) {
                    break;
                }
                if (cpuWins == 2 && playerWins == 1) {
                    break;
                }
            }

            // Envia o resultado final para o cliente
            if (playerWins == 3) {
                out.println("Voce venceu o jogo!");
            } else {
                out.println("A CPU venceu o jogo!");
            }

            // Fechando as conexões
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Reiniciando..");
            Jogo.main(null);
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
                return "draw";
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