package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import ro.luca1152.typing.TypingGame;

import static jdk.nashorn.internal.objects.Global.println;

public class Crate extends Image {
    // Static because in Java the super() call has to be first
    private static int zIndex = 0;
    private static String words[] = {
            "if", "ad", "by",
            "for", "max", "mix",
            "pair", "gift", "jump",
            "white", "alien", "board",
            "smooth", "strong", "barrel",
            "skateboard"};
    private static String word;

    private BackgroundLabel label;
    private Table table;

    private Vector2[] finishPoints;

    public Crate(TypingGame game, float x, float y, boolean mapCoordinates, Vector2[] finishPoints) {
        super(randomTexture(game));
        if (mapCoordinates) {
            x *= 64;
            y *= 64;
        }
        this.finishPoints = finishPoints;
        setPosition(x, y);
        table = new Table();
        table.setSize(getWidth(), getHeight());
        table.setPosition(getX(), getY() + 50f);
        label = new BackgroundLabel(word, game.getLabelStyle());
        table.add(label).size(label.getPrefWidth() + 4, label.getPrefHeight() + 3);
        label.setAlignment(Align.center);
    }

    private static Texture randomTexture(TypingGame game) {
        word = words[MathUtils.random(0, words.length - 1)];
        if (word.length() <= 3)
            return game.getManager().get("textures/wooden_crate.png");
        else if (word.length() <= 100)
            return game.getManager().get("textures/golden_crate.png");
        return null;
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null) {
            stage.addActor(table);
            table.setZIndex(100); // A lower number still lets the turret be over the label (???)
            this.setZIndex(1);
        }
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        if (table != null) {
            table.setPosition(getX(), getY() + 50);
            for (Vector2 finishPoint : finishPoints) {
                if (getX() == finishPoint.x * 64 && getY() == finishPoint.y * 64) {
                    remove();
                    table.remove();
                }
            }
        }
    }

    public String getWord() {
        return word;
    }
}
