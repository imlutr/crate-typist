package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.Crate;
import ro.luca1152.typing.actors.GameMap;

public class PlayScreen extends ScreenAdapter {
    private TypingGame game;
    private Stage stage;
    private GameMap currentMap;

    public PlayScreen(TypingGame game) {
        this.game = game;
        stage = new Stage(game.viewport, game.batch);

        // Map
        currentMap = new GameMap("map1");
        stage.addActor(currentMap);

        // Crates
        Crate leftCrate = new Crate(-1, 8, true);
        leftCrate.addAction(sequence(
                delay(2f),
                moveBy(576f, 0f, 4.5f),
                moveBy(0f, -384f, 3f)));
        stage.addActor(leftCrate);
        Crate rightCrate = new Crate(10, 1, true);
        rightCrate.addAction(sequence(
                delay(2f),
                moveBy(-576f, 0f, 4.5f),
                moveBy(0f, 384f, 3f)));
        stage.addActor(rightCrate);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Keys.A || keycode == Keys.LEFT)
                    currentMap.turret.rotatingLeft = true;
                if (keycode == Keys.D || keycode == Keys.RIGHT)
                    currentMap.turret.rotatingRight = true;
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Keys.A || keycode == Keys.LEFT)
                    currentMap.turret.rotatingLeft = false;
                if (keycode == Keys.D || keycode == Keys.RIGHT)
                    currentMap.turret.rotatingRight = false;
                return true;
            }
        });
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
