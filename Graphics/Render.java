package Graphics;


public class Render {

    public final int width;
    public final int height;
    public final int[] pixels;

    //Initialize the width of 800 and height of 600 in the constructor
    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        //create 480,000 pixels
        this.pixels = new int[width * height];
    }

    public void draw (Render render, int xOffset, int yOffset) {
        for (int y = 0; y < render.height; y++) {
            int yPix = y + yOffset;
            for(int x = 0; x < render.width; x++) {
                int xPix = x + xOffset;
                pixels[xPix + yPix * width] = render.pixels[x + y * render.width];
                //System.out.println("x:" + x + " y:" + y);
            }
        }
    }
}

