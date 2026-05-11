package systems;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SceneManager {

    private static JFrame window;
    private static JPanel container;
    private static CardLayout layout;
    private static String currentScene;

    public static void init(JFrame frame) {
        window = frame;

        layout = new CardLayout();
        container = new JPanel(layout);

        window.setContentPane(container);
    }

    public static void register(String name, JPanel scene) {
        container.add(scene, name);
    }

    public static void show(String name) {
        layout.show(container, name);
        currentScene = name;
    }

    public static String getCurrentScene() {
        return currentScene;
    }

    public static JFrame getWindow() {
        return window;
    }
}