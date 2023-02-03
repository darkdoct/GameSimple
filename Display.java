import Graphics.Render;
import Graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class Display extends Canvas implements Runnable {


    //static constant dont change and final dont change

    public static final String TITLE = "Game Simple Pre-Alpha 0.01";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    ///////////////////////////////////////////////////Thread start
    private Thread animator;
    private Render render;
    private Screen screen;
    private BufferedImage img;
    private int pixels[];
    private volatile boolean running = false;
    private void start() {
        if (running)
            return;
        //if not running set running to true

        running = true;
        animator = new Thread(this);
        animator.start();

        //
        //
        //
        //
        //
        //
        //
        //
        //
        //         System.out.println("Game Starting");
    }

    @Override
    public void run() {
        //make a game loop
        while (running) {
            gameUpdate();
            gameRender();
            // System.out.println("Game Running");
        }
    }

    private void gameUpdate() {
    }
    private void gameRender() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            //create 3d game by assigning 3
            createBufferStrategy(3);
            return;
        }

        screen.render();

        for (int i = 0; i < WIDTH*HEIGHT; i++ ){
            pixels[i] = screen.pixels[i];
        }


        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
        //System.out.println("Game Rendering");
    }

    private void stop(){
        if (!running)
            return;
        //otherwise stop
        running = false;
        try {
            animator.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
    ///////////////////////////////////////////////////Thread end

    public Display() {
        //render = new Render(WIDTH, HEIGHT);
        screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
    }

    public static void main(String[] args) {
        //create an object
        Display game = new Display();
        //create a window frame
        JFrame frame = new JFrame();
        //add the game to it
        frame.add(game);
        //turn off resize
        frame.setResizable(false);
        //turn on visible
        frame.setVisible(true);
        //set the size
        frame.setSize(WIDTH, HEIGHT);
        //terminate the console and running frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);


        game.start();
    }


}

