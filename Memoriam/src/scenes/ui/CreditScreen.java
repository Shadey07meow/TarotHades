package scenes.ui;

import images.*;
import java.awt.*;
import javax.swing.*;
import scenes.templates.UIScreen;

public class CreditScreen extends UIScreen {
    private JButton backBtn;
    private final Image creditBackground;

    public CreditScreen(GameFrame gameFrame) {
        super("credits", gameFrame);
        setLayout(new BorderLayout());

        this.creditBackground = ImageLibrary.get().creditBackground;

        JPanel creditsPanel = new JPanel();
        creditsPanel.setOpaque(false);
        creditsPanel.setLayout(new GridLayout(2, 2, 0, 0));
        creditsPanel.setBorder(BorderFactory.createEmptyBorder(325, 200, 10, 0));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().calryaIcon,
                "CalRya",
                "Lindsay Salvacion",
                "Graphics Programmer"
        ));


        creditsPanel.add(createCreditItem(
                ImageLibrary.get().shadeyIcon,
                "Shadey",
                "Daniel Hans Alava",
                "Project Architect"
        ));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().herielleIcon,
                "Herielle",
                "Herielle Margallo",
                "Systems Engineer"
        ));

        creditsPanel.add(createCreditItem(
                ImageLibrary.get().samIcon,
                "Sam",
                "Samantha Nicole Tabulao",
                "Sound Designer"
        ));


        JLabel specialThanks = new JLabel("<html><center> Special Thanks To: DAN (@kape_catpuccino) for the MEMORIAM logo !</center></html>");
        specialThanks.setForeground(Color.WHITE);
        specialThanks.setHorizontalAlignment(SwingConstants.CENTER);
        specialThanks.setFont(new Font("Monospaced", Font.BOLD, 20));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(specialThanks, BorderLayout.SOUTH);
        mainPanel.add(creditsPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        

        backBtn = gameFrame.createImageButton(ImageLibrary.get().backBtn, 150, 60);
        backBtn.addActionListener(e -> gameFrame.showPanel("menu"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createCreditItem(Image icon, String ign, String name, String desc) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel iconLabel = new JLabel(new ImageIcon(icon.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));

        JLabel text = new JLabel("<html>" + ign + "<br/>" + name + "<br/>" + desc + "</html>");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Monospaced", Font.BOLD, 20));

        panel.add(iconLabel);
        panel.add(text);

        return panel;
    }

    private JPanel specialItem(String ign, String name, String desc) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        JLabel text = new JLabel("<html>" + ign + "<br/>" + name + "<br/>" + desc + "</html>");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Monospaced", Font.BOLD, 20));
        panel.add(text);

        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(creditBackground, 0, 0, getWidth(), getHeight(), this);
    }
}