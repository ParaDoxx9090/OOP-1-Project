package Entity;

public class Category {
    private String categoryName;
    private Profile[] profiles = new Profile[50]; 
    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void insertProfile(int index, Profile p) {
        profiles[index] = p;
    }

    public Profile getProfile(int index) {
        return profiles[index];
    }

    public void removeProfile(int index) {
        profiles[index] = null;
    }

    public String getCategoryData() {
        String data = "";
        data += "******** " + categoryName + " **********\n";
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i] != null) {
                data += "~~~~~~~~ Index: " + i + " ~~~~~~~~\n";
                data += profiles[i].getProfile();
            }
        }
        data += "*******************************\n";
        return data;
    }

    public Profile[] getAllProfiles() {
        return profiles;
    }
}