package util;

import core.quanly.service.KhuyenMaiService;
import core.quanly.service.impl.KhuyenMaiImpl;
import core.quanly.viewmodel.KhachHangRespone;
import core.quanly.viewmodel.KhuyenMaiResponse;
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
                    List<KhuyenMaiResponse> listDangdienRa = khuyenMaiService.GetKhuyenMaiDangDienRa();
                    for (KhuyenMaiResponse xx : listDangdienRa) {
                        khuyenMaiService.updateKhuyenMaiDangDienRa(xx.getIdkm());
                    }
                    List<KhuyenMaiResponse> listKhongDienRa = khuyenMaiService.GetKhuyenMaiKhongDienRa();
                    for (KhuyenMaiResponse xx : listKhongDienRa) {
                        khuyenMaiService.updateKhuyenMaiKhongDienRa(xx.getIdkm());
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        };
        countDownThread.start();
    }
}
