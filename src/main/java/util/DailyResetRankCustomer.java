package util;

import core.quanly.repository.KhachHangRespository;
import domainmodels.KhachHang;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class DailyResetRankCustomer extends Thread {

    private static final KhachHangRespository khachHangRespository = new KhachHangRespository();

    public static void dailyChecking() {
        Thread countDownThread = new Thread() {
            @Override
            public void run() {
                do {
                    List<KhachHang> listKhachHang = khachHangRespository.getAllKhacHangDangHoatDong();
                    for (KhachHang xx : listKhachHang) {
                        if (xx.getThoiDiemResetGanNhat() == null) {
                            if (xx.getCreatedDate() + 15552000000L < Calendar.getInstance().getTimeInMillis()) {
                                xx.setThoiDiemResetGanNhat(Calendar.getInstance().getTimeInMillis());
                                xx.setCapBac(0);
                                khachHangRespository.saveOrUpdate(xx);
                            }
                        }else{
                            if (xx.getThoiDiemResetGanNhat()+ 15552000000L < Calendar.getInstance().getTimeInMillis()) {
                                xx.setThoiDiemResetGanNhat(Calendar.getInstance().getTimeInMillis());
                                xx.setCapBac(0);
                                khachHangRespository.saveOrUpdate(xx);
                            }
                        }
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
