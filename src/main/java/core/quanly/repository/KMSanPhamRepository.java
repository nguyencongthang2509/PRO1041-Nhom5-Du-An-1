/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KMSanphamReponse;
import domainmodels.ChiTietSP;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;


/**
 *
 * @author thiennvtph26140
 */
public class KMSanPhamRepository extends repository.CrudRepository<String, ChiTietSP, KMSanphamReponse >{
    public KMSanPhamRepository(){
    className = ChiTietSP.class.getName();
    res = "new core.quanly.viewmodel.KMSanphamReponse(a.id, a.sanPham.ma, a.sanPham.ten, a.hang.ten, a.mauSac.ten, a.kichThuoc.ten)";
    }
    
        public List<KMSanphamReponse> getAllResponseNoKhuyenMai() {
        List<KMSanphamReponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KMSanphamReponse(a.id, a.sanPham.ma, a.sanPham.ten, a.hang.ten, a.mauSac.ten, a.kichThuoc.ten) FROM " + className + " a LEFT JOIN a.khuyenMai b where b.id is null";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
}
