package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
        Label.LabelStyle labelStyle23bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_23.fnt", BitmapFont.class), Color.WHITE);

        final Label mapLabel = new Label("Map ([RED]hard[])", game.getLabelStyle30());
        mapLabel.setPosition(Gdx.graphics.getWidth() / 2f - mapLabel.getPrefWidth() / 2f, 595);

        Image mapBorder = new Image(game.getManager().get("textures/pixel.png", Texture.class));
        mapBorder.setColor(149 / 255f, 165 / 255f, 166 / 255f, 1f);
        mapBorder.setSize(340f, 340f);
        mapBorder.setPosition(Gdx.graphics.getWidth() / 2f - mapBorder.getWidth() / 2f, 240f);

        Image mapImage = new Image(game.getManager().get("maps/map0.png", Texture.class));
        mapImage.setSize(320, 320);
        mapImage.setPosition(Gdx.graphics.getWidth() / 2f - mapImage.getWidth() / 2f, 250f);

        Image turretImage = new Image(game.getManager().get("textures/turret.png", Texture.class));
        turretImage.setSize(turretImage.getWidth() / 2f, turretImage.getHeight() / 2f);
        turretImage.setPosition(Gdx.graphics.getWidth() / 2f - turretImage.getWidth() / 2f, 385);

        Button backButton = new Button("back", labelStyle23bg) {
            @Override
            public void doSomething() {
            }
        };
        backButton.setPosition(50f, mapBorder.getY() + mapBorder.getHeight() / 2f - backButton.getPrefHeight() / 2f);

        Button nextButton = new Button("next", labelStyle23bg) {
            @Override
            public void doSomething() {

            }
        };
        nextButton.setPosition(Gdx.graphics.getWidth() - nextButton.getPrefWidth() - 50f, mapBorder.getY() + mapBorder.getHeight() / 2f - backButton.getPrefHeight() / 2f);

        Button playButton = new Button("play", labelStyle23bg) {
            @Override
            public void doSomething() {
                game.setScreen(game.playScreen);
            }
        };
        playButton.setPosition(Gdx.graphics.getWidth() / 2f - playButton.getPrefWidth() / 2f, 170f);

        Button howToPlayButton = new Button("how to play", labelStyle23bg) {
            @Override
            public void doSomething() {

            }
        };
        howToPlayButton.setPosition(Gdx.graphics.getWidth() / 2f - howToPlayButton.getPrefWidth() / 2f, playButton.getY() - 30f - playButton.getPrefHeight());

        Button yourScoresButton = new Button("your scores", labelStyle23bg) {
            @Override
            public void doSomething() {

            }
        };
        yourScoresButton.setPosition(Gdx.graphics.getWidth() / 2f - yourScoresButton.getPrefWidth() / 2f, playButton.getY() - 2 * 30f - 2 * howToPlayButton.getPrefHeight());

        addActors(mapLabel, mapBorder, mapImage, turretImage);
        addButtons(backButton, nextButton, playButton, howToPlayButton, yourScoresButton);
    }

    @Override
    public void render(float delta) {
        getStage().act(delta);
        Gdx.gl20.glClearColor(52 / 255f, 152 / 255f, 219 / 255f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getStage().draw();
    }
}
