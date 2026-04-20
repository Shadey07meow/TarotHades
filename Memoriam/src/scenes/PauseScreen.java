package scenes;

import images.ImageLibrary;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PauseScreen extends UIScreen {

    private JButton resumeBtn;
    private JButton menuBtn;

    public PauseScreen(GameFrame gameFrame) {
        super("pause");

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Paused");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        resumeBtn = gameFrame.createImageButton(ImageLibrary.get().optionBtn, 250, 100);
        resumeBtn.addActionListener(e -> {
            gameFrame.showPanel("start"); // resume game
        });

        menuBtn = gameFrame.createImageButton(ImageLibrary.get().optionBtn, 250, 100);
        menuBtn.addActionListener(e -> {
            gameFrame.showPanel("menu"); // menu
        });
        
        gameFrame.addHoverEffect(
            menuBtn,
            ImageLibrary.get().optionBtn,
            ImageLibrary.get().optionBtnHover,
            353, 100
        );

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(resumeBtn);
        panel.add(menuBtn);

        add(panel, BorderLayout.CENTER);
    }
}