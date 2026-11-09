package model;

public class MonAn {
    private String ma;
    private String ten;
    private double gia;
    private int soLuong;

    public MonAn(String ma, String ten, double gia, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getMa() { return ma; }
    public String getTen() { return ten; }
    public double getGia() { return gia; }
    public int getSoLuong() { return soLuong; }

    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-20s | %10.0f đ | SL: %d |",
                ma, ten, gia, soLuong);
    }
}