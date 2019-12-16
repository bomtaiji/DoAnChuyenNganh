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

public class bunAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> mangBun;

    public bunAdapter(Context context, ArrayList<SanPham> mangBun) {
        this.context = context;
        this.mangBun = mangBun;
    }

    @Override
    public int getCount() {
        return mangBun.size();
    }

    @Override
    public Object getItem(int position) {
        return mangBun.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenBun,txtGiaBun,txtMotaBun;
        public ImageView imageViewBun;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_bun,null);
            viewHolder.txtTenBun=(TextView) convertView.findViewById(R.id.textViewtenBun);
            viewHolder.txtGiaBun=(TextView) convertView.findViewById(R.id.textViewgiaBun);
            viewHolder.txtMotaBun=(TextView) convertView.findViewById(R.id.textViewgmotaBun);
            viewHolder.imageViewBun=(ImageView) convertView.findViewById(R.id.imageviewBun);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SanPham sanPham= (SanPham) getItem(position);
        viewHolder.txtTenBun.setText(sanPham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaBun.setText("Giá : "+decimalFormat.format(sanPham.getGiasp())+"đồng");
        viewHolder.txtMotaBun.setMaxLines(2);
        viewHolder.txtMotaBun.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaBun.setText(sanPham.getMota());
        Picasso.with(context).load(sanPham.getHinhanh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imageViewBun);

        return convertView;
    }
}
