import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BusinessCardView extends JFrame {

    public BusinessCardView(String name, String profession, String education, String certifications,
                            String experience, String email, String phone, List<Project> projects) {
        setTitle("Kartvizit Önizleme");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        // Profil bilgileri
        JTextArea taInfo = new JTextArea();
        taInfo.setText("Ad Soyad: " + name + "\nMeslek: " + profession + "\nEğitim: " + education +
                "\nSertifikalar: " + certifications + "\nDeneyim: " + experience +
                "\nE-posta: " + email + "\nTelefon: " + phone);
        taInfo.setEditable(false);
        taInfo.setFont(new Font("Arial", Font.PLAIN, 13));
        taInfo.setBorder(BorderFactory.createTitledBorder("Kullanıcı Bilgileri"));
        mainPanel.add(taInfo);

        // Projeler
        for (Project p : projects) {
            JPanel card = new JPanel(new BorderLayout(5, 5));
            card.setBorder(BorderFactory.createTitledBorder(p.getProjectName() + " - " + p.getRole()));

            JTextArea taDesc = new JTextArea(p.getDescription() + "\nTarih: " + p.getStartDate() + " - " + p.getEndDate());
            taDesc.setWrapStyleWord(true);
            taDesc.setLineWrap(true);
            taDesc.setEditable(false);
            card.add(taDesc, BorderLayout.CENTER);

            mainPanel.add(card);
            mainPanel.add(Box.createVerticalStrut(5));
        }

        // Simülasyon butonu
        JButton btnShare = new JButton("Kartviziti Paylaş (Simülasyon)");
        btnShare.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Kartvizit Paylaşma Butonu Temsilidir.", "Bilgi", JOptionPane.INFORMATION_MESSAGE));

        add(scrollPane, BorderLayout.CENTER);
        add(btnShare, BorderLayout.SOUTH);
    }
}