package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
    private Label.LabelStyle labelStyle17bg, labelStyle30;
    private String[] wordList;
    private Preferences preferences;

    public LoadingScreen loadingScreen;
    public MainMenuScreen mainMenuScreen;
    public PlayScreen playScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640f, 640f);
        assetManager = new AssetManager();
        wordList = new String[2261];
        preferences = Gdx.app.getPreferences("Typing Game by Luca1152");

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

    public Label.LabelStyle getLabelStyle17bg() {
        return labelStyle17bg;
    }

    public void setLabelStyle17bg(Label.LabelStyle labelStyle17bg) {
        this.labelStyle17bg = labelStyle17bg;
    }

    public Label.LabelStyle getLabelStyle30() {
        return labelStyle30;
    }

    public void setLabelStyle30(Label.LabelStyle labelStyle30) {
        this.labelStyle30 = labelStyle30;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
