import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class EditProfile extends JFrame{
    private JPanel EditProfile;
    private JLabel titleLabel, titleLabelTwo, profileLabel;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<RentalItem> rentalItems = new ArrayList<>();
    private PlaceholderTextField emailField;
    private PlaceholderPasswordField passwordField, confirmpasswordField;
    private JButton _save, _cancel, _deleteProfile, _signOut;
    
    public EditProfile(ArrayList<User> users, ArrayList<RentalItem> rentalItems){
        this.users = users;
        this.rentalItems = rentalItems;
        EditProfile = new JPanel(null);  // Set the layout to null for absolute positioning
        setContentPane(EditProfile);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Edit Profile");
        setLocationRelativeTo(null);
        
        Components();
        BackgroundImage();
        setVisible(true);
    }

    private void Components(){
        // Create labels to display the title
        titleLabel = new JLabel("viva");
        titleLabel.setBounds(56, 25, 100, 30);
        titleLabel.setForeground(new Color(0, 191, 255));
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        add(titleLabel);
    
        titleLabelTwo = new JLabel("flìx");
        titleLabelTwo.setBounds(133, 25, 100, 30);
        titleLabelTwo.setForeground(new Color(242, 133, 0));
        titleLabelTwo.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        add(titleLabelTwo);
        
        profileLabel = new JLabel("Edit Profile");
        profileLabel.setBounds(425,115,500,30);
        profileLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        profileLabel.setForeground(Color.WHITE);
        add(profileLabel);
        
        JButton _backbutton = new JButton("←");
        _backbutton.setBounds(40, 100, 80, 50);
        _backbutton.setBackground(new Color(0, 0, 0));
        _backbutton.setFont(new Font("Netflix-sans", Font.BOLD, 45));
        _backbutton.setOpaque(false);
        _backbutton.setForeground(Color.WHITE);
        _backbutton.setBorderPainted(false); // Remove the border
        _backbutton.setFocusPainted(false);  // Remove the focus border
        _backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePageUI(rentalItems, users);
                dispose();
            }
        });
        add(_backbutton);
        
        // Configuration for Profile Button
        ImageIcon profileIcon = new ImageIcon("image/profile.png");  // Ensure the path and extension are correct
        Image scaledProfileImage = profileIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledProfileIcon = new ImageIcon(scaledProfileImage);
        JButton _profileButton = new JButton(scaledProfileIcon);
        _profileButton.setBounds(425, 160, 60, 60);  // x, y, width, height
        add(_profileButton);
        
        emailField = new PlaceholderTextField("Enter your email address");
        emailField.setBounds(500, 160, 280, 40);
        emailField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        emailField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        emailField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        emailField.setOpaque(false); // Make the text field transparent
        emailField.setForeground(Color.WHITE); // Set the text color to white
        add(emailField);

        passwordField = new PlaceholderPasswordField("Enter New Password");
        passwordField.setBounds(500, 235, 280, 40);
        passwordField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        passwordField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        passwordField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        passwordField.setOpaque(false); // Make the text field transparent
        passwordField.setForeground(Color.WHITE); // Set the text color to white
        add(passwordField);

        confirmpasswordField = new PlaceholderPasswordField("Confirm New Password");
        confirmpasswordField.setBounds(500, 310, 280, 40);
        confirmpasswordField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        confirmpasswordField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        confirmpasswordField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        confirmpasswordField.setOpaque(false); // Make the text field transparent
        confirmpasswordField.setForeground(Color.WHITE); // Set the text color to white
        add(confirmpasswordField);
        
        _save = new JButton("SAVE");
        _save.setBounds(425, 490, 90, 40);
        _save.setBackground(Color. WHITE);
        _save.setFont(new Font("Arial", Font.BOLD, 14));
        _save.setForeground(Color.BLACK);
        _save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String newPassword = new String(passwordField.getPassword()); // Get new password
                String confirmPassword = new String(confirmpasswordField.getPassword()); // Get confirm password

                // Find the user by email
                User currentUser = null;
                for (User u : users) {
                    if (u.getEmail().equals(email)) {
                        currentUser = u;
                        break;
                    }
                }

                if (currentUser == null) {
                    JOptionPane.showMessageDialog(null, "No user found with this email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the new password is empty
                if (newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a new password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the confirm password is empty
                if (confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please confirm your new password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if new password matches confirm password
                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if new password is different from current password
                if (newPassword.equals(currentUser.getPassword())) {
                    JOptionPane.showMessageDialog(null, "New password cannot be the same as the current password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Confirm the password change
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your password?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    currentUser.setPassword(newPassword); // Update user's password
                    JOptionPane.showMessageDialog(null, "Profile updated successfully!"); // Show success message
                    new HomePageUI(rentalItems, users);
                    dispose(); // Close the edit profile frame
                }

            }
        });
        add(_save);
        
        _cancel = new JButton("CANCEL");
        _cancel.setBounds(520, 490, 90, 40);
        _cancel.setBackground(Color. BLACK);
        _cancel.setFont(new Font("Arial", Font.BOLD, 14));
        _cancel.setForeground(Color.WHITE);
        _cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePageUI(rentalItems, users);
                dispose();
            }
        });
        add(_cancel);
        
        _deleteProfile = new JButton("DELETE PROFILE");
        _deleteProfile.setBounds(615, 490, 160, 40);
        _deleteProfile.setBackground(Color.BLACK);
        _deleteProfile.setFont(new Font("Arial", Font.BOLD, 14));
        _deleteProfile.setForeground(Color.WHITE);
        _deleteProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
        
                // Find the user by email and password
                User currentUser = null;
                for (User u : users) {
                    if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                        currentUser = u;
                        break;
                    }
                }
        
                if (currentUser == null) {
                    JOptionPane.showMessageDialog(null, "Incorrect email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // Confirm deletion
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your profile?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    users.remove(currentUser); // Remove user from ArrayList
                    JOptionPane.showMessageDialog(null, "Profile deleted successfully."); // Inform user
                    new SignIn(users, rentalItems); // Go back to sign-in screen
                    dispose(); // Close current window
                }
            }
        });

        add(_deleteProfile);
        
        _signOut = new JButton("SIGN OUT");
        _signOut.setBounds(1025, 550, 110, 40);
        _signOut.setBackground(new Color(242, 133, 0));
        _signOut.setFont(new Font("Arial", Font.BOLD, 14));
        _signOut.setForeground(Color. WHITE);
        _signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    new SignIn(users, rentalItems); // Go back to sign-in screen
                    dispose(); // Close current window
                }
            }
        });
        add(_signOut);

        // Create the show password button
        ImageIcon eyeIcon = new ImageIcon("image/eye_icon.png");
        Image eyeImage = eyeIcon.getImage();
        Image scaledEyeImage = eyeImage.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
        eyeIcon = new ImageIcon(scaledEyeImage);
        
    }


    private void BackgroundImage() {
        ImageIcon editprofileIcon = new ImageIcon("image/homepage.jpg");
        JLabel editprofileLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = editprofileIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
    
        editprofileLabel.setBounds(0, 0, getWidth(), getHeight());
        EditProfile.add(editprofileLabel);
    
        // Ensure the label is repainted whenever the panel is resized
        EditProfile.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                editprofileLabel.setSize(EditProfile.getSize());
                editprofileLabel.repaint();
            }
        });
    }
    
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 0, 0));
        button.setFont(new Font("Netflix-sans", Font.BOLD, 16));
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false); // Remove the border
        button.setFocusPainted(false);  // Remove the focus border
    }

}