package service;

import model.MonAn;
import java.util.ArrayList;
import java.util.List;

public class QuanLyMonAn {
    private List<MonAn> danhSach = new ArrayList<>();

    public void them(MonAn m) {
        for (MonAn x : danhSach) {
            if (x.getMa().equalsIgnoreCase(m.getMa())) {
                System.out.println("❌ Mã món đã tồn tại!");
                return;
            }
        }
        danhSach.add(m);
        System.out.println("✅ Thêm món thành công!");
    }

    public boolean xoa(String ma) {
        return danhSach.removeIf(m -> m.getMa().equalsIgnoreCase(ma));
    }

    public MonAn timTheoMa(String ma) {
        for (MonAn m : danhSach) {
            if (m.getMa().equalsIgnoreCase(ma)) return m;
        }
        return null;
    }

    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("📭 Danh sách món trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH MÓN ĂN KÈM =====");
        for (MonAn m : danhSach) System.out.println(m);
    }

    public List<MonAn> getDanhSach() { return danhSach; }
}