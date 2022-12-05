package core.quanly.repository;

import config.HibernateUtil;
import domainmodels.ChatLieu;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.Hang;
import domainmodels.KhachHang;
import domainmodels.KhuyenMai;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.NhanVien;
import domainmodels.SanPham;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thangncph26123
 */
public class InsertData {

    private CTSanPhamRepository cTSanPhamRepository = new CTSanPhamRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private SPHangRepository sPHangRepository = new SPHangRepository();

    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            MauSac mauSac = new MauSac();
            mauSac.setMa("MS001");
            mauSac.setTen("White");
            session.saveOrUpdate(mauSac);

            MauSac mauSac1 = new MauSac();
            mauSac1.setMa("MS002");
            mauSac1.setTen("Black");
            session.saveOrUpdate(mauSac1);

            MauSac mauSac2 = new MauSac();
            mauSac2.setMa("MS003");
            mauSac2.setTen("Yellow");
            session.saveOrUpdate(mauSac2);

            MauSac mauSac3 = new MauSac();
            mauSac3.setMa("MS004");
            mauSac3.setTen("Blue");
            session.saveOrUpdate(mauSac3);

            MauSac mauSac4 = new MauSac();
            mauSac4.setMa("MS005");
            mauSac4.setTen("Green");
            session.saveOrUpdate(mauSac4);

            Hang hang = new Hang();
            hang.setMa("H0001");
            hang.setTen("Nike");
            session.saveOrUpdate(hang);

            Hang hang1 = new Hang();
            hang1.setMa("H0002");
            hang1.setTen("Adidas");
            session.saveOrUpdate(hang1);
            
            Hang hang3 = new Hang();
            hang3.setMa("H0003");
            hang3.setTen("Vans");
            session.saveOrUpdate(hang3);

            Hang hang2 = new Hang();
            hang2.setMa("H0003");
            hang2.setTen("Puma");
            session.saveOrUpdate(hang2);

            KichThuoc kichThuoc = new KichThuoc();
            kichThuoc.setMa("KT001");
            kichThuoc.setTen("39");
            session.saveOrUpdate(kichThuoc);

            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setMa("CL001");
            chatLieu.setTen("Da PU");
            session.saveOrUpdate(chatLieu);
            
            ChatLieu chatLieu1 = new ChatLieu();
            chatLieu1.setMa("CL002");
            chatLieu1.setTen("Vải canvas");
            session.saveOrUpdate(chatLieu1);

            KichThuoc kichThuoc1 = new KichThuoc();
            kichThuoc1.setMa("KT002");
            kichThuoc1.setTen("40");
            session.saveOrUpdate(kichThuoc1);

            KichThuoc kichThuoc2 = new KichThuoc();
            kichThuoc2.setMa("KT003");
            kichThuoc2.setTen("38");
            session.saveOrUpdate(kichThuoc2);

            KichThuoc kichThuoc3 = new KichThuoc();
            kichThuoc3.setMa("KT004");
            kichThuoc3.setTen("37");
            session.saveOrUpdate(kichThuoc3);

            KichThuoc kichThuoc4 = new KichThuoc();
            kichThuoc4.setMa("KT005");
            kichThuoc4.setTen("36");
            session.saveOrUpdate(kichThuoc4);

            KichThuoc kichThuoc5 = new KichThuoc();
            kichThuoc5.setMa("KT006");
            kichThuoc5.setTen("41");
            session.saveOrUpdate(kichThuoc5);

            SanPham sanPham = new SanPham();
            sanPham.setMa("Sp001");
            sanPham.setTen("Air Force 1");
            session.saveOrUpdate(sanPham);

            SanPham sanPham1 = new SanPham();
            sanPham1.setMa("Sp002");
            sanPham1.setTen("Adidas F1");
            session.saveOrUpdate(sanPham1);

            SanPham sanPham2 = new SanPham();
            sanPham2.setMa("Sp003");
            sanPham2.setTen("Puma seciture");
            session.saveOrUpdate(sanPham2);

            SanPham sanPham3 = new SanPham();
            sanPham3.setMa("Sp004");
            sanPham3.setTen("Jordan one low");
            session.saveOrUpdate(sanPham3);
            
            SanPham sanPham4 = new SanPham();
            sanPham4.setMa("Sp005");
            sanPham4.setTen("Vans classic");
            session.saveOrUpdate(sanPham4);

            NhanVien nhanVien = new NhanVien();
            nhanVien.setMa("NV001");
            nhanVien.setTen("Nguyễn Công Thắng");
            nhanVien.setVaiTro(0);
            nhanVien.setDiaChi("Thái Bình");
            nhanVien.setEmail("congthang25092003@gmail.com");
            nhanVien.setSdt("0971089763");
            nhanVien.setMatKhau("E10ADC3949BA59ABBE56E057F20F883E");
            try {
                nhanVien.setNgaySinh(new SimpleDateFormat("dd-MM-yyyy").parse("25-09-2003"));
            } catch (Exception e) {
            }
            nhanVien.setGioiTinh(0);
            session.saveOrUpdate(nhanVien);
            
