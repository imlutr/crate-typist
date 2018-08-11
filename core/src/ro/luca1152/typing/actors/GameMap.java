package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ro.luca1152.typing.TypingGame;

public class GameMap extends Image {
    private int mapId;
    private Turret turret;

    public GameMap(TypingGame game, int mapId) {
        super(game.getManager().get("maps/map" + mapId + ".png", Texture.class));
        this.mapId = mapId;
        turret = new Turret(game, 284f, 284f);
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null)
            getStage().addActor(turret);
    }

    public Turret getTurret() {
        return turret;
    }

    public int getId() {
        return mapId;
    }
}
