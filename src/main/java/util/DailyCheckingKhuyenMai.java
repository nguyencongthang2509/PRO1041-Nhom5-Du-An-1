package util;

import core.quanly.service.KhuyenMaiService;
import core.quanly.service.impl.KhuyenMaiImpl;
import core.quanly.viewmodel.KhachHangRespone;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class DailyCheckingKhuyenMai extends Thread {

    private static KhuyenMaiService khuyenMaiService = new KhuyenMaiImpl();

    public static void dailyChecking() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhuyenMai> listDangdienRa = khuyenMaiService.GetAllKhuyenMaiDangDienRa();
                    if (listDangdienRa != null) {
                        for (KhuyenMai xx : listDangdienRa) {
                            khuyenMaiService.updateKhuyenMaiDangDienRa(xx.getId());
                        }
                    }
                    List<KhuyenMai> listKhongDienRa = khuyenMaiService.GetAllKhuyenMaiKhongDienRa();
                    if (listKhongDienRa != null) {
                        for (KhuyenMai xx : listKhongDienRa) {
                            khuyenMaiService.updateKhuyenMaiKhongDienRa(xx.getId());
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }
}
