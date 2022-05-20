package Snake;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Rectangle> body;
    private int w = Game.width;
    private int h = Game.height;
    private int d = Game.dimension;

    private String move;

    // creates a array to be the snakes body. Fills the body with three squares
    public Snake() {
        body = new ArrayList<>();
        Rectangle temp;
        int initial_count = 0;

        // Add three square elements to snake starting body
        while (initial_count < 3) {
            temp = new Rectangle(d, d);
            temp.setLocation((w / 2 - initial_count) * d, (h / 2) * d);
            body.add(temp);
            initial_count++;
        }

        move = "NOTHING";
    }

    // Create a Snake and add to front and remove back to help snake to move
    public void move() {
        if (move != "NOTHING") {
            Rectangle first = body.get(0);
            Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
            if (move == "UP") {
                temp.setLocation(first.x, first.y - Game.dimension);
            } else if (move == "DOWN") {
                temp.setLocation(first.x, first.y + Game.dimension);
            } else if (move == "LEFT") {
                temp.setLocation(first.x - Game.dimension, first.y);
            } else {
                temp.setLocation(first.x + Game.dimension, first.y);
            }
            body.add(0, temp);
            body.remove(body.size() - 1);

        }
    }

    //Add a rectangle to the snakes body while in motion
    public void grow() {
        Rectangle first = body.get(0);
        Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
        if (move == "UP") {
            temp.setLocation(first.x, first.y - Game.dimension);
        } else if (move == "DOWN") {
            temp.setLocation(first.x, first.y + Game.dimension);
        } else if (move == "LEFT") {
            temp.setLocation(first.x - Game.dimension, first.y);
        } else {
            temp.setLocation(first.x + Game.dimension, first.y);
        }
        body.add(0, temp);
    }

    //Get the body of the snake from the array
    public ArrayList<Rectangle> getBody() {
        return body;
    }

    public void setBody(ArrayList<Rectangle> body) {
        this.body = body;
    }

    public int getX() {
        return body.get(0).x;
    }

    public int getY() {
        return body.get(0).y;
    }

    //set move variable according to how the snake is supposed to move
    public void up() {
        if (move != "DOWN") {
            move = "UP";
        }

    }

    public void down() {
        if (move != "UP") {
            move = "DOWN";
        }
    }

    public void left() {
        if (move != "RIGHT"){
            move = "LEFT";
        }
    }
    public void right() {
        if (move != "LEFT") {
            move = "RIGHT";
        }
    }
}
