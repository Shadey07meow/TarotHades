package scenes;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import object.*;
import systems.*;

public class WinScreen extends UIScreen {

    private final JLabel title;
    private final JTextArea text;

    public WinScreen(GameFrame gameFrame) {
        super("win", gameFrame);

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.BLACK);

        title = new JLabel("YOU ESCAPED", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 64));
        title.setForeground(new Color(255, 255, 255));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setBackground(Color.BLACK);
        text.setForeground(new Color(220, 220, 220));
        text.setFont(new Font("Monospaced", Font.PLAIN, 20));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setMaximumSize(new Dimension(800, 300));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton restartButton = new JButton("NEW RUN");
        JButton menuButton = new JButton("MAIN MENU");

        styleButton(restartButton);
        styleButton(menuButton);

        restartButton.addActionListener(e -> {
            GameStats.get().reset();
            gameFrame.showPanel("start");
        });

        menuButton.addActionListener(e -> {
            gameFrame.showPanel("menu");
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(menuButton);

        center.add(Box.createVerticalGlue());
        center.add(title);
        center.add(Box.createVerticalStrut(30));
        center.add(text);
        center.add(Box.createVerticalStrut(30));
        center.add(buttonPanel);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
    }
    @Override
    public void onInitiate() {

        StringBuilder sb = new StringBuilder();

        sb.append("A silence settles...\n\n");

        sb.append("Kills: ")
          .append(SaveSystem.getKills())
          .append("\n");

        sb.append("Relic: ")
          .append(SaveSystem.getRelic())
          .append("\n\n");

        sb.append("Memories carried forward:\n");

        Map<PlayerAbility, Integer> abilities =
                LevelManager.getPlayableScreen(
                        LevelManager.levels.size() - 1)
                        .getWorldRenderer()
                        .getPlayer()
                        .getAbilityMap();

        if (abilities == null || abilities.isEmpty()) {
            sb.append("  None\n");
        } else {
            for (Map.Entry<PlayerAbility, Integer> e : abilities.entrySet()) {
                sb.append("  ")
                  .append(e.getKey())
                  .append(" Lv.")
                  .append(e.getValue())
                  .append("\n");
            }
        }

        sb.append("\nThe dungeon forgets you.");

        text.setText(sb.toString());
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
    }
}