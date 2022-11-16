/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public interface KichThuocService {

    List<KichThuoc> getAll();

    String insert(KichThuoc kichthuoc);

    String update(KichThuoc kichthuoc);

    String delete(KichThuoc kichthuoc);

}
