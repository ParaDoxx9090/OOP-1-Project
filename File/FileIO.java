package File;
import Entity.*;
import java.io.*;
import java.util.*;

public class FileIO {
    public static void loadFromFile(Category[] categories) {
        try {
            Scanner sc = new Scanner(new File("./File/Categories.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                categories[Integer.parseInt(data[0])] = new Category(data[1]);
            }
            sc.close();

            sc = new Scanner(new File("./File/Profiles.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                int categoryNo = Integer.parseInt(data[0]);
                int position   = Integer.parseInt(data[1]);
                String name       = data[2];
                String phone      = data[3];
                String gender     = data[4];
                int    age        = Integer.parseInt(data[5]);
                String profession = data[6];
                String city       = data[7];

                categories[categoryNo].insertProfile(position,
                        new Profile(name, phone, gender, age, profession, city));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Files not found or empty. Starting fresh.");
        }
    }

    public static void saveChangesInFile(Category[] categories) {
        try {
            File dir = new File("./File");
            if (!dir.exists()) dir.mkdirs();

            FileWriter catWriter  = new FileWriter(new File("./File/Categories.txt"));
            FileWriter profWriter = new FileWriter(new File("./File/Profiles.txt"));

            for (int catNo = 0; catNo < categories.length; catNo++) {
                if (categories[catNo] != null) {
                    catWriter.write(catNo + ";" + categories[catNo].getCategoryName() + "\n");

                    Profile[] profiles = categories[catNo].getAllProfiles();
                    for (int pos = 0; pos < profiles.length; pos++) {
                        if (profiles[pos] != null) {
                            profWriter.write(
                                catNo                      + ";" +
                                pos                        + ";" +
                                profiles[pos].getName()      + ";" +
                                profiles[pos].getPhone()     + ";" +
                                profiles[pos].getGender()    + ";" +
                                profiles[pos].getAge()       + ";" +
                                profiles[pos].getProfession()+ ";" +
                                profiles[pos].getCity()      + "\n"
                            );
                        }
                    }
                }
            }
            catWriter.close();
            profWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
