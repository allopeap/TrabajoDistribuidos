package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PokemonGameGUI extends JFrame {
    private JPanel panelPokemon1, panelPokemon2, panelAttacks;
    private JButton btnAttack1, btnAttack2, btnAttack3, btnAttack4;
    private JProgressBar progressBarPokemon1, progressBarPokemon2;

    public PokemonGameGUI(Combate c) {
        // Configurar la ventana principal
        setTitle("Pokemon Battle");
        setSize(1920,1080 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Añadir espacio entre componentes

        // Paneles para los Pokémon y las barras de vida
        panelPokemon1 = createPokemonPanel(c.p1.getNombre(),true);
        panelPokemon2 = createPokemonPanel(c.p2.getNombre(),false);

        // Configurar el tamaño de los botones
        Dimension buttonSize = new Dimension(100, 30); // Ajustar según tus necesidades

        List<Ataque> l = c.p1.getAtaques();
        btnAttack1 = new JButton(l.get(0).getNombre());
        btnAttack1.setPreferredSize(buttonSize);

        btnAttack2 = new JButton(l.get(1).getNombre());
        btnAttack2.setPreferredSize(buttonSize);

        btnAttack3 = new JButton(l.get(2).getNombre());
        btnAttack3.setPreferredSize(buttonSize);

        btnAttack4 = new JButton(l.get(3).getNombre());
        btnAttack4.setPreferredSize(buttonSize);

        // Panel para los botones de ataque
        panelAttacks = new JPanel();
        panelAttacks.setLayout(new GridLayout(1, 4, 10, 10));
        panelAttacks.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Agregar botones al panel de ataques
        panelAttacks.add(btnAttack1);
        panelAttacks.add(btnAttack2);
        panelAttacks.add(btnAttack3);
        panelAttacks.add(btnAttack4);

        // Agregar paneles a la ventana
        add(panelPokemon1, BorderLayout.WEST);
        add(panelPokemon2, BorderLayout.EAST);
        add(panelAttacks, BorderLayout.SOUTH);

        // Mostrar la ventana
        setVisible(true);

        // Manejadores de eventos para los botones de ataque
        btnAttack1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el ataque 1

                c.p2.recibirDano(c.p1.atacar(0));
                updateHealthBars(c.p2.getVida()); // Actualizar barras de vida después de un ataque
            }
        });

        btnAttack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el ataque 2
                c.p2.recibirDano(c.p1.atacar(1));
                updateHealthBars(c.p2.getVida());
            }
        });

        btnAttack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el ataque 3
                c.p2.recibirDano(c.p1.atacar(2));
                updateHealthBars(c.p2.getVida());
            }
        });

        btnAttack4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el ataque 4
                c.p2.recibirDano(c.p1.atacar(3));
                updateHealthBars(c.p2.getVida());
            }
        });


    }

    private void updateHealthBars(double d) {



        progressBarPokemon2.setValue((int) d);
    }



    private JPanel createPokemonPanel(String pokemonName, boolean isFirstPokemon) {
        JPanel panelPokemon = new JPanel();
        panelPokemon.setLayout(new BorderLayout());

        JLabel lblPokemon = new JLabel(pokemonName, JLabel.CENTER);
        ImageIcon pokemonImage = new ImageIcon("images/" + pokemonName.toLowerCase() + ".png");
        lblPokemon.setIcon(pokemonImage);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(100);
        progressBar.setStringPainted(true);

        // Configurar el tamaño de la barra de vida
        Dimension progressBarSize = new Dimension(150, 20); // Ajustar según tus necesidades
        progressBar.setPreferredSize(progressBarSize);

        // Agregar componentes al panel
        panelPokemon.add(lblPokemon, BorderLayout.CENTER);
        panelPokemon.add(progressBar, BorderLayout.NORTH);

        // Asignar la barra de progreso a la variable correspondiente según si es el primer o segundo Pokémon
        if (isFirstPokemon) {
            progressBarPokemon1 = progressBar;
        } else {
            progressBarPokemon2 = progressBar;
        }

        return panelPokemon;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListaPokemon l = new ListaPokemon();

                Combate c = new Combate(l.getPokemons().get(0),l.getPokemons().get(1));
                PokemonGameGUI pg = new PokemonGameGUI(c);


            }
        });
    }
}

