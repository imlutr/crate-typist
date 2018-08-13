package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Collections;

import ro.luca1152.typing.TypingGame;

public class BaseMenuScreen extends ScreenAdapter {
    private Stage stage;
    ArrayList<Button> buttons;
    public InputAdapter inputAdapter;

    public BaseMenuScreen(final TypingGame game) {
        stage = new Stage(game.getViewport(), game.getBatch());
        buttons = new ArrayList<Button>();

        inputAdapter = new InputAdapter() {
            private Actor getHitActor(int screenX, int screenY, boolean touchable) {
                return stage.hit(screenX, Gdx.graphics.getHeight() - screenY, touchable);
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                Actor hitActor = getHitActor(screenX, screenY, false);
                for (Button button : buttons) {
                    if (hitActor == button) button.setColor(Color.ORANGE);
                    else button.setColor(Color.WHITE);
                }
                return true;
            }

            private Actor touchedActor;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touchedActor = getHitActor(screenX, screenY, false);
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Actor currentActor = getHitActor(screenX, screenY, false);
                if (currentActor == touchedActor && currentActor != null && buttons.contains(currentActor)) {
                    game.enterKeySound.play();
                    ((Button) currentActor).doSomething();
                }
                return true;
            }
        };
    }

    void addButtons(Button... buttonsToAdd) {
        Collections.addAll(buttons, buttonsToAdd);
        addActors(buttonsToAdd);
    }

    void addActors(Actor... actorsToAdd) {
        for (Actor actor : actorsToAdd) {
            stage.addActor(actor);
        }
    }

    Stage getStage() {
        return stage;
    }

    @Override
    public void hide() {
        super.hide();
        getStage().getActors().removeRange(0, getStage().getActors().size - 1);
    }
}
