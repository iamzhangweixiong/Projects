package Z_RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangwx.myapplication.R;

/**
 * Created by zhangwx on 2016/12/10.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private SparseArray<String> dataArray = new SparseArray<>(20);

    public RecycleAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < dataArray.size(); i++) {
            dataArray.put(i, "i am a card of the f*cking type --> " + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclecard, parent, false);
        return new CardHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CardHolder) {
            ((CardHolder) holder).setText(R.id.recycle_text, dataArray.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    public void addDataAtTop(int size) {

    }

}
