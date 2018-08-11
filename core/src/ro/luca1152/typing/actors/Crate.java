package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Crate extends Image {
    public Crate(float x, float y, boolean mapCoordinates) {
        super(new Texture("textures/wooden_crate.png"));
        if (mapCoordinates) {
            x *= 64;
            y *= 64;
        }
        setPosition(x, y);
    }
}
