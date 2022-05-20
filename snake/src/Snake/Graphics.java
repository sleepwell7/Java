package Snake;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics
extends JPanel
implements ActionListener{
    private Timer t = new Timer(100, this);
    public String state;
    private Snake s;
    private Food f;
    private Game game;


    public Graphics(Game g){
        t.start();
        state = "START";




        game = g;
        s = g.getPlayer();
        f = g.getFood();

        //add Key listner
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;



        int playerScore = (s.getBody().size() - 3) * 10;
        int highScore = 0;
        
        try {
            File myObj = new File("highscore.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                highScore = Integer.parseInt(data);
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println("Error");
        }

        // Paint Background
        Color backgroundColor = new Color(153-102);
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, Game.width * Game.dimension + 10, Game.height * Game.dimension + 10);


        // Determines what to paint based on game state
        if (state == "START") {
            // Beginning text if game is not running
            g2d.setColor(Color.WHITE);
            g2d.drawString("Press any Key to begin", Game.width / 2 * Game.dimension - 50, Game.height / 2 * Game.dimension - 15);
        } else if(state == "RUNNING"){
            // Paint Food when game starts
            g2d.setColor(Color.RED);
            //g2d.fillRect(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension, Game.dimension);
            g2d.fillOval(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension - 6, Game.dimension - 6);
            // Paint Snake when game starts
            g2d.setColor(Color.GREEN);
            for (Rectangle r : s.getBody()) {
                g2d.fill(r);
            }
            // Paint player score
            g2d.setColor(Color.WHITE);
            g2d.drawString("Score " + (playerScore),10, 15);
            // Paint high score
            g2d.setColor(Color.WHITE);
            if (playerScore > highScore){
                highScore = playerScore;
            }
            g2d.drawString("High Score: " + highScore,Game.width * Game.dimension - 100, 15);
        }else {
            //paints score when game over
            g2d.setColor(Color.WHITE);
            g2d.drawString("GAME OVER!", Game.width / 2 * Game.dimension - 35, Game.height / 2 * Game.dimension - 15);
            g2d.drawString("Your Score is: " + ((s.getBody().size() - 3) * 10), Game.width / 2 * Game.dimension - 45, Game.height / 2 * Game.dimension);

            if (playerScore >= highScore) {
                g2d.setColor(Color.WHITE);
                g2d.drawString("Congratulations: You have the new High Score", Game.width / 2 * Game.dimension - 125, Game.height / 2 * Game.dimension + 20);
                try {
                    FileWriter myWriter = new FileWriter("highscore.txt");
                    myWriter.write(String.valueOf(playerScore));
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error has occurred");
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();

        game.update();
    }

}
