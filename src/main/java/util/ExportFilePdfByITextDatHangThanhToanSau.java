
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
public class ExportFilePdfByITextDatHangThanhToanSau {

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
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMa() + "\n Sneaker Store").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 230, 200, 200};
            Table customerInforTableKH = new Table(colWidth);
            customerInforTableKH.setFont(font);
            customerInforTableKH.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng đặt hàng").setBold().setBorder(Border.NO_BORDER));

            customerInforTableKH.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getHoTen()).setBorder(Border.NO_BORDER));
            customerInforTableKH.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
                customerInforTableKH.addCell(new Cell().add("Rank:").setBorder(Border.NO_BORDER));
                customerInforTableKH.addCell(new Cell().add(hoaDon.getKhachHang().getCapBac() == 0 ? "Đồng" : (hoaDon.getKhachHang().getCapBac() == 1 ? "Bạc" : (hoaDon.getKhachHang().getCapBac() == 2 ? "Vàng" : "Kim cương"))).setBorder(Border.NO_BORDER));
            }
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin người nhận").setBold().setBorder(Border.NO_BORDER));

            customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getTenNguoiNhan()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add(hoaDon.getSdtNguoiNhan()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDon.getDiaChi()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Ngày đặt hàng:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
            String date = sdf.format(hoaDon.getNgayTao());
            customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            Table personShipInforTable = new Table(colWidth);
            personShipInforTable.setFont(font);
            personShipInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin người ship").setBold().setBorder(Border.NO_BORDER));

            personShipInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add(hoaDon.getTenNguoiShip()).setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add("Ngày mong muốn:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            String dateShip = sdf.format(hoaDon.getNgayMongMuon());
            personShipInforTable.addCell(new Cell().add(dateShip).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add(hoaDon.getSdtNguoiShip()).setBorder(Border.NO_BORDER));
            personShipInforTable.addCell(new Cell().add("Hình thức:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("Giao hàng").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            personShipInforTable.addCell(new Cell().add("TT:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
            personShipInforTable.addCell(new Cell().add("Thanh toán khi nhận").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));

            float itemColWidth[] = {30, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");
            for (BhHoaDonChiTietResponse xx : listHoaDonChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSP()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getHang() + " " + xx.getMauSac() + " " + "Size: " + xx.getSize()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(xx.getGiaBan()) + (xx.getGiamGia().compareTo(BigDecimal.ZERO) > 0 ? " (*)" : "") + " Vnđ").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(new BigDecimal(xx.getSoLuong()).multiply(xx.getGiaBan())) + " Vnđ").setBorder(Border.NO_BORDER));
            }

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Phí ship").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienShip()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add("% giảm giá").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
                itemTable.addCell(new Cell().add(hoaDon.getKhachHang().getCapBac() == 0 ? "0 %" : (hoaDon.getKhachHang().getCapBac() == 1 ? "3 %" : (hoaDon.getKhachHang().getCapBac() == 2 ? "5 %" : "10 %"))).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            }
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getThanhTien()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền cần thu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            BigDecimal tienKhachTra = hoaDon.getTienKhachTra() == null ? new BigDecimal(0) : hoaDon.getTienKhachTra();
            itemTable.addCell(new Cell().add(df.format(hoaDon.getThanhTien()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

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
