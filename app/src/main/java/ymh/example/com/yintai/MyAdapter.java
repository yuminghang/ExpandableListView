package ymh.example.com.yintai;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


//自定义适配器
class MyAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    //图片数组
    private int childPos;
    private int grouposition;
    private String s;


    MyAdapter(Context context, int grouposition, int childPosition) {
        this.context = context;
        this.childPos = childPosition;
        this.grouposition = grouposition;
    }

    public int getCount() {
        if (childPos == 1 && grouposition == 0) {
            return 1;
        } else if (childPos == 0 && grouposition == 0) {
            return 3;
        } else if (childPos == 6 && grouposition == 1)
            return 2;
        return 4;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public Button getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            btn = new Button(context);
//            btn.setAdjustViewBounds(false);//设置边界对齐
//            btn.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型
            btn.setPadding(8, 8, 8, 8);//设置间距
        } else {
            btn = (Button) convertView;
        }
        if (grouposition == 0) {
            switch (position) {
                case 0:
                    if (childPos == 0) {
                        s = "全部";
                    } else {
                        s = "鞋帽";
                    }
                    break;
                case 1:
                    s = "服饰";
                    break;
                case 2:
                    s = "饰品";
                    break;
            }
//            btn.setWidth(15);
//            btn.setWidth(15);
            btn.setBackgroundResource(R.drawable.circle_button_big);//为ImageView设置图片资源
            btn.setLayoutParams(new GridView.LayoutParams(200, 200));//设置ImageView对象布局

        } else {
            char pos = (char) (position += 65 + (childPos * 4));
            s = String.valueOf(pos);
            btn.setBackgroundResource(R.drawable.circle_button);//为ImageView设置图片资源
            btn.setLayoutParams(new GridView.LayoutParams(150, 150));//设置ImageView对象布局

        }

        btn.setText(s);
        btn.setTextColor(context.getResources().getColor(R.color.black));
        btn.setClickable(false);
        btn.setFocusable(false);
        return btn;
    }
}