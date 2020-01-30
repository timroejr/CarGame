import javax.swing.*;

public class CarGame {

    private static JFrame appFrame;

    public CarGame() {

        setup();

    }

    public static void main(String[] args) {

        setup();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(500,500);
        appFrame.setVisible(true);

    }

    public static void setup() {

        appFrame = new JFrame("Car Game");


    }


}