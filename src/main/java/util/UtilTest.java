
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
import core.quanly.viewmodel.ThongKeTheoKhoangResponse;
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
import java.util.List;
import javax.swing.JOptionPane;

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
                "Bi???u ????? doanh thu c???a c???a h??ng t??? n??m " + (nam - 4) + "-HI???N T???I",
                "N??m", "Doanh s???",
                createDatasetTheoNam(), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoThang(int nam) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Bi???u ????? doanh thu c???a c???a h??ng n??m " + nam,
                "Th??ng", "Doanh s???",
                createDatasetTheoThangCuaNam(nam), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoThang(int thang, int nam) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Bi???u ????? doanh thu c???a c???a h??ng th??ng " + thang + "-" + nam,
                "Ng??y", "Doanh s???",
                createDatasetTheoThang(thang, nam), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public JFreeChart createChartTheoKhoangThoiGian(List<ThongKeTheoKhoangResponse> list) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart("Bi???u ????? doanh thu c???a c???a h??ng t??? " + list.get(0).getNgay() + "/" + list.get(0).getThang() + "/" + list.get(0).getNam() + " ?????n " + +list.get(list.size() - 1).getNgay() + "/" + list.get(list.size() - 1).getThang() + "/" + list.get(list.size() - 1).getNam(), "NG??Y", "Doanh s???", createDatasetTheoKhoangThoiGian(list), PlotOrientation.VERTICAL, true, true, true);
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
            dataset.addValue(new Double(Get().getDoanhThuNam2(i).doubleValue()), "Doanh s???", i + "");
        }
        System.out.println(getyear);
        return dataset;
    }

    public CategoryDataset createDatasetTheoThangCuaNam(int nam) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(new Double(Get().getDoanhThuThang(i, nam).doubleValue()), "Doanh s???", i + "");
        }
        return dataset;
    }

    public CategoryDataset createDatasetTheoThang(int thang, int nam) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
            for (int i = 1; i <= 30; i++) {
                dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh s???", i + "");
            }
        } else if (thang == 2) {
            if ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0) {
                for (int i = 1; i <= 29; i++) {
                    dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh s???", i + "");
                }
            } else {
                for (int i = 1; i <= 28; i++) {
                    dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh s???", i + "");
                }
            }
        } else {
            for (int i = 1; i <= 31; i++) {
                dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(i, thang, nam).doubleValue()), "Doanh s???", i + "");
            }
        }
        return dataset;
    }

    public CategoryDataset createDatasetTheoKhoangThoiGian(List<ThongKeTheoKhoangResponse> list) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < list.size(); i++) {
            dataset.addValue(new Double(Get().getDoanhThuNgayTrongThang(list.get(i).getNgay(), list.get(i).getThang(), list.get(i).getNam()).doubleValue()), "Doanh s???", list.get(i).getNgay() + "-" + list.get(i).getThang() + "");
        }
        return dataset;
    }

}
