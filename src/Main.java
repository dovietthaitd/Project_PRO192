import model.AmTra;
import model.MonAn;
import model.Tra;
import service.QuanLyAmTra;
import service.QuanLyHoaDon;
import service.QuanLyMonAn;
import service.QuanLyTra;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static QuanLyTra qlTra = new QuanLyTra();
    static QuanLyAmTra qlAm = new QuanLyAmTra();
    static QuanLyMonAn qlMon = new QuanLyMonAn();
    static QuanLyHoaDon qlHD = new QuanLyHoaDon(qlTra, qlAm, qlMon);

    public static void main(String[] args) {
        khoiTaoDuLieu();

        while (true) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║   🍵 QUẢN LÝ PHÒNG TRÀ 🍵 ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Quản lý trà           ║");
            System.out.println("║ 2. Quản lý ấm trà        ║");
            System.out.println("║ 3. Quản lý món ăn kèm    ║");
            System.out.println("║ 4. Tạo hóa đơn           ║");
            System.out.println("║ 0. Thoát                 ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Chọn: ");

            String chon = sc.nextLine();
            switch (chon) {
                case "1": menuTra(); break;
                case "2": menuAm(); break;
                case "3": menuMon(); break;
                case "4": qlHD.taoHoaDon(sc); break;
                case "0":
                    System.out.println("👋 Tạm biệt!");
                    return;
                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ.");
            }
        }
    }

    // ================== MENU TRÀ ==================
    static void menuTra() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ TRÀ ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm trà");
            System.out.println("3. Xóa trà");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String chon = sc.nextLine();
            switch (chon) {
                case "1": qlTra.hienThi(); break;
                case "2":
                    System.out.print("Mã: "); String ma = sc.nextLine();
                    System.out.print("Tên: "); String ten = sc.nextLine();
                    System.out.print("Giá: "); double gia = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int sl = Integer.parseInt(sc.nextLine());
                    qlTra.them(new Tra(ma, ten, gia, sl));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String maXoa = sc.nextLine();
                    System.out.println(qlTra.xoa(maXoa) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== MENU ẤM ==================
    static void menuAm() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ẤM TRÀ ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm ấm");
            System.out.println("3. Xóa ấm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String chon = sc.nextLine();
            switch (chon) {
                case "1": qlAm.hienThi(); break;
                case "2":
                    System.out.print("Mã: "); String ma = sc.nextLine();
                    System.out.print("Tên: "); String ten = sc.nextLine();
                    System.out.print("Giá thuê/giờ: "); double gia = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int sl = Integer.parseInt(sc.nextLine());
                    qlAm.them(new AmTra(ma, ten, gia, sl));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String maXoa = sc.nextLine();
                    System.out.println(qlAm.xoa(maXoa) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== MENU MÓN ==================
    static void menuMon() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÓN ĂN KÈM ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm món");
            System.out.println("3. Xóa món");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            String chon = sc.nextLine();
            switch (chon) {
                case "1": qlMon.hienThi(); break;
                case "2":
                    System.out.print("Mã: "); String ma = sc.nextLine();
                    System.out.print("Tên: "); String ten = sc.nextLine();
                    System.out.print("Giá: "); double gia = Double.parseDouble(sc.nextLine());
                    System.out.print("Số lượng: "); int sl = Integer.parseInt(sc.nextLine());
                    qlMon.them(new MonAn(ma, ten, gia, sl));
                    break;
                case "3":
                    System.out.print("Nhập mã cần xóa: ");
                    String maXoa = sc.nextLine();
                    System.out.println(qlMon.xoa(maXoa) ? "✅ Đã xóa!" : "❌ Không tìm thấy!");
                    break;
                case "0": return;
            }
        }
    }

    // ================== DỮ LIỆU MẪU ==================
    static void khoiTaoDuLieu() {
        // Trà phổ biến
        qlTra.them(new Tra("T01", "Phổ nhĩ(10g/phần)", 350000, 50));
        qlTra.them(new Tra("T02", "Phổ nhĩ quýt(1 viên)", 50000, 30));
        qlTra.them(new Tra("T03", "Đại Hồng Bào", 45000, 40));
        qlTra.them(new Tra("T04", "Bạch Hào Ngân Châm", 40000, 60));
        qlTra.them(new Tra("T05", "Mông Đỉnh Hoàng Nha", 30000, 25));

        // Ấm trà
        qlAm.them(new AmTra("A01", "Ấm tử sa nhỏ", 200000, 10));
        qlAm.them(new AmTra("A02", "Ấm Sứ", 250000, 8));
        qlAm.them(new AmTra("A03", "Ấm Tử Đào", 150000, 12));
        qlAm.them(new AmTra("A04", "Ấm Nê Hưng", 200000, 10));
        qlAm.them(new AmTra("A04", "Ấm Vinh Xương", 200000, 10));

        // Món ăn kèm
        qlMon.them(new MonAn("M01", "Bánh đậu xanh", 15000, 100));
        qlMon.them(new MonAn("M02", "Bánh trung thu", 30000, 50));
        qlMon.them(new MonAn("M03", "Hạt hướng dương", 10000, 200));
        qlMon.them(new MonAn("M04", "Mứt sen", 20000, 80));
    }
}