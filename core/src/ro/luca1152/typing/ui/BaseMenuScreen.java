package ro.luca1152.typing.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Collections;

public class BaseMenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final ArrayList<ro.luca1152.typing.ui.Button> buttons;
    private final InputAdapter inputAdapter;

    public BaseMenuScreen(final Viewport viewport,
                          final Batch batch,
                          final AssetManager manager) {
        stage = new Stage(viewport, batch);
        buttons = new ArrayList<ro.luca1152.typing.ui.Button>();
        inputAdapter = new InputAdapter() {
            private Actor touchedActor;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touchedActor = getHitActor(screenX, screenY);
                return true;
            }

            private Actor getHitActor(int screenX, int screenY) {
                return stage.hit(screenX, Gdx.graphics.getHeight() - screenY, false);
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Actor currentActor = getHitActor(screenX, screenY);
                if (currentActor == touchedActor && currentActor != null && buttons.contains(currentActor)) {
                    manager.get("audio/enter_key.wav", Sound.class).play();
                    ((ro.luca1152.typing.ui.Button) currentActor).doSomething();
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                Actor hitActor = getHitActor(screenX, screenY);
                for (ro.luca1152.typing.ui.Button button : buttons) {
                    if (hitActor == button) button.setColor(Color.ORANGE);
                    else button.setColor(Color.WHITE);
                }
                return true;
            }
        };
    }

    protected void addButtons(ro.luca1152.typing.ui.Button... buttonsToAdd) {
        Collections.addAll(buttons, buttonsToAdd);
        addActors(buttonsToAdd);
    }

    protected void addActors(Actor... actorsToAdd) {
        for (Actor actor : actorsToAdd) {
            stage.addActor(actor);
        }
    }

    @Override
    public void hide() {
        super.hide();
        getStage().getActors().removeRange(0, getStage().getActors().size - 1);
    }

    protected Stage getStage() {
        return stage;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    protected InputAdapter getInputAdapter() {
        return inputAdapter;
    }
}
