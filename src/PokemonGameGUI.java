package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PokemonGameGUI extends JFrame {
    private JLabel pokemonImageLabel1, pokemonImageLabel2;
    private JButton attackButton1, attackButton2, attackButton3, attackButton4;
    private JProgressBar player1HealthBar, player2HealthBar;
    private JLabel player1NameLabel, player2NameLabel;

    Combate c = null;

    int jugador = 0;

    public PokemonGameGUI(Combate combate, int i) {
        this.c = combate;
        this.jugador = i;
        // Configuración básica del JFrame
        setTitle("Pokemon Battle: Player" + this.jugador);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicialización de componentes
        pokemonImageLabel1 = new JLabel(new ImageIcon("./src/images/" + c.p1.getNombre() + ".png"));
        pokemonImageLabel2 = new JLabel(new ImageIcon("./src/images/" + c.p2.getNombre() + ".png"));
        List<Ataque> l = null;
        if (this.jugador == 1) {
            l = c.p1.getAtaques();
        } else {
            l = c.p2.getAtaques();
        }
        attackButton1 = new JButton(l.get(0).getNombre());
        attackButton2 = new JButton(l.get(1).getNombre());
        attackButton3 = new JButton(l.get(2).getNombre());
        attackButton4 = new JButton(l.get(3).getNombre());

        player1HealthBar = new JProgressBar(0, 100);
        player1HealthBar.setValue((int) c.p1.getVida());
        player1HealthBar.setPreferredSize(new Dimension(300, 20)); // Ajusta el tamaño de la barra
        player2HealthBar = new JProgressBar(0, 100);
        player2HealthBar.setValue((int) c.p2.getVida());
        player2HealthBar.setPreferredSize(new Dimension(300, 20)); // Ajusta el tamaño de la barra

        player1NameLabel = new JLabel("Jugador 1" + ": " + c.p1.getNombre());
        player2NameLabel = new JLabel("Jugador 2"+": " + c.p2.getNombre());

        // Configuración de diseño
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(pokemonImageLabel1);
        centerPanel.add(pokemonImageLabel2);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Añade espacio entre los botones
        buttonPanel.add(attackButton1);
        buttonPanel.add(attackButton2);
        buttonPanel.add(attackButton3);
        buttonPanel.add(attackButton4);

        JPanel healthBarsPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Añade espacio entre las barras de vida
        healthBarsPanel.add(player1NameLabel);
        healthBarsPanel.add(player2NameLabel);
        healthBarsPanel.add(player1HealthBar);
        healthBarsPanel.add(player2HealthBar);

        // Agregar componentes al JFrame
        add(centerPanel, BorderLayout.CENTER);
        add(healthBarsPanel, BorderLayout.NORTH); // Coloca las barras de vida arriba
        add(buttonPanel, BorderLayout.SOUTH);




        // Configurar acciones de los botones
        attackButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador == 1) {
                    c.p2.recibirDano(c.p1.atacar(0));
                    actualizarBarras();

                } else {
                    c.p1.recibirDano(c.p2.atacar(0));
                    actualizarBarras();
                }
                invertirVisibilidad(false);

            }
        });

        attackButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador == 1) {
                    c.p2.recibirDano(c.p1.atacar(1));
                    actualizarBarras();
                } else {
                    c.p1.recibirDano(c.p2.atacar(1));
                    actualizarBarras();
                }
                invertirVisibilidad(false);

            }
        });

        attackButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador == 1) {
                    c.p2.recibirDano(c.p1.atacar(2));
                    actualizarBarras();
                } else {
                    c.p1.recibirDano(c.p2.atacar(2));
                    actualizarBarras();
                }
                invertirVisibilidad(false);

            }
        });

        attackButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jugador == 1) {
                    c.p2.recibirDano(c.p1.atacar(3));
                    actualizarBarras();
                } else {
                    c.p1.recibirDano(c.p2.atacar(3));
                    actualizarBarras();
                }
                invertirVisibilidad(false);

            }
        });
    }

    public boolean isVisible() {
        return attackButton1.isVisible();
    }

    public void invertirVisibilidad(boolean b) {
            if(b==false){
                attackButton1.setText("Espera tu turno");
                attackButton2.setText("Espera tu turno");
                attackButton3.setText("Espera tu turno");
                attackButton4.setText("Espera tu turno");
            } else {
                List<Ataque> l = null;
                if (this.jugador == 1) {
                    l = c.p1.getAtaques();
                } else {
                    l = c.p2.getAtaques();
                }
                attackButton1.setText(l.get(0).getNombre());
                attackButton2.setText(l.get(1).getNombre());
                attackButton3.setText(l.get(2).getNombre());
                attackButton4.setText(l.get(3).getNombre());
            }
            attackButton1.setVisible(b);
            attackButton2.setVisible(b);
            attackButton3.setVisible(b);
            attackButton4.setVisible(b);

    }
    public void actualizarBarras(){
        this.player1HealthBar.setValue((int) c.p1.getVida());
        this.player2HealthBar.setValue((int) c.p2.getVida());

    }
}

