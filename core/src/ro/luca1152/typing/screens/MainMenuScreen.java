package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.BackgroundLabel;

public class MainMenuScreen extends BaseMenuScreen {
    private final TypingGame game;

    public MainMenuScreen(final TypingGame game) {
        super(game);
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
        game.music.setVolume(.4f);
        game.music.play();
        game.music.stop();
        game.music.play();
        game.music.setLooping(true);
        Label.LabelStyle labelStyle23bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_23.fnt", BitmapFont.class), Color.WHITE);

        Label.LabelStyle labelStyle50bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_50.fnt", BitmapFont.class), Color.WHITE);
        BackgroundLabel gameNameLabel = new BackgroundLabel("CRATE TYPIST", labelStyle50bg);
        gameNameLabel.setPosition(Gdx.graphics.getWidth() / 2f - gameNameLabel.getPrefWidth() / 2f, 450);
        gameNameLabel.setColor(Color.ORANGE);

        Button playButton = new Button("play", labelStyle23bg) {
            @Override
            public void doSomething() {
                game.setScreen(game.playScreen);
            }
        };
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getPrefWidth() / 2f, 350f);

        addActors(gameNameLabel);
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
