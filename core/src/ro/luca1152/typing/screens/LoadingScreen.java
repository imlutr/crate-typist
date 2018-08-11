package ro.luca1152.typing.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ro.luca1152.typing.TypingGame;

public class LoadingScreen extends ScreenAdapter {
    private TypingGame game;

    public LoadingScreen(TypingGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        update();
    }

    @Override
    public void show() {
        loadAssets();
    }

    private void loadAssets() {
        game.getManager().load("textures/wooden_crate.png", Texture.class);
        game.getManager().load("textures/golden_crate.png", Texture.class);
        game.getManager().load("textures/turret.png", Texture.class);
        game.getManager().load("textures/pixel.png", Texture.class);
        game.getManager().load("maps/map0.png", Texture.class);
        game.getManager().load("fonts/pt_mono.fnt", BitmapFont.class);
    }

    private void update() {
        if (game.getManager().update()) {
            game.getManager().get("textures/wooden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/golden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/turret.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("maps/map0.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.setLabelStyle(new Label.LabelStyle(game.getManager().get("fonts/pt_mono.fnt", BitmapFont.class), Color.WHITE));
            Image background = new Image(game.getManager().get("textures/pixel.png", Texture.class));
            background.setColor(0f, 0f, 0f, .3f);
            game.getLabelStyle().background = background.getDrawable();

            game.setScreen(new PlayScreen(game));
        }
    }
}
