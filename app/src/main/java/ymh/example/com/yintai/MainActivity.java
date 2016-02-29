package ymh.example.com.yintai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ExpandableListView mainlistview = null;
    List<String> parent = null;
    Map<String, List<String>> map = null;
    GridView gv;
    private MyAdapter adatper;
    private int imgs;
    private MyListAdapter myListAdapter;
    private TextView parent_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainlistview = (ExpandableListView) this.findViewById(R.id.main_expandablelistview);

        initData();
        myListAdapter = new MyListAdapter();
        mainlistview.setAdapter(myListAdapter);
//        mainlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(MainActivity.this, "hshaha" + childPosition + groupPosition, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
        mainlistview.setGroupIndicator(null);
        mainlistview.
//        mainlistview.setDividerHeight(30);
//        mainlistview.setBackgroundResource(R.color.black);
//        View view = View.inflate(this, R.layout.layout_parent, null);
//        parent_textview = (TextView) view.findViewById(R.id.parent_textview);
//        mainlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                view.setBackgroundResource(R.color.black);
//                mainlistview.collapseGroup(1 - position);
//                Toast.makeText(MainActivity.this, ";;" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        mainlistview.setSelectedGroup(0);
//        /**
//         * 关闭其他父group
//         */
//        mainlistview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                mainlistview.collapseGroup(1 - groupPosition);
//            }
//        });
    }
//    mainlistview.setOnClickListener()


    // 初始化数据
    public void initData() {
        parent = new ArrayList<String>();
        parent.add("品牌查找");
        parent.add("字母查找");
        imgs = 0;
        map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<>();
        list1.add("child1-1");
        list1.add("child1-2");
        list1.add("child1-3");
        map.put("品牌查找", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("child2-1");
        list2.add("child2-2");
        list2.add("child2-3");
        map.put("字母查找", list2);


    }

    class MyListAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener {

        //得到子item需要关联的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gridview_layout, null);
            }
            gv = (GridView) convertView.findViewById(R.id.gv);
            if (groupPosition == 1) {
                gv.setNumColumns(4);// 设置每行列数
            } else {
                gv.setNumColumns(3);// 设置每行列数
            }
            adatper = new MyAdapter(MainActivity.this, groupPosition, childPosition);
            gv.setAdapter(adatper);// 设置菜单Adapter
            gv.setOnItemClickListener(this);
            adatper.notifyDataSetChanged();
            return convertView;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            int size = map.get(key).size();
            if (groupPosition == 1) {
                return 7;
            } else {
                return 2;
            }
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //设置父item组件
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_parent, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.parent_textview);
            tv.setText(MainActivity.this.parent.get(groupPosition));
            tv.setTextColor(getResources().getColor(R.color.bg_gray));
            tv.setTextSize(18);
            return tv;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(MainActivity.this, "当前选中的是:" + position, Toast.LENGTH_SHORT).show();
            Button btn = (Button) view;
            String test = btn.getText().toString();
            Toast.makeText(MainActivity.this, "" + test, Toast.LENGTH_SHORT).show();
        }
    }
}

