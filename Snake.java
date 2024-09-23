import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Snake {
    int x, y, w, h;
    Direction direction = Direction.HOLD;

    ArrayList<Rectangle> parts = new ArrayList<>();

    public Snake(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        parts.add(new Rectangle(x, y, w, h));
    }

    public void draw(Graphics g) {
        //g.drawRect(x, y, w, h);
        int rColor = 0, gColor = 102, bColor = 102, cnt = parts.size();
        for (int i = 0; i < cnt; ++i) {
            int newRColor = (int) ((double) (255 - rColor) * (1.0 - (double) i / cnt)) + rColor;
            int newGColor = (int) ((double) (255 - gColor) * (1.0 - (double) i / cnt)) + gColor;
            int newBColor = (int) ((double) (255 - bColor) * (1.0 - (double) i / cnt)) + bColor;
            g.setColor(new Color(newRColor, newGColor, newBColor));
            g.fillRect(parts.get(i).x, parts.get(i).y, (int) parts.get(i).getWidth(), (int) parts.get(i).getHeight());
            g.setColor(new Color(0, 0, 0));
            g.drawRect(parts.get(i).x, parts.get(i).y, (int) parts.get(i).getWidth(), (int) parts.get(i).getHeight());
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean move(boolean needPop) {
        if (needPop) {
            parts.removeFirst();
        }
        switch (direction) {
            case UP:
                y -= h;
                break;
            case DOWN:
                y += h;
                break;
            case LEFT:
                x -= w;
                break;
            case RIGHT:
                x += w;
                break;
        }
        x = (x + Constants.WindowW) % Constants.WindowW;
        y = (y + Constants.WindowH) % Constants.WindowH;

        Rectangle newPart = new Rectangle(x, y, w, h);
        for (Rectangle part : parts) {
            if (part.intersects(newPart)) {
                return false;
            }
        }
        parts.add(newPart);
        return true;
    }

    public Rectangle getFigure() {
        return new Rectangle(x, y, w, h);
    }
}
