import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class JokenpoClientPvp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o ip(host):");
            String host = scanner.next();
            System.out.println("Digite a mesma porta utilizada no servidor:");
            int porta = scanner.nextInt();
            Socket socket = new Socket(host, porta);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream output = new PrintStream(socket.getOutputStream(), true);

            String serverMessage = input.readLine();
            System.out.println(serverMessage);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Digite sua jogada (Pedra, Papel ou Tesoura), voce so precisa ganhar 1 vez: ");
                String move = consoleInput.readLine();

                output.println(move);

                String serverResponse = input.readLine();
                System.out.println(serverResponse);

                if (serverResponse.equals("Fim do jogo. Digite 'sair' para sair.")) {
                    String quit = consoleInput.readLine();

                    if (quit.equals("sair")) {
                        output.println("sair");
                        break;
                    }
                } else if (serverResponse.equals("Desconectando...")) {
                    break;
                }

                String nextRound = input.readLine();
                System.out.println(nextRound);
            }

            input.close();
            output.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
