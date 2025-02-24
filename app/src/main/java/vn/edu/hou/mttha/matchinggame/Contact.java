package vn.edu.hou.mttha.matchinggame;

public class Contact {
    String ID;
    String hoTen;
    String diaChi;
    String email;
    String soDT;
    String ngayTao;
    public Contact() {
    }

    public Contact(String ID, String hoTen, String diaChi, String email, String soDT, String ngayTao) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
        this.soDT = soDT;
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ID='" + ID + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", email='" + email + '\'' +
                ", soDT='" + soDT + '\'' +
                ", ngayTao='" + ngayTao + '\'' +
                '}';
    }
}
