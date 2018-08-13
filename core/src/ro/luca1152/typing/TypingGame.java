package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ro.luca1152.typing.screens.LoadingScreen;

public class TypingGame extends Game {
    private Batch batch;
    private Viewport viewport;
    private AssetManager assetManager;
    private Label.LabelStyle labelStyle;
    private String[] wordList;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640f, 640f);
        assetManager = new AssetManager();
        wordList = new String[2261];

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

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public void setLabelStyle(Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
}
