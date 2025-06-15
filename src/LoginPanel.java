import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lblStatus;

    public LoginPanel() {
        setTitle("Giriş");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tfUsername = new JTextField();
        pfPassword = new JPasswordField();
        JButton btnLogin = new JButton("Giriş Yap");
        lblStatus = new JLabel("");

        panel.add(new JLabel("Kullanıcı Adı:"));
        panel.add(tfUsername);
        panel.add(new JLabel("Şifre:"));
        panel.add(pfPassword);
        panel.add(new JLabel(""));
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnLogin.addActionListener(this::handleLogin);
    }

    private void handleLogin(ActionEvent e) {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        if (username.equals("admin") && password.equals("147258")) {
            lblStatus.setText("Giriş başarılı!");
            openDashboard();
        } else {
            lblStatus.setText("Hatalı kullanıcı adı veya şifre.");
        }
    }

    private void openDashboard() {
        SwingUtilities.invokeLater(() -> {
            FreelancerDashboard dashboard = new FreelancerDashboard();
            dashboard.setVisible(true);
        });
        dispose();
    }
}