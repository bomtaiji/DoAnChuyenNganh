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

public class ComAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> mangCom;

    public ComAdapter(Context context, ArrayList<SanPham> mangCom) {
        this.context = context;
        this.mangCom = mangCom;
    }

    @Override
    public int getCount() {
        return mangCom.size();
    }

    @Override
    public Object getItem(int position) {
        return mangCom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenCom,txtGiaCom,txtMotaCom;
        public ImageView imageViewCom;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_com,null);
            viewHolder.txtTenCom=(TextView) convertView.findViewById(R.id.textViewtenCom);
            viewHolder.txtGiaCom=(TextView) convertView.findViewById(R.id.textViewgiaCom);
            viewHolder.txtMotaCom=(TextView) convertView.findViewById(R.id.textViewgmotaCom);
            viewHolder.imageViewCom=(ImageView) convertView.findViewById(R.id.imageviewCom);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SanPham sanPham= (SanPham) getItem(position);
        viewHolder.txtTenCom.setText(sanPham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaCom.setText("Giá : "+decimalFormat.format(sanPham.getGiasp())+"đồng");
        viewHolder.txtMotaCom.setMaxLines(2);
        viewHolder.txtMotaCom.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaCom.setText(sanPham.getMota());
        Picasso.with(context).load(sanPham.getHinhanh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imageViewCom);

        return convertView;
    }
}
