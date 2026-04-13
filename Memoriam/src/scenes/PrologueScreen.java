package scenes;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PrologueScreen extends UIScreen {

    private final String[] story = {
            "Long ago,",
            "The world fell into two.",
            "It falls onto you to make right what is wrong.",
            "Make them whole,",
            "Put on a show.",
            "You have no name, no land, no past.",
            "You are the Fool."
    };

    private int index = 0;

    private final JLabel textLabel = new JLabel("", SwingConstants.CENTER);

    private float alpha = 0f;
    private float orangeBlend = 0f;

    private enum State {
        FADE_IN,
        SHOWING,
        FADE_OUT,
        ORANGE_TRANSITION,
        GAME_FADE
    }

    private State state = State.FADE_IN;

    private Timer timer;

    private final GameFrame gameFrame;

    public PrologueScreen(GameFrame gameFrame) {
        super("prologue");
        this.gameFrame = gameFrame;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        textLabel.setFont(new Font("Serif", Font.PLAIN, 42));
        add(textLabel, BorderLayout.CENTER);

        JLabel hint = new JLabel("Click to continue", SwingConstants.CENTER);
        hint.setFont(new Font("Arial", Font.PLAIN, 18));
        hint.setForeground(Color.GRAY);
        add(hint, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }
        });
    }

    // RESET
    @Override
    public void onInitiate() {
        index = 0;
        alpha = 0f;
        orangeBlend = 0f;
        state = State.FADE_IN;

        textLabel.setText(story[index]);
        startLoop();
    }


    private void handleClick() {

        if (state == State.FADE_IN || state == State.FADE_OUT || state == State.ORANGE_TRANSITION)
            return;

        if (state == State.SHOWING) {

            if (index < story.length - 1) {
                state = State.FADE_OUT;
            } else {
                state = State.ORANGE_TRANSITION;
            }
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
                    updateColor();
                }

                case FADE_OUT -> {
                    alpha -= 0.06f;

                    if (alpha <= 0f) {
                        alpha = 0f;
                        index++;
                        textLabel.setText(story[index]);
                        state = State.FADE_IN;
                    }
                    updateColor();
                }

                case ORANGE_TRANSITION -> {
                    orangeBlend += 0.03f;

                    if (orangeBlend >= 1f) {
                        orangeBlend = 1f;
                        state = State.GAME_FADE;
                    }
                    updateColor();
                }

                case GAME_FADE -> {
                    alpha -= 0.04f;

                    if (alpha <= 0f) {
                        alpha = 0f;
                        timer.stop();

                        Timer delay = new Timer(250, ev -> gameFrame.showPanel("start"));
                        delay.setRepeats(false);
                        delay.start();
                    }
                    updateColor();
                }

                case SHOWING -> {
                    // idle
                }
            }
        });

        timer.start();
    }


    private void updateColor() {

        Color white = new Color(255, 255, 255);
        Color red = new Color(255, 0, 0);

        Color blended = blend(white, red, orangeBlend);

        textLabel.setForeground(new Color(
                blended.getRed(),
                blended.getGreen(),
                blended.getBlue(),
                (int) (alpha * 255)
        ));

        repaint();
    }

    private Color blend(Color a, Color b, float t) {
        t = Math.max(0f, Math.min(1f, t));

        return new Color(
                (int) (a.getRed() + (b.getRed() - a.getRed()) * t),
                (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t),
                (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * t)
        );
    }
}