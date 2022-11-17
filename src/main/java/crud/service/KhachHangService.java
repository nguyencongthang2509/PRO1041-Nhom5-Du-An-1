/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import domainmodels.KhachHang;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public interface KhachHangService {

    List<KhachHang> getAll();

    String insert(KhachHang khachhang);

    String update(KhachHang khachhang);

    String delete(String ma);

}
