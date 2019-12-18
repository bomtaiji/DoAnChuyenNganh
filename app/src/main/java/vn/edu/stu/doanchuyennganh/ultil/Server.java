package vn.edu.stu.doanchuyennganh.ultil;

import com.android.volley.toolbox.StringRequest;

public class Server {
    public static String localhost="http://192.168.0.100";
    public static String DuongDanLoaiSP= localhost +"/DoAn/hienthiLoaiSanPham.php";
    public static String DuongDanSanPham= localhost +"/DoAn/hienthiSanPham.php";
    public static  String DuongDanCom= localhost+"/DoAn/LaySanPham.php?page=";
    public static String DuongDanDonHang= localhost+ "/DoAn/Dondathang.php";
    public static String ĐuonganChiTietDonHang= localhost+ "/DoAn/ChiTietDonHang.php";
}
