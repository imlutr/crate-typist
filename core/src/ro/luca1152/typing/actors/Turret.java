package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

@SuppressWarnings("FieldCanBeLocal")
public class Turret extends Image {
    // Constants
    private final float ORIGIN_X = 36f;
    private final float ORIGIN_Y = 36f;

    // Booleans
    public boolean rotatingLeft = false;
    public boolean rotatingRight = false;

    Turret(TypingGame game, float x, float y) {
        super(game.getManager().get("textures/turret.png", Texture.class));
        setPosition(x, y);
        setOrigin(ORIGIN_X, ORIGIN_Y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (rotatingLeft)
            rotateBy(100f * delta);
        if (rotatingRight)
            rotateBy(-100f * delta);
    }
}
