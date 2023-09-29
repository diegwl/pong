import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static JFrame frame = new JFrame("Pong");

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(650, 515);
        frame.setResizable(false);

        PongGame game = new PongGame();

        frame.add(game);

        frame.setVisible(true);


        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                game.gameLogic();

                game.repaint();

            }
        });

        timer.start();


    }
}
