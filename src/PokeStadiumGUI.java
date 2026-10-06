import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokeStadiumGUI {

    private JPanel panel1;
    private JPanel panelPokemon2;
    private JPanel panelPokemon1;
    private JPanel panelCentral;
    private JProgressBar barraVida1;
    private JProgressBar barraVida2;
    private JLabel imagenPoke1;
    private JLabel nombrePoke1;
    private JLabel estadisticasPoke1;
    private JLabel imagenPoke2;
    private JLabel nombrePoke2;
    private JLabel estadisticasPoke2;
    private JTextField txtNombre1;
    private JTextField txtNombre2;
    private JButton btnRandom2;
    private JButton btnRandom1;
    private JButton btnCombatir;
    private JTextArea txtLog;
    private JButton btnCargar1;
    private JButton btnCargar2;

    private final PokeApiClient apiClient = new PokeApiClient();

    private Pokemon pokemon1;
    private Pokemon pokemon2;

    private void cargarPokemon1(){
        String nombre = txtNombre1.getText().trim();
        if (nombre.isEmpty()){
            txtLog.append("Escribe el nombre del pokemon 1. \n");
            return;
        }
    }




    public PokeStadiumGUI(){
        btnRandom1.setText("Random");
        btnRandom2.setText("Random");
        btnCombatir.setText("¡Combatir!");
        txtLog.setEditable(false);
        btnCargar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLog.append("Buscando Pokemon 1: " + txtNombre1.getText()+ "\n");
            }
        });
        btnCargar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLog.append(("Buscando Pokemon 2: "+ txtNombre2.getText()+"\n"));
            }
        });
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("PokeStadiumGUI");
        frame.setContentPane(new PokeStadiumGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


