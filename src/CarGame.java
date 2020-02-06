import com.sun.source.doctree.AttributeTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;

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

        bindKey(panelControl, "UP");
        bindKey(panelControl, "DOWN");

        appFrame.getContentPane().add(panelControl, "South");
        appFrame.setVisible(true);


    }

    private static void setup() {

        try {
            background = ImageIO.read(new File("src/track700.png"));
            car1 = ImageIO.read(new File("src/bluecar.png"));
            car2 = ImageIO.read(new File("src/greencar.png"));

        } catch (IOException ioe) {

        }

    }

    private static class StartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //endGame = true;



            Thread t1 = new Thread(new Animate());
            Thread t2 = new Thread(new PlayerMoverOne());
            t1.start();
            t2.start();
        }
    }

    private static class PlayerMoverOne implements Runnable {

        private double velocitystep;
        private double rotatestep;

        public PlayerMoverOne() {

            velocitystep = 0.01;
            rotatestep = 0.05;
        }

        @Override
        public void run() {
            while (endGame == false) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }

                if (upPressed == true) {
                    playerOneVelocity = playerOneVelocity + velocitystep;
                }

                if (downPressed == true) {
                    playerOneVelocity = playerOneVelocity - velocitystep;
                }

//                if (leftPressed == true) {
//                    if (playerOneRotation < 0.0) {
//                        playerOneRotation
//                    }
//                }


            }
        }
    }

    private static class QuitGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static void bindKey(JPanel panel, String inputStr) {


        panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed " + inputStr), inputStr + " pressed");
        panel.getActionMap().put(inputStr + " pressed", new KeyPressed(inputStr));

        panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released " + inputStr), inputStr + " released");
        panel.getActionMap().put(inputStr + " released", new KeyReleased(inputStr));

    }

    private static class KeyPressed extends AbstractAction {

        public KeyPressed() {
            action = "";
        }

        public KeyPressed(String input) {
            action = input;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = true;
                System.out.println("working");
            }

            if (action.equals("DOWN")) {
                downPressed = true;
            }
        }

        private static String action;
    }

    private static class KeyReleased extends AbstractAction {

        public KeyReleased() {
            action = "";
        }

        public KeyReleased(String input) {
            action = input;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = false;
            }

            if (action.equals("DOWN")) {
                downPressed = false;
            }
        }

        private static String action;

    }



    private static class Animate implements Runnable {

        @Override
        public void run() {
           // while (endGame == false)
            backgroundDraw();
            playerOneDraw();

            if (upPressed) {
                //System.out.println("pressed up");
            }

            if (!upPressed) {
                //System.out.println("released up");
            }

//            if (endGame == false) {
//                backgroundDraw();
//            }
        }

        private static void backgroundDraw() {
            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(background, 0, 0, null);
        }

        private static void playerOneDraw() {

            Graphics g = appFrame.getGraphics();
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(car1, 50, 100, null);

        }
    }

    private static class CollisionChecker implements Runnable {

        @Override
        public void run() {

        }

    }

    private static JFrame appFrame;

    private static BufferedImage background;
    private static BufferedImage car1;
    private static BufferedImage car2;

    private static boolean upPressed;
    private static boolean downPressed;
    private static boolean leftPressed;
    private static boolean rightPressed;

    private static double playerOneVelocity;
    private static double playerTwoVelocity;

    private static double playerOneRotation;
    private static double playerTwoRotation;

    private static boolean endGame;

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;


}