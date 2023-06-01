import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import TextoLento.TextoLento;

public class Jogo {
    public static void main(String[] args) {
        // Textos para o inicio do game
        Scanner scanner = new Scanner(System.in);
        String titutlo = "Bem-vindo ao RPG dos Crias!\n";
        String ambiente = "Voce esta em um beco escuro no meio de SP, ha dois caminhos para seguir. Escolha seu caminho:\n";
        String op1 = "1. Caminho da rua de baixo\n";
        String op2 = "2. Entrar no beco\n";
        String op3 = "3. BONUS: Jokenpo PVP\n";
        String op4 = "4. Sair\n";
        TextoLento.exibeTextoLento(titutlo, 50);
        TextoLento.exibeTextoLento(ambiente, 50);
        TextoLento.exibeTextoLento(op1, 25);
        TextoLento.exibeTextoLento(op2, 25);
        TextoLento.exibeTextoLento(op3, 25);
        TextoLento.exibeTextoLento(op4, 25);
        int caminho = scanner.nextInt();

        // Switch case simples para definir qual caminho o personagem ira seguir
        switch (caminho) {
            case 1:
                System.out.println("Voce escolheu o caminho da esquerda.");
                System.out.println("=============================");
                System.out.println("Voce encontrou um inimigo!");
                System.out.println("=============================");
                lutar();
                break;
            case 2:
                System.out.println("Voce escolheu o caminho da direita.");
                System.out.println("=============================");
                System.out.println("Voce encontrou um tesouro!");
                System.out.println("=============================");
                coletarTesouro();
                break;
            case 3:
                System.out.println("Voce escolheu a modalidade bonus.");
                System.out.println("=============================");
                jokenpo();
                break;
            case 4:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
        scanner.close();
    }

    // Funcoes estaticas para as ações do jogador
    public static void lutar() {
        System.out.println("=============================");
        System.out.println("Começa a batalha!");
        System.out.println("Voce precisa vencer 3 vezes para avançar!");
        System.out.println("=============================");
        System.out.println("Inimigo:");
        inimigoEmAscii();
        abrirClientCpu();
        JokenpoServerCpu.main(null);
    }

    public static void coletarTesouro() {
        System.out.println("=============================");
        System.out.println("Voce encontrou um CHINELO!");
        System.out.println("=============================");
        System.out.println("Reiniciando..");
        Jogo.main(null);
    }

    public static void jokenpo() {
        System.out.println("=============================");
        System.out.println("Jogo PVP iniciado");
        System.out.println("=============================");
        abrirClientPvp();
        abrirClientPvp();
        JokenpoServerPvp.main(null);
    }

    public static void inimigoEmAscii() {
        try {
            // Path da imagem
            BufferedImage image = ImageIO.read(new File("./Imgs/ladrao_digital.jpg"));

            // Redimensionamento da imagem para Ascii
            int newWidth = 40;
            int newHeight = (int) Math.floor((double) image.getHeight() * newWidth / image.getWidth());
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics()
                    .drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);

            // Converte cada pixel em um caractere ASCII e imprima
            for (int y = 0; y < resizedImage.getHeight(); y++) {
                for (int x = 0; x < resizedImage.getWidth(); x++) {
                    int pixel = resizedImage.getRGB(x, y);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;
                    int gray = (red + green + blue) / 3;

                    // Aplique o método de correspondência de caracteres
                    char asciiChar = getAsciiCharacter(gray);
                    System.out.print(asciiChar);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char getAsciiCharacter(int gray) {
        char asciiChar;
        if (gray >= 240) {
            asciiChar = ' ';
        } else if (gray >= 210) {
            asciiChar = '.';
        } else if (gray >= 190) {
            asciiChar = '-';
        } else if (gray >= 170) {
            asciiChar = '=';
        } else if (gray >= 140) {
            asciiChar = '+';
        } else if (gray >= 120) {
            asciiChar = '*';
        } else if (gray >= 100) {
            asciiChar = '#';
        } else {
            asciiChar = '#';
        }
        return asciiChar;
    }

    public static void abrirClientCpu() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Se estiver executando no Windows
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "java JokenpoClientCpu");
                // Define o diretório onde o arquivo Java está localizado
                File diretorio = new File("../A3_sistemas_dist");
                processBuilder.directory(diretorio);
            } else {
                throw new IOException("Sistema operacional nao suportado.");
            }

            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void abrirClientPvp() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // Se estiver executando no Windows
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "java JokenpoClientPvp");
                // Define o diretório onde o arquivo Java está localizado
                File diretorio = new File("../A3_sistemas_dist");
                processBuilder.directory(diretorio);
            } else {
                // Se não puder detectar o sistema operacional
                throw new IOException("Sistema operacional nao suportado.");
            }

            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
