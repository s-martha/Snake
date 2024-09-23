import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private Snake snake;
    private Timer timer;
    private Food food;

    public GamePanel() {
        this.snake = new Snake(100, 100, Constants.SnakePartW, Constants.SnakePartH);
        setFocusable(true);
        addKeyListener(this);
        this.timer = new Timer(Constants.TimerDelay, this);
        timer.start();
        this.food = new Food(Constants.FoodW, Constants.FoodH, Constants.WindowW - Constants.FoodW - 20, Constants.WindowH - Constants.FoodH - 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        food.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {  // no use of it
        //System.out.println("typed   " + e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                if (timer.isRunning()) {
                    timer.stop();
                    snake.setDirection(Direction.HOLD);
                } else {
                    timer.start();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("released   " + e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean needPop = true;
        if (isIntersection()) {
            food.generatePos();
            needPop = false;
        }
        boolean allOk = snake.move(needPop);
        repaint();

        if (!allOk) {
            snake = new Snake(100, 100, Constants.SnakePartW, Constants.SnakePartH);
            snake.direction = Direction.HOLD;
            //timer.stop();
        }
    }

    private boolean isIntersection() {
        Rectangle snakeRect = snake.getFigure();
        Rectangle foodRect = food.getFigure();
        return snakeRect.intersects(foodRect);
    }
}

