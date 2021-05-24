package snakegame2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
    ArrayList<Point> snakePoints; 
    final int STARTSIZE = 5;
    char direction;
    static int GAME_HEIGHT, GAME_WIDTH, UNIT_SIZE;
    int snakeSize;
    
    Snake(int UNIT_SIZE, int GAME_HEIGHT, int GAME_WIDTH){
        
        snakePoints = new ArrayList<Point>();
        direction = 'R';
        snakeSize = STARTSIZE;
        
        this.UNIT_SIZE = UNIT_SIZE;
        this.GAME_HEIGHT = GAME_HEIGHT;
        this.GAME_WIDTH = GAME_WIDTH;
        
        createSnake();
    }
    
    void createSnake(){
        for(int i = 0; i < STARTSIZE; i++){
            if(i == 0)
                snakePoints.add(new Point((GAME_WIDTH/UNIT_SIZE)/2*UNIT_SIZE, (GAME_HEIGHT/UNIT_SIZE)/3*UNIT_SIZE));
            else
                snakePoints.add(new Point(snakePoints.get(i-1).getX()-UNIT_SIZE, snakePoints.get(i-1).getY()));
        }
        
    }

    void move(){
        for(int i = snakeSize-1; i > 0; i--){
            if(snakePoints.get(i) != null){
                snakePoints.get(i).setX(snakePoints.get(i-1).getX());
                snakePoints.get(i).setY(snakePoints.get(i-1).getY());
            }
            else{
                snakePoints.add(new Point(snakePoints.get(i-1).getX(), snakePoints.get(i-1).getY()));
            }
        }
        
        switch (direction){
            case 'R':
                snakePoints.get(0).setXY(snakePoints.get(0).getX() + UNIT_SIZE, snakePoints.get(0).getY());
                break;
            case 'L':
                snakePoints.get(0).setXY(snakePoints.get(0).getX() - UNIT_SIZE, snakePoints.get(0).getY());
                break;
            case 'U':
                snakePoints.get(0).setXY(snakePoints.get(0).getX(), snakePoints.get(0).getY() - UNIT_SIZE);
                break;
            case 'D':
                snakePoints.get(0).setXY(snakePoints.get(0).getX(), snakePoints.get(0).getY() + UNIT_SIZE);
                break;
        }
    }
    
    boolean checkCollision(){
        //kolizja: lewa sciana
        if(this.getHeadX() < 0)
            return true;
        //kolizja: prawa sciana
        if(this.getHeadX() > GAME_WIDTH)
            return true;
        //kolizja: dolna sciana
        if(this.getHeadY() < 0)
            return true;
        //kolizja: gorna sciana
        if(this.getHeadY() > GAME_HEIGHT)
            return true;
        //kolizja: waz
        for(int i = 1; i < snakePoints.size(); i++){
            if(this.getHeadX() == snakePoints.get(i).getX())
                if(this.getHeadY() == snakePoints.get(i).getY())
                    return true;
        }
        return false;
    }
    
    void draw(Graphics g){
        for(int i = 0; i < snakePoints.size(); i++){
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(snakePoints.get(i).getX(), snakePoints.get(i).getY(), UNIT_SIZE, UNIT_SIZE);
            }
            else{
                g.setColor(new Color(0, 128, 0));
                g.fillRect(snakePoints.get(i).getX(), snakePoints.get(i).getY(), UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    public void enlarge(){
        this.snakeSize++;
        snakePoints.add(new Point(snakePoints.get(snakePoints.size()-1).getX(), snakePoints.get(snakePoints.size()-1).getY()));
    }
    
    public void setDirection(char direction) {
        this.direction = direction;
    }
    
    public int getHeadX(){
        return snakePoints.get(0).getX();
    }
    
    public int getHeadY(){
        return snakePoints.get(0).getY();
    }
    
    
}
