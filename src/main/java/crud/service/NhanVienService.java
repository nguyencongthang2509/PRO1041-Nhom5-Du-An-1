/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import domainmodels.NhanVien;
import java.util.List;

/**
 *
 * @author longnhph26222
 */
public interface NhanVienService {

    List<NhanVien> getList();

    String insert(NhanVien nv);

    String update(NhanVien nv);

    String delete(String id);
}
