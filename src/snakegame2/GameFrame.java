package snakegame2;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    GameFrame(){
        this.getContentPane().add(new GamePanel());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snek");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
    }
}
