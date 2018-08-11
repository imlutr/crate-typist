package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ro.luca1152.typing.actors.GameMap;
import ro.luca1152.typing.screens.LoadingScreen;
import ro.luca1152.typing.screens.PlayScreen;

public class TypingGame extends Game {
    private Batch batch;
    private Viewport viewport;
    private AssetManager assetManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport =  new FitViewport(640f, 640f);
        assetManager = new AssetManager();

        setScreen(new LoadingScreen(this));
    }

    public Batch getBatch() {
        return batch;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public AssetManager getManager() {
        return assetManager;
    }
}
