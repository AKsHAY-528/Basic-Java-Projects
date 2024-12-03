import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWindow extends JFrame {
    private JLabel heading;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private Font font = new Font("Serif", Font.BOLD, 48);
    private Font dateFont = new Font("Serif", Font.PLAIN, 24);

    MyWindow() {
        super.setTitle("My Digital Clock");
        super.setSize(650, 400);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        this.startClock();
        super.setVisible(true);
    }

    public void createGUI() {
        // Heading with a new elegant font and shadow effect
        heading = new JLabel("Digital Clock", SwingConstants.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD, 50));
        heading.setForeground(Color.WHITE);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        heading.setOpaque(false);

        // Date and Time Labels with rounded borders and shadows
        dateLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel.setFont(dateFont);
        timeLabel.setFont(font);

        // Date in gold color and time in cyan for visibility
        dateLabel.setForeground(new Color(255, 215, 0));
        timeLabel.setForeground(new Color(0, 255, 255));

        // Custom panel for the main layout with a gradient and rounded borders
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 51, 102);
                Color color2 = new Color(0, 153, 204);
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
            }
        };

        // Panel design and layout
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.add(heading);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing
        mainPanel.add(dateLabel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(timeLabel);

        // Shadow effect panel for a layered appearance
        JPanel shadowPanel = new JPanel();
        shadowPanel.setLayout(new BorderLayout());
        shadowPanel.setBackground(Color.DARK_GRAY);
        shadowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        shadowPanel.add(mainPanel);

        // Set background and border layout for the window content
        this.setContentPane(shadowPanel);
    }

    public void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Format for 12-hour time format with AM/PM
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a"); // 12-hour format
                String date = dateFormat.format(new Date());
                String time = timeFormat.format(new Date());
                dateLabel.setText(date);
                timeLabel.setText(time);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new MyWindow();
    }
}

