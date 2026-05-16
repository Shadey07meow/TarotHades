package scenes;

import images.*;
import java.awt.*;
import javax.swing.*;

public class CreditScreen extends UIScreen {
    private JButton backBtn;
    private final Image creditBackground;

    public CreditScreen(GameFrame gameFrame) {
        super("credits", gameFrame);
        setLayout(new BorderLayout());

        this.creditBackground = ImageLibrary.get().creditBackground;

        JPanel creditsPanel = new JPanel();
        creditsPanel.setOpaque(false);
        creditsPanel.setLayout(new GridLayout(2, 2, 10, 10));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().placeHolderIcon,
                "FILLER",
                "FILLER"
        ));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().placeHolderIcon,
                "FILLER",
                "FILLER"
        ));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().placeHolderIcon,
                "FILLER",
                "FILLER"
        ));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().placeHolderIcon,
                "FILLER",
                "FILLER"
        ));



        add(creditsPanel, BorderLayout.CENTER);

        backBtn = gameFrame.createImageButton(ImageLibrary.get().backBtn, 150, 60);
        backBtn.addActionListener(e -> gameFrame.showPanel("menu"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createCreditItem(Image icon, String name, String desc) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel iconLabel = new JLabel(new ImageIcon(icon.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        JLabel text = new JLabel("<html><b>" + name + "</b><br/>" + desc + "</html>");
        text.setForeground(Color.WHITE);

        panel.add(iconLabel);
        panel.add(text);

        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(creditBackground, 0, 0, getWidth(), getHeight(), this);
    }
}