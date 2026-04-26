package scenes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class CutsceneScreen extends UIScreen {

    private String[] lines = {};
    private int index = 0;

    private final JLabel textLabel = new JLabel("", SwingConstants.CENTER);

    private float alpha = 0f;

    private enum State {
        FADE_IN,
        SHOWING,
        FADE_OUT
    }

    private State state = State.FADE_IN;

    private Timer timer;

    private final GameFrame gameFrame;

    public CutsceneScreen(GameFrame gameFrame) {
        super("cutscene", gameFrame);
        this.gameFrame = gameFrame;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        textLabel.setFont(new Font("Serif", Font.PLAIN, 42));
        textLabel.setForeground(Color.WHITE);
        add(textLabel, BorderLayout.CENTER);

        JLabel hint = new JLabel("Click to continue", SwingConstants.CENTER);
        hint.setForeground(Color.GRAY);
        add(hint, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }
        });
    }

    public void setCutscene(String[] newLines) {
        this.lines = newLines;
    }

    @Override
    public void onInitiate() {
        if (lines == null || lines.length == 0) return;

        index = 0;
        alpha = 0f;
        state = State.FADE_IN;

        textLabel.setText(lines[index]);

        startLoop();
    }

    private void handleClick() {

        if (state != State.SHOWING) return;

        if (index < lines.length - 1) {
            state = State.FADE_OUT;
        } else {
            endCutscene();
        }
    }

    private void startLoop() {

        if (timer != null) timer.stop();

        timer = new Timer(20, e -> {

            switch (state) {

                case FADE_IN -> {
                    alpha += 0.05f;

                    if (alpha >= 1f) {
                        alpha = 1f;
                        state = State.SHOWING;
                    }

                    updateAlpha();
                }

                case FADE_OUT -> {
                    alpha -= 0.06f;

                    if (alpha <= 0f) {
                        alpha = 0f;
                        index++;
                        textLabel.setText(lines[index]);
                        state = State.FADE_IN;
                    }

                    updateAlpha();
                }

                case SHOWING -> {
                    // idle
                }
            }
        });

        timer.start();
    }

    private void updateAlpha() {
        textLabel.setForeground(new Color(255, 255, 255, (int)(alpha * 255)));
        repaint();
    }

    private void endCutscene() {

        if (timer != null) timer.stop();

        gameFrame.gameStart.nextLevelAfterCutscene();
        gameFrame.showPanel("start");
    }

    public void loadCutsceneForLevel(int level) {

        switch (level) {

            case 1 -> lines = new String[]{
                "You feel something shift.",
                "The world responds to your presence.",
                "Level 1"
            };

            case 2 -> lines = new String[]{
                "The halls stretch further.",
                "Something is watching.",
                "Level 2"
            };

            case 3 -> lines = new String[]{
                "The ruins whisper.",
                "You are not alone.",
                "Level 3"
            };

            case 4 -> lines = new String[]{
                "You’ve come far.",
                "But something awaits.",
                "Level 4"
            };

            case 5 -> lines = new String[]{
                "This is it.",
                "The final act.",
                "Level 5: Boss."
            };

            default -> lines = new String[]{"..."};
        }
    }
}