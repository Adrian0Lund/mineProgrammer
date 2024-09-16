import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUIRutenett {
    public int rad, kol;
    public Rutenett rutenett;
    public JButton[][] knappeliste;

    public GUIRutenett(int rad, int kol, Rutenett rutenett){
        this.rad = rad;
        this.kol = kol;
        this.rutenett = rutenett;
        startGUI();
    }
    public void startGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        JFrame vindu = new JFrame("Game of life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menyPanel = new JPanel();
        
        JLabel antLeveneLabel = new JLabel("Generasjon nummer: 0.   Antall levende: 0");
        JButton startKnapp = new JButton("Start");
        JButton avsluttKnapp = new JButton("Avslutt");

        menyPanel.add(antLeveneLabel);
        menyPanel.add(startKnapp);
        menyPanel.add(avsluttKnapp);

        vindu.add(menyPanel, BorderLayout.NORTH);

        JPanel knappePanel = new JPanel(new GridLayout(rad, kol));
        knappeliste = new JButton[rad][kol];
        
        for(int i = 0; i<rad; i++){
            for (int j = 0; j < kol; j++) {
                JButton knapp = new JButton();
                knapp.setBackground(Color.WHITE);
                knappePanel.add(knapp);
                knappeliste[i][j] = knapp;
                knapp.addActionListener(new celleKnappTrykk(knapp));
            }
        }

        startKnapp.addActionListener(new knappStart(rutenett, knappeliste, antLeveneLabel));
        avsluttKnapp.addActionListener(new knappAvslutt());

        vindu.add(knappePanel, BorderLayout.CENTER);

        vindu.pack();  
        vindu.setLocationRelativeTo(null);
	    vindu.setVisible(true);
    }
}