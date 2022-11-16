/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.RepositoryTrangThaiHoaDon;
import crud.service.TrangThaiHoaDonService;
import domainmodels.TrangThaiHoaDon;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public class TrangThaiHoaDonImpl implements TrangThaiHoaDonService {

    RepositoryTrangThaiHoaDon reposTrangThaiHoaDon = new RepositoryTrangThaiHoaDon();

    @Override
    public List<TrangThaiHoaDon> getAll() {
        return reposTrangThaiHoaDon.getAll();
    }

    @Override
    public String insert(TrangThaiHoaDon TrangThaiHoaDon) {
        boolean check = reposTrangThaiHoaDon.updateOrinsert(TrangThaiHoaDon);
        if (check) {
            System.out.println("Insert Successfully");
        }
        return "Insert Failded";
    }

    @Override
    public String update(TrangThaiHoaDon TrangThaiHoaDon) {
        boolean check = reposTrangThaiHoaDon.updateOrinsert(TrangThaiHoaDon);
        if (check) {
            System.out.println("Update Successfully");
        }
        return "Update Failded";
    }

    @Override
    public String delete(String ma) {
        TrangThaiHoaDon kh = reposTrangThaiHoaDon.getbyID(ma);
        boolean check = reposTrangThaiHoaDon.delete(kh);
        if (check) {
            System.out.println("Delete Successfully");
        }
        return "Delete Failded";
    }

}
