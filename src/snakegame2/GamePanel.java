package snakegame2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.font.TextAttribute.FONT;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
    static final int UNIT_SIZE = 20;
    static final int GAME_WIDTH = 540;
    static final int GAME_HEIGHT = 540;
    static final int DELAY = 100;
    Snake snake;
    Timer timer;
    boolean isRunning;
    Token token;
    boolean moveFlag;
    
    GamePanel(){
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.addKeyListener(new KeyAdapterListener());
        
        snake = new Snake(UNIT_SIZE, GAME_HEIGHT, GAME_WIDTH);
        token = new Token(snake, UNIT_SIZE, GAME_HEIGHT, GAME_WIDTH);
        timer = new Timer(DELAY, this);
        timer.start();
        isRunning = true;
        moveFlag = false;
    }
    
    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       
       draw(g);
    }

    void draw(Graphics g){
        for(int i = 0; i< GAME_HEIGHT/UNIT_SIZE; i++)
            {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, GAME_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, GAME_WIDTH, i*UNIT_SIZE);
        }
        if(isRunning){

            
            snake.draw(g);
            token.draw(g);
            points(g);
        }
        else
            gameOver(g);
    }
    
    void gameOver(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 40));
        g.drawString("GAME OVER" , UNIT_SIZE*9, GAME_HEIGHT/2);
        points(g);
    }
    
    void points(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("Points: " + token.getPoints(), UNIT_SIZE, GAME_HEIGHT-UNIT_SIZE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isRunning){
            token.checkCollect();
            snake.move();
            moveFlag = true;
            if(snake.checkCollision())
                isRunning = !isRunning;
        }
        repaint();
    }
    
    public class KeyAdapterListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            if(moveFlag){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        if(snake.direction != 'L'){
                            snake.direction = 'R';
                            moveFlag = false;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(snake.direction != 'D'){
                            snake.direction = 'U';
                            moveFlag = false;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake.direction != 'U'){
                            snake.direction = 'D';
                            moveFlag = false;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(snake.direction != 'R'){
                            snake.direction = 'L';
                            moveFlag = false;
                        }
                        break;
                }
            }
        }
    }
        
    
}
