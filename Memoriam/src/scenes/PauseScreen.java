package scenes;

import images.ImageLibrary;
import systems.SoundManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PauseScreen extends UIScreen {

    private JButton resumeBtn;
    private JButton menuBtn;
    private Image background = ImageLibrary.get().pauseBg;

    public PauseScreen(GameFrame gameFrame) {
        super("pause", gameFrame);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Paused");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        resumeBtn = gameFrame.createImageButton(ImageLibrary.get().practiceBtn, 250, 100);
        resumeBtn.addActionListener(e -> {
            SoundManager.get().playSFX("button");
            gameFrame.showPanel("start"); // resume game
        });

        menuBtn = gameFrame.createImageButton(ImageLibrary.get().practiceBtn, 250, 100);
        menuBtn.addActionListener(e -> {
            SoundManager.get().playSFX("button");
            gameFrame.showPanel("menu"); // menu
        });
        
        gameFrame.addHoverEffect(
            menuBtn,
            ImageLibrary.get().practiceBtn,
            ImageLibrary.get().practiceBtnHover,
            353, 100
        );

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(resumeBtn);
        panel.add(menuBtn);

        add(panel, BorderLayout.CENTER);
    }


}