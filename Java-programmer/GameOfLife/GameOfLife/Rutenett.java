import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;

public class Rutenett {
    public int antRader;
    public int antKolonner;
    public Celle[][] rutene;

    public Rutenett(int rad, int kol){
        antRader = rad;
        antKolonner = kol;
        rutene = new Celle[rad][kol];
    }

    public void lagCelle(int rad, int kol, boolean levende){
        Celle nyCelle = new Celle();
        if(levende == true){
            nyCelle.settLevende();
        }
        rutene[rad][kol] = nyCelle;
    }

    public void kobleGUITilCeller(JButton[][] knappeliste){
        for(int i=0; i<antRader; i++){
            for(int j=0; j<antKolonner; j++){
                if(knappeliste[i][j].getBackground().equals(Color.BLACK)){
                    lagCelle(i, j, true);
                }
                else{
                    lagCelle(i, j, false);
                }
            } 
        }
    }

    public Celle hentCelle(int rad, int kol){
        if((rad>=0 && rad < antRader) && (kol>=0 && kol < antKolonner)){
            return rutene[rad][kol];
        }
        else{
            return null;
        }
    }

    public void tegnGUIRutenett(){
        new GUIRutenett(antRader, antKolonner, this);
    }

    public void settNaboer(int rad, int kol){       
        for(int i = -1; i<2; i++){
            for(int j = -1; j<2; j++){
                if(hentCelle(rad+i, kol+j) != null){
                    if(i != 0 || j != 0){
                        rutene[rad][kol].leggTilNabo(rutene[rad+i][kol+j]);
                    }
                }
            }
        }
    }

    public void kobleAlleCeller(){
        for(int i = 0; i<antRader; i++){
            for(int j = 0; j<antKolonner; j++){
                settNaboer(i, j);
            }
        }
    }

    public int antallLevende(){
        int levendeCeller = 0;
        
        for(int i = 0; i<antRader; i++){
            for(int j = 0; j<antKolonner; j++){
                if(rutene[i][j].erLevende()){
                    levendeCeller++;
                }
            }
        }

        return levendeCeller;
    }
}
