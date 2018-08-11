package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

public class Crate extends Image {
    public Crate(TypingGame game, float x, float y, boolean mapCoordinates) {
        super(randomTexture(game));
        if (mapCoordinates) {
            x *= 64;
            y *= 64;
        }
        setPosition(x, y);
    }

    private static Texture randomTexture(TypingGame game) {
        if (MathUtils.random(1, 2) == 1)
            return game.getManager().get("textures/wooden_crate.png");
        else
            return game.getManager().get("textures/golden_crate.png");
    }
}
