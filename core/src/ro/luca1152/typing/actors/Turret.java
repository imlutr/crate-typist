package ro.luca1152.typing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

@SuppressWarnings("FieldCanBeLocal")
public class Turret extends Image {
    private Crate targetCrate;
    public boolean rotatingLeft = false;
    public boolean rotatingRight = false;

    Turret(TypingGame game, float x, float y) {
        super(game.getManager().get("textures/turret.png", Texture.class));
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

    public void setTargetCrate(Crate targetCrate) {
        this.targetCrate = targetCrate;
    }

    private void rotateTo(Actor target) {
        float angle = (float)Math.atan2((getY() + getOriginY()) - (target.getY() + target.getOriginY()),(getX() +  getOriginX()) - (target.getX() + target.getOriginX()));
        angle = (float)(angle * (180/Math.PI));
        if(angle < 0)
            angle = 360 - (-angle);
        angle += 90;
        if(angle > 360)
            angle = angle - 360;
        setRotation(angle);
    }
}
