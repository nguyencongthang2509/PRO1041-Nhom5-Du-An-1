/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service;

import crud.viewmodel.ChucVuViewModel;
import domainmodels.ChucVu;
import java.util.List;

/**
 *
 * @author longnhph26222
 */
public interface ChucVuService {
    List<ChucVuViewModel> getList();
    String insert(ChucVu c);
    String update(ChucVu c);
    String delete(String ma);
}
