
package util;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import domainmodels.HoaDon;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class ExportFilePdfByITextDatHang {

    public static final String pathUnicode = "font\\unicode.ttf";

    public void exportBill(HoaDon hoaDon, List<BhHoaDonChiTietResponse> listHoaDonChiTiet, String pathFile) {
        try {
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Bill Sneaker Store").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("M?? h??a ????n: " + hoaDon.getMa() + "\n Sneaker Store").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 230, 200, 200};
            Table customerInforTableKH = new Table(colWidth);
            customerInforTableKH.setFont(font);
            customerInforTableKH.addCell(new Cell(0, 4)
                    .add("Th??ng tin kh??ch h??ng ?????t h??ng").setBold().setBorder(Border.NO_BORDER));

            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
                customerInforTableKH.addCell(new Cell().add("H??? t??n:").setBorder(Border.NO_BORDER));
                customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
                customerInforTableKH.addCell(new Cell().add("S??T:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTableKH.addCell(new Cell().add("Rank:").setBorder(Border.NO_BORDER));
                customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getCapBac() == 0 ? "?????ng" : (hoaDon.getKhachHang().getCapBac() == 1 ? "B???c" : (hoaDon.getKhachHang().getCapBac() == 2 ? "V??ng" : "Kim c????ng"))).setBorder(Border.NO_BORDER));
            }
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Th??ng tin ng?????i nh???n").setBold().setBorder(Border.NO_BORDER));

            customerInforTable.addCell(new Cell().add("H??? t??n:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getTenNguoiNhan()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("S??T:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add(hoaDon.getSdtNguoiNhan()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add("?????a ch???:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getDiaChi()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Ng??y thanh to??n:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
            String date = sdf.format(hoaDon.getNgayThanhToan());
            customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            Table personShipInforTable = new Table(colWidth);
            personShipInforTable.setFont(font);
            personShipInforTable.addCell(new Cell(0, 4)
                    .add("Th??ng tin ng?????i ship").setBold().setBorder(Border.NO_BORDER));

            personShipInforTable.addCell(new Cell().add("H??? t??n:").setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add(hoaDon.getTenNguoiShip()).setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add("Ng??y mong mu???n:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            String dateShip = sdf.format(hoaDon.getNgayMongMuon());
            personShipInforTable.addCell(new Cell().add(dateShip).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("S??T:").setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add(hoaDon.getSdtNguoiShip()).setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add("H??nh th???c:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("Giao h??ng").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("TT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
            personShipInforTable.addCell(new Cell().add("Thanh to??n tr?????c").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            float itemColWidth[] = {30, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("T??n s???n ph???m").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Th??ng tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Gi?? b??n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Th??nh ti???n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");
            for (BhHoaDonChiTietResponse xx : listHoaDonChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSP()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getHang() + " " + xx.getMauSac() + " " + "Size: " + xx.getSize()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(xx.getGiaBan()) + (xx.getGiamGia().compareTo(BigDecimal.ZERO) > 0 ? " (*)" : "") + " Vn??").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(new BigDecimal(xx.getSoLuong()).multiply(xx.getGiaBan())) + " Vn??").setBorder(Border.NO_BORDER));
            }

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ph?? ship").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienShip()) + " Vn??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("% gi???m gi??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(hoaDon.getKhachHang().getCapBac() == 0 ? "0 %" : (hoaDon.getKhachHang().getCapBac() == 1 ? "3 %" : (hoaDon.getKhachHang().getCapBac() == 2 ? "5 %" : "10 %"))).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            }
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("T???ng ti???n").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getThanhTien()) + " Vn??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ti???n kh??ch tr???").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            BigDecimal tienKhachTra = hoaDon.getTienKhachTra() == null ? new BigDecimal(0) : hoaDon.getTienKhachTra();
            itemTable.addCell(new Cell().add(df.format(tienKhachTra) + " Vn??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ng??n h??ng").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            BigDecimal tienKhachCK = hoaDon.getTienKhachChuyenKhoan() == null ? new BigDecimal(0) : hoaDon.getTienKhachChuyenKhoan();
            itemTable.addCell(new Cell().add(df.format(tienKhachCK) + " Vn??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ti???n th???a").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienThua()) + " Vn??").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("L??u ??: Qu?? kh??ch h??y gi??? l???i h??a ????n,\nN???u s???n ph???m g???p v???n ????? g?? c?? th??? tr??? h??ng trong v??ng 3 ng??y,\n ch??? th???c hi???n tr??? h??ng cho nh???ng s???n ph???m kh??ng ??p d???ng khuy???n m???i.\nNh???ng s???n ph???m ???????c ????nh d???u (*) ??? gi?? b??n l?? nh???ng s???n ph???m ???? c?? gi???m gi?? khuy???n m???i").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("Tr?????ng cao ?????ng FPT Polytechnich, P.Tr???nh V??n B??,\nP.Ph????ng Canh, Q.Nam T??? Li??m, TP.H?? N???i").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("C???m ??n qu?? kh??ch v?? h???n g???p l???i\nHotline: 0686868686").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTableKH);
            document.add(customerInforTable);
            document.add(personShipInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
                document.add(customerLuuY);
            }
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
