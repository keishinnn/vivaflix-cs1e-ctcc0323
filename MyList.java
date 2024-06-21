import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyList extends JFrame {
    private JPanel myListPanel;
    private ImageIcon myListIcon, profileIcon;
    private JLabel myListLabel, titleLabel, titleLabelTwo, listLabel;
    private JButton _homeButton, _tvshowsButton, _moviesButton, profileButton;
    private ArrayList<RentalItem> rentalItems;
    private ArrayList<User> users = new ArrayList<>();
    
    public MyList(ArrayList<RentalItem> rentalItems, ArrayList<User> users) {
        this.rentalItems = rentalItems;
        this.users = users;
        myListPanel = new JPanel(null);
        setContentPane(myListPanel);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VivaFlix");
        setLocationRelativeTo(null);
        
        initializeComponents();
        addBackgroundImage();
        setVisible(true);
    }

    private void initializeComponents() {
        listLabel = new JLabel("Rented Items");
        listLabel.setBounds(100,110,500,30);
        listLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        listLabel.setForeground(Color.WHITE);
        myListPanel.add(listLabel);
        
        
        titleLabel = new JLabel("viva");
        titleLabel.setBounds(56, 25, 100, 30);
        titleLabel.setForeground(new Color(0, 191, 255));
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        myListPanel.add(titleLabel);

        titleLabelTwo = new JLabel("flìx");
        titleLabelTwo.setBounds(133, 25, 100, 30);
        titleLabelTwo.setForeground(new Color(242, 133, 0));
        titleLabelTwo.setFont(new Font("Netflix-sans", Font.BOLD, 40));
        myListPanel.add(titleLabelTwo);

        _homeButton = new JButton("Home");
        _homeButton.setBounds(223, 25, 80, 40);
        styleButton(_homeButton);
        _homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePageUI(rentalItems, users);
                dispose();
            }
        });
        myListPanel.add(_homeButton);

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
        myListPanel.add(_tvshowsButton);

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
        myListPanel.add(_moviesButton);

        UnderlinedButton _myListButton = new UnderlinedButton("My List");
        _myListButton.setBounds(553, 25, 90, 40);
        styleButton(_myListButton);
        myListPanel.add(_myListButton);

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

        displayRentedItems();
    }

    private void displayRentedItems() {
        int yOffset = 175;
        int xOffset = 100;
        final int MAX_XOFFSET = 930;
        final int BUTTON_WIDTH = 225;
        final int BUTTON_HEIGHT = 150;
        final int X_INCREMENT = 275;
        final int Y_INCREMENT = 260;

        for (RentalItem item : rentalItems) {
            JButton movieButton = item.getMovieButton();

            // Check if adding another button will exceed the allowed space
            if (xOffset > MAX_XOFFSET) {
                JOptionPane.showMessageDialog(null,
                        "Maximum items reached. No more items can be added to My List.",
                        "Limit Reached",
                        JOptionPane.WARNING_MESSAGE);
                break;
            }

            movieButton.setBounds(xOffset, yOffset, BUTTON_WIDTH, BUTTON_HEIGHT);
            movieButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show dialog with 3 options: Watch, Cancel, Time Duration
                    String[] options = {"Watch", "Cancel", "Time Duration"};
                    int choice = JOptionPane.showOptionDialog(null,
                            "Choose an option for " + item.getTitle(),
                            "Movie Options",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]);

                    switch (choice) {
                        case 0:
                            // Open a new frame for playing the video
                            playVideo(item.getTitle());
                            break;
                        case 1:
                            // Remove the movie from the rentalItems list and update the panel
                            rentalItems.remove(item);
                            myListPanel.remove(movieButton);
                            myListPanel.revalidate();
                            myListPanel.repaint();
                            JOptionPane.showMessageDialog(null,
                                    "You have cancelled the movie " + item.getTitle() + ".",
                                    "Cancel",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 2:
                            // Display the remaining time in HH:MM:SS format
                            showTimeRemaining(item.getRentalDays());
                            break;
                    }
                }
            });

            myListPanel.add(movieButton);

            // Update xOffset and yOffset for the next button
            xOffset += X_INCREMENT;
            if (xOffset > MAX_XOFFSET) {
                xOffset = 100;
                yOffset += Y_INCREMENT;
            }
        }
    
        myListPanel.revalidate();
        myListPanel.repaint();
    }

    
    private void playVideo(String title) {
        JFrame videoFrame = new JFrame("Playing: " + title);
        videoFrame.setSize(1000, 550);
        videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        videoFrame.setLocationRelativeTo(null);
        
        JFXPanel jfxPanel = new JFXPanel();
        videoFrame.add(jfxPanel);
        videoFrame.setVisible(true);
        
        Platform.runLater(() -> {
            // Find the video file matching the title
            String videoFileName = findVideoFile(title);
            if (videoFileName == null) {
                JOptionPane.showMessageDialog(null,
                        "Video file not found for " + title,
                        "File Not Found",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            File videoFile = new File(videoFileName);
            Media media = new Media(videoFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            StackPane root = new StackPane();
            root.getChildren().add(mediaView);
            Scene scene = new Scene(root, 800, 600);
            jfxPanel.setScene(scene);
            mediaPlayer.play();
        });
    }


    
    private String findVideoFile(String title) {
        // Assuming your videos are stored in "Vivaflix/videos"
        File videoDir = new File("videos");
        if (!videoDir.isDirectory()) {
            System.err.println("Directory 'Vivaflix/videos' not found.");
            return null;
        }
        
        String[] videoFiles = videoDir.list();
        if (videoFiles == null) {
            System.err.println("No files found in 'Vivaflix/videos' directory.");
            return null;
        }
        
        // Iterate through video files and find a match for the title
        for (String fileName : videoFiles) {
            if (fileName.toLowerCase().equals(title.toLowerCase() + ".mp4")) {
                String filePath = videoDir.getPath() + File.separator + fileName;
                File videoFile = new File(filePath);
                if (videoFile.exists() && videoFile.isFile()) {
                    return filePath;
                } else {
                    System.err.println("File not found or is not a regular file: " + filePath);
                    return null;
                }
            }
        }
        
        System.err.println("Video file not found for title: " + title);
        return null; // File not found
    }



    private void showTimeRemaining(int rentalDays) {
        long totalSeconds = rentalDays * 24 * 60 * 60; // Convert days to seconds
    
        JFrame countdownFrame = new JFrame("Time Remaining");
        countdownFrame.setSize(300, 200);
        countdownFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        countdownFrame.setLocationRelativeTo(null);
        
        JLabel countdownLabel = new JLabel();
        countdownLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countdownFrame.add(countdownLabel);
        countdownFrame.setVisible(true);
    
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            long remainingSeconds = totalSeconds; // Remaining seconds starts with total seconds
    
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (remainingSeconds >= 0) {
                        long hours = remainingSeconds / 3600;
                        long minutes = (remainingSeconds % 3600) / 60;
                        long seconds = remainingSeconds % 60;
                        countdownLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                        remainingSeconds--;
                    } else {
                        timer.cancel(); // Stop the timer when time is up
                        countdownFrame.dispose();
                    }
                });
            }
        };
    
        timer.scheduleAtFixedRate(timerTask, 0, 1000); // Schedule the timer task to run every second
    }



    private void addBackgroundImage() {
        myListIcon = new ImageIcon("image/homepage.jpg");
        myListLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = myListIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        myListLabel.setBounds(0, 0, getWidth(), getHeight());
        myListPanel.add(myListLabel);

        myListPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                myListLabel.setSize(myListPanel.getSize());
                myListLabel.repaint();
            }
        });
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 0, 0));
        button.setFont(new Font("Netflix-sans", Font.BOLD, 16));
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}

