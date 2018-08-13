package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ro.luca1152.typing.screens.LoadingScreen;
import ro.luca1152.typing.screens.MainMenuScreen;
import ro.luca1152.typing.screens.PlayScreen;

public class TypingGame extends Game {
    private Batch batch;
    private Viewport viewport;
    private AssetManager assetManager;
    private Label.LabelStyle backgroundLabelStyle;
    private String[] wordList;

    public LoadingScreen loadingScreen;
    public MainMenuScreen mainMenuScreen;
    public PlayScreen playScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640f, 640f);
        assetManager = new AssetManager();
        wordList = new String[2261];

        loadingScreen = new LoadingScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        playScreen = new PlayScreen(this);

        setScreen(loadingScreen);
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

    public Label.LabelStyle getBackgroundLabelStyle() {
        return backgroundLabelStyle;
    }

    public void setBackgroundLabelStyle(Label.LabelStyle backgroundLabelStyle) {
        this.backgroundLabelStyle = backgroundLabelStyle;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
}
