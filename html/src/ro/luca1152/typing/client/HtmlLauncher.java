package ro.luca1152.typing.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import ro.luca1152.typing.TypingGame;

public class HtmlLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(600, 600);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new TypingGame();
    }
}