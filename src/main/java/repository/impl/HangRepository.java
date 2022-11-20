/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import core.quanly.viewmodel.HangViewModel;
import domainmodels.Hang;
import infrastructure.listener.CreatePrimaryEntityListener;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class HangRepository extends CrudRepository<UUID, Hang, HangViewModel> {

    public HangRepository() {
        className = Hang.class.getName();
        res = "new viewmodel.HangViewModel(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<Hang> list = new HangRepository().getAll();
        for (Hang c : list) {
            System.out.println(c.toString());
        }
    }
}
