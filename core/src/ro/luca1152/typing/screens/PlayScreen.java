package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

public class PlayScreen extends ScreenAdapter {
    private TypingGame game;
    private Stage stage;

    public PlayScreen(TypingGame game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        stage.addActor(new Image(new Texture("background.png")));
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
