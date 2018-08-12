package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

@SuppressWarnings("FieldCanBeLocal")
public class Turret extends Image {
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
        if (rotatingLeft)
            rotateBy(100f * delta);
        if (rotatingRight)
            rotateBy(-100f * delta);
    }
}
