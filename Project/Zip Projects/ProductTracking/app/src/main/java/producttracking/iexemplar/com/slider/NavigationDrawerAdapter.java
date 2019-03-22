package producttracking.iexemplar.com.slider;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.service.model.NavDrawerItem;
import producttracking.iexemplar.com.ui.Home;

/************************************************************************************
 * Class      : NavigationDrawerAdapter
 * Created on : 11/20/2017
 * Updated on : 11/21/2017
 * Created By : iExemplar Software India Pvt Ltd.
 **************************************************************************************/
public class NavigationDrawerAdapter extends BaseAdapter {
    NavigationCallBack navigationCallBack;
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    /************************************************************************************
     * Class      : NavigationDrawerAdapter
     * Use        : constructor for navigationDrawerapater
     * Created on : 11/20/2017
     * Updated on : 11/21/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data, NavigationCallBack navigationCallBack) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.navigationCallBack = navigationCallBack;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        MyViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.nav_drawer_row, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }


        NavDrawerItem current = data.get(position);
        holder.tvNavItemTxt.setText(current.getTitle());

        if (Home.selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.color.colorAccent);
           // holder.tvNavItemTxt.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            holder.tvNavItemTxt.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        } else {
            holder.itemView.setBackgroundResource(android.R.color.transparent);
            holder.tvNavItemTxt.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.selectedPosition = position;
                notifyDataSetChanged();
                navigationCallBack.clickNavigationDetails(position);

            }
        });
        return convertView;
    }


    /************************************************************************************
     * Class      : NavigationDrawerAdapter
     * Use        : Method call for holding the widgets of ids
     * Created on : 11/20/2017
     * Updated on : 11/21/2017
     * Created By : iExemplar Software India Pvt Ltd.
     **************************************************************************************/
    class MyViewHolder {

        @BindView(R.id.tv_nav_item_text)
        TextView tvNavItemTxt;

        @BindView(R.id.itemView)
        RelativeLayout itemView;

        public MyViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);

        }
    }

    public interface NavigationCallBack {

        void clickNavigationDetails(int position);

    }
}
