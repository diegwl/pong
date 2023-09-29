import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameBall;
    private Paddle userPaddle, pcPaddle;

    private int userScore, pcScore, bounceCount, speedBounce;

    private int userMouseY;

    public PongGame() {
        gameBall = new Ball(300, 200,3,3,3, 10, Color.YELLOW);
        userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
        pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);
        bounceCount = 0;
        speedBounce = 0;

        userMouseY = 0;

        userScore = 0;
        pcScore = 0;

        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        userMouseY = e.getY();

    }

    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        gameBall.paint(g);

        userPaddle.paint(g);
        pcPaddle.paint(g);

        g.setColor(Color.WHITE);
        g.drawString("Score - User [" + userScore + "] PC [" + pcScore + "] | Bounces: " + bounceCount + " | Speed: " + gameBall.getSpeed(), 250, 20);
    }

    public void gameLogic() {
        gameBall.moveBall();
        gameBall.bouceOffEdges(0, WINDOW_HEIGHT);

        userPaddle.moveTowards(userMouseY);
        pcPaddle.moveTowards(gameBall.getY());

        if (userPaddle.checkCollision(gameBall)){
            gameBall.reverseX();
            bounceCount++;
            speedBounce++;
        }
        if (pcPaddle.checkCollision(gameBall)){
            gameBall.reverseX();
            bounceCount++;
            speedBounce++;
        }

        if (speedBounce == 3) {
            gameBall.increaseSpeed();
            speedBounce = 0;
        }

        if (gameBall.getX() < 0) {
            pcScore++;
            reset();
        } else if (gameBall.getX() >= WINDOW_WIDTH - 7) {
            userScore++;
            reset();
        }
    }

    public void reset() {
        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCx(3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;
    }
}
