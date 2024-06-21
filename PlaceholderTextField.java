import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
        if (getText().isEmpty() && !isFocusOwner()) {
            placeholderLabel.setVisible(true);
            placeholderLabel.setFont(getFont().deriveFont(Font.PLAIN, 14));
            placeholderLabel.setBounds(10, 10, getWidth() - 20, getHeight() - 20);
        } else {
            placeholderLabel.setVisible(false);
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
