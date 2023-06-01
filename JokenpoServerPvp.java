import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class JokenpoServerPvp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite a porta:");
            int porta = scanner.nextInt();
            ServerSocket serverSocket = new ServerSocket(porta/*1234*/);
            System.out.println("Servidor iniciado. Aguardando conexoes...");

            Socket player1 = serverSocket.accept();
            System.out.println("Jogador 1 conectado.");

            Socket player2 = serverSocket.accept();
            System.out.println("Jogador 2 conectado.");

            BufferedReader player1Input = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            PrintStream player1Output = new PrintStream(player1.getOutputStream(), true);

            BufferedReader player2Input = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            PrintStream player2Output = new PrintStream(player2.getOutputStream(), true);

            player1Output.println("Voce e o Jogador 1.");
            player2Output.println("Voce e o Jogador 2.");

            while (true) {
                String movePlayer1 = player1Input.readLine();
                String movePlayer2 = player2Input.readLine();

                System.out.println("Jogada do Jogador 1: " + movePlayer1);
                System.out.println("Jogada do Jogador 2: " + movePlayer2);

                // Verificar se algum jogador deseja sair
                if (movePlayer1.equals("sair") || movePlayer2.equals("sair")) {
                    break;
                }

                if (!isValidMove(movePlayer1) || !isValidMove(movePlayer2)) {
                    player1Output.println("Jogada invalida. Tente novamente.");
                    player2Output.println("Jogada invalida. Tente novamente.");
                    continue;
                }

                String result = vencedor(movePlayer1, movePlayer2);

                player1Output.println(result);
                player2Output.println(result);

                if (result.equals("Empate")) {
                    player1Output.println("Proxima rodada!");
                    player2Output.println("Proxima rodada!");
                } else {
                    player1Output.println("Fim do jogo. Digite 'sair' para sair.");
                    player2Output.println("Fim do jogo. Digite 'sair' para sair.");

                    String quitPlayer1 = player1Input.readLine();
                    String quitPlayer2 = player2Input.readLine();

                    if (quitPlayer1.equals("sair") || quitPlayer2.equals("sair")) {
                        break;
                    }

                    player1Output.println("Proxima rodada!");
                    player2Output.println("Proxima rodada!");
                }
            }

            player1Output.println("Desconectando...");
            player2Output.println("Desconectando...");

            // Fechar as conexões individualmente em blocos try-catch separados
            try {
                player1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                player2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Reiniciando..");
        Jogo.main(null);
    }

    private static String vencedor(String movePlayer1, String movePlayer2) {
        if (movePlayer1.equals(movePlayer2)) {
            return "Empate";
        } else if ((movePlayer1.equals("Pedra") && movePlayer2.equals("Tesoura")) ||
                (movePlayer1.equals("Tesoura") && movePlayer2.equals("Papel")) ||
                (movePlayer1.equals("Papel") && movePlayer2.equals("Pedra"))) {
            return "Jogador 1 venceu!";
        } else {
            return "Jogador 2 venceu!";
        }
    }

    private static boolean isValidMove(String move) {
        return move.equals("Pedra") || move.equals("Papel") || move.equals("Tesoura");
    }
}
