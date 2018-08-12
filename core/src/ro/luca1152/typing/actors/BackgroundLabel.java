package ro.luca1152.typing.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BackgroundLabel extends Label {
    public BackgroundLabel(CharSequence text, LabelStyle labelStyle) {
        super(text, labelStyle);
        Pixmap labelBg = new Pixmap((int)getPrefWidth(), (int)getPrefHeight(), Pixmap.Format.RGBA8888);
        labelBg.setColor(0f, 0f, 0f, 0.55f);
        labelBg.fill();
        getStyle().background = new Image(new Texture(labelBg)).getDrawable();
    }
}
