import java.util.Scanner;

public class Oblig7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Skriv inn filnavn: ");
        String filnavn = input.nextLine();

        Labyrint lab = new Labyrint(filnavn);

        System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
        String valg = input.nextLine();

        while(!valg.equals("-1")){
            System.out.println("Aapninger: ");
            String[] biter = valg.strip().split(" ");
            lab.refresh();
            try {
                lab.finnUtveiFra(Integer.parseInt(biter[0]), Integer.parseInt(biter[1]));
            } catch (NumberFormatException e) {
                System.out.println("Du maa skrive inn heltall som koordinater");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Du maa skrive to heltall med mellomrom som koordinater");
            }

            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            valg = input.nextLine();
        }
        input.close();
    }
}