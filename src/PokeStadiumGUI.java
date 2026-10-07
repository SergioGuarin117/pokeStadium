import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;

public class PokeStadiumGUI implements BattleListener {

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
    private JLabel pokeBattle;

    private final PokeApiClient apiClient = new PokeApiClient();

    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Battle batallaActual;
    private Pokemon atacanteActual;
    private Pokemon defensorActual;
    private javax.swing.Timer temporizadorCombate;

    private void cargarPokemon1(){
        String nombre = txtNombre1.getText().trim();
        if (nombre.isEmpty()){
            txtLog.append("Escribe el nombre del pokemon 1. \n");

        }
        try {
            Pokemon cargado = apiClient.consultarPokemon(nombre);
            mostrarPokemon1(cargado);
            txtNombre1.setText(cargado.getNombre());
            txtLog.append("Pokemon 1 cargado: "+ cargado.getNombre()+"\n");
        }catch (Exception e){
            txtLog.append("No se pudo cargar el pokemon 1: "+ e.getMessage());
        }

    }

    private void cargarPokemon2(){
        String nombre = txtNombre2.getText().trim();
        if (nombre.isEmpty()){
            txtLog.append("Escribe el nombre del pokemon 1. \n");

        }
        try {
            Pokemon cargado = apiClient.consultarPokemon(nombre);
            mostrarPokemon2(cargado);
            txtNombre2.setText(cargado.getNombre());
            txtLog.append("Pokemon 2 cargado: "+ cargado.getNombre()+"\n");
        }catch (Exception e){
            txtLog.append("No se pudo cargar el pokemon 2: "+ e.getMessage());
        }
    }

    private void mostrarPokemon1(Pokemon cargado){
        this.pokemon1 = cargado;
        btnCombatir.setEnabled(pokemon1 != null && pokemon2 != null);
        nombrePoke1.setText(cargado.getNombre().toUpperCase(Locale.ROOT));
        barraVida1.setMaximum(cargado.getHp());
        barraVida1.setValue(cargado.getHpActual());
        barraVida1.setMaximum(cargado.getHp());
        barraVida1.setValue(cargado.getHpActual());
        actualizarColorVida(barraVida1);


        estadisticasPoke1.setText(
                "<html><table cellpadding='2'>"
                        + "<tr><td>HP:</td><td>" + cargado.getHp() + "</td></tr>"
                        + "<tr><td>Ataque:</td><td>" + cargado.getAtaque() + "</td></tr>"
                        + "<tr><td>Defensa:</td><td>" + cargado.getDefensa() + "</td></tr>"
                        + "<tr><td>Velocidad:</td><td>" + cargado.getVelocidad() + "</td></tr>"
                        + "</table></html>"
        );

        try {
            URL url = new URL(cargado.getSprite());
            ImageIcon icono = new ImageIcon(url);
            imagenPoke1.setIcon(icono);
            imagenPoke1.setText("");
        }catch (MalformedURLException e){
            imagenPoke1.setText("Imagen no disponible");
        }



    }

    private void mostrarPokemon2(Pokemon cargado){
        this.pokemon2 = cargado;
        nombrePoke2.setText(cargado.getNombre().toUpperCase(Locale.ROOT));
        barraVida2.setMaximum(cargado.getHp());
        barraVida2.setValue(cargado.getHpActual());
        btnCombatir.setEnabled(pokemon1 != null && pokemon2 != null);
        barraVida2.setMaximum(cargado.getHp());
        barraVida2.setValue(cargado.getHpActual());
        actualizarColorVida(barraVida2);

        estadisticasPoke2.setText(
                "<html><table cellpadding='2'>"
                        + "<tr><td>HP:</td><td>" + cargado.getHp() + "</td></tr>"
                        + "<tr><td>Ataque:</td><td>" + cargado.getAtaque() + "</td></tr>"
                        + "<tr><td>Defensa:</td><td>" + cargado.getDefensa() + "</td></tr>"
                        + "<tr><td>Velocidad:</td><td>" + cargado.getVelocidad() + "</td></tr>"
                        + "</table></html>"
        );

        try {
            URL url = new URL(cargado.getSprite());
            ImageIcon icono = new ImageIcon(url);
            imagenPoke2.setIcon(icono);
            imagenPoke2.setText("");
        }catch (MalformedURLException e){
            imagenPoke2.setText("Imagen no disponible");
        }


    }

