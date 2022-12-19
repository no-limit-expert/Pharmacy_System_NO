import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {
        Scanner input = new Scanner(System.in);
        System.out.println("Oppgi absolute path til Legesystem fil i .txt format:");
        String absolutePath = input.nextLine();
        input.close();

        Legesystem system = new Legesystem(absolutePath);

        system.startLegesystem();
    }
}

