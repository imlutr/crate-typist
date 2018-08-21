package ro.luca1152.typing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ro.luca1152.typing.MyGame;

@Singleton
public class LoadingScreen extends ScreenAdapter {
    private final Game game;
    private final MainMenuScreen mainMenuScreen;
    private final AssetManager manager;
    private String[] wordList;
    private float timer;

    @Inject
    public LoadingScreen(Game game,
                         MainMenuScreen mainMenuScreen,
                         AssetManager manager,
                         @Named("wordList") String[] wordList) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        this.manager = manager;
        this.wordList = wordList;
    }

    @Override
    public void show() {
        loadAssets();
    }

    private void loadAssets() {
        Gdx.app.log(LoadingScreen.class.getSimpleName(), "Started loading assets.");
        loadSkin();
        loadTextures();
        loadSounds();
        loadMusic();
        loadWordList();
    }

    private void loadSkin() {
        manager.load("skin/skin.atlas", TextureAtlas.class);
        manager.load("skin/skin.json", Skin.class, new SkinLoader.SkinParameter("skin/skin.atlas"));
    }

    private void loadTextures() {
        manager.load("textures/wooden_crate.png", Texture.class);
        manager.load("textures/copper_crate.png", Texture.class);
        manager.load("textures/golden_crate.png", Texture.class);
        manager.load("textures/diamond_crate.png", Texture.class);
        manager.load("textures/amethyst_crate.png", Texture.class);
        manager.load("textures/turret.png", Texture.class);
        manager.load("textures/bullet.png", Texture.class);
        manager.load("textures/pixel.png", Texture.class);
        manager.load("maps/map0.png", Texture.class);
    }

    private void loadSounds() {
        manager.load("audio/single_key.mp3", Sound.class);
        manager.load("audio/enter_key.wav", Sound.class);
        manager.load("audio/error.mp3", Sound.class);
        manager.load("audio/crate_breaking.wav", Sound.class);
        manager.load("audio/shutdown.mp3", Sound.class);
    }

    private void loadMusic() {
        manager.load("audio/music.mp3", Music.class);
    }

    private void loadWordList() {
        MyGame.wordList = Gdx.files.internal("words").readString().split("\\s+");
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl20.glClearColor(46 / 255f, 204 / 255f, 113 / 255f, 11);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void update(float delta) {
        timer += delta;
        if (manager.update()) {
            setTextureFilters();
            enableMarkup();
            timer = (int) (timer * 1000) / 1000f; // Get 3 decimal places
            Gdx.app.log(LoadingScreen.class.getSimpleName(), "Finished loading assets in " + timer + "s.");
            game.setScreen(mainMenuScreen);
        }
    }

    private void setTextureFilters() {
        manager.get("textures/wooden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/copper_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/golden_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/diamond_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/amethyst_crate.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/turret.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("textures/bullet.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
        manager.get("maps/map0.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
    }

    private void enableMarkup() {
        Skin skin = manager.get("skin/skin.json", Skin.class);
        skin.getFont("tiny").getData().markupEnabled = true;
        skin.getFont("small").getData().markupEnabled = true;
        skin.getFont("medium").getData().markupEnabled = true;
        skin.getFont("large").getData().markupEnabled = true;
    }
}
