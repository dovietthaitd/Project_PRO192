package service;

import model.AmTra;
import model.ChiTietHoaDon;
import model.MonAn;
import model.Tra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyHoaDon {
    private QuanLyTra qlTra;
    private QuanLyAmTra qlAm;
    private QuanLyMonAn qlMon;

    public QuanLyHoaDon(QuanLyTra qlTra, QuanLyAmTra qlAm, QuanLyMonAn qlMon) {
        this.qlTra = qlTra;
        this.qlAm = qlAm;
        this.qlMon = qlMon;
    }

    public void taoHoaDon(Scanner sc) {
        List<ChiTietHoaDon> chiTiet = new ArrayList<>();

        System.out.println("\n========== TẠO HÓA ĐƠN ==========");

        // ============ BƯỚC 1: MUA TRÀ ============
        System.out.println("\n--- [1] MUA TRÀ ---");
        qlTra.hienThi();
        while (true) {
            System.out.print("Nhập mã trà (Enter để bỏ qua): ");
            String ma = sc.nextLine().trim();
            if (ma.isEmpty()) break;

            Tra t = qlTra.timTheoMa(ma);
            if (t == null) { System.out.println("❌ Không tìm thấy trà!"); continue; }
            if (t.getSoLuong() <= 0) { System.out.println("❌ Trà đã hết hàng!"); continue; }

            int sl = nhapSoNguyen(sc, "Số lượng mua: ");
            if (sl <= 0) { System.out.println("❌ Số lượng phải > 0!"); continue; }
            if (sl > t.getSoLuong()) {
                System.out.println("❌ Chỉ còn " + t.getSoLuong() + " phần!");
                continue;
            }

            chiTiet.add(new ChiTietHoaDon("Trà", t.getTen(), t.getGia(), sl));
            t.setSoLuong(t.getSoLuong() - sl);
            System.out.printf("  ✅ Đã thêm: %s x%d\n", t.getTen(), sl);
        }

        // ============ BƯỚC 2: THUÊ ẤM (không bắt buộc) ============
        System.out.println("\n--- [2] THUÊ ẤM TRÀ (nếu có) ---");
        System.out.print("Khách có thuê ấm không? (y/n): ");
        String coThue = sc.nextLine().trim();
        if (coThue.equalsIgnoreCase("y")) {
            qlAm.hienThi();
            while (true) {
                System.out.print("Nhập mã ấm (Enter để bỏ qua): ");
                String ma = sc.nextLine().trim();
                if (ma.isEmpty()) break;

                AmTra a = qlAm.timTheoMa(ma);
                if (a == null) { System.out.println("❌ Không tìm thấy ấm!"); continue; }
                if (a.isDangThue()) { System.out.println("❌ Ấm đang được thuê!"); continue; }

                int gio = nhapSoNguyen(sc, "Số giờ thuê: ");
                if (gio <= 0) { System.out.println("❌ Số giờ phải > 0!"); continue; }

                chiTiet.add(new ChiTietHoaDon("Ấm thuê", a.getTen() + " (" + gio + "h)", a.getGiaThue(), gio));
                a.setDangThue(true);
                System.out.printf("  ✅ Đã thuê: %s trong %d giờ\n", a.getTen(), gio);
            }
        }

        // ============ BƯỚC 3: ĐỒ ĂN KÈM (không bắt buộc) ============
        System.out.println("\n--- [3] ĐỒ ĂN KÈM (nếu có) ---");
        System.out.print("Khách có đặt đồ ăn kèm không? (y/n): ");
        String coAn = sc.nextLine().trim();
        if (coAn.equalsIgnoreCase("y")) {
            qlMon.hienThi();
            while (true) {
                System.out.print("Nhập mã món (Enter để bỏ qua): ");
                String ma = sc.nextLine().trim();
                if (ma.isEmpty()) break;

                MonAn m = qlMon.timTheoMa(ma);
                if (m == null) { System.out.println("❌ Không tìm thấy món!"); continue; }
                if (m.getSoLuong() <= 0) { System.out.println("❌ Món đã hết!"); continue; }

                int sl = nhapSoNguyen(sc, "Số lượng: ");
                if (sl <= 0) { System.out.println("❌ Số lượng phải > 0!"); continue; }
                if (sl > m.getSoLuong()) {
                    System.out.println("❌ Chỉ còn " + m.getSoLuong() + " phần!");
                    continue;
                }

                chiTiet.add(new ChiTietHoaDon("Đồ ăn kèm", m.getTen(), m.getGia(), sl));
                m.setSoLuong(m.getSoLuong() - sl);
                System.out.printf("  ✅ Đã thêm: %s x%d\n", m.getTen(), sl);
            }
        }

        // ============ BƯỚC 4: IN HÓA ĐƠN ============
        if (chiTiet.isEmpty()) {
            System.out.println("\n⚠️ Hóa đơn trống, không có gì để thanh toán.");
            return;
        }
        inHoaDon(chiTiet);
    }

    // ============ HÀM IN HÓA ĐƠN ============
    private void inHoaDon(List<ChiTietHoaDon> chiTiet) {
        double tongBill = 0;

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   🍵 HÓA ĐƠN TIỆM TRÀ MẠN 🍵             ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-14s | %-20s | %10s | %5s | %12s ║%n",
                "Loại", "Tên", "Đơn giá", "SL", "Thành tiền");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");

        for (ChiTietHoaDon ct : chiTiet) {
            System.out.printf("║ %-14s | %-20s | %8.0f đ | %5d | %9.0f đ ║%n",
                    ct.getLoai(), ct.getTen(), ct.getDonGia(),
                    ct.getSoLuong(), ct.getThanhTien());
            tongBill += ct.getThanhTien();
        }

        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-56s %18.0f đ ║%n", "TỔNG BILL:", tongBill);
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        System.out.printf("💰 Khách phải thanh toán: %.0f đ\n", tongBill);
        System.out.println("Cảm ơn quý khách đã ghé tiệm! 🍵\n");
    }

    // ============ HÀM NHẬP SỐ AN TOÀN ============
    private int nhapSoNguyen(Scanner sc, String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                int so = Integer.parseInt(sc.nextLine().trim());
                return so;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }
}