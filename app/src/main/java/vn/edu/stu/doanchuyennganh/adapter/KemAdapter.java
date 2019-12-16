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

public class KemAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> mangKem;

    public KemAdapter(Context context, ArrayList<SanPham> mangKem) {
        this.context = context;
        this.mangKem = mangKem;
    }

    @Override
    public int getCount() {
        return mangKem.size();
    }

    @Override
    public Object getItem(int position) {
        return mangKem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtTenKem,txtGiaKem,txtMotaKem;
        public ImageView imageViewKem;


    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_kem,null);
            viewHolder.txtTenKem=(TextView) convertView.findViewById(R.id.textViewtenKem);
            viewHolder.txtGiaKem=(TextView) convertView.findViewById(R.id.textViewgiaKem);
            viewHolder.txtMotaKem=(TextView) convertView.findViewById(R.id.textViewgmotaKem);
            viewHolder.imageViewKem=(ImageView) convertView.findViewById(R.id.imageviewKem);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SanPham sanPham= (SanPham) getItem(position);
        viewHolder.txtTenKem.setText(sanPham.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaKem.setText("Giá : "+decimalFormat.format(sanPham.getGiasp())+"đồng");
        viewHolder.txtMotaKem.setMaxLines(2);
        viewHolder.txtMotaKem.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaKem.setText(sanPham.getMota());
        Picasso.with(context).load(sanPham.getHinhanh())
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(viewHolder.imageViewKem);

        return convertView;
    }
}
