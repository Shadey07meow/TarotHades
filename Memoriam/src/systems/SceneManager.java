package systems;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SceneManager {

    private static JFrame window;
    private static JPanel container;
    private static CardLayout layout;

    public static void init(JFrame frame) {
        window = frame;

        layout = new CardLayout();
        container = new JPanel(layout);

        window.setContentPane(container);
    }

    // Register a scene (panel)
    public static void register(String name, JPanel scene) {
        container.add(scene, name);
    }

    // Switch scenes
    public static void show(String name) {
        layout.show(container, name);
    }

    public static JFrame getWindow() {
        return window;
    }
}