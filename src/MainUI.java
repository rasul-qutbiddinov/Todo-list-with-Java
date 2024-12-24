import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    private TaskManager taskManager;

    public MainUI() {
        taskManager = new TaskManager();
        initializeUI(); // GUI'ni yaratish
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Task Management Application");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Orqa fon gradient
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(173, 216, 230)); // SkyBlue fon
        frame.add(panel);

        // Title va Description inputlari uchun panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(new Color(240, 248, 255)); // AliceBlue fon
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(inputPanel);

        JLabel titleLabel = new JLabel("Task Title:");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(titleLabel);

        JTextField titleText = new JTextField(20);
        titleText.setPreferredSize(new Dimension(250, 30));
        titleText.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        titleText.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(titleText);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(descLabel);

        JTextField descText = new JTextField(20);
        descText.setPreferredSize(new Dimension(250, 30));
        descText.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        descText.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(descText);

        // Add Task tugmasi
        JButton addButton = new JButton("Add Task");
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setPreferredSize(new Dimension(150, 35));
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inputPanel.add(addButton);

        // Tasklar ro'yxatini ko'rsatish uchun
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        panel.add(scrollPane);

        // Task qo'shish
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                String desc = descText.getText();
                Task task = new Task(title, desc, "No Date", "Medium");
                taskManager.addTask(task);
                updateTaskList(taskPanel);

                // Input maydonlarini tozalash
                titleText.setText("");
                descText.setText("");
            }
        });

        frame.setVisible(true);
    }

    // Task ro'yxatini yangilash
    private void updateTaskList(JPanel taskPanel) {
        taskPanel.removeAll(); // Old tasklarni olib tashlash
        for (Task task : taskManager.getTasks()) {
            JPanel taskItemPanel = new JPanel();
            taskItemPanel.setLayout(new BoxLayout(taskItemPanel, BoxLayout.X_AXIS));
            taskItemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            taskItemPanel.setBackground(new Color(255, 255, 255)); // Task kartochka fon

            taskItemPanel.setPreferredSize(new Dimension(650, 60));

            JLabel taskLabel = new JLabel(
                    "<html><b>" + task.getTitle() + "</b><br>" + task.getDescription() + "</html>");
            taskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            taskLabel.setPreferredSize(new Dimension(400, 50));
            taskItemPanel.add(taskLabel);

            // Edit tugmasi
            JButton editButton = new JButton("Edit");
            editButton.setIcon(new ImageIcon("edit.png")); // Ikona qo'shish
            editButton.setBackground(new Color(255, 223, 0)); // Oltin rang
            editButton.setForeground(Color.BLACK);
            editButton.setFont(new Font("Arial", Font.PLAIN, 12));
            editButton.setPreferredSize(new Dimension(120, 35));
            editButton.setFocusPainted(false);
            editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newTitle = JOptionPane.showInputDialog("Enter new title:", task.getTitle());
                    if (newTitle != null && !newTitle.isEmpty()) {
                        task.setTitle(newTitle);
                        updateTaskList(taskPanel);
                    }
                }
            });
            taskItemPanel.add(editButton);

            // Remove tugmasi
            JButton removeButton = new JButton("Remove");
            removeButton.setIcon(new ImageIcon("delete.png")); // Ikona qo'shish
            removeButton.setBackground(new Color(255, 69, 0)); // Qizil rang
            removeButton.setForeground(Color.WHITE);
            removeButton.setFont(new Font("Arial", Font.PLAIN, 12));
            removeButton.setPreferredSize(new Dimension(120, 35));
            removeButton.setFocusPainted(false);
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    taskManager.removeTask(task);
                    updateTaskList(taskPanel);
                }
            });
            taskItemPanel.add(removeButton);

            // Hover effekti qo'shish
            editButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    editButton.setBackground(new Color(255, 215, 0)); // Hover effekti
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    editButton.setBackground(new Color(255, 223, 0)); // Normal
                }
            });

            removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    removeButton.setBackground(new Color(255, 99, 71)); // Hover effekti
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    removeButton.setBackground(new Color(255, 69, 0)); // Normal
                }
            });

            taskPanel.add(taskItemPanel);
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
