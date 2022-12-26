
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
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
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
public class ExportPhieuTraHang {

    public static final String pathUnicode = "font\\unicode.ttf";

    public void exportBill(HoaDon hoaDon, HoaDonTraHang hoaDonTraHang, List<HoaDonTraHangChiTiet> listHoaDonTraHangChiTiet, String pathFile) {
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

            table.addCell(new Cell().add("Hóa đơn trả hàng").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("Sneaker Store").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 250, 200, 200};
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getKhachHang().getDiaChi()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Ngày trả hàng:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
            String date = sdf.format(hoaDonTraHang.getNgayDoiTra());
            customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            Table customerInforTable1 = new Table(colWidth);
            customerInforTable1.setFont(font);
            customerInforTable1.addCell(new Cell(0, 4)
                    .add("Thông tin nhân viên trả hàng").setBold().setBorder(Border.NO_BORDER));

            customerInforTable1.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            customerInforTable1.addCell(new Cell().add(hoaDonTraHang.getNhanVien().getTen()).setBorder(Border.NO_BORDER));
            customerInforTable1.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable1.addCell(new Cell().add(hoaDonTraHang.getNhanVien().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            float itemColWidth[] = {15, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL trả").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Đơn giá").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");
            for (HoaDonTraHangChiTiet xx : listHoaDonTraHangChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSP()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenHang() + " " + xx.getMauSac() + " " + "Size: " + xx.getKichThuoc()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuongTra() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(xx.getGiaBan())).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(new BigDecimal(xx.getSoLuongTra()).multiply(xx.getGiaBan())) + " Vnđ").setBorder(Border.NO_BORDER));
            }
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền hoàn").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDonTraHang.getTienHoanTraKhach()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("Lưu ý: Đây là hóa đơn trả hàng, nếu khách hàng có thắc mắc,\nvui lòng liên hệ cho cửa hàng theo hotline ở dưới").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("Trường cao đẳng FPT Polytechnich, P.Trịnh Văn Bô,\nP.Phương Canh, Q.Nam Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0686868686").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(customerInforTable1);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            document.add(customerLuuY);
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            document.close();

            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new ExportFilePdfByITextTaiQuay().exportBill();
//    }
}
