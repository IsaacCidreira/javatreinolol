import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        StatusUserGame statusUserGame = new StatusUserGame();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o nick da sua conta:");
        String nickName = scanner.nextLine();

        System.out.print("Digite quantas wins você tem:");
        int vitoria = scanner.nextInt();

        System.out.print("Digite quantas loses você tem:");
        int lose = scanner.nextInt();

        statusUserGame.win = vitoria;
        statusUserGame.lose = lose;

        User user = new User();

        user.username = nickName;
        user.status = statusUserGame;

        CalcularElo calcularElo = new CalcularElo();
        calcularElo.mmr(user);

        System.out.printf("Olá %s o seu  MMR é: %d%n", user.username, user.mmr);
        System.out.printf("Olá %s o seu ELO é: %s%n", user.username, user.elo);

    }
}
