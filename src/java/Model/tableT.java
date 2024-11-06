/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tran tung
 */
public class tableT {
      private int TableID;
    private String TableName;

    private String Status;

    public tableT() {

    }

    public tableT(int TableID, String TableName, String Status) {
        this.TableID = TableID;
        this.TableName = TableName;
        this.Status = Status;
    }

    public int getTableID() {
        return TableID;
    }

    public void setTableID(int TableID) {
        this.TableID = TableID;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "table{" + "TableID=" + TableID + ", TableName=" + TableName + ", Status=" + Status + '}';
    }
}
