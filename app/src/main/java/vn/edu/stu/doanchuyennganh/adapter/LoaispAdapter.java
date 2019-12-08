package vn.edu.stu.doanchuyennganh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.model.LoaiSanPham;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<LoaiSanPham> arrlistLoaisp;
    Context context;

    public LoaispAdapter(ArrayList<LoaiSanPham> arrlistLoaisp, Context context) {
        this.arrlistLoaisp = arrlistLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrlistLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrlistLoaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int position, View view, ViewGroup Viewgrout) {
        ViewHolder viewHolder=null;
        if(viewHolder==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_sanpham,null);
            viewHolder.txttenloaisp= (TextView) view.findViewById(R.id.textviewLoaisp);
            viewHolder.imgloaisp=(ImageView)view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        LoaiSanPham loaisp=(LoaiSanPham) getItem(position);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaiSP());
        Picasso.with(context).load(loaisp.getHinhanhloaiSP())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imgloaisp);
        return view;
    }

}
