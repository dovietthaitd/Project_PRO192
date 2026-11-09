package model;

public class ChiTietHoaDon {
    private String loai;       // "Trà", "Ấm thuê", "Đồ ăn kèm"
    private String ten;
    private double donGia;
    private int soLuong;       // số lượng (hoặc số giờ nếu là ấm)
    private double thanhTien;  // = donGia * soLuong

    public ChiTietHoaDon(String loai, String ten, double donGia, int soLuong) {
        this.loai = loai;
        this.ten = ten;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = donGia * soLuong;
    }

    public String getLoai()      { return loai; }
    public String getTen()       { return ten; }
    public double getDonGia()    { return donGia; }
    public int getSoLuong()      { return soLuong; }
    public double getThanhTien() { return thanhTien; }
}