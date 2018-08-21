package ro.luca1152.typing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.codejargon.feather.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

class GameModule {
    private Game game;

    GameModule(Game game) {
        this.game = game;
    }

    @Provides
    @Singleton
    Game getGame() {
        return game;
    }

    @Provides
    @Singleton
    Batch getBatch() {
        return new SpriteBatch();
    }

    @Provides
    @Singleton
    AssetManager getManager() {
        return new AssetManager();
    }

    @Provides
    @Singleton
    Viewport getViewport() {
        return new FitViewport(640f, 640f);
    }

    @Provides
    @Singleton
    @Named("wordList")
    String[] getWordList() {
        return MyGame.wordList;
    }
}
