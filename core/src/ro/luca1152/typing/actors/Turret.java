package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.Util;

@SuppressWarnings("FieldCanBeLocal")
public class Turret extends Image {
    private final TypingGame game;
    private Crate targetCrate;
    public boolean rotatingLeft = false;
    public boolean rotatingRight = false;

    Turret(TypingGame game, float x, float y) {
        super(game.getManager().get("textures/turret.png", Texture.class));
        this.game = game;
        setPosition(x, y);
        setOrigin(36f, 36f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (targetCrate != null) {
            rotateTo(targetCrate);
        }
        if (rotatingLeft)
            rotateBy(100f * delta);
        if (rotatingRight)
            rotateBy(-100f * delta);
    }

    public void shoot(Crate targetCrate) {
        Bullet bullet = new Bullet(game, this, targetCrate);
        getStage().addActor(bullet);
        bullet.setZIndex(1);
    }

    public void setTargetCrate(Crate targetCrate) {
        this.targetCrate = targetCrate;
    }

    private void rotateTo(Actor target) {
        float angle = Util.getAngleBetween(this, target);
        float angleDiff = Math.abs(getRotation() - angle);
        if (angleDiff > 1f && angleDiff <= 250f)
            addAction(Actions.rotateTo(angle, 0.05f, Interpolation.exp5In));
        else
            setRotation(angle);
    }
}
