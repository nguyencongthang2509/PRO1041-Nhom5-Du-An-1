/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import core.quanly.viewmodel.SPChatLieuResponse;
import domainmodels.ChatLieu;
import java.util.ArrayList;
import java.util.List;
import repository.CrudRepository;

/**
 *
 * @author HP
 */
public class SPChatLieuRepository extends CrudRepository<String, ChatLieu, SPChatLieuResponse>{
    
    public SPChatLieuRepository(){
        className = ChatLieu.class.getName();
        res = "new core.quanly.viewmodel.SPChatLieuResponse(a.id, a.ma, a.ten)";
    }
    
    public static void main(String[] args) {
        List<ChatLieu> list = new SPChatLieuRepository().getAll();
        System.out.println(list);
    }
}
