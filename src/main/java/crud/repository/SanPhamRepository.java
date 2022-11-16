/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.repository;

import config.HibernateUtil;
import domainmodels.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class SanPhamRepository {

    private String fromTable = "From SanPham";
    private Session session = HibernateUtil.getSession();

    public List<SanPham> getAll() {
        Query query = session.createQuery(fromTable, SanPham.class);
        List<SanPham> listSP = query.getResultList();
        return listSP;
    }

    public SanPham getOne(String ma) {
        String hql = fromTable + "WHERE ma =: ma";
        Query query = session.createQuery(hql);
        query.setParameter("ma", ma);
        SanPham sanpham = (SanPham) query.getSingleResult();
        return sanpham;
    }

    public Boolean add(SanPham sanPham) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = session.beginTransaction();
            session.save(sanPham);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean update(SanPham sanPham) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = session.beginTransaction();
            session.saveOrUpdate(sanPham);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean delete(SanPham sanPham) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = session.beginTransaction();
            session.delete(sanPham);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
