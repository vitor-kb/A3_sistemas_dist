import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Cpu.Server.JokenpoServerCpu;
import Pvp.Server.JokenpoServerPvp;
import TextoLento.TextoLento;

public class Jogo {
    public static void main(String[] args) {
        // Textos para o inicio do game
        Scanner scanner = new Scanner(System.in);
        String titutlo = "Bem-vindo ao RPG dos Crias!\n";
        String ambiente = "Você está em um beco escuro no meio de SP, há dois caminhos para seguir. Escolha seu caminho:\n";
        String op1 = "1. Caminho da rua de baixo\n";
        String op2 = "2. Entrar no beco\n";
        String op3 = "3. BÔNUS: Jokenpo PVP\n";
        TextoLento.exibeTextoLento(titutlo, 50);
        TextoLento.exibeTextoLento(ambiente, 50);
        TextoLento.exibeTextoLento(op1, 25);
        TextoLento.exibeTextoLento(op2, 25);
        TextoLento.exibeTextoLento(op3, 25);
        int caminho = scanner.nextInt();

        // Switch case simples para definir qual caminho o personagem ira seguir
        switch (caminho) {
            case 1:
                System.out.println("Você escolheu o caminho da esquerda.");
                System.out.println("Você encontrou um inimigo!");
                lutar();
                break;
            case 2:
                System.out.println("Você escolheu o caminho da direita.");
                System.out.println("Você encontrou um tesouro!");
                coletarTesouro();
                break;
            case 3:
                System.out.println("Você escolheu a modalidade bônus.");
                jokenpo();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
        scanner.close();
    }

    // Funcoes estaticas para as ações do jogador
    public static void lutar() {
        System.out.println("Começa a batalha!");
        System.out.println("Você precisa vencer 3 vezes para avançar!");
        System.out.println("Por favor, inicie o cliente do Jogador");
        System.out.println("Inimigo:");
        inimigoEmAscii();
        JokenpoServerCpu.main(null);
    }

    public static void coletarTesouro() {
        System.out.println("Você encontrou um tesouro incrível!");
    }

    public static void jokenpo() {
        System.out.println("Jogo PVP iniciado");
        System.out.println("Por favor, inicie os clientes do Jogador 1 e 2");
        JokenpoServerPvp.main(null);
    }

    public static void inimigoEmAscii() {
        try {
            // Carregue a imagem do arquivo
            BufferedImage image = ImageIO.read(new File("./Imgs/ladrao_digital.jpg"));

            // Redimensione a imagem para um tamanho menor (opcional)
            int newWidth = 40;
            int newHeight = (int) Math.floor((double) image.getHeight() * newWidth / image.getWidth());
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics()
                    .drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);

            // Converta cada pixel em um caractere ASCII e imprima
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
}
