package Z_UI;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2016/12/14.
 */
public class AnimationUtils {

    public void startRotateAnimation(View view, long durationMillis, int repeatCount) {
        if (view == null) return;
        Animation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        view.setAnimation(rotateAnimation);
        rotateAnimation.setInterpolator(linearInterpolator);
        rotateAnimation.setDuration(durationMillis);
        rotateAnimation.setRepeatCount(repeatCount);
        rotateAnimation.start();
    }
}
