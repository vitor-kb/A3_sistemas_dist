import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class JokenpoClientPvp {
        public static void main(String[] args) {
            try {
                Socket socket = new Socket("localhost", 1234);
    
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }