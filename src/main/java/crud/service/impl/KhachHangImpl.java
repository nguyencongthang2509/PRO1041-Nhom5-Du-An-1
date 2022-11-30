/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.RepositoryKhachHang;
import crud.service.KhachHangService;
import domainmodels.KhachHang;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public class KhachHangImpl implements KhachHangService {

    RepositoryKhachHang reposKhachHang = new RepositoryKhachHang();

    @Override
    public List<KhachHang> getAll() {
        return reposKhachHang.getAll();
    }

    @Override
    public String insert(KhachHang khachhang) {
        boolean check = reposKhachHang.updateOrinsert(khachhang);
        if (check) {
            System.out.println("Insert Successfully");
        }
        return "Insert Failded";
    }

    @Override
    public String update(KhachHang khachhang) {
        boolean check = reposKhachHang.updateOrinsert(khachhang);
        if (check) {
            System.out.println("Update Successfully");
        }
        return "Update Failded";
    }

    @Override
    public String delete(String ma) {
        KhachHang kh = reposKhachHang.getbyID(ma);
        boolean check = reposKhachHang.delete(kh);
        if (check) {
            System.out.println("Delete Successfully");
        }
        return "Delete Failded";
    }

    public static void main(String[] args) {
        KhachHangService kh = new KhachHangImpl();
        List<KhachHang> list = kh.getAll();
        for (KhachHang x : list) {
            System.out.println(x.toString());
        }

    }

}
