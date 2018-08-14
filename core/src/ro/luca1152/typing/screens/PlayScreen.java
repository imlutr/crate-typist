package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.actors.Crate;
import ro.luca1152.typing.actors.GameMap;
import ro.luca1152.typing.actors.Turret;

public class PlayScreen extends ScreenAdapter {
    private final TypingGame game;
    private Stage stage;
    private static GameMap map;
    private float delay = 0f;

    public PlayScreen(TypingGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.music.play();
        game.music.setVolume(.4f);
        game.music.setLooping(true);
        stage = new Stage(game.getViewport(), game.getBatch());
        map = new GameMap(game, 0, true);
        stage.addActor(map);
        setInputProcessor();
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Turret turret = PlayScreen.map.getTurret();
                if (keycode >= Keys.A && keycode <= Keys.Z && delay <= 0f) {
                    if (turret.getTargetCrate() == null) {
                        boolean found = false;
                        for (Actor child : PlayScreen.map.getCrates().getChildren()) {
                            Crate crate = ((Crate) child);
                            if (crate.firstCharFromWord() == keycodeToChar(keycode) && !crate.isOffScreen()) {
                                found = true;
                                crate.keyPressed(keycodeToString(keycode));
                                turret.setTargetCrate(crate);
                                PlayScreen.map.getTurret().shoot(true);
                                return true;
                            }
                        }
                        // A key was entered that does not correspond to any word => mistake
                        if (!found) {
                            PlayScreen.map.resetScoreMultiplier();
                        }
                    } else {
                        // The word is in progress
                        if (turret.getTargetCrate().firstCharFromWord() == keycodeToChar(keycode)) {
                            turret.getTargetCrate().keyPressed(keycodeToString(keycode));
                            turret.shoot(false);
                        }

                        // A wrong key was entered
                        else if (turret.getTargetCrate().firstCharFromWord() != keycodeToChar(keycode)) {
                            PlayScreen.map.resetScoreMultiplier();
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
