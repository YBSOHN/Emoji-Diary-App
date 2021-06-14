package yb.emojidiary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import yb.emojidiary.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by YB on 9/24/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    final float IMAGE_SIZE = 35;
    Context inContext;
    LayoutInflater mInflater;
    ArrayList<datadiary> dataList;

    Bitmap[] drawablesWeahter = new Bitmap[9];
    Bitmap[] drawablesActivity = new Bitmap[16];
    Bitmap[] drawablesFleeing = new Bitmap[20];

    public RecyclerViewAdapter(Context mContext, ArrayList<datadiary>list){
        inContext=mContext;
        mInflater= LayoutInflater.from(mContext);
        dataList=list;
        loadDrawable(R.drawable.weather0, drawablesWeahter);
        loadDrawable(R.drawable.activity0, drawablesActivity);
        loadDrawable(R.drawable.emoticon0, drawablesFleeing);
    }

    private void loadDrawable(int id, Bitmap[] result){
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inSampleSize = 4;

        for(int i = 0; i < result.length; ++ i){
            result[i] = BitmapFactory.decodeResource(inContext.getResources(), id + i, o);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.home,parent,false));
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(dataList.get(position).d.getTime());
        holder.dateTextView.setText(formatted);

        DrawImage(holder.emoticonWeatherLayout, dataList.get(position).a, drawablesWeahter);
        DrawImage(holder.emoticonActivityLayout, dataList.get(position).a, drawablesActivity);
        DrawImage(holder.emoticonLayout, dataList.get(position).a, drawablesFleeing);


    }


    private void DrawImage(ViewGroup layout, String data, Bitmap[] img){
        Set<Integer> set = new TreeSet<>();

        String[] arr = data.split(",");
        for(int i=0;i<arr.length;++i) {
            set.add(Integer.parseInt(arr[i])) ;
        }

        int index = 0;
        Iterator<Integer> it2 = set.iterator();
        while(it2.hasNext()){
            int id = it2.next();
            ImageView emoticonImage2 = new ImageView(inContext);
            emoticonImage2.setImageBitmap(img[id]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(convertDpToPixels(IMAGE_SIZE), convertDpToPixels(IMAGE_SIZE));
            int marginTop = 0;
            if(++index > 4){
                marginTop = convertDpToPixels(10);
            }
            lp.setMargins(convertDpToPixels(10), marginTop,convertDpToPixels(10),0);
            layout.addView(emoticonImage2, lp);
        }
    }

    public int convertDpToPixels(float dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, inContext.getResources().getDisplayMetrics());
        return px;
    }

    public int getItemCount() {
        return dataList.size();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder{

        TextView dateTextView;
        GridLayout emoticonLayout, emoticonWeatherLayout, emoticonActivityLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
            emoticonLayout = (GridLayout) itemView.findViewById(R.id.emoticonlayout);
            emoticonWeatherLayout = (GridLayout) itemView.findViewById(R.id.emoticonweatherlayout);
            emoticonActivityLayout = (GridLayout) itemView.findViewById(R.id.emoticonactivitylayout);
        }
    }
}
