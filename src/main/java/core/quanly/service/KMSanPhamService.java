/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.KMSanphamReponse;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface KMSanPhamService {
    List<KMSanphamReponse> GetAllResponse();
    
    List<KMSanphamReponse> GetAllNoKhuyenMai();
}