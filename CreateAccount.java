import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccount extends JFrame {
    private JPanel createAccount;
    private JLabel textOne, textTwo, textThree, textFour, textFive, titleLabel, titleLabelTwo;
    private PlaceholderTextField emailField;
    private PlaceholderPasswordField passwordField;
    private JCheckBox checkbox;
    private JButton nextButton, showPasswordButton;
    private boolean passwordVisible = false;
    private ArrayList<User> user;
    private ArrayList<RentalItem> rentalItems;

    public CreateAccount(String email, ArrayList<RentalItem> rentalItems) {
        this.rentalItems = rentalItems;
        createAccount = new JPanel();
        setLayout(new BorderLayout());
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Account");
        setLocationRelativeTo(null);
        
        user = new ArrayList<>();
        
        showPasswordButton = new JButton("* See Password");
        showPasswordButton.setBounds(425, 380, 150, 50); // Place the button inside the text field
        showPasswordButton.setContentAreaFilled(false); // Remove button background
        showPasswordButton.setBorderPainted(false); // Remove button border
        showPasswordButton.setFont(new Font("Arial", Font.BOLD , 14)); // Set the font to Arial Bold
        showPasswordButton.setFocusPainted(false); // Remove focus border
        // Add action listener to the button
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
        add(showPasswordButton);

        textOne = new JLabel("Create a password to start");
        textOne.setBounds(440, 115, 1000, 40);
        textOne.setForeground(new Color(64, 64, 64));
        textOne.setFont(new Font("Helvetica", Font.BOLD, 28));
        add(textOne);

        textTwo = new JLabel("your streaming");
        textTwo.setBounds(442, 155, 1000, 40);
        textTwo.setForeground(new Color(64, 64, 64));
        textTwo.setFont(new Font("Helvetica", Font.BOLD, 28));
        add(textTwo);

        // Create labels to display the Main Page Text
        textThree = new JLabel("Just a few more steps and you're done!");
        textThree.setBounds(442, 198, 800, 40);
        textThree.setForeground(new Color(64, 64, 64));
        textThree.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(textThree);

        // Create labels to display the Main Page Text
        textFour = new JLabel("We hate paperwork, too.");
        textFour.setBounds(442, 221, 1000, 40);
        textFour.setForeground(new Color(64, 64, 64));
        textFour.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(textFour);

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

        emailField = new PlaceholderTextField("Enter your email address");
        emailField.setBounds(442, 265, 400, 50);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set the font to Arial Bold
        emailField.setText(email);
        add(emailField);

        passwordField = new PlaceholderPasswordField("Enter your password");
        passwordField.setBounds(442, 325, 400, 50);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set the font to Arial Bold
        add(passwordField);

        checkbox = new JCheckBox("Please do not email me Vivaflix special offers.");
        checkbox.setBounds(442, 414, 400, 50);
        checkbox.setForeground(new Color(64, 64, 64));
        checkbox.setFont(new Font("Helvetica", Font.PLAIN, 17));
        checkbox.setBackground(Color.WHITE); // Set the button background to white
        add(checkbox);

        nextButton = new JButton("Next");
        nextButton.setBounds(442, 462, 400, 60);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        nextButton.setBackground(new Color(242, 133, 0)); // Set the button background to tangerine
        nextButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getText());

        // Validate password length
        if (password.length() <= 8) {
            JOptionPane.showMessageDialog(createAccount, "Password must be more than 8 characters", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if the email is already in use
            boolean emailExists = false;
            for (User u : user) {
                if (u.getEmail().equals(email)) {
                    u.setPassword(password);
                    emailExists = true;
                    break;
                }
            }

            // If email does not exist, create a new user
            if (!emailExists) {
                User newUser = new User(email, password); // Assuming User class has a constructor User(String email, String password)
                user.add(newUser);
            }
                // Show first dialog to ask if user wants to rent
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to create this account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
            // User chose to rent, now ask for rental duration
                JOptionPane.showMessageDialog(createAccount, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new SignIn(user, rentalItems); // Pass the user ArrayList to SignIn constructor
                dispose(); // Close the CreateAccount frame
            }  else if (response == JOptionPane.NO_OPTION) {
            // User chose not to rent
                emailField.setText("");
                passwordField.setText("");
                return;
            }
            
        }
    }
});


        add(nextButton);

        // Create labels to display the Main Page Text
        textFive = new JLabel("vivaflix  © 2024");
        textFive.setBounds(590, 635, 1000, 40);
        textFive.setForeground(Color.GRAY);
        textFive.setFont(new Font("Calibri", Font.BOLD, 10));
        add(textFive);

        CustomPanel panel = new CustomPanel();
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    private void togglePasswordVisibility() {
        if (passwordVisible) {
            passwordField.setEchoChar('•'); // Hide password
        } else {
            passwordField.setEchoChar((char) 0); // Show password
        }
        passwordVisible = !passwordVisible;
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();

            // 80% white background
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, (int) (height * 0.8));

            // line background
            g.setColor(new Color(220, 220, 220));
            g.fillRect(0, (int) (height * 0.12), width, (int) (height * 0.002));

            // 20% grey background
            g.setColor(new Color(240, 240, 240));
            g.fillRect(0, (int) (height * 0.8), width, (int) (height * 0.2));
        }
    }
}

class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;
    private JLabel placeholderLabel;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
        this.placeholderLabel = new JLabel(placeholder);
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(getFont().deriveFont(Font.PLAIN, 14));
        setLayout(null);

        add(placeholderLabel);
        placeholderLabel.setBounds(10, 10, getWidth() - 20, getHeight() - 20);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                updatePlaceholder();
            }

            @Override
            public void focusLost(FocusEvent e) {
                updatePlaceholder();
            }
        });
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        updatePlaceholder();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        placeholderLabel.setBounds(10, 10, width - 20, height - 20);
    }

    private void updatePlaceholder() {
        if (new String(getPassword()).isEmpty() && !isFocusOwner()) {
            placeholderLabel.setVisible(true);
            placeholderLabel.setFont(getFont().deriveFont(Font.PLAIN, 14));
            placeholderLabel.setBounds(10, 10, getWidth() - 20, getHeight() - 20);
        } else {
            placeholderLabel.setVisible(false);
        }
    }
}
