package Entity;

public class Profile {
    private String name;
    private String phone;
    private String gender;
    private int age;
    private String profession;
    private String city;

    public Profile() {
        System.out.println("Empty Profile Created.");
    }

    public Profile(String name, String phone, String gender, int age, String profession, String city) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.profession = profession;
        this.city = city;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPhone(String phone) { this.phone = phone; }
    public String getPhone() { return phone; }

    public void setGender(String gender) { this.gender = gender; }
    public String getGender() { return gender; }

    public void setAge(int age) { this.age = age; }
    public int getAge() { return age; }

    public void setProfession(String profession) { this.profession = profession; }
    public String getProfession() { return profession; }

    public void setCity(String city) { this.city = city; }
    public String getCity() { return city; }

    public void showProfile() {
        System.out.println("Name      : " + name);
        System.out.println("Phone No. : " + phone);
        System.out.println("Gender    : " + gender);
        System.out.println("Age       : " + age);
        System.out.println("Profession: " + profession);
        System.out.println("City      : " + city);
    }

    public String getProfile() {
        return  "Name       : " + name       + "\n" +
                "Phone No.  : " + phone      + "\n" +
                "Gender     : " + gender     + "\n" +
                "Age        : " + age + " years\n" +
                "Profession : " + profession + "\n" +
                "City       : " + city       + "\n";
    }
}
