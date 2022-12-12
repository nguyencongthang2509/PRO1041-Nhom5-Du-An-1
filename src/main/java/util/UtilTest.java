/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author thangncph26123
 */
//import javax.swing.JFrame;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
import core.quanly.repository.ThongKeHangHoaRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class UtilTest {

    public UtilTest() {

    }

    private static ThongKeHangHoaRepository Get() {
        ThongKeHangHoaRepository cc = new ThongKeHangHoaRepository();
        return cc;

    }

    public JFreeChart createChartTheoNam() throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        int nam = Integer.parseInt(format);
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỦA CỬA HÀNG TỪ NĂM " + (nam - 4) + "-HIỆN TẠI",
                "Năm", "Doanh số",
                createDatasetTheoNam(), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoThang(int nam) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỦA CỬA HÀNG NĂM " + nam,
                "Tháng", "Doanh số",
                createDatasetTheoThangCuaNam(nam), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoThang(int thang, int nam) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỦA CỬA HÀNG NĂM " + thang,
                "Ngày", "Doanh số",
                createDatasetTheoThang(thang, nam), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoKhoangThoiGian(int ngay, int thang, int nam) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỦA CỬA HÀNG NĂM " + thang,
                "Ngày", "Doanh số",
                createDatasetTheoKhoangThoiGian(ngay, thang, nam), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    private CategoryDataset createDatasetTheoNam() throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);
        int getyear = Integer.parseInt(format);
        for (int i = getyear; i > getyear - 5; i--) {
            dataset.addValue(new Double(Get().getDoanhThuNam2(i).doubleValue()), "Doanh số", i + "");
        }
        System.out.println(getyear);
        return dataset;
    }

    public CategoryDataset createDatasetTheoThangCuaNam(int nam) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(new Double(Get().getDoanhThuThang(i, nam).doubleValue()), "Doanh số", i + "");
        }
        return dataset;
    }

    public CategoryDataset createDatasetTheoThang(int thang, int nam) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            for (int i = 1; i <= 30; i++) {
                dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh số", i + "");
            }
        } else if (thang == 2) {
            for (int i = 1; i <= 28; i++) {
                dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh số", i + "");
            }
        } else {
            for (int i = 1; i <= 31; i++) {
                dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh số", i + "");
            }
        }
        return dataset;
    }

    public CategoryDataset createDatasetTheoKhoangThoiGian(int ngay, int thang, int nam) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(ngay, thang, nam).doubleValue()), "Doanh số", ngay + "");
        return dataset;
    }

}
