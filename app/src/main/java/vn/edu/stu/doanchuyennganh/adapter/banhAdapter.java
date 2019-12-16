package vn.edu.stu.doanchuyennganh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.stu.doanchuyennganh.R;
import vn.edu.stu.doanchuyennganh.model.SanPham;

public class banhAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> mangBanh;

    public banhAdapter(Context context, ArrayList<SanPham> mangBanh) {
        this.context = context;
        this.mangBanh = mangBanh;
    }

    @Override
    public int getCount() {
        return mangBanh.size();
    }

    @Override
    public Object getItem(int position) {
        return mangBanh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenBanh,txtGiaBanh,txtMotaBanh;
        public ImageView imageViewBanh;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_banh,null);
            viewHolder.txtTenBanh=(TextView) convertView.findViewById(R.id.textViewtenBanh);
            viewHolder.txtGiaBanh=(TextView) convertView.findViewById(R.id.textViewgiaBanh);
            viewHolder.txtMotaBanh=(TextView) convertView.findViewById(R.id.textViewgmotaBanh);
            viewHolder.imageViewBanh=(ImageView) convertView.findViewById(R.id.imageviewBanh);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SanPham sanPham= (SanPham) getItem(position);
        viewHolder.txtTenBanh.setText(sanPham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaBanh.setText("Giá : "+decimalFormat.format(sanPham.getGiasp())+"đồng");
        viewHolder.txtMotaBanh.setMaxLines(2);
        viewHolder.txtMotaBanh.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaBanh.setText(sanPham.getMota());
        Picasso.with(context).load(sanPham.getHinhanh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imageViewBanh);

        return convertView;
    }
}
