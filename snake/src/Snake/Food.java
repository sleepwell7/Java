package Snake;

import java.awt.Rectangle;

public class Food{

    private int x;
    private int y;

    public  Food(Snake player) {
        this.random_spawn(player);
    }

    // Creates the food randomly on the screen
    public void random_spawn(Snake player){
        boolean onSnake = true;
        while(onSnake) {
            onSnake = false;

            x = (int)(Math.random() * Game.width);
            y = (int)(Math.random() * Game.height);
            for (Rectangle r : player.getBody()){
                if(r.x == x && r.y == y){
                    onSnake = true;
                }
            }

        }
    }

    //Getter and Setters for Food Location
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
