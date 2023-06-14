package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {

    private int x_1, x_2, y_1, y_2, x_ball, y_ball;
    private int x_velo, y_velo;

    private Timer timer;
    boolean isGameOver = false;



    public GameFrame() {

        x_1 = 0;
        x_2 = 760;
        y_1 = 300;
        y_2 = 300;
        x_ball = 390;
        y_ball = 390;
        x_velo = 1;
        y_velo = 1;

        this.setVisible(true);
        this.setSize(new Dimension(800, 800));
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);

         timer = new Timer(1, e -> {
            moveBall();
            paint(this.getGraphics());
        });
        timer.start();


    }



    public void moveBall() {
        if(y_ball > y_1 && y_ball < y_1 + 100 && x_ball <= 40) {
            x_velo *= -1;
        }
        if(y_ball > y_2 && y_ball < y_2 + 100 && x_ball >= 720) {
            x_velo *= -1;
        }

        if(x_ball + x_velo*2 < 740 && x_ball + x_velo*2 >= 40 ) {
            x_ball += x_velo*2;
        } else {
            if(x_ball <= 40) {
                drawEndScreen("Player 2", this.getGraphics());
            } else {
                drawEndScreen("Player 1", this.getGraphics());
            }
            isGameOver = true;

        }
        if(y_ball + y_velo*3 < 760 && y_ball + y_velo*3 > 0) {
            y_ball += y_velo*3;
        } else {
            y_velo *= -1;
        }


    }


    @Override
    public void paint(Graphics g) {
        if(isGameOver) {
            timer.stop();
            return;
        }
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, 800, 800);
        drawPanel(Color.red, graphics2D, x_1, y_1);
        drawPanel(Color.blue, graphics2D, x_2, y_2);
        drawBall(graphics2D, x_ball, y_ball);

    }

    public void drawPanel(Color color, Graphics2D graphics2D, int x, int y) {
        graphics2D.setColor(color);
        graphics2D.fillRect(x, y, 40, 100);
    }

    public void drawEndScreen(String winner, Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, 800, 800);
        graphics2D.setColor(Color.red);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 20));
        graphics2D.drawString("Game Over!", 350, 400);
        graphics2D.setColor(Color.green);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.drawString("Sieger: " + winner, 400, 450);
    }

    public void drawBall(Graphics2D graphics2D, int x, int y) {
        graphics2D.setColor(Color.white);
        graphics2D.fillOval(x, y, 40, 40);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w') {
            if(y_1 - 50 >= 0) {
                y_1 -= 50;
            }
            return;
        }
        if(e.getKeyChar() == 's') {
            if(y_1 + 50 <= 800) {
                y_1 += 50;
            }
            return;
        }
        if(e.getKeyChar() == 'i') {
            if(y_2 - 50 >= 0) {
                y_2 -= 50;
            }
            return;
        }
        if(e.getKeyChar() == 'k') {
            if(y_2 + 50 <= 800) {
                y_2 += 50;
            }
            return;
        }
    }
}