    public void iniciarCombateLento(){
        txtLog.setText("");
        btnCombatir.setEnabled(false);
        batallaActual = new Battle(pokemon1, pokemon2, this);
        atacanteActual = batallaActual.IniciativaPokemon(pokemon1, pokemon2);
        defensorActual = (atacanteActual == pokemon1) ? pokemon2 : pokemon1;
        temporizadorCombate = new javax.swing.Timer(1000, e -> {
            batallaActual.atacar(atacanteActual, defensorActual);

            if (defensorActual.getHpActual() <= 0) {
                temporizadorCombate.stop();
                return;
            }

            Pokemon temporal = atacanteActual;
            atacanteActual = defensorActual;
            defensorActual = temporal;
        });

        temporizadorCombate.start();
    }

    private void cargarAleatorio1(){
        int id = (int) (Math.random()*151)+1;
        txtNombre1.setText(String.valueOf(id));
        cargarPokemon1();
    }

    private void cargarAleatorio2(){
        int id = (int) (Math.random()*151)+1;
        txtNombre2.setText(String.valueOf(id));
        cargarPokemon2();
    }

    private void actualizarColorVida(JProgressBar barra) {
        int porcentaje = (int)(100.0 * barra.getValue()/barra.getMaximum());

        if (porcentaje<=15){
            barra.setForeground(Color.RED);
        }else if(porcentaje<=59){
            barra.setForeground(Color.YELLOW);
        }else {
            barra.setForeground(Color.GREEN);
        }
    }





    public PokeStadiumGUI(){
        btnRandom1.setText("Random");
        btnRandom2.setText("Random");
        btnCombatir.setText("¡Combatir!");
        btnCombatir.setEnabled(false);
        txtLog.setEditable(false);

        pokeBattle.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        txtLog.setBackground(new Color(255, 248, 220));
        txtLog.setForeground(new Color(31, 41, 55));
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtLog.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(24, 35, 54), 3),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        btnCargar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLog.append("Buscando Pokemon 1: " + txtNombre1.getText()+ "\n");
                cargarPokemon1();
            }
        });
        btnCargar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLog.append(("Buscando Pokemon 2: "+ txtNombre2.getText()+"\n"));
                cargarPokemon2();
            }
        });

        btnRandom1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cargarAleatorio1();
            }
        });
        btnRandom2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cargarAleatorio2();
            }
        });
        btnCombatir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLog.setText("");
                iniciarCombateLento();
            }
        });

        panel1.setBackground(new Color(24, 35, 54));
        panelPokemon1.setBackground(new Color(215, 232, 255));
        panelPokemon2.setBackground(new Color(255, 225, 220));
        panelCentral.setBackground(new Color(238, 242, 250));
        pokeBattle.setForeground(new Color(255, 215, 80));
        pokeBattle.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
        //pokeBattle.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnCombatir.setBackground(new Color(220, 53, 69));
        btnCombatir.setForeground(Color.WHITE);


    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("POKESTADIUM");
        frame.setContentPane(new PokeStadiumGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,500);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("pokeball.png").getImage());
        URL iconUrl = PokeStadiumGUI.class.getResource("/pokeball.png");
        if (iconUrl != null) {
            frame.setIconImage(new ImageIcon(iconUrl).getImage());
        } else {
            System.out.println("No se encontró pokeball.png");
        }
        frame.setVisible(true);


    }

    @Override
    public void enTurno(String atacante, String defensor, int daño, boolean critico, double modificador) {
        if (daño == 0){
            txtLog.append(atacante + " Fallo el ataque!\n");
        }else {
            txtLog.append(atacante + " Ataca a " + defensor + " y causa "+ daño + (critico ? " ¡¡Critico!! " : "") + "\n");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        }
    }

    @Override
    public void cambioHp(String pokemon, int hpActual) {
        if (nombrePoke1.getText().equalsIgnoreCase(pokemon)){
            barraVida1.setValue(hpActual);
            actualizarColorVida(barraVida1);
        }else if(nombrePoke2.getText().equalsIgnoreCase(pokemon)){
            barraVida2.setValue(hpActual);
            actualizarColorVida(barraVida2);
        }

    }

    @Override
    public void finalizarBatalla(String ganador) {
        txtLog.append("¡"+ganador+" Ha ganado!\n");
    }

}