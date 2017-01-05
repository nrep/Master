package Database;

/**
 * Created by YocyTang on 2017/1/4.
 */
public class People {
    private String username;
    private String address;
    private String tel;
    private String sex;
    private int age;
    private String descript;
    private String email;
    public  People(String username, String sex, String tel,String email, int age, String address,  String descript){
        this.username = username;
        this.sex = sex;
        this.tel = tel;
        this.age = age;
        this.address = address;
        this.descript = descript;
        this.email = email;
    }
    public String getUsername(){
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDescript() {
        return descript;
    }

    public String getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "username: "+username+" ||"+" sex: "+sex+" ||"+" address: "+address+" || "+" age: "+age+" || "+"tel: " +
                tel+" descript "+descript;
    }
}
