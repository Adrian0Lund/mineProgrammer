import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    public Rute[][] ruter;
    public int rad, kol;
    public String filnavn;

    public Labyrint(String filnavn){
        this.filnavn = filnavn;
        lesFil();
        kobleRuter();
        System.out.println(this);
    }

    public void finnUtveiFra(int radNr, int kolNr){
        ruter[radNr][kolNr].finn(null, true);
    }

    public Rute hentRute(int radNr, int kolNr){ //Hentet fra Oblig1 (GameOfLife)
        if((radNr >= 0 && radNr < rad) && (kolNr >= 0 && kolNr < kol)){
            return ruter[radNr][kolNr];
        }
        else{
            return null;
        }
    }

    private void settNaboer(int radNr, int kolNr){ //Hentet fra Oblig1 (GameOfLife)
        for(int i = -1; i<2; i++){
            for(int j = -1; j<2; j++){
                if(i == 0 && j == 0) continue;

                if(i == 0 || j== 0){
                    if(hentRute(radNr+i, kolNr+j) != null){
                        ruter[radNr][kolNr].settNabo(ruter[radNr+i][kolNr+j], i, j);
                    }
                }
            }
        }
    }

    private void kobleRuter(){ //Hentet fra Oblig1 (GameOfLife)
        for(int i = 0; i<rad; i++){
            for(int j = 0; j<kol; j++){
                settNaboer(i, j);
            }
        }
    }

    private void lesFil(){
        try {
            File fil = new File(filnavn);
            Scanner filLeser = new Scanner(fil);
            int radNr = 0;
            boolean forste = true;

            while(filLeser.hasNextLine()){
                String linje = filLeser.nextLine();
                if(forste){
                    String[] tall = linje.strip().split(" ");
                    try {
                        rad = Integer.parseInt(tall[0]);
                        kol = Integer.parseInt(tall[1]); 
                        ruter = new Rute[rad][kol];
                        forste = false;
                        continue;
                    } catch (NumberFormatException e) {
                        System.out.println("Feil tall for rader eller kolonner");
                        System.exit(0);
                    }
                }

                linje.strip();
                for (int i = 0; i < kol; i++) {
                    char tegn = linje.charAt(i);

                    if(tegn == '#'){
                        ruter[radNr][i] = new SortRute(radNr, i, this);
                    }
                    else if (tegn == '.') {
                        if((radNr == 0 || radNr + 1 == rad) || (i == 0 || i+1 == kol)){
                            ruter[radNr][i] = new Aapning(radNr, i, this);
                        }
                        else{
                            ruter[radNr][i] = new HvitRute(radNr, i, this);
                        }
                    }
                    else{
                        System.out.println("Dette var ikke riktig tegn: " + tegn);
                        System.exit(0);
                    }
                }
                radNr++;
            }
            filLeser.close();

        } catch (FileNotFoundException e) {
            System.out.println("Filen ble ikke funnet");
            System.exit(0);
        }
    }

    public void refresh(){
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kol; j++) {
                ruter[i][j].restart();
            }
        }
    }

    public String toString(){
        String utskrift = "";
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kol; j++) {
                utskrift += ruter[i][j];
            }
            utskrift += "\n";
        }
        return utskrift;
    }
}
