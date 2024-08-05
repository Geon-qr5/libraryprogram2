package old02;

public class Pub {

    private String pub_no;
    private String pubName;
    private String phone;

    public Pub(String pub_no, String pubName, String phone) {
        this.pub_no = pub_no;
        this.pubName = pubName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        String str1 = "출판사 번호 : " + pub_no;
        String str2 = "출판사명 : " + pubName;
        String str3 = "전화번호 : " + phone;

        return str1 + " /" + str2+ " /" + str3;
    }

    public String getPub_no() {
        return pub_no;
    }

    public String getPubName() {
        return pubName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPub_no(String pub_no) {
        this.pub_no = pub_no;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
