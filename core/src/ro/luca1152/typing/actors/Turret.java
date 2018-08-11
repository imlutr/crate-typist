package ro.luca1152.typing.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

public class Turret extends Image {
    public boolean rotatingLeft = false;
    public boolean rotatingRight = false;

    Turret(float x, float y) {
        super(TypingGame.turretTexture);
        setPosition(x, y);
        setOrigin(45f, 45f);
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
