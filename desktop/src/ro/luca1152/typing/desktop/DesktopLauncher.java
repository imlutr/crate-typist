package ro.luca1152.typing.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ro.luca1152.typing.TypingGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
        config.title = "Typing";
        config.samples = 4;
        new LwjglApplication(new TypingGame(), config);
    }
}
