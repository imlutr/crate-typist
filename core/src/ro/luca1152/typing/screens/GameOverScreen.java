package ro.luca1152.typing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
public class GameOverScreen extends BaseMenuScreen {
    private final Game game;
    private final PlayScreen playScreen;
    private final AssetManager manager;
    private Skin skin;
    public int score;
    public int wave;

    @Inject
    public GameOverScreen(Game game,
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
    public void show() {
        super.show();
        skin = manager.get("skin/skin.json", Skin.class);
        Gdx.input.setInputProcessor(getInputAdapter());
        manager.get("audio/music.mp3", Music.class).stop();

        BackgroundLabel gameOverLabel = new BackgroundLabel("GAME OVER", skin, "large", "white");
        gameOverLabel.setPosition(Gdx.graphics.getWidth() / 2f - gameOverLabel.getPrefWidth() / 2f, 450);
        gameOverLabel.setColor(Color.ORANGE);

        BackgroundLabel scoreLabel = new BackgroundLabel("Score: [WHITE]" + score + "[]", skin, "small", "white");
        scoreLabel.setPosition(gameOverLabel.getX(), gameOverLabel.getY() - gameOverLabel.getPrefHeight() + 10);
        scoreLabel.setColor(Color.ORANGE);

        BackgroundLabel waveLabel = new BackgroundLabel("Wave: [WHITE]" + wave + "[]", skin, "small", "white");
        waveLabel.setPosition(gameOverLabel.getX(), scoreLabel.getY() - scoreLabel.getPrefHeight() - 20);
        waveLabel.setColor(Color.ORANGE);

        ro.luca1152.typing.ui.Button playButton = new Button("retry", skin, "small", "white") {
            @Override
            public void doSomething() {
                game.setScreen(playScreen);
            }
        };
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getPrefWidth() / 2f, 250f);

        addActors(gameOverLabel, scoreLabel, waveLabel);
        addButtons(playButton);
    }

    @Override
    public void render(float delta) {
        getStage().act(delta);
        Gdx.gl20.glClearColor(46 / 255f, 204 / 255f, 113 / 255f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getStage().draw();
    }
}
