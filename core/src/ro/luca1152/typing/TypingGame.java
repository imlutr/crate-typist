package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ro.luca1152.typing.screens.PlayScreen;

public class TypingGame extends Game {
    public static Texture turretTexture;

    public Batch batch;
    public Viewport viewport = new FitViewport(640f, 640f);

    @Override
    public void create() {
        batch = new SpriteBatch();
        turretTexture = new Texture("textures/turret.png");
        turretTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setScreen(new PlayScreen(this));
    }
}
