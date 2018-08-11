package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameMap extends Image {
    public Turret turret;

    public GameMap(String mapName) {
        super(new Texture("maps/" + mapName + ".png"));
        turret = new Turret(275f, 275f);
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null)
            getStage().addActor(turret);
    }
}
