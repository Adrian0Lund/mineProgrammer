import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rad;
        int kol;

        System.out.print("Skriv inn antall rader: ");
        rad = input.nextInt();
        System.out.print("Skriv inn antall kolonner: ");
        kol = input.nextInt();

        Verden verden = new Verden(rad, kol);
        verden.tegn();
        //verden.oppdatering();

        input.close();
    }
}
