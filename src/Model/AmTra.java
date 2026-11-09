package model;

public class AmTra {
    private String ma;
    private String ten;
    private double giaThue;   // giá thuê / giờ
    private int soLuong;
    private boolean dangThue; // đang được thuê hay không

    public AmTra(String ma, String ten, double giaThue, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.giaThue = giaThue;
        this.soLuong = soLuong;
        this.dangThue = false;
    }

    public String getMa() { return ma; }
    public String getTen() { return ten; }
    public double getGiaThue() { return giaThue; }
    public int getSoLuong() { return soLuong; }
    public boolean isDangThue() { return dangThue; }

    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setDangThue(boolean dangThue) { this.dangThue = dangThue; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-20s | %8.0f đ/h | SL: %d | %s |",
                ma, ten, giaThue, soLuong, dangThue ? "Đang thuê" : "Sẵn sàng");
    }
}