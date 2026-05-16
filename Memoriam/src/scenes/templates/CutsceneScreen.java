package scenes.templates;

import scenes.ui.GameFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import systems.SoundManager;

public class CutsceneScreen extends UIScreen {

    private String[] lines = {};
    private int index = 0;
    private volatile boolean finishedTransition = false;

    private final JLabel textLabel = new JLabel("", SwingConstants.CENTER);

    private float alpha = 0f;
    private boolean endingCutscene = false;

    private enum State {
        FADE_IN,
        SHOWING,
        FADE_OUT
    }

    private State state = State.FADE_IN;

    private Timer timer;

    public CutsceneScreen(GameFrame gameFrame) {
        super("cutscene", gameFrame);

        finishedTransition = false;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        textLabel.setFont(new Font("Serif", Font.PLAIN, 42));
        textLabel.setForeground(new Color(255, 255, 255, 0));
        add(textLabel, BorderLayout.CENTER);

        JLabel hint = new JLabel("Click to continue", SwingConstants.CENTER);
        hint.setForeground(Color.GRAY);
        add(hint, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() != MouseEvent.BUTTON1) return;

                if (!isShowing() || !isVisible()) return;

                if (state == State.FADE_OUT) return;

                handleClick();
            }
        });
    }

    public void setCutscene(String[] newLines) {
        this.lines = newLines;
    }

    @Override
    public void onInitiate() {

        if (lines == null || lines.length == 0) {
            lines = new String[]{"..."};
        }

        if (timer != null) {
            timer.stop();
            timer = null;
        }

        finishedTransition = false;

        SoundManager.get().playMusic("storyMusic");

        index = 0;
        alpha = 0f;

        state = State.FADE_IN;

        textLabel.setText(lines[0]);
        textLabel.setForeground(new Color(255, 255, 255, 0));

        startLoop();
    }

    private void handleClick() {

        if (state == State.FADE_IN) {
            alpha = 1f;
            state = State.SHOWING;
            updateAlpha();
            return;
        }

        if (state != State.SHOWING) return;

        if (index < lines.length - 1) {
            state = State.FADE_OUT;
        } else {
            endCutscene();
        }
    }

    private void startLoop() {

        if (timer != null) {
            timer.stop();
            timer = null;
        }

        timer = new Timer(16, e -> {

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

                        if (index >= lines.length) {
                            endCutscene();
                            return;
                        }

                        textLabel.setText(lines[index]);
                        state = State.FADE_IN;
                    }

                    updateAlpha();
                }

                case SHOWING -> {
                }
            }
        });

        timer.start();
    }

    private void updateAlpha() {
        textLabel.setForeground(new Color(255, 255, 255, (int)(alpha * 255)));
        textLabel.repaint();
        textLabel.revalidate();
    }

    private void endCutscene() {

        if (timer != null) {
            timer.stop();
            timer = null;
        }

        finishedTransition = true;

        if (endingCutscene) {
            getGameFrame().showPanel("win");
        }
    }

    public void loadEndingCutscene() {

        endingCutscene = true;

        lines = new String[] {
            "The silence is no more.",
            "The birds sing their songs and everything looks brighter than before.",
            "You stand alone.",
            "But alive.",
            "Justice awaits."
        };
    }

    public void loadCutsceneForLevel(int level) {

        endingCutscene = false;

        switch (level) {

            case 1 -> lines = new String[]{
                "You enter the forests, leading into the ruins of what you once knew.",
                "You feel something shift.",
                "The world responds to your presence.",
                "Level 1"
            };

            case 2 -> lines = new String[]{
                "The birds utter no sound as you pass by, the water remains unmoving.",
                "Something is watching.",
                "Level 2"
            };

            case 3 -> lines = new String[]{
                "You clutch onto the cards you've picked up.",
                "They are your only companion.",
                "Level 3"
            };

            case 4 -> lines = new String[]{
                "You've come far. You can almost see the ruins from here.",
                "You know something awaits you there.",
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

    public boolean isFinishedLoading() {
        return finishedTransition;
    }
}