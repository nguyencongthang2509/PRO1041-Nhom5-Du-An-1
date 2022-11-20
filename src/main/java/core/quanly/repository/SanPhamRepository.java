/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import domainmodels.SanPham;
import java.util.List;
import javax.transaction.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author HP
 */
public class SanPhamRepository {

    private String fromTable = "FROM san_pham";
    private Session session = HibernateUtil.getSession();

    public List<SanPham> getAll() {
        Query query = session.createQuery(fromTable, SanPham.class);
        List<SanPham> lstSp = query.getResultList();
        return lstSp;
    }

    public SanPham getOne(String ma) {
        String sql = fromTable + "WHERE ma=: ma";
        Query query = session.createQuery(sql);
        query.setParameter("ma", ma);
        SanPham sanPham = (SanPham) query.getSingleResult();
        return sanPham;
    }

    public Boolean add(SanPham sanPham) {
        Transaction trans = null;
        try ( Session session = HibernateUtil.getSession()) {
            trans = (Transaction) session.beginTransaction();
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
            trans = (Transaction) session.beginTransaction();
            session.update(sanPham);
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
            trans = (Transaction) session.beginTransaction();
            session.delete(sanPham);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
