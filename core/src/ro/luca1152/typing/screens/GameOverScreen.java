package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.BackgroundLabel;

public class GameOverScreen extends BaseMenuScreen {
    TypingGame game;
    public float score;
    public int wave;

    public GameOverScreen(TypingGame game) {
        super(game);
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        game.music.stop();
        Gdx.input.setInputProcessor(inputAdapter);
        Label.LabelStyle labelStyle50bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_50.fnt", BitmapFont.class), Color.WHITE);
        Label.LabelStyle labelStyle23bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_23.fnt", BitmapFont.class), Color.WHITE);

        BackgroundLabel gameOverLabel = new BackgroundLabel("GAME OVER", labelStyle50bg);
        gameOverLabel.setPosition(Gdx.graphics.getWidth() / 2f - gameOverLabel.getPrefWidth() / 2f, 450);
        gameOverLabel.setColor(Color.ORANGE);

        BackgroundLabel scoreLabel = new BackgroundLabel("Score: [WHITE]" + (int)(score-3) + "[]", labelStyle23bg);
        scoreLabel.setPosition(gameOverLabel.getX(), gameOverLabel.getY() - gameOverLabel.getPrefHeight() + 10);
        scoreLabel.setColor(Color.ORANGE);

        BackgroundLabel waveLabel = new BackgroundLabel("Wave: [WHITE]" + wave + "[]", labelStyle23bg);
        waveLabel.setPosition(gameOverLabel.getX(), scoreLabel.getY() - scoreLabel.getPrefHeight() - 20);
        waveLabel.setColor(Color.ORANGE);

        Button playButton = new Button("retry", labelStyle23bg) {
            @Override
            public void doSomething() {
                game.setScreen(game.playScreen);
            }
        };
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getPrefWidth() / 2f, 250f);

        Button menuButton = new Button("menu", labelStyle23bg) {
            @Override
            public void doSomething() {
                game.setScreen(game.mainMenuScreen);
            }
        };
        menuButton.setPosition(Gdx.graphics.getWidth() / 2f - menuButton.getPrefWidth() / 2f, playButton.getY() - playButton.getPrefHeight() - 20);

        addActors(gameOverLabel, scoreLabel, waveLabel);
        addButtons(playButton, menuButton);
    }

    @Override
    public void render(float delta) {
        getStage().act(delta);
        Gdx.gl20.glClearColor(46 / 255f, 204 / 255f, 113 / 255f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getStage().draw();
    }
}
