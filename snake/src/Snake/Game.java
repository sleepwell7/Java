package Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game
implements KeyListener{
    private Snake snake;
    private Food food;
    private Graphics graphics;

    private JFrame window;
    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;


    public Game() {

        window = new JFrame();
        snake = new Snake();
        food = new Food(snake);
        graphics = new Graphics(this);
        window.add(graphics);
        window.setTitle("Snake");
        window.setSize(width * dimension + 2, height * dimension + 4);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        graphics.state = "RUNNING";
    }

    public void update(){
        if(graphics.state == "RUNNING"){
           if(check_food_collision()){
               snake.grow();
               food.random_spawn(snake);
           } else if (check_wall_collision() || check_self_collision()) {
               graphics.state = "END";
           }else {
               snake.move();
           }
        }
    }

    //check if snake hits wall
    private boolean check_wall_collision (){
        if(snake.getX() < 0 || snake.getX() >= width * dimension
                || snake.getY() < 0 || snake.getY() >= height * dimension){
            return true;
        }
        return false;
    }

    // Check if snake eats food
    private boolean check_food_collision(){
        if (snake.getX() == food.getX() * Game.dimension
                && snake.getY() == food.getY() * Game.dimension){
            return true;
        }
        return false;
    }

    // Check if snake bites itself
    private boolean check_self_collision (){
        for(int i = 1; i < snake.getBody().size(); i++){
            if(snake.getX() == snake.getBody().get(i).x
                    && snake.getY() == snake.getBody().get(i).y){
                return true;
            }
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (graphics.state == "RUNNING") {
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                snake.up();
            } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                snake.down();
            } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                snake.left();
            } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D){
                snake.right();
            }
        }else{
            this.start();
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}

    public Snake getPlayer() {
        return snake;
    }

    public void setPlayer(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }
}

