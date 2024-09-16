import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class celleKnappTrykk implements ActionListener {
    private JButton celleKnapp;
    private static boolean startet = false;

    public celleKnappTrykk(JButton button){
        celleKnapp = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(startet) return;

        if(celleKnapp.getBackground().equals(Color.WHITE)){
            celleKnapp.setBackground(Color.BLACK);
        }
        else{
            celleKnapp.setBackground(Color.WHITE);
        }
    }
    
    public static void iGang(){
        startet = true;
    }
}
