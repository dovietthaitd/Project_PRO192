package service;

import model.AmTra;
import java.util.ArrayList;
import java.util.List;

public class QuanLyAmTra {
    private List<AmTra> danhSach = new ArrayList<>();

    public void them(AmTra a) {
        for (AmTra x : danhSach) {
            if (x.getMa().equalsIgnoreCase(a.getMa())) {
                System.out.println("❌ Mã ấm đã tồn tại!");
                return;
            }
        }
        danhSach.add(a);
        System.out.println("✅ Thêm ấm thành công!");
    }

    public boolean xoa(String ma) {
        return danhSach.removeIf(a -> a.getMa().equalsIgnoreCase(ma));
    }

    public AmTra timTheoMa(String ma) {
        for (AmTra a : danhSach) {
            if (a.getMa().equalsIgnoreCase(ma)) return a;
        }
        return null;
    }

    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("📭 Danh sách ấm trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH ẤM TRÀ =====");
        for (AmTra a : danhSach) System.out.println(a);
    }

    public List<AmTra> getDanhSach() { return danhSach; }
}