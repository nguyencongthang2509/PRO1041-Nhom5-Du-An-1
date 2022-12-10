//package util;
//
//import core.quanly.viewmodel.BhHoaDonChiTietResponse;
//import domainmodels.HoaDon;
//import java.util.Calendar;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//public class ExportFilePdf {
//
//    PDDocument invc;
//    String InvoiceTitle = new String("Sneaker Store");
//    String SubTitle = new String("See you again");
//
//    public ExportFilePdf() {
//        invc = new PDDocument();
//        PDPage newpage = new PDPage();
//        invc.addPage(newpage);
//    }
//
//    public void WriteInvoice(HoaDon hoaDon, List<BhHoaDonChiTietResponse> listHoaDonChiTiet) {
//        System.out.println(hoaDon.getId());
//        PDPage mypage = invc.getPage(0);
//        try {
//            PDPageContentStream cs = new PDPageContentStream(invc, mypage);
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 20);
//            cs.setFont(PDType1Font.TIMES_ROMAN, 0);
//            cs.newLineAtOffset(140, 750);
//            cs.showText(InvoiceTitle);
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
//            cs.newLineAtOffset(270, 690);
//            cs.showText(SubTitle);
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(60, 610);
//            cs.showText("Customer Name: ");
//            cs.newLine();
//            cs.showText("Phone: ");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(170, 610);
//            cs.showText("Sneaker store");
//            cs.newLine();
//            cs.showText(hoaDon.getKhachHang().getSdt());
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(80, 540);
//            cs.showText("Product Name");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(200, 540);
//            cs.showText("Quantity");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(310, 540);
//            cs.showText("Color x Size");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(410, 540);
//            cs.showText("Price");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(80, 520);
//            for (int i = 0; i < listHoaDonChiTiet.size(); i++) {
//                cs.showText(listHoaDonChiTiet.get(i).getTenSP());
//                cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(200, 520);
//            for (int i = 0; i < listHoaDonChiTiet.size(); i++) {
//                cs.showText(listHoaDonChiTiet.get(i).getSoLuong() + "");
//                cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(310, 520);
//            for (int i = 0; i < listHoaDonChiTiet.size(); i++) {
//                cs.showText(listHoaDonChiTiet.get(i).getMauSac() + " x " + listHoaDonChiTiet.get(i).getSize());
//                cs.newLine();
//            }
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
//            cs.setLeading(20f);
//            cs.newLineAtOffset(410, 520);
//            for (int i = 0; i < listHoaDonChiTiet.size(); i++) {
//                cs.showText(listHoaDonChiTiet.get(i).getGiaBan() + " Vnd");
//                cs.newLine();
//            }
//            cs.endText();
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(310, (500 - (20 * listHoaDonChiTiet.size())));
//            cs.showText("Total: ");
//            cs.endText();
//
//            cs.beginText();
//            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
//            cs.newLineAtOffset(410, (500 - (20 * listHoaDonChiTiet.size())));
//            cs.showText(hoaDon.getThanhTien() + " Vnd");
//            cs.endText();
//
//            cs.close();
//            Long currentTime = Calendar.getInstance().getTimeInMillis();
//            invc.save("hoaDon" + currentTime + ".pdf");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
