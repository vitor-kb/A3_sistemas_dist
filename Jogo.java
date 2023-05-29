import java.util.Scanner;

import Cpu.Server.JokenpoServerCpu;
import Pvp.Server.JokenpoServerPvp;
import TextoLento.TextoLento;

public class Jogo {
    public static void main(String[] args) {
        //Textos para o inicio do game
        Scanner scanner = new Scanner(System.in);
        String titutlo = "Bem-vindo ao RPG dos Crias!\n";
        String ambiente = "Você está em um beco escuro no meio de SP, há dois caminhos para seguir. Escolha seu caminho:\n";
        String op1 = "1. Caminho da esquerda\n";
        String op2 = "2. Caminho da direita\n";
        String op3 = "3. BÔNUS: Jokenpo PVP\n";
        TextoLento.exibeTextoLento(titutlo, 50);
        TextoLento.exibeTextoLento(ambiente, 50);
        TextoLento.exibeTextoLento(op1, 25);
        TextoLento.exibeTextoLento(op2, 25);
        TextoLento.exibeTextoLento(op3, 25);
        int caminho = scanner.nextInt();

        //Switch case simples para definir qual caminho o personagem ira seguir
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
            default:
                System.out.println("Opção inválida.");
                break;
        }
        scanner.close();
    }

    //Funcoes estaticas para as ações do jogador
    public static void lutar() {
        System.out.println("Começa a batalha!");
        System.out.println("Por favor, inicie o cliente do Jogador");
        System.out.println("Você precisa vencer 3 vezes para avançar!");
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
    
}
