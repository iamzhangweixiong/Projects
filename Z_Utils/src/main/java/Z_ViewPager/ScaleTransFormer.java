package Z_ViewPager;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhangwx on 2016/12/21.
 */
public class ScaleTransFormer implements ViewPager.PageTransformer {

    private static final float OFFSCREEN_PAGE_SCALE_FACTOR = 0.75F;
    private static final float OFFSCREEN_PAGE_ALPHA_FACTOR = 0.70F;

    @Override
    public void transformPage(View page, float position) {
        if (position < -2 || position > 2) {
            return;
        }
        if (position >= -1 && position <= 1) {
            changeView(page, Math.abs(position));
        } else {
            changeView(page, 1.0F);
        }
    }

    private void changeView(@NonNull View view, float factor) {
        final float scaleFactor = 1.0F - ((1.0F - OFFSCREEN_PAGE_SCALE_FACTOR) * factor);
        final float alphaFactor = 1.0F - ((1.0F - OFFSCREEN_PAGE_ALPHA_FACTOR) * factor);
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
        view.setAlpha(alphaFactor);
    }
}
