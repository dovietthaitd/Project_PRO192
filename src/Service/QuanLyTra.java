package service;

import model.Tra;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTra {
    private List<Tra> danhSach = new ArrayList<>();

    public void them(Tra t) {
        // kiểm tra trùng mã
        for (Tra x : danhSach) {
            if (x.getMa().equalsIgnoreCase(t.getMa())) {
                System.out.println("❌ Mã trà đã tồn tại!");
                return;
            }
        }
        danhSach.add(t);
        System.out.println("✅ Thêm trà thành công!");
    }

    public boolean xoa(String ma) {
        return danhSach.removeIf(t -> t.getMa().equalsIgnoreCase(ma));
    }

    public Tra timTheoMa(String ma) {
        for (Tra t : danhSach) {
            if (t.getMa().equalsIgnoreCase(ma)) return t;
        }
        return null;
    }

    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("📭 Danh sách trà trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH TRÀ =====");
        for (Tra t : danhSach) System.out.println(t);
    }

    public List<Tra> getDanhSach() { return danhSach; }
}