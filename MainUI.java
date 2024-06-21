import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainUI extends JFrame {
    private JPanel mainUI;
    private ImageIcon backgroundIcon;
    private JLabel backgroundLabel;
    private JPanel blackPanel;
    private ArrayList<User> user = new ArrayList<>();
    private ArrayList<RentalItem> rentalItems = new ArrayList<>();

    public MainUI() {
        
        mainUI = new JPanel(null);  // Set the layout to null for absolute positioning
        setContentPane(mainUI);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VivaFlix");
        setLocationRelativeTo(null);
        
        

        MainButtonsTextFields(); // Add text fields and buttons
        BackgroundImage();

        setVisible(true);
    }

    // Method to update the bounds of the background label based on current frame size
    private void updateBackgroundBounds() {
        backgroundLabel.setBounds(0, 0, getWidth(), (int) (getHeight() * 0.85));
        backgroundLabel.repaint(); // Ensure repaint
    }

    // Method to update the bounds of the black panel based on current frame size
    private void updateBlackPanelBounds() {
        blackPanel.setBounds(0, (int) (getHeight() * 0.74), getWidth(), (int) (getHeight() * 0.3));
        blackPanel.repaint(); // Ensure repaint
    }

    private void MainButtonsTextFields() {
        // Create and configure the rounded text field
        PlaceholderTextField emailField = new PlaceholderTextField("Enter your email address");
        emailField.setBounds(410, 350, 250, 50); // Adjust bounds as needed
        emailField.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        emailField.setOpaque(false); // Make the text field transparent
        emailField.setForeground(Color.WHITE); // Set the text color to white
        emailField.setBackground(new Color(0, 0, 0, 204)); // Set the alpha value of the background color to 80%
        mainUI.add(emailField); // Add the text field to the main UI panel

        // Create labels to display the title
        JLabel titleLabel = new JLabel("viva");
        titleLabel.setBounds(210, 30, 100, 30);
        titleLabel.setForeground(new Color(0, 191, 255));
        titleLabel.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        mainUI.add(titleLabel);

        JLabel titleLabelTwo = new JLabel("flìx");
        titleLabelTwo.setBounds(287, 30, 100, 30);
        titleLabelTwo.setForeground(new Color(242, 133, 0));
        titleLabelTwo.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        mainUI.add(titleLabelTwo);

        // Create and configure the rounded button
        JButton signInButton = new RoundedButton("Sign In");
        signInButton.setBounds(940, 30, 83, 35);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(new Color(242, 133, 0)); // Set the button background to tangerine
        signInButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to Arial Bold
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignIn(user, rentalItems);
                dispose();
            }
        });
        mainUI.add(signInButton);

        // Create and configure the rounded button
        JButton getStarted = new RoundedButton("Get Started  >");
        getStarted.setBounds(670, 350, 137, 50);
        getStarted.setForeground(Color.WHITE);
        getStarted.setBackground(new Color(242, 133, 0)); // Set the button background to tangerine
        getStarted.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font to Arial Bold
        getStarted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if (email.isEmpty() || !email.contains("@")) {
                    JOptionPane.showMessageDialog(mainUI, "Not a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.equals("admin@gmail.com")) {
                    user.add(new User(email, "defaultpassword")); // Add user with default password
                    new CreateAccount(email, rentalItems); // Proceed to CreateAccount class
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(mainUI, "This email is reserved for admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });        
        // Create labels to display the Main Page Text
        JLabel textOne = new JLabel("Unlimited Movies, TV shows, and more");
        textOne.setBounds(285, 215, 1000, 40);
        textOne.setForeground(Color.WHITE);
        textOne.setFont(new Font("Calibri", Font.BOLD, 40));
        mainUI.add(textOne);

        // Create labels to display the Main Page Text
        JLabel textTwo = new JLabel("Watch anywhere. Cancel anytime.");
        textTwo.setBounds(435, 260, 1000, 40);
        textTwo.setForeground(Color.WHITE);
        textTwo.setFont(new Font("Calibri", Font.BOLD, 25));
        mainUI.add(textTwo);

        // Create labels to display the Main Page Text
        JLabel textThree = new JLabel("Ready to watch? Enter your email to create or restart your membership.");
        textThree.setBounds(355, 300, 1000, 40);
        textThree.setForeground(Color.WHITE);
        textThree.setFont(new Font("Calibri", Font.PLAIN, 17));
        mainUI.add(textThree);

        // Create labels to display the Main Page Text
        JLabel textFour = new JLabel("vivaflix  © 2024");
        textFour.setBounds(590, 635, 1000, 40);
        textFour.setForeground(Color.GRAY);
        textFour.setFont(new Font("Calibri", Font.BOLD, 10));
        mainUI.add(textFour);

        mainUI.add(getStarted);
    }

    private void BackgroundImage() {
        // Initialize the background image and label
        backgroundIcon = new ImageIcon("image/background.jpg");
        backgroundLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = backgroundIcon.getImage();
                // Custom drawing if needed
                g.drawImage(image, 0, 0, getWidth(), (int) (getHeight() * 0.85), this);
            }
        };
        updateBackgroundBounds();

        // Initialize the black panel
        blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);
        updateBlackPanelBounds();

        // Add a component listener to detect frame resizing
        mainUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundBounds();
                updateBlackPanelBounds();
            }
        });

        mainUI.add(backgroundLabel); // Add the background label first
        mainUI.add(blackPanel); // Add black panel to the main UI
    }

    public static void main(String[] args) {
        new MainUI();
    }
}

class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text) {
        super(text);
        this.radius = 10; // You can change this value to adjust the roundness
        setOpaque(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color and rounded rectangle
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker()); // Darker color when pressed
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw the text
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded border
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        setContentAreaFilled(false); // Ensure transparency for custom painting
    }
}

class PlaceholderTextField extends JTextField {
    private String placeholder;
    private JLabel placeholderLabel;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.placeholderLabel = new JLabel(placeholder);
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(getFont().deriveFont(Font.PLAIN, 14));
        setLayout(null);

        add(placeholderLabel);
        placeholderLabel.setBounds(10, 10, getWidth() - 20, getHeight() - 20);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                placeholderLabel.setBounds(10, 10, getWidth() - 20, getHeight() - 20);
            }
        });

        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (getText().isEmpty()) {
                    placeholderLabel.setVisible(false);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().isEmpty()) {
                    placeholderLabel.setVisible(true);
                }
            }
        });
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        placeholderLabel.setVisible(t.isEmpty());
    }
}
