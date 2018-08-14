package ro.luca1152.typing.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ro.luca1152.typing.TypingGame;
import ro.luca1152.typing.Util;

public class Bullet extends Label {
    private Rectangle collisionBox;
    private Crate targetCrate;

    private float delay;
    private boolean removeBullet = false;

    Bullet(TypingGame game, char letter, float x, float y, Crate targetCrate) {
        super(Character.toString(letter), game.getLabelStyle17bg());
        this.targetCrate = targetCrate;
        this.setPosition(x, y);
        this.setOrigin(getWidth() / 2f, getHeight() / 2f);
        this.collisionBox = new Rectangle();
        this.collisionBox.setSize(getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveToTarget(delta);
        handleCollision(delta);
    }

    private void moveToTarget(float delta) {
        float distanceSquared = (getX() - targetCrate.getX()) * (getX() - targetCrate.getX()) + (getY() - targetCrate.getY()) * (getY() - targetCrate.getY());
        addAction(Actions.moveTo(targetCrate.getX() + targetCrate.getOriginX(), targetCrate.getY() + targetCrate.getOriginY(), distanceSquared * .001f * delta));
    }

    private void handleCollision(float delta) {
        if (getCollisionBox().overlaps(targetCrate.getCollisionBox()) && !removeBullet) {
            targetCrate.collision();
            remove();
        }
    }

    private Rectangle getCollisionBox() {
        collisionBox.setPosition(getX(), getY());
        return collisionBox;
    }
}
