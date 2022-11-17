/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package crud.service.impl;

import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface KhuyenMaiServiceImpl {
    List<KhuyenMai> getList ();
    
    String update(KhuyenMai khuyenMai);
    
    String insert(KhuyenMai khuyenMai);
    
    String delete(String ma);
    
    
}
