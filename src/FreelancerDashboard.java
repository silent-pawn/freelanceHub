import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FreelancerDashboard extends JFrame {
    private List<Project> projects = new ArrayList<>();
    private JPanel projectPanel;

    // Kullanıcı bilgileri
    private JTextField tfName, tfProfession, tfEducation, tfCertifications, tfExperience, tfEmail, tfPhone;

    public FreelancerDashboard() {
        setTitle("Admin Paneli");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));


        JPanel infoPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Profil Bilgileri"));

        tfName = new JTextField();
        tfProfession = new JTextField();
        tfEducation = new JTextField();
        tfCertifications = new JTextField();
        tfExperience = new JTextField();
        tfEmail = new JTextField();
        tfPhone = new JTextField();

        infoPanel.add(new JLabel("Ad Soyad:"));
        infoPanel.add(tfName);
        infoPanel.add(new JLabel("Meslek:"));
        infoPanel.add(tfProfession);
        infoPanel.add(new JLabel("Eğitim:"));
        infoPanel.add(tfEducation);
        infoPanel.add(new JLabel("Sertifikalar:"));
        infoPanel.add(tfCertifications);
        infoPanel.add(new JLabel("Deneyimler:"));
        infoPanel.add(tfExperience);
        infoPanel.add(new JLabel("E-posta:"));
        infoPanel.add(tfEmail);
        infoPanel.add(new JLabel("Telefon:"));
        infoPanel.add(tfPhone);

        add(infoPanel, BorderLayout.NORTH);

        // Projeleri Gösteren Panel
        projectPanel = new JPanel();
        projectPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(projectPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Projeler"));
        add(scrollPane, BorderLayout.CENTER);

        // Alt Butonlar
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAdd = new JButton("Proje Ekle");
        JButton btnCard = new JButton("Kartvizit Oluştur");

        btnAdd.addActionListener(e -> addProject());
        btnCard.addActionListener(e -> showBusinessCard());

        bottomPanel.add(btnAdd);
        bottomPanel.add(btnCard);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshProjectList();
    }

    private void addProject() {
        ProjectFormDialog dialog = new ProjectFormDialog(this, null);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            projects.add(dialog.getProject());
            refreshProjectList();
        }
    }

    private void editProject(Project project) {
        ProjectFormDialog dialog = new ProjectFormDialog(this, project);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            refreshProjectList();
        }
    }

    private void refreshProjectList() {
        projectPanel.removeAll();

        for (Project p : projects) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(Color.WHITE);

            JLabel lblName = new JLabel(p.getProjectName() + " - " + p.getRole());
            JTextArea taInfo = new JTextArea(p.getDescription() + "\n" +
                    "Tarih: " + p.getStartDate() + " - " + p.getEndDate());
            taInfo.setEditable(false);
            taInfo.setLineWrap(true);
            taInfo.setWrapStyleWord(true);

            JButton btnEdit = new JButton("Düzenle");
            JButton btnDelete = new JButton("Sil");

            btnEdit.addActionListener(e -> editProject(p));
            btnDelete.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(this, "Bu projeyi silmek istiyor musunuz?",
                        "Onay", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    projects.remove(p);
                    refreshProjectList();
                }
            });

            JPanel btnPanel = new JPanel();
            btnPanel.add(btnEdit);
            btnPanel.add(btnDelete);

            card.add(lblName, BorderLayout.NORTH);
            card.add(taInfo, BorderLayout.CENTER);
            card.add(btnPanel, BorderLayout.SOUTH);

            projectPanel.add(card);
        }

        projectPanel.revalidate();
        projectPanel.repaint();
    }

    private void showBusinessCard() {
        String name = tfName.getText();
        String profession = tfProfession.getText();
        String education = tfEducation.getText();
        String certifications = tfCertifications.getText();
        String experience = tfExperience.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();

        BusinessCardView view = new BusinessCardView(name, profession, education, certifications,
                experience, email, phone, projects);
        view.setVisible(true);
    }
}