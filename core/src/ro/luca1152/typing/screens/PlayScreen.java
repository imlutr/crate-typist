package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.GameMap;

public class PlayScreen extends ScreenAdapter {
    private Stage stage;

    // Map
    private GameMap map;

    PlayScreen(TypingGame game) {
        stage = new Stage(game.getViewport(), game.getBatch());

        map = new GameMap(game, 0);
        stage.addActor(map);

        setInputProcessor();
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode >= Keys.A && keycode <= Keys.Z) {
                }

                if (keycode == Keys.A || keycode == Keys.LEFT)
                    map.getTurret().rotatingLeft = true;
                if (keycode == Keys.D || keycode == Keys.RIGHT)
                    map.getTurret().rotatingRight = true;
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Keys.A || keycode == Keys.LEFT)
                    map.getTurret().rotatingLeft = false;
                if (keycode == Keys.D || keycode == Keys.RIGHT)
                    map.getTurret().rotatingRight = false;
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
