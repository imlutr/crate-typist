package ro.luca1152.typing.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Util {
    public static float getAngleBetween(Actor a, Actor b) {
        float angle = (float) Math.atan2((a.getY() + a.getOriginY()) - (b.getY() + b.getOriginY()), (a.getX() + a.getOriginX()) - (b.getX() + b.getOriginX()));
        angle = (float) (angle * (180 / Math.PI));
        if (angle < 0)
            angle = 360 - (-angle);
        angle += 90;
        if (angle > 360)
            angle = angle - 360;
        return angle;
    }
}
