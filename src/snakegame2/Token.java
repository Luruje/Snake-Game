package snakegame2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Token extends Point{
    
    static int UNIT_SIZE, GAME_HEIGHT, GAME_WIDTH;
    Random random;
    Snake snake;
    int points;
    
    Token(Snake snake, int UNIT_SIZE, int GAME_HEIGHT, int GAME_WIDTH){
        this.UNIT_SIZE = UNIT_SIZE;
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
        this.snake = snake;
        points = 0;
        random = new Random();
        newApple();
    }
    
    
    public void checkCollect(){
        if(snake.getHeadX()+UNIT_SIZE/2 >= this.x && snake.getHeadX()+UNIT_SIZE/2 <= this.x+20){
            if(snake.getHeadY()+UNIT_SIZE/2 >= this.y && snake.getHeadY()+UNIT_SIZE/2 <= this.y+20){
            newApple();
            points++;
            snake.enlarge();
            }
        }
    }
    
    void newApple(){
        this.x = random.nextInt((GAME_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        this.y = random.nextInt((GAME_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    void draw(Graphics g){
        g.setColor(Color.red);
        g.drawOval(x, y, 20, 20);
    }

    public int getPoints() {
        return points;
    }
    
}
