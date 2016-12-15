package Z_RecycleView.data;

import android.graphics.drawable.Drawable;

import com.zhangwx.myapplication.MyApplication;
import com.zhangwx.myapplication.R;

import java.util.Random;

/**
 * Created by zhangwx on 2016/12/14.
 */
public class ListData {
    public Drawable getDrawable() {
        return MyApplication.getContext().getResources().getDrawable(R.drawable.password_bao1);
    }

    public String getText() {
        Random random = new Random();
        return "my feedData ------>>> " + random.nextInt(100);
    }

}
