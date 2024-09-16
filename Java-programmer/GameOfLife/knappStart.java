import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class knappStart implements ActionListener {
    private boolean trykket = false;
    private Rutenett rutenett;
    private JButton[][] knappeliste;
    private JLabel antLevendeLabel;
    private int genNr = 0;

    public knappStart(Rutenett rn, JButton[][] kl, JLabel antLevende){
        this.rutenett = rn;
        this.knappeliste = kl;
        this.antLevendeLabel = antLevende;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(trykket) return;

        trykket = true;
        rutenett.kobleGUITilCeller(knappeliste);
        rutenett.kobleAlleCeller();       
        celleKnappTrykk.iGang();

        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException f) {
                System.exit(0);
            }

            tegn();
            antLevendeLabel.setText("Generasjon nummer : " + genNr + ".   Antall levende: " + rutenett.antallLevende());
            oppdatering();
        }
    }

    public void oppdatering(){
        genNr++;
        for(int i = 0; i<rutenett.antRader; i++){
            for(int j = 0; j<rutenett.antKolonner; j++){
                rutenett.hentCelle(i, j).tellLevendeNaboer();
                rutenett.hentCelle(i, j).oppdaterStatus();
            }
        }
    }

    public void tegn(){
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                if(rutenett.hentCelle(i, j).erLevende()){
                    knappeliste[i][j].setBackground(Color.BLACK);
                }
                else{
                    knappeliste[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}

/*
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                if(rutenett.hentCelle(i, j).antLevendeNaboer > 0){
                    System.out.println(i + "," + j + " sin antall levende naboer er: " + rutenett.hentCelle(i, j).antLevendeNaboer);
                }
            }
        }
 */