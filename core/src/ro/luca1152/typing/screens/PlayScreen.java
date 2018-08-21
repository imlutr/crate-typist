package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.inject.Inject;
import javax.inject.Singleton;

import ro.luca1152.typing.actors.Crate;
import ro.luca1152.typing.actors.GameMap;
import ro.luca1152.typing.actors.Turret;

@Singleton
public class PlayScreen extends ScreenAdapter {
    private final Batch batch;
    private final AssetManager manager;
    private final Viewport viewport;
    private GameMap map;
    private Stage stage;
    private float delay = 0f;

    @Inject
    public PlayScreen(Batch batch,
                      AssetManager manager,
                      Viewport viewport) {
        this.batch = batch;
        this.manager = manager;
        this.viewport = viewport;
    }

    @Override
    public void show() {
        stage = new Stage(viewport, batch);

        map = new GameMap(0, true);
        stage.addActor(map);

        setInputProcessor();

        Music music = manager.get("audio/music.mp3", Music.class);
        music.play();
        music.setVolume(.4f);
        music.setLooping(true);
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Turret turret = map.getTurret();
                if (keycode >= Keys.A && keycode <= Keys.Z && delay <= 0f) {
                    if (turret.getTargetCrate() == null) {
                        for (Actor child : map.getCrates().getChildren()) {
                            Crate crate = ((Crate) child);
                            if (crate.firstCharFromWord() == keycodeToChar(keycode) && !crate.isOffScreen()) {
                                crate.keyPressed(keycodeToString(keycode));
                                turret.setTargetCrate(crate);
                                map.getTurret().shoot(true);
                                return true;
                            }
                        }

                        // A key was entered that does not correspond to any word => mistake
                        map.resetScoreMultiplier();
                    } else {
                        // The word is in progress
                        if (turret.getTargetCrate().firstCharFromWord() == keycodeToChar(keycode)) {
                            turret.getTargetCrate().keyPressed(keycodeToString(keycode));
                            turret.shoot(false);
                        }

                        // A wrong key was entered
                        else if (turret.getTargetCrate().firstCharFromWord() != keycodeToChar(keycode)) {
                            map.resetScoreMultiplier();
                        }
                    }
                }
                return false;
            }
        });
    }

    private char keycodeToChar(int keycode) throws IllegalArgumentException {
        if (keycode < 29 || keycode > 54)
            throw new IllegalArgumentException("Keycode out of range.");
        return (char) (keycode + 68);
    }

    private String keycodeToString(int keycode) throws IllegalArgumentException {
        return Character.toString(keycodeToChar(keycode));
    }

    @Override
    public void render(float delta) {
        delay -= delta;
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
