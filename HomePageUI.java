import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class HomePageUI extends JFrame{
    private JPanel HomePageUI, TVShows;
    private ImageIcon homepageIcon;
    private JLabel homepageLabel, titleLabel, titleLabelTwo, _tvShowLabel, _moviesLabel;
    private JButton _homeButton, _tvshowsButton, _moviesButton, _myListButton, _peakyblinders, _strangerthings, _loki, _thewitcher, _insideout2, _onepiece, _haikyu, _grandma;
    private ImageIcon _peakyIcon, _strangerIcon, _lokiIcon, _thewitcherIcon, _insideout2Icon, _onepieceIcon, _haikyuIcon, _grandmaIcon;
    private ArrayList<RentalItem> rentalItems = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    
    public HomePageUI(ArrayList<RentalItem> rentalItems, ArrayList<User> users){
        this.rentalItems = rentalItems;
        this.users = users;
        HomePageUI = new JPanel(null);  // Set the layout to null for absolute positioning
        setContentPane(HomePageUI);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VivaFlix");
        setLocationRelativeTo(null);
        
        Movies();
        TVShows();
        Components();
        BackgroundImage();
        setVisible(true);
    }
    
    private void Components(){
        _tvShowLabel = new JLabel("Popular TV Shows");
        _tvShowLabel.setBounds(100,110,500,30);
        _tvShowLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        _tvShowLabel.setForeground(Color.WHITE);
        HomePageUI.add(_tvShowLabel);
        
        _tvShowLabel = new JLabel("Trending Movies");
        _tvShowLabel.setBounds(100,370,500,40);
        _tvShowLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        _tvShowLabel.setForeground(Color.WHITE);
        HomePageUI.add(_tvShowLabel);
        
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
        
        UnderlinedButton _homeButton = new UnderlinedButton("Home");
        _homeButton.setBounds(223, 25, 80, 40);
        styleButton(_homeButton);
        add(_homeButton);

        _tvshowsButton = new JButton("TV Shows");
        _tvshowsButton.setBounds(313, 25, 120, 40);
        styleButton(_tvshowsButton);
        _tvshowsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TVshows(rentalItems, users);
                dispose();
            }
        });
        add(_tvshowsButton);
        
        _moviesButton = new JButton("Movies");
        _moviesButton.setBounds(448, 25, 90, 40);
        styleButton(_moviesButton);
        _moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MoviesPanel(rentalItems, users);
                dispose();
            }
        });
        add(_moviesButton);
        
        _myListButton = new JButton("My List");
        _myListButton.setBounds(538, 25, 120, 40);
        styleButton(_myListButton);
        _myListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyList(rentalItems, users);
                dispose();
            }
        });
        add(_myListButton);
        
        // Configuration for Profile Button
        ImageIcon profileIcon = new ImageIcon("image/profile.png");  // Ensure the path and extension are correct
        Image scaledProfileImage = profileIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon scaledProfileIcon = new ImageIcon(scaledProfileImage);
        JButton _profileButton = new JButton(scaledProfileIcon);
        _profileButton.setBounds(940, 25, 35, 35);  // x, y, width, height
        _profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditProfile(users, rentalItems);
                dispose();
            }
        });
        add(_profileButton);
        
        JButton profileLabel = new JButton("Profile");
        profileLabel.setBounds(980, 25, 90, 30);
        profileLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditProfile(users, rentalItems);
                dispose();
            }
        });
        styleButton(profileLabel);
        add(profileLabel);

    }
    
    
    private void BackgroundImage() {
        homepageIcon = new ImageIcon("image/homepage.jpg");
        homepageLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = homepageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        homepageLabel.setBounds(0, 0, getWidth(), getHeight());
        HomePageUI.add(homepageLabel);

        // Ensure the label is repainted whenever the panel is resized
        HomePageUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                homepageLabel.setSize(HomePageUI.getSize());
                homepageLabel.repaint();
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
    
    private void TVShows() {
        // Configuration for Peaky Blinders button
        ImageIcon peakyIcon = new ImageIcon("image/peakyblinders.jpg");  // Ensure the path and extension are correct
        Image scaledPeakyImage = peakyIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledPeakyIcon = new ImageIcon(scaledPeakyImage);
        _peakyblinders = new JButton(scaledPeakyIcon);
        _peakyblinders.setBounds(100, 175, 225, 150);  // x, y, width, height
    _peakyblinders.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if Peaky Blinders is already rented
        boolean isAlreadyRented = rentalItems.stream()
                .anyMatch(item -> item.getTitle().equals("Peaky Blinders"));

        if (isAlreadyRented) {
            JOptionPane.showMessageDialog(null,
                    "Peaky Blinders is already rented. You cannot rent it again.",
                    "Already Rented",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Show first dialog to ask if user wants to rent
        String[] rentOptions = {"Rent", "No"};
        int rentChoice = JOptionPane.showOptionDialog(null,
                "Do you wish to rent Peaky Blinders?",
                "Rent Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                rentOptions,
                null);

        if (rentChoice == JOptionPane.YES_OPTION) {
            // User chose to rent, now ask for rental duration
            String daysInput = JOptionPane.showInputDialog(null,
                    "Enter the number of days you want to rent Peaky Blinders: " + "\n3 days, 7 days, 30 days",
                    "Rental Duration",
                    JOptionPane.PLAIN_MESSAGE);

            // Process user input based on predefined durations
            int rentalDays = 0;
            double pricePerDay = 100; // Example price per day

            if (daysInput != null) {
                switch (daysInput.toLowerCase()) {
                    case "3":
                        rentalDays = 3;
                        break;
                    case "7":
                        rentalDays = 7;
                        break;
                    case "30":
                        rentalDays = 30;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                }

                // Calculate total cost based on rental days and price per day
                double totalCost = rentalDays * pricePerDay;

                // Instantiate RentalItem and add it to rentalItems
                RentalItem peakyBlinders = new RentalItem("Peaky Blinders", pricePerDay, rentalDays, _peakyblinders);
                rentalItems.add(peakyBlinders);                

                JOptionPane.showMessageDialog(null,
                        "You rented Peaky Blinders for " + rentalDays + " days. Total cost: ₱" + totalCost
                                + "\nIt will be added to My List Tab.");
            }
        } else if (rentChoice == JOptionPane.NO_OPTION) {
            // User chose not to rent
            return;
        }
        }
    });

        HomePageUI.add(_peakyblinders);
        
        // Configuration for Stranger Things button
        ImageIcon strangerIcon = new ImageIcon("image/strangerthings.jpg");  // Ensure the path and extension are correct
        Image scaledStrangerImage = strangerIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledStrangerIcon = new ImageIcon(scaledStrangerImage);
        _strangerthings = new JButton(scaledStrangerIcon);
        _strangerthings.setBounds(375, 175, 225, 150);  // x, y, width, height
        _strangerthings.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("Stranger Things"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "Stranger Things is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent Stranger Things?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent Peaky Blinders: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 100; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem strangerThings = new RentalItem("Stranger Things", pricePerDay, rentalDays, _strangerthings);
                    rentalItems.add(strangerThings);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented Stranger Things for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_strangerthings);
        
        // Configuration for Loki Things button
        ImageIcon lokiIcon = new ImageIcon("image/loki.jpeg");  // Ensure the path and extension are correct
        Image scaledLokiImage = lokiIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledLokiIcon = new ImageIcon(scaledLokiImage);
        _loki = new JButton(scaledLokiIcon);
        _loki.setBounds(655, 175, 225, 150);  // x, y, width, height
        _loki.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("Loki"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "Loki is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent Loki?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent Loki: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 100; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem loki = new RentalItem("Loki", pricePerDay, rentalDays, _loki);
                    rentalItems.add(loki);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented Loki for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_loki);
        
        // Configuration for Loki Things button
        ImageIcon thewitcherIcon = new ImageIcon("image/thewitcher.jpg");  // Ensure the path and extension are correct
        Image scaledThewitcherImage = thewitcherIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledThewitcherIcon = new ImageIcon(scaledThewitcherImage);
        _thewitcher = new JButton(scaledThewitcherIcon);
        _thewitcher.setBounds(930, 175, 225, 150);  // x, y, width, height
        _thewitcher.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("The Witcher"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "The Witcher is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent The Witcher?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent The Witcher: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 100; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem theWitcher = new RentalItem("The Witcher", pricePerDay, rentalDays, _thewitcher);
                    rentalItems.add(theWitcher);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented The Witcher for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_thewitcher);
        
        
    
        // Ensure the panel is updated after adding the buttons
        HomePageUI.revalidate();
        HomePageUI.repaint();
    }
    
    private void Movies(){
        // Configuration for Peaky Blinders button
        ImageIcon insideout2Icon = new ImageIcon("image/insideout2.jpg");  // Ensure the path and extension are correct
        Image scaledInsideout2Image = insideout2Icon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledInsideout2Icon = new ImageIcon(scaledInsideout2Image);
        _insideout2 = new JButton(scaledInsideout2Icon);
        _insideout2.setBounds(100, 435, 225, 150);  // x, y, width, height
        _insideout2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("Inside Out 2"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "Inside Out 2 is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent Inside Out 2?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent Inside Out 2: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 150; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem strangerThings = new RentalItem("Inside Out 2", pricePerDay, rentalDays, _insideout2);
                    rentalItems.add(strangerThings);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented Inside Out 2 for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_insideout2);
        
        // Configuration for Stranger Things button
        ImageIcon onepieceIcon = new ImageIcon("image/onepiece.jpg");  // Ensure the path and extension are correct
        Image scaledOnepieceImage = onepieceIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledOnepieceIcon = new ImageIcon(scaledOnepieceImage);
        _onepiece = new JButton(scaledOnepieceIcon);
        _onepiece.setBounds(375, 435, 225, 150);  // x, y, width, height
        _onepiece.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("One Piece: Red Film"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "One Piece: Red Film is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent One Piece: Red Film?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent One Piece: Red Film: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 150; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem onepiece = new RentalItem("One Piece: Red Film", pricePerDay, rentalDays, _onepiece);
                    rentalItems.add(onepiece);
                   
                    JOptionPane.showMessageDialog(null,
                            "You rented One Piece: Red Film for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_onepiece);
        
        // Configuration for Loki Things button
        ImageIcon haikyuIcon = new ImageIcon("image/haikyu.jpg");  // Ensure the path and extension are correct
        Image scaledHaikyuImage = haikyuIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledHaikyuIcon = new ImageIcon(scaledHaikyuImage);
        _haikyu = new JButton(scaledHaikyuIcon);
        _haikyu.setBounds(655, 435, 225, 150);  // x, y, width, height
        _haikyu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("Haikyuu: The Battle of the Garbage Dump"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "Haikyuu: The Battle of the Garbage Dump is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent Haikyuu: The Battle of the Garbage Dump?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent Haikyuu: The Battle of the Garbage Dump: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 150; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem haikyu = new RentalItem("Haikyuu: The Battle of the Garbage Dump", pricePerDay, rentalDays, _haikyu);
                    rentalItems.add(haikyu);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented Haikyuu: The Battle of the Garbage Dump for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_haikyu);
        
        // Configuration for Loki Things button
        ImageIcon grandmaIcon = new ImageIcon("image/grandma.jpg");  // Ensure the path and extension are correct
        Image scaledGrandmaImage = grandmaIcon.getImage().getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledGrandmaIcon = new ImageIcon(scaledGrandmaImage);
        _grandma = new JButton(scaledGrandmaIcon);
        _grandma.setBounds(930, 435, 225, 150);  // x, y, width, height
        _grandma.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if Peaky Blinders is already rented
            boolean isAlreadyRented = rentalItems.stream()
                    .anyMatch(item -> item.getTitle().equals("How To Make Millions Before Grandma Dies"));
    
            if (isAlreadyRented) {
                JOptionPane.showMessageDialog(null,
                        "How To Make Millions Before Grandma Dies is already rented. You cannot rent it again.",
                        "Already Rented",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            // Show first dialog to ask if user wants to rent
            String[] rentOptions = {"Rent", "No"};
            int rentChoice = JOptionPane.showOptionDialog(null,
                    "Do you wish to rent How To Make Millions Before Grandma Dies?",
                    "Rent Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    rentOptions,
                    null);
    
            if (rentChoice == JOptionPane.YES_OPTION) {
                // User chose to rent, now ask for rental duration
                String daysInput = JOptionPane.showInputDialog(null,
                        "Enter the number of days you want to rent How To Make Millions Before Grandma Dies: " + "\n3 days, 7 days, 30 days",
                        "Rental Duration",
                        JOptionPane.PLAIN_MESSAGE);
    
                // Process user input based on predefined durations
                int rentalDays = 0;
                double pricePerDay = 150; // Example price per day
    
                if (daysInput != null) {
                    switch (daysInput.toLowerCase()) {
                        case "3":
                            rentalDays = 3;
                            break;
                        case "7":
                            rentalDays = 7;
                            break;
                        case "30":
                            rentalDays = 30;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Invalid input for rental duration. Please enter either '3 days', '7 days', or '30 days'.",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                    }
    
                    // Calculate total cost based on rental days and price per day
                    double totalCost = rentalDays * pricePerDay;
    
                    // Instantiate RentalItem and add it to rentalItems
                    RentalItem grandma = new RentalItem("How To Make Millions Before Grandma Dies", pricePerDay, rentalDays, _grandma);
                    rentalItems.add(grandma);
    
                    JOptionPane.showMessageDialog(null,
                            "You rented How To Make Millions Before Grandma Dies for " + rentalDays + " days. Total cost: ₱" + totalCost
                                    + "\nIt will be added to My List Tab.");
                }
            } else if (rentChoice == JOptionPane.NO_OPTION) {
                // User chose not to rent
                return;
            }
            }
        });
        HomePageUI.add(_grandma);
    
        
        // Ensure the panel is updated after adding the buttons
        HomePageUI.revalidate();
        HomePageUI.repaint();
    }
}


