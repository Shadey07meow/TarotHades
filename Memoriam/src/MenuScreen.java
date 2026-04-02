import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuScreen extends ShowablePanel{
    // Initial screen when opening the game
    private final Image backgroundImage;
    private JButton startBtn;
    private JButton creditBtn;


    public MenuScreen(GameFrame gameFrame)
    {
        super("menu");
        this.backgroundImage = new ImageIcon(getClass().getResource("/assets/backgroundImage.PNG")).getImage();
        

        startBtn = gameFrame.createImageButton("/assets/startBtn.PNG", 350, 175);
        creditBtn = gameFrame.createImageButton("/assets/optionBtn.PNG", 350, 175);

        startBtn.addActionListener(e -> {
            gameFrame.showPanel("start");
        });
        creditBtn.addActionListener(e -> {
            gameFrame.showPanel("credits");
        });

        setLayout(new GridLayout(1,2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(350, 175, 0, 0));
        buttonPanel.setMaximumSize(new Dimension(500, 700));


        buttonPanel.add(startBtn);
        buttonPanel.add(creditBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
