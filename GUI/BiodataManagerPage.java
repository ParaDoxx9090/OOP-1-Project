package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class BiodataManagerPage extends JFrame implements ActionListener {
    Font font15 = new Font("Consolas", Font.BOLD, 15);

    // ── Category section labels & fields 
    JLabel catLabel, catNoLabel, catNameLabel;
    JTextField catNoTextField, catNameTextField;
    JButton addCatButton, removeCatButton, clearCatButton;

    // ── Profile section labels & fields 
    JLabel profileLabel;
    JLabel nameLabel, phoneLabel, genderLabel, ageLabel, professionLabel, cityLabel;
    JTextField nameTextField, phoneTextField, genderTextField, ageTextField, professionTextField, cityTextField;
    JButton createProfileButton, updateProfileButton, removeProfileButton, clearProfileButton;

    // ── Save
    JTextArea screen;
    Category[] categories = new Category[100];

  
    public BiodataManagerPage() {
        super("Marriage Biodata Manager");
        this.setSize(850, 720);
        this.setLocation(200, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./images/marriage.jpg").getImage());
        this.setLayout(null);

        FileIO.loadFromFile(categories);

        // ── Title
        createLabel(200, 0, 450, 40, "       Marriage BioData Registry");

        // ── Left display
        screen = new JTextArea();
        screen.setFont(font15);
        screen.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(10, 40, 490, 620);
        this.add(scrollPane);
        
        // CATEGORY SECTION
        catLabel = createLabel(550, 40, 220, 28, "── General Information ──");

        catNoLabel   = createLabel(520,  75, 145, 28, "Serial No.");
        catNoTextField = createTextField(670, 75, 150, 28, "");

        catNameLabel  = createLabel(520, 108, 145, 28, "Category Name");
        catNameTextField = createTextField(670, 108, 150, 28, "");

        addCatButton    = createButton(520, 143, 145, 30, "Add");
        addCatButton.setBackground(new Color(34, 139, 34));
        addCatButton.setForeground(Color.WHITE);

        removeCatButton = createButton(670, 143, 150, 30, "Remove");
        removeCatButton.setBackground(Color.RED);
        removeCatButton.setForeground(Color.WHITE);

        clearCatButton  = createButton(520, 178, 300, 28, "Clear Fields");
        clearCatButton.setBackground(Color.DARK_GRAY);
        clearCatButton.setForeground(Color.WHITE);

        // PROFILE SECTION
       
        profileLabel = createLabel(550, 218, 300, 28, "──── Manage Profile ────");

        // Profile fields 
        nameLabel       = createLabel(520, 252, 145, 28, "Full Name");
        nameTextField     = createTextField(670, 252, 150, 28, "");

        phoneLabel      = createLabel(520, 285, 145, 28, "Phone No.");
        phoneTextField    = createTextField(670, 285, 150, 28, "");

        genderLabel     = createLabel(520, 318, 145, 28, "Gender");
        genderTextField   = createTextField(670, 318, 150, 28, "");

        ageLabel        = createLabel(520, 351, 145, 28, "Age");
        ageTextField      = createTextField(670, 351, 150, 28, "");

        professionLabel = createLabel(520, 384, 145, 28, "Profession");
        professionTextField = createTextField(670, 384, 150, 28, "");

        cityLabel       = createLabel(520, 417, 145, 28, "City");
        cityTextField     = createTextField(670, 417, 150, 28, "");

        // Profile buttons
        createProfileButton = createButton(520, 452, 300, 30, "Create Profile");
        createProfileButton.setBackground(new Color(34, 139, 34));
        createProfileButton.setForeground(Color.WHITE);

        updateProfileButton = createButton(520, 487, 145, 30, "Update");
        updateProfileButton.setBackground(new Color(66, 179, 255));
        updateProfileButton.setForeground(Color.WHITE);

        removeProfileButton = createButton(670, 487, 150, 30, "Remove");
        removeProfileButton.setBackground(Color.RED);
        removeProfileButton.setForeground(Color.WHITE);

        clearProfileButton  = createButton(520, 522, 300, 28, "Clear Fields");
        clearProfileButton.setBackground(Color.DARK_GRAY);
        clearProfileButton.setForeground(Color.WHITE);

        // ── Save all changes
        saveChangesButton = createButton(520, 562, 300, 32, "💾  Save All Changes");
        saveChangesButton.setBackground(new Color(30, 30, 200));
        saveChangesButton.setForeground(Color.WHITE);

        // ── Background image 
        JLabel background = new JLabel(new ImageIcon("./images/bdnew.jpg"));
        background.setBounds(0, 0, 850, 720);
        this.add(background);

        updateScreen();
        this.setVisible(true);
    }
    // ── Helper builders
    JLabel createLabel(int x, int y, int w, int h, String text) {
        JLabel c = new JLabel(text);
        c.setBounds(x, y, w, h);
        c.setFont(font15);
        c.setForeground(Color.WHITE);
        this.add(c);
        return c;
    }
    JTextField createTextField(int x, int y, int w, int h, String text) {
        JTextField c = new JTextField(text);
        c.setBounds(x, y, w, h);
        c.setFont(font15);
        this.add(c);
        return c;
    }

    JButton createButton(int x, int y, int w, int h, String text) {
        JButton c = new JButton(text);
        c.setBounds(x, y, w, h);
        c.setFont(font15);
        c.setBackground(Color.GRAY);
        c.setForeground(Color.BLACK);
        c.addActionListener(this);
        this.add(c);
        return c;
    }

    // ── Refresh display 
    public void updateScreen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                sb.append(i).append(". ").append(categories[i].getCategoryData()).append("\n");
            }
        }
        screen.setText(sb.toString());
    }
    // ── Event handling 
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // ── Add category
            if (e.getSource() == addCatButton) {
                String cNo   = catNoTextField.getText().trim();
                String cName = catNameTextField.getText().trim();
                if (!cNo.isEmpty() && !cName.isEmpty()) {
                    int idx = Integer.parseInt(cNo);
                    if (categories[idx] == null) {
                        categories[idx] = new Category(cName);
                        updateScreen();
                    } else {
                        JOptionPane.showMessageDialog(this, "Serial No. already taken!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Enter Serial No. and Category Name!");
                }
            }
            // ── Remove category 
            else if (e.getSource() == removeCatButton) {
                String cNo = catNoTextField.getText().trim();
                if (!cNo.isEmpty()) {
                    int idx = Integer.parseInt(cNo);
                    if (categories[idx] != null) {
                        if (JOptionPane.showConfirmDialog(this, "Remove category and all its profiles?")
                                == JOptionPane.YES_OPTION) {
                            categories[idx] = null;
                            updateScreen();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Category not found!");
                    }
                }
            }
            // ── Clear category fields
            else if (e.getSource() == clearCatButton) {
                catNoTextField.setText("");
                catNameTextField.setText("");
            }

            // ── Create profile 
            else if (e.getSource() == createProfileButton) {
                String cNo  = catNoTextField.getText().trim();
                String name = nameTextField.getText().trim();
                String phone = phoneTextField.getText().trim();
                String gender = genderTextField.getText().trim();
                String age  = ageTextField.getText().trim();
                String prof = professionTextField.getText().trim();
                String city = cityTextField.getText().trim();

                if (!cNo.isEmpty() && !name.isEmpty() && !age.isEmpty()) {
                    int catIdx = Integer.parseInt(cNo);
                    if (categories[catIdx] != null) {
                        int slot = findNextSlot(catIdx);
                        if (slot == -1) {
                            JOptionPane.showMessageDialog(this, "Category is full (50 profiles max)!");
                        } else {
                            Profile p = new Profile(name, phone, gender, Integer.parseInt(age), prof, city);
                            categories[catIdx].insertProfile(slot, p);
                            updateScreen();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Category does not exist! Add it first.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Serial No., Full Name, and Age are required!");
                }
            }
            // ── Update profile
            else if (e.getSource() == updateProfileButton) {
                String cNo  = catNoTextField.getText().trim();
                String name = nameTextField.getText().trim();
                if (!cNo.isEmpty() && !name.isEmpty()) {
                    int catIdx = Integer.parseInt(cNo);
                    int pos    = findProfileByName(catIdx, name);
                    if (pos != -1) {
                        Profile p = categories[catIdx].getProfile(pos);
                        if (!phoneTextField.getText().trim().isEmpty())      p.setPhone(phoneTextField.getText().trim());
                        if (!genderTextField.getText().trim().isEmpty())     p.setGender(genderTextField.getText().trim());
                        if (!ageTextField.getText().trim().isEmpty())        p.setAge(Integer.parseInt(ageTextField.getText().trim()));
                        if (!professionTextField.getText().trim().isEmpty()) p.setProfession(professionTextField.getText().trim());
                        if (!cityTextField.getText().trim().isEmpty())       p.setCity(cityTextField.getText().trim());
                        updateScreen();
                    } else {
                        JOptionPane.showMessageDialog(this, "Profile with that name not found in this category!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Enter Serial No. and the Full Name of the profile to update!");
                }
            }
            // ── Remove profile
            else if (e.getSource() == removeProfileButton) {
                String cNo  = catNoTextField.getText().trim();
                String name = nameTextField.getText().trim();
                if (!cNo.isEmpty() && !name.isEmpty()) {
                    int catIdx = Integer.parseInt(cNo);
                    int pos    = findProfileByName(catIdx, name);
                    if (pos != -1) {
                        if (JOptionPane.showConfirmDialog(this, "Remove profile of \"" + name + "\"?")
                                == JOptionPane.YES_OPTION) {
                            categories[catIdx].removeProfile(pos);
                            updateScreen();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Profile with that name not found in this category!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Enter Serial No. and the Full Name of the profile to remove!");
                }
            }
            // ── Clear profile fields
            else if (e.getSource() == clearProfileButton) {
                nameTextField.setText("");
                phoneTextField.setText("");
                genderTextField.setText("");
                ageTextField.setText("");
                professionTextField.setText("");
                cityTextField.setText("");
            }
            // ── Save all data
            else if (e.getSource() == saveChangesButton) {
                if (JOptionPane.showConfirmDialog(this, "Save all changes to file?") == JOptionPane.YES_OPTION) {
                    FileIO.saveChangesInFile(categories);
                    JOptionPane.showMessageDialog(this, "Data saved successfully!");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Input Error: Check that Serial No. and Age are numbers.",
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Helpers  
    private int findNextSlot(int catIdx) {
        Profile[] profiles = categories[catIdx].getAllProfiles();
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i] == null) return i;
        }
        return -1;
    }

    private int findProfileByName(int catIdx, String name) {
        if (categories[catIdx] == null) return -1;
        Profile[] profiles = categories[catIdx].getAllProfiles();
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i] != null && profiles[i].getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }
}
