import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarGame {

    public CarGame() {

        setup();

    }

    public static void main(String[] args) {

        setup();

        appFrame = new JFrame("Car Game");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(700,575);


        JPanel panelControl = new JPanel();

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new StartGame());
        panelControl.add(newGameButton);

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(new QuitGame());
        panelControl.add(quitButton);

        appFrame.getContentPane().add(panelControl, "South");

        appFrame.setVisible(true);


    }

    private static void setup() {

        try {
            background = ImageIO.read(new File("src/track700.png"));
            car1 = ImageIO.read(new File("src/bluecar.png"));
        } catch (IOException ioe) {

        }

    }

    private static class StartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            endGame = false;

            Thread t1 = new Thread(new Animate());
            t1.start();
        }
    }

    private static class QuitGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class Animate implements Runnable {

        @Override
        public void run() {
            backgroundDraw();
//            if (endGame == false) {
//                backgroundDraw();
//            }
        }

        private static void backgroundDraw() {
            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(background, 0, 0, null);
        }
    }

    private static class CollisionChecker implements Runnable {

        @Override
        public void run() {

        }

        private static void backgroundDraw() {

            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(background, 0, 0, null);


        }

    }

    private static JFrame appFrame;

    private static BufferedImage background;
    private static BufferedImage car1;

    private static boolean endGame;


}