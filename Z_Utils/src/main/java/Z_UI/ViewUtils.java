package Z_UI;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zhangwx on 2016/9/5.
 */
public class ViewUtils {
    public static void recycleView(View view) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(null);
        } else {
            view.setBackground(null);
        }
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageBitmap(null);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                recycleView(child);
            }
            viewGroup.removeAllViews();
        }
    }
}
