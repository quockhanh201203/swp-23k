/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dao.food;
import model.table;

/**
 *
 * @author tran tung
 */
public class tableDAO extends DBContext{
        public List<table> getTableList() {
        List<table> tableList = new ArrayList<>();
        String sql = " select * from [table]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                table p = new table();

                tableList.add(new table(
                        rs.getInt(1),  rs.getString(2), rs.getString(3)                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return tableList;
    }
        public static void main(String[] args) {
        tableDAO dao = new tableDAO();
        List<table> pr = dao.getTableList(); 

        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }
}
