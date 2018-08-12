package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ro.luca1152.typing.TypingGame;

public class LoadingScreen extends ScreenAdapter {
    private final TypingGame game;
    private float timer; // How much time loading the assets takes

    public LoadingScreen(TypingGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    @Override
    public void show() {
        loadAssets();
    }

    private void loadAssets() {
        Gdx.app.log(LoadingScreen.class.getSimpleName(), "Started loading assets.");
        game.getManager().load("textures/wooden_crate.png", Texture.class);
        game.getManager().load("textures/golden_crate.png", Texture.class);
        game.getManager().load("textures/diamond_crate.png", Texture.class);
        game.getManager().load("textures/amethyst_crate.png", Texture.class);
        game.getManager().load("textures/turret.png", Texture.class);
        game.getManager().load("textures/bullet.png", Texture.class);
        game.getManager().load("maps/map0.png", Texture.class);
        game.getManager().load("fonts/pt_mono.fnt", BitmapFont.class);
        game.setWordList(Gdx.files.internal("words").readString().split("\\s+"));
    }

    private void update(float delta) {
        timer += delta;
        if (game.getManager().update()) {
            game.getManager().get("textures/wooden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/golden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/diamond_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/amethyst_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/turret.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/bullet.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("maps/map0.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("fonts/pt_mono.fnt", BitmapFont.class).getData().markupEnabled = true;
            game.setLabelStyle(new Label.LabelStyle(game.getManager().get("fonts/pt_mono.fnt", BitmapFont.class), Color.WHITE));

            timer = (int)(timer*1000) / 1000f; // Get only 3 decimal places
            Gdx.app.log(LoadingScreen.class.getSimpleName(), "Finished loading assets in " + timer + "s.");

            game.setScreen(new PlayScreen(game));
        }
    }
}
