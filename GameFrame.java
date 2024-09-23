import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame() /*throws HeadlessException*/ {
        super();
        setBounds(100, 100, Constants.WindowW, Constants.WindowH);
        setVisible(true);
        gamePanel = new GamePanel();
        add(gamePanel);
    }
}
