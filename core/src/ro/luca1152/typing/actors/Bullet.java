package ro.luca1152.typing.actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ro.luca1152.typing.MyGame;
import ro.luca1152.typing.ui.BackgroundLabel;

public class Bullet extends BackgroundLabel {
    private Rectangle collisionBox;
    private Crate targetCrate;

    private boolean removeBullet = false;

    Bullet(char letter, float x, float y, Crate targetCrate) {
        super(Character.toString(letter), MyGame.feather.instance(AssetManager.class).get("skin/skin.json", Skin.class), "tiny", "white");
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
        handleCollision();
    }

    private void moveToTarget(float delta) {
        float distanceSquared = (getX() - targetCrate.getX()) * (getX() - targetCrate.getX()) + (getY() - targetCrate.getY()) * (getY() - targetCrate.getY());
        addAction(Actions.moveTo(targetCrate.getX() + targetCrate.getOriginX(), targetCrate.getY() + targetCrate.getOriginY(), distanceSquared * .001f * delta));
    }

    private void handleCollision() {
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
