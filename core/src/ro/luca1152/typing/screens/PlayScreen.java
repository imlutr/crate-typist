package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.Crate;
import ro.luca1152.typing.actors.GameMap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.after;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

public class PlayScreen extends ScreenAdapter {
    private TypingGame game;
    private Stage stage;

    // Map
    private JsonValue mapsJson; // the file "maps/maps.json"
    private GameMap map;

    private float crateTimer = 0f;


    PlayScreen(TypingGame game) {
        this.game = game;
        stage = new Stage(game.getViewport(), game.getBatch());

        map = new GameMap(game, 0);
        stage.addActor(map);
        JsonReader jsonReader = new JsonReader();
        mapsJson = jsonReader.parse(Gdx.files.internal("maps/maps.json"));

        setInputProcessor();
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
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
        update(delta);
        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
        spawnCrates(delta);
    }

    private void spawnCrates(float delta) {
        crateTimer -= delta;
        if (crateTimer <= 0f) {
            crateTimer = 5f;
            int randomSpawn = MathUtils.random(0, mapsJson.get(map.getId()).getInt("numOfSpawns") - 1);
            JsonValue spawn = mapsJson.get(map.getId()).get("spawns").get(randomSpawn);
            JsonValue path = spawn.get("path");

            Crate crate = new Crate(game, spawn.getInt("x"), spawn.getInt("y"), true);
            for (JsonValue value : path)
                crate.addAction(after(moveTo(value.getInt("x") * 64, value.getInt("y") * 64, value.getInt("distance") / 2f)));
            stage.addActor(crate);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
