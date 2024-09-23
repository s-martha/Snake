import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Food {
    int x, y, w, h, ScreenSzW, ScreenSzH;

    public Food(int w, int h, int ScreenSzW, int ScreenSzH) {
        this.w = w;
        this.h = h;
        this.ScreenSzW = ScreenSzW;
        this.ScreenSzH = ScreenSzH;
        generatePos();
    }

    public void generatePos() {
        Random rand = new Random();
        this.x = 10 + rand.nextInt(ScreenSzW);
        this.y = 10 + rand.nextInt(ScreenSzH);
    }

    public void draw(Graphics g) {
        Random rand = new Random();
        int rColor = rand.nextInt(256);
        int gColor = rand.nextInt(256);
        int bColor = rand.nextInt(256);
        g.setColor(new Color(rColor, gColor, bColor));
        g.fillRect(x, y, w, h);
        g.setColor(new Color(0,0,0));
        g.drawRect(x, y, w, h);
    }

    public Rectangle getFigure() {
        return new Rectangle(x, y, w, h);
    }

    public void intersect(int x, int y, int w, int h) {
        generatePos();
    }
}
