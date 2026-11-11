package service;

import model.TeaPot;
import model.BillDetail;
import model.Dish;
import model.Tea;
import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BillManager {
    private TeaManager teaManager;
    private TeaPotManager teaPotManager;
    private DishManager dishManager;
    private RoomManager roomManager;

    public BillManager(TeaManager teaManager, TeaPotManager teaPotManager, DishManager dishManager, RoomManager roomManager) {
        this.teaManager = teaManager;
        this.teaPotManager = teaPotManager;
        this.dishManager = dishManager;
        this.roomManager = roomManager;
    }

    

    public void createBill(Scanner sc) {
        List<BillDetail> billDetails = new ArrayList<>();
        // ============ BƯỚC 1: Thuê Phòng ============
        System.out.println("\n========== TẠO HÓA ĐƠN ==========");
        System.out.println("\n--- [0] THUÊ PHÒNG (nếu có) ---");
        System.out.print("Khách có thuê phòng không? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            roomManager.display();
            while (true) {
                System.out.print("Nhập mã phòng (Enter để bỏ qua): ");
                String id = sc.nextLine().trim();
                if (id.isEmpty()) break;

                Room p = roomManager.findById(id);
                if (p == null) { System.out.println("❌ Không tìm thấy phòng!"); continue; }
                if (p.isStatus()) { System.out.println("❌ Phòng đang có khách!"); continue; }

                int h = readInteger(sc, "Số giờ thuê: ");
                if (h <= 0) { System.out.println("❌ Số giờ phải > 0!"); continue; }

                billDetails.add(new BillDetail("Phòng", p.getName()+ " (" + h + "h)", p.getPrice(), h));
                p.setStatus(true);
                System.out.printf("  ✅ Đã thuê: %s trong %d giờ\n", p.getName(), h);
                break; // một khách thường chỉ thuê một phòng
            }
        }


        // ============ BƯỚC 2: MUA TRÀ ============
        System.out.println("\n--- [2] MUA TRÀ ---");
        teaManager.displayAll();
        while (true) {
            System.out.print("Nhập mã trà (Enter để bỏ qua): ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) break;

            Tea tea = teaManager.findById(id);
            if (tea == null) { System.out.println("❌ Không tìm thấy trà!"); continue; }
            if (tea.getQuantity() <= 0) { System.out.println("❌ Trà đã hết hàng!"); continue; }

            int quantity = readInteger(sc, "Số lượng mua: ");
            if (quantity <= 0) { System.out.println("❌ Số lượng phải > 0!"); continue; }
            if (quantity > tea.getQuantity()) {
                System.out.println("❌ Chỉ còn " + tea.getQuantity() + " phần!");
                continue;
            }

            billDetails.add(new BillDetail("Trà", tea.getName(), tea.getPrice(), quantity));
            tea.setQuantity(tea.getQuantity() - quantity);
            System.out.printf("  ✅ Đã thêm: %s x%d\n", tea.getName(), quantity);
        }

        // ============ BƯỚC 3: THUÊ ẤM (không bắt buộc) ============
        System.out.println("\n--- [3] THUÊ ẤM TRÀ (nếu có) ---");
        System.out.print("Khách có thuê ấm không? (y/n): ");
        String rentTeaPot = sc.nextLine().trim();
        if (rentTeaPot.equalsIgnoreCase("y")) {
            teaPotManager.displayAll();
            while (true) {
                System.out.print("Nhập mã ấm (Enter để bỏ qua): ");
                String id = sc.nextLine().trim();
                if (id.isEmpty()) break;

                TeaPot teaPot = teaPotManager.findById(id);
                if (teaPot == null) { System.out.println("❌ Không tìm thấy ấm!"); continue; }
                if (teaPot.isRented()) { System.out.println("❌ Ấm đang được thuê!"); continue; }

                int hours = readInteger(sc, "Số giờ thuê: ");
                if (hours <= 0) { System.out.println("❌ Số giờ phải > 0!"); continue; }

                billDetails.add(new BillDetail("Ấm thuê", teaPot.getName() + " (" + hours + "h)", teaPot.getRentalPrice(), hours));
                teaPot.setRented(true);
                System.out.printf("  ✅ Đã thuê: %s trong %d giờ\n", teaPot.getName(), hours);
            }
        }

        // ============ BƯỚC 4: ĐỒ ĂN KÈM (không bắt buộc) ============
        System.out.println("\n--- [4] ĐỒ ĂN KÈM (nếu có) ---");
        System.out.print("Khách có đặt đồ ăn kèm không? (y/n): ");
        String orderSnacks = sc.nextLine().trim();
        if (orderSnacks.equalsIgnoreCase("y")) {
            dishManager.displayAll();
            while (true) {
                System.out.print("Nhập mã món (Enter để bỏ qua): ");
                String id = sc.nextLine().trim();
                if (id.isEmpty()) break;

                Dish dish = dishManager.findById(id);
                if (dish == null) { System.out.println("❌ Không tìm thấy món!"); continue; }
                if (dish.getQuantity() <= 0) { System.out.println("❌ Món đã hết!"); continue; }

                int quantity = readInteger(sc, "Số lượng: ");
                if (quantity <= 0) { System.out.println("❌ Số lượng phải > 0!"); continue; }
                if (quantity > dish.getQuantity()) {
                    System.out.println("❌ Chỉ còn " + dish.getQuantity() + " phần!");
                    continue;
                }

                billDetails.add(new BillDetail("Đồ ăn kèm", dish.getName(), dish.getPrice(), quantity));
                dish.setQuantity(dish.getQuantity() - quantity);
                System.out.printf("  ✅ Đã thêm: %s x%d\n", dish.getName(), quantity);
            }
        }

        // ============ BƯỚC 5: IN HÓA ĐƠN ============
        if (billDetails.isEmpty()) {
            System.out.println("\n⚠️ Hóa đơn trống, không có gì để thanh toán.");
            return;
        }
        printBill(billDetails);
    }

    // ============ HÀM IN HÓA ĐƠN ============
    private void printBill(List<BillDetail> billDetails) {
        double totalBill = 0;

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   🍵 HÓA ĐƠN TIỆM TRÀ MẠN 🍵             ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-14s | %-20s | %10s | %5s | %12s ║%n",
                "Loại", "Tên", "Đơn giá", "SL", "Thành tiền");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");

        for (BillDetail detail : billDetails) {
            System.out.printf("║ %-14s | %-20s | %8.0f đ | %5d | %9.0f đ ║%n",
                    detail.getType(), detail.getName(), detail.getUnitPrice(),
                    detail.getQuantity(), detail.getTotalPrice());
            totalBill += detail.getTotalPrice();
        }

        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-56s %18.0f đ ║%n", "TỔNG BILL:", totalBill);
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
        System.out.printf("💰 Khách phải thanh toán: %.0f đ\n", totalBill);
        System.out.println("Cảm ơn quý khách đã ghé tiệm! 🍵\n");
    }

    // ============ HÀM NHẬP SỐ AN TOÀN ============
    private int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }
}