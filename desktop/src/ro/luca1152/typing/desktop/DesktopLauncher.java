package ro.luca1152.typing.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import ro.luca1152.typing.MyGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640;
        config.height = 640;
        config.title = "Typing";
        config.samples = 4;
        config.initialBackgroundColor = new Color(46 / 255f, 204 / 255f, 113 / 255f, 1f);
        new LwjglApplication(new MyGame(), config);
    }
}
