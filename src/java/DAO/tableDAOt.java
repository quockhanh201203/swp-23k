/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.tableT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tran tung
 */
public class tableDAOt extends DBContext{
     public List<tableT> getTableList() {
        List<tableT> tableList = new ArrayList<>();
        String sql = " select * from [table]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tableT p = new tableT();

                tableList.add(new tableT(
                        rs.getInt(1),  rs.getString(2), rs.getString(3)                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return tableList;
}
}