import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class SignIn extends JFrame {
    private ArrayList<User> users;
    private JPanel signIn;
    private ImageIcon backgroundImage;
    private JLabel backgroundLabel;
    private JLabel textOne, textTwo, textThree, textFour, textFive, titleLabel, titleLabelTwo;
    private PlaceholderTextField emailField;
    private PlaceholderPasswordField passwordField;
    private JButton showPasswordButton, signInbutton, forgotButton, signUpbutton;
    private boolean passwordVisible = false;
    private JCheckBox checkbox;
    private ArrayList<RentalItem> rentalItems = new ArrayList<>();

   public SignIn(ArrayList<User> users, ArrayList<RentalItem> rentalItems) {
        this.users = users; // Initialize user list with provided ArrayList<User>
        signIn = new JPanel(null); // Set the layout to null for absolute positioning
        setContentPane(signIn);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign in");
        setLocationRelativeTo(null);
        
        users = new ArrayList<>();
    
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
        users.add(new User("admin@gmail.com", "admin12345"));
        
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

        textOne = new JLabel("Sign In");
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

        passwordField = new PlaceholderPasswordField("Password");
        passwordField.setBounds(490, 235, 300, 50);
        passwordField.setBackground(new Color(50, 50, 50, 50)); // 50 is the alpha value (transparency)
        passwordField.setBorder(new RoundedBorder(10)); // 10 is the radius of the rounded corners
        passwordField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        passwordField.setOpaque(false); // Make the text field transparent
        passwordField.setForeground(Color.WHITE); // Set the text color to white

        // Create the show password button
        ImageIcon eyeIcon = new ImageIcon("image/eye_icon.png");
        Image eyeImage = eyeIcon.getImage();
        Image scaledEyeImage = eyeImage.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
        eyeIcon = new ImageIcon(scaledEyeImage);
        
        signInbutton = new JButton("Sign In");
        signInbutton.setBounds(490, 310, 300, 50);
        signInbutton.setBackground(new Color(242, 133, 0));
        signInbutton.setFont(new Font("Netflix-sans", Font.BOLD, 16));
        signInbutton.setForeground(Color.WHITE); // Set the text color to white
        // Add action listener to the Sign In button
        signInbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(); // Call login method when button is clicked
            }
        });
        add(signInbutton);
        
        textTwo = new JLabel("OR");
        textTwo.setBounds(627, 375, 300, 40);
        textTwo.setForeground(Color.GRAY);
        textTwo.setFont(new Font("Helvetica", Font.BOLD, 16));
        add(textTwo);
        
        forgotButton = new JButton("Forgot Password?");
        forgotButton.setBounds(490, 433, 300, 50);
        forgotButton.setBackground(new Color(242, 133, 0));
        forgotButton.setFont(new Font("Netflix-sans", Font.BOLD, 16));
        forgotButton.setOpaque(false); // Make the text field transparent
        forgotButton.setForeground(Color.WHITE); // Set the text color to white
        forgotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ForgotPassword(users, rentalItems);
                dispose();
            }
        });
        add(forgotButton);
        
        checkbox = new JCheckBox(" Remember me");
        checkbox.setBounds(490, 496, 200, 50);
        checkbox.setForeground(Color. WHITE);
        checkbox.setFont(new Font("Helvetica", Font.PLAIN, 16));
        checkbox.setBackground(Color.WHITE); // Set the button background to white
        checkbox.setOpaque(false); // Make the text field transparent
        add(checkbox);
        
        textThree = new JLabel("New to vivaflix?");
        textThree.setBounds(490, 540, 200, 40);
        textThree.setForeground(Color.GRAY);
        textThree.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(textThree);
        
        signUpbutton = new JButton("Sign up now.");
        signUpbutton.setBounds(585, 540, 128, 40);
        signUpbutton.setBackground(new Color(0,0,0));
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
    
    // Method to handle login
    private void login() {
        String enteredEmail = emailField.getText(); // Get entered username
        String enteredPassword = new String(passwordField.getPassword()); // Get entered password
        
        if (enteredEmail.isEmpty() && enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Username and Password");
            return;
        } else if (enteredEmail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Username");
            return;
        } else if (enteredPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Password");
            return;
        }
                
        for (User u : users) { // Loop through the list of users
            if (u.getEmail().equals(enteredEmail) && !u.getPassword().equals(enteredPassword)){
                JOptionPane.showMessageDialog(null, "Wrong password!");
                break;
            } else if(u.getEmail().equals(enteredEmail) && u.getPassword().equals(enteredPassword)) { // Check if username and password match
                JOptionPane.showMessageDialog(signIn, "Log In Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the main frame
                new HomePageUI(rentalItems, users); // Open the welcome frame for the user
                return; // Exit the method
            }
        }
        JOptionPane.showMessageDialog(signIn, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE); // Show error message if login fails
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

// Custom border class to create rounded corners
class RoundedBorder extends AbstractBorder {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(5, 10, 5, 10);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 10;
        insets.top = insets.bottom = 5;
        return insets;
    }
}