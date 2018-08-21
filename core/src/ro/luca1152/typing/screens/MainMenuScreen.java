package ro.luca1152.typing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.luca1152.typing.ui.BackgroundLabel;
import ro.luca1152.typing.ui.BaseMenuScreen;
import ro.luca1152.typing.ui.Button;

@Singleton
public class MainMenuScreen extends BaseMenuScreen {
    private final AssetManager manager;
    private final PlayScreen playScreen;
    private final Game game;
    private Skin skin;

    @Inject
    public MainMenuScreen(Game game,
                          PlayScreen playScreen,
                          Viewport viewport,
                          Batch batch,
                          AssetManager manager) {
        super(viewport, batch, manager);
        this.game = game;
        this.playScreen = playScreen;
        this.manager = manager;
    }


    @Override
    public void render(float delta) {
        getStage().act(delta);
        Gdx.gl20.glClearColor(46 / 255f, 204 / 255f, 113 / 255f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getStage().draw();
    }

    @Override
    public void show() {
        this.skin = manager.get("skin/skin.json", Skin.class);
        Gdx.input.setInputProcessor(getInputAdapter());
        BackgroundLabel gameNameLabel = new BackgroundLabel("CRATE TYPIST", skin, "large", "white");
        gameNameLabel.setPosition(Gdx.graphics.getWidth() / 2f - gameNameLabel.getPrefWidth() / 2f, 450);
        gameNameLabel.setColor(Color.ORANGE);

        ro.luca1152.typing.ui.Button playButton = new Button("play", skin, "small", "white") {
            @Override
            public void doSomething() {
                game.setScreen(playScreen);
            }
        };
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getPrefWidth() / 2f, 350f);

        addActors(gameNameLabel);
        addButtons(playButton);
    }
}