            NhanVien nhanVien1 = new NhanVien();
            nhanVien1.setMa("NV002");
            nhanVien1.setTen("Nguyễn Văn An");
            nhanVien1.setVaiTro(1);
            nhanVien1.setDiaChi("Thái Bình");
            nhanVien1.setEmail("annv@gmail.com");
            nhanVien1.setSdt("0234354566");
            nhanVien1.setMatKhau("E10ADC3949BA59ABBE56E057F20F883E");
            try {
                nhanVien1.setNgaySinh(new SimpleDateFormat("dd-MM-yyyy").parse("25-09-2000"));
            } catch (Exception e) {
            }
            nhanVien1.setGioiTinh(0);
            session.saveOrUpdate(nhanVien1);
            
            KhachHang khachHangBanLe = new KhachHang();
            khachHangBanLe.setMa("KH000");
            khachHangBanLe.setHoTen("Khách bán lẻ");
            khachHangBanLe.setEmail("khachbanle");
            khachHangBanLe.setGioiTinh(0);
            try {
                khachHangBanLe.setNgaySinh(new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2000"));
            } catch (Exception e) {
            }
            khachHangBanLe.setSdt("0123456789");
            khachHangBanLe.setDiaChi("Hà Nội");
            session.saveOrUpdate(khachHangBanLe);

            KhachHang khachHang = new KhachHang();
            khachHang.setMa("KH001");
            khachHang.setHoTen("Nguyễn Văn Quân");
            khachHang.setEmail("quannq@gmail.com");
            khachHang.setGioiTinh(0);
            try {
                khachHang.setNgaySinh(new SimpleDateFormat("dd-MM-yyyy").parse("25-12-2001"));
            } catch (Exception e) {
            }
            khachHang.setSdt("0835434546");
            khachHang.setDiaChi("Hà Nội");
            session.saveOrUpdate(khachHang);

            KhachHang khachHang1 = new KhachHang();
            khachHang1.setMa("KH002");
            khachHang1.setHoTen("Nguyễn Hoàng Anh");
            khachHang1.setEmail("anhasd@gmail.com");
            khachHang1.setGioiTinh(0);
            try {
                khachHang1.setNgaySinh(new SimpleDateFormat("dd-MM-yyyy").parse("25-8-2002"));
            } catch (Exception e) {
            }
            khachHang1.setSdt("0835434546");
            khachHang1.setDiaChi("Hà Nội");
            session.saveOrUpdate(khachHang1);
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setMa("KM001");
            khuyenMai.setLoaiKhuyenMai(0);
            khuyenMai.setTen("Big sale 22-2");
            khuyenMai.setNgayBatDau(new Date());
            khuyenMai.setNgayKetThuc(new Date());
            khuyenMai.setGiaTri(Double.parseDouble("20"));
            session.saveOrUpdate(khuyenMai);
            KhuyenMai khuyenMai1 = new KhuyenMai();
            khuyenMai1.setMa("KM002");
            khuyenMai1.setLoaiKhuyenMai(1);
            khuyenMai1.setTen("Big sale 8/3");
            khuyenMai1.setNgayBatDau(new Date());
            khuyenMai1.setNgayKetThuc(new Date());
            khuyenMai1.setGiaTri(Double.parseDouble("50000"));
            session.saveOrUpdate(khuyenMai1);

            ChiTietSP chiTietSP = new ChiTietSP();
            chiTietSP.setMaChiTietSP("CTSP01");
            chiTietSP.setHang(hang);
            chiTietSP.setGiaBan(new BigDecimal("250000"));
            chiTietSP.setKichThuoc(kichThuoc);
            chiTietSP.setMauSac(mauSac);
            chiTietSP.setSoLuongTon(1000);
            chiTietSP.setSanPham(sanPham);
            chiTietSP.setMaVach("123");
            chiTietSP.setChatLieu(chatLieu);
            session.saveOrUpdate(chiTietSP);

//            ChiTietSP chiTietSP1 = new ChiTietSP();
//            chiTietSP1.setMaChiTietSP("CTSP02");
//            chiTietSP1.setHang(hang);
//            chiTietSP1.setGiaBan(new BigDecimal("250000"));
//            chiTietSP1.setKichThuoc(kichThuoc1);
//            chiTietSP1.setMauSac(mauSac);
//            chiTietSP1.setSoLuongTon(1000);
//            chiTietSP1.setSanPham(sanPham);
//            chiTietSP1.setChatLieu(chatLieu);
//            chiTietSP1.setMaVach("02354356546");
//            session.saveOrUpdate(chiTietSP1);
//
//            ChiTietSP chiTietSP2 = new ChiTietSP();
//            chiTietSP2.setMaChiTietSP("CTSP03");
//            chiTietSP2.setHang(hang);
//            chiTietSP2.setGiaBan(new BigDecimal("250000"));
//            chiTietSP2.setKichThuoc(kichThuoc2);
//            chiTietSP2.setMauSac(mauSac2);
//            chiTietSP2.setSoLuongTon(1000);
//            chiTietSP2.setSanPham(sanPham);
//            chiTietSP2.setChatLieu(chatLieu);
//            chiTietSP2.setMaVach("123456");
//            session.saveOrUpdate(chiTietSP2);
//
//            ChiTietSP chiTietSP3 = new ChiTietSP();
//            chiTietSP3.setMaChiTietSP("CTSP04");
//            chiTietSP3.setHang(hang);
//            chiTietSP3.setGiaBan(new BigDecimal("250000"));
//            chiTietSP3.setKichThuoc(kichThuoc3);
//            chiTietSP3.setMauSac(mauSac);
//            chiTietSP3.setSoLuongTon(1000);
//            chiTietSP3.setSanPham(sanPham);
//            chiTietSP3.setMaVach("0123456789");
//            chiTietSP3.setChatLieu(chatLieu);
//            session.saveOrUpdate(chiTietSP3);
//
//            ChiTietSP chiTietSP4 = new ChiTietSP();
//            chiTietSP4.setMaChiTietSP("CTSP05");
//            chiTietSP4.setHang(hang);
//            chiTietSP4.setGiaBan(new BigDecimal("250000"));
//            chiTietSP4.setKichThuoc(kichThuoc4);
//            chiTietSP4.setMauSac(mauSac1);
//            chiTietSP4.setSoLuongTon(1000);
//            chiTietSP4.setSanPham(sanPham);
//            chiTietSP4.setMaVach("123456789");
//            chiTietSP4.setChatLieu(chatLieu);
//            session.saveOrUpdate(chiTietSP4);
//
//            ChiTietSP chiTietSP5 = new ChiTietSP();
//            chiTietSP5.setMaChiTietSP("CTSP06");
//            chiTietSP5.setHang(hang1);
//            chiTietSP5.setGiaBan(new BigDecimal("200000"));
//            chiTietSP5.setKichThuoc(kichThuoc4);
//            chiTietSP5.setMauSac(mauSac2);
//            chiTietSP5.setSoLuongTon(1000);
//            chiTietSP5.setSanPham(sanPham2);
//            chiTietSP5.setMaVach("234");
//            chiTietSP5.setChatLieu(chatLieu);
//            session.saveOrUpdate(chiTietSP5);
//
//            ChiTietSP chiTietSP6 = new ChiTietSP();
//            chiTietSP6.setMaChiTietSP("CTSP07");
//            chiTietSP6.setHang(hang2);
//            chiTietSP6.setGiaBan(new BigDecimal("230000"));
//            chiTietSP6.setKichThuoc(kichThuoc2);
//            chiTietSP6.setMauSac(mauSac4);
//            chiTietSP6.setSoLuongTon(1000);
//            chiTietSP6.setSanPham(sanPham2);
//            chiTietSP6.setMaVach("567");
//            chiTietSP6.setChatLieu(chatLieu);
//            session.saveOrUpdate(chiTietSP6);
//
//            ChiTietSP chiTietSP7 = new ChiTietSP();
//            chiTietSP7.setMaChiTietSP("CTSP08");
//            chiTietSP7.setHang(hang1);
//            chiTietSP7.setGiaBan(new BigDecimal("300000"));
//            chiTietSP7.setKichThuoc(kichThuoc3);
//            chiTietSP7.setMauSac(mauSac2);
//            chiTietSP7.setSoLuongTon(1000);
//            chiTietSP7.setSanPham(sanPham1);
//            chiTietSP7.setMaVach("678");
//            chiTietSP7.setChatLieu(chatLieu);
//            session.saveOrUpdate(chiTietSP7);

            ChiTietSPKhuyenMai chiTietSPKhuyenMai = new ChiTietSPKhuyenMai();
            chiTietSPKhuyenMai.setChiTietSPId(chiTietSP);
            chiTietSPKhuyenMai.setKhuyenMaiId(khuyenMai);
            chiTietSPKhuyenMai.setDonGia(chiTietSP.getGiaBan());
            chiTietSPKhuyenMai.setTrangThai(0);
            chiTietSPKhuyenMai.setDonGiaConLai(chiTietSP.getGiaBan().subtract(chiTietSP.getGiaBan().multiply(new BigDecimal(khuyenMai.getGiaTri()).divide(new BigDecimal(100)))));
            session.saveOrUpdate(chiTietSPKhuyenMai);

//            ChiTietSPKhuyenMai chiTietSPKhuyenMai2 = new ChiTietSPKhuyenMai();
//            chiTietSPKhuyenMai2.setChiTietSPId(chiTietSP1);
//            chiTietSPKhuyenMai2.setKhuyenMaiId(khuyenMai);
//            chiTietSPKhuyenMai2.setDonGia(chiTietSP1.getGiaBan());
//            chiTietSPKhuyenMai2.setTrangThai(1);
//            chiTietSPKhuyenMai2.setDonGiaConLai(chiTietSP1.getGiaBan().subtract(chiTietSP1.getGiaBan().multiply(new BigDecimal(khuyenMai.getGiaTri()).divide(new BigDecimal(100)))));
//            session.saveOrUpdate(chiTietSPKhuyenMai2);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
