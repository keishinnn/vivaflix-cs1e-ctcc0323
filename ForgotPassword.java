import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class ForgotPassword extends JFrame {
    private ArrayList<User> users;
    private JPanel signIn;
    private ImageIcon backgroundImage;
    private JLabel backgroundLabel;
    private JLabel textOne, textThree, textFour, textFive, titleLabel, titleLabelTwo;
    private PlaceholderTextField emailField;
    private PlaceholderPasswordField passwordField, confirmpasswordField;
    private JButton showPasswordButton, signInbutton, signUpbutton;
    private boolean passwordVisible = false;
    private ArrayList<RentalItem> rentalItems;

    public ForgotPassword(ArrayList<User> users, ArrayList<RentalItem> rentalItems) {
        this.rentalItems = rentalItems;
        this.users = users; // Initialize user list with provided ArrayList<User>
        signIn = new JPanel(null); // Set the layout to null for absolute positioning
        setContentPane(signIn);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign in");
        setLocationRelativeTo(null);

        Components();
        BackgroundImage(); // Call the method to add the background image

        // Add a component listener to update the background bounds when the frame is resized
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundBounds();
            }
        });

        setVisible(true);
    }

    // Method to update the bounds of the background label based on current frame size
    private void updateBackgroundBounds() {
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        backgroundLabel.repaint(); // Ensure repaint
    }

    private void BackgroundImage() {
        backgroundImage = new ImageIcon("image/background_2.jpg");
        backgroundLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = backgroundImage.getImage();
                // Draw the background image
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                // Calculate the center position for the rectangle
                int rectWidth = 400;
                int rectHeight = 600;

                // Adjust these values to change the rectangle's position
                int offsetX = 0; // Change this value to move the rectangle horizontally
                int offsetY = 0; // Change this value to move the rectangle vertically

                int rectX = (getWidth() - rectWidth) / 2 + offsetX;
                int rectY = (getHeight() - rectHeight) / 3 + offsetY;

                // Set the color with opacity and draw the rectangle
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 200)); // Black color with 80% opacity (200 out of 255)
                g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
            }
        };
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); // Initial bounds
        signIn.add(backgroundLabel); // Add the label to the panel
    }

    private void Components() {
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
                new SignIn(users, rentalItems);
                dispose();
            }
        });
        add(_backbutton);
        
        // Create labels to display the title
        JLabel titleLabel = new JLabel("viva");
        titleLabel.setBounds(210, 30, 100, 30);
        titleLabel.setForeground(new Color(0, 191, 255));
        titleLabel.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        add(titleLabel);

        JLabel titleLabelTwo = new JLabel("flìx");
        titleLabelTwo.setBounds(287, 30, 100, 30);
        titleLabelTwo.setForeground(new Color(242, 133, 0));
        titleLabelTwo.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        add(titleLabelTwo);

        textOne = new JLabel("Reset Password");
        textOne.setBounds(490, 85, 500, 40);
        textOne.setForeground(Color.WHITE);
        textOne.setFont(new Font("Helvetica", Font.BOLD, 28));
        add(textOne);

        emailField = new PlaceholderTextField("Enter your email address");
        emailField.setBounds(490, 160, 300, 50);
        emailField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        emailField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        emailField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        emailField.setOpaque(false); // Make the text field transparent
        emailField.setForeground(Color.WHITE); // Set the text color to white
        add(emailField);

        passwordField = new PlaceholderPasswordField("Enter New Password");
        passwordField.setBounds(490, 235, 300, 50);
        passwordField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        passwordField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        passwordField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        passwordField.setOpaque(false); // Make the text field transparent
        passwordField.setForeground(Color.WHITE); // Set the text color to white
        add(passwordField);

        confirmpasswordField = new PlaceholderPasswordField("Confirm Password");
        confirmpasswordField.setBounds(490, 310, 300, 50);
        confirmpasswordField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        confirmpasswordField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        confirmpasswordField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        confirmpasswordField.setOpaque(false); // Make the text field transparent
        confirmpasswordField.setForeground(Color.WHITE); // Set the text color to white
        add(confirmpasswordField);

        // Create the show password button
        ImageIcon eyeIcon = new ImageIcon("image/eye_icon.png");
        Image eyeImage = eyeIcon.getImage();
        Image scaledEyeImage = eyeImage.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
        eyeIcon = new ImageIcon(scaledEyeImage);

        signInbutton = new JButton("Reset");
        signInbutton.setBounds(490, 400, 300, 50);
        signInbutton.setBackground(new Color(242, 133, 0));
        signInbutton.setFont(new Font("Netflix-sans", Font.BOLD, 16));
        signInbutton.setForeground(Color.WHITE); // Set the text color to white
        signInbutton.addActionListener(new ActionListener() {
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
                    new SignIn(users, rentalItems);
                    dispose();
                }
            }
        });
        add(signInbutton);

        textThree = new JLabel("New to vivaflix?");
        textThree.setBounds(490, 540, 200, 40);
        textThree.setForeground(Color.GRAY);
        textThree.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(textThree);

        signUpbutton = new JButton("Sign up now.");
        signUpbutton.setBounds(585, 540, 128, 40);
        signUpbutton.setBackground(new Color(0, 0, 0));
        signUpbutton.setFont(new Font("Netflix-sans", Font.BOLD, 15));
        signUpbutton.setOpaque(false);
        signUpbutton.setForeground(Color.WHITE);
        signUpbutton.setBorderPainted(false); // Remove the border
        signUpbutton.setFocusPainted(false);  // Remove the focus border
        signUpbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccount("", rentalItems);
                dispose();
            }
        });
        add(signUpbutton);

        showPasswordButton = new JButton(eyeIcon);
        showPasswordButton.setBounds(260, 10, 25, 30); // Place the button inside the text field
        showPasswordButton.setContentAreaFilled(false); // Remove button background
        showPasswordButton.setBorderPainted(false); // Remove button border
        showPasswordButton.setFocusPainted(false); // Remove focus border

        // Add action listener to the button
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });

        // Use a JLayeredPane to overlay the button on the text field
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(passwordField.getBounds());
        passwordField.setBounds(0, 0, 300, 50);
        layeredPane.add(passwordField, Integer.valueOf(0));
        layeredPane.add(showPasswordButton, Integer.valueOf(1));

        add(layeredPane);
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            passwordField.setEchoChar('•'); // Hide password
        } else {
            passwordField.setEchoChar((char) 0); // Show password
        }
        passwordVisible = !passwordVisible;
    }

}
