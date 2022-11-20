/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.util.List;
import java.util.UUID;
import repository.CrudRepository;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienRepository extends CrudRepository<String, NhanVien, NhanVienResponse>{
    public NhanVienRepository(){
    className=NhanVien.class.getName();
    res="new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
            + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro)";
    }
    public static void main(String[] args) {
        List<NhanVienResponse> lst = new NhanVienRepository().getAllResponse();
        System.out.println(lst);
    }
}
