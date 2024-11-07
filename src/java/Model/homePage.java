/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tran tung
 */
public class homePage {
    private int ID;
    private String banner;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String script1;
    private String script2;

    public homePage() {
    }

    public homePage(int ID, String banner, String img1, String img2, String img3, String img4, String script1, String script2) {
        this.ID = ID;
        this.banner = banner;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.script1 = script1;
        this.script2 = script2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getScript1() {
        return script1;
    }

    public void setScript1(String script1) {
        this.script1 = script1;
    }

    public String getScript2() {
        return script2;
    }

    public void setScript2(String script2) {
        this.script2 = script2;
    }

    @Override
    public String toString() {
        return "homePage{" + "ID=" + ID + ", banner=" + banner + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + ", img4=" + img4 + ", script1=" + script1 + ", script2=" + script2 + '}';
    }
    
}
