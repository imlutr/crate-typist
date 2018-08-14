package ro.luca1152.typing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
        Gdx.gl20.glClearColor(46 / 255f, 204 / 255f, 113 / 255f, 11);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {
        loadAssets();
    }

    private void loadAssets() {
        Gdx.app.log(LoadingScreen.class.getSimpleName(), "Started loading assets.");
        game.getManager().load("textures/wooden_crate.png", Texture.class);
        game.getManager().load("textures/copper_crate.png", Texture.class);
        game.getManager().load("textures/golden_crate.png", Texture.class);
        game.getManager().load("textures/diamond_crate.png", Texture.class);
        game.getManager().load("textures/amethyst_crate.png", Texture.class);
        game.getManager().load("textures/turret.png", Texture.class);
        game.getManager().load("textures/bullet.png", Texture.class);
        game.getManager().load("textures/pixel.png", Texture.class);
        game.getManager().load("maps/map0.png", Texture.class);
        game.getManager().load("fonts/pt_mono_17.fnt", BitmapFont.class);
        game.getManager().load("fonts/pt_mono_23.fnt", BitmapFont.class);
        game.getManager().load("fonts/pt_mono_30.fnt", BitmapFont.class);
        game.getManager().load("fonts/pt_mono_50.fnt", BitmapFont.class);
        game.getManager().load("audio/music.mp3", Music.class);
        game.getManager().load("audio/single_key.mp3", Sound.class);
        game.getManager().load("audio/enter_key.wav", Sound.class);
        game.getManager().load("audio/error.mp3", Sound.class);
        game.getManager().load("audio/crate_breaking.wav", Sound.class);
        game.getManager().load("audio/shutdown.mp3", Sound.class);
        game.setWordList(Gdx.files.internal("words").readString().split("\\s+"));
    }

    private void update(float delta) {
        timer += delta;
        if (game.getManager().update()) {
            game.getManager().get("textures/wooden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/copper_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/golden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/diamond_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/amethyst_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/turret.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("textures/bullet.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("maps/map0.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
            game.getManager().get("fonts/pt_mono_17.fnt", BitmapFont.class).getData().markupEnabled = true;
            game.setLabelStyle17bg(new Label.LabelStyle(game.getManager().get("fonts/pt_mono_17.fnt", BitmapFont.class), Color.WHITE));
            game.getManager().get("fonts/pt_mono_23.fnt", BitmapFont.class).getData().markupEnabled = true;
            game.getManager().get("fonts/pt_mono_30.fnt", BitmapFont.class).getData().markupEnabled = true;
            game.setLabelStyle30(new Label.LabelStyle(game.getManager().get("fonts/pt_mono_30.fnt", BitmapFont.class), Color.WHITE));
            game.labelStyle30bg = new Label.LabelStyle(game.getManager().get("fonts/pt_mono_30.fnt", BitmapFont.class), Color.WHITE);
            game.music = game.getManager().get("audio/music.mp3", Music.class);
            game.singleKeySound = game.getManager().get("audio/single_key.mp3", Sound.class);
            game.enterKeySound = game.getManager().get("audio/enter_key.wav", Sound.class);
            game.errorSound = game.getManager().get("audio/error.mp3", Sound.class);
            game.crateBreakingSound = game.getManager().get("audio/crate_breaking.wav", Sound.class);
            game.shutdownSound = game.getManager().get("audio/shutdown.mp3", Sound.class);

            timer = (int) (timer * 1000) / 1000f; // Get only 3 decimal places
            Gdx.app.log(LoadingScreen.class.getSimpleName(), "Finished loading assets in " + timer + "s.");

            game.setScreen(game.mainMenuScreen);
        }
    }
}
