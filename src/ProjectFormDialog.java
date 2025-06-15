import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProjectFormDialog extends JDialog {
    private JTextField tfProjectName, tfRole, tfStartDate, tfEndDate;
    private JTextArea taDescription;
    private boolean saved = false;
    private Project project;

    public ProjectFormDialog(Frame owner, Project projectToEdit) {
        super(owner, true);
        setTitle(projectToEdit == null ? "Yeni Proje" : "Projeyi Düzenle");
        setSize(400, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        tfProjectName = new JTextField();
        tfRole = new JTextField();
        taDescription = new JTextArea(3, 20);
        tfStartDate = new JTextField(" ");
        tfEndDate = new JTextField(" ");

        formPanel.add(new JLabel("Proje Adı:"));
        formPanel.add(tfProjectName);
        formPanel.add(new JLabel("Rolünüz:"));
        formPanel.add(tfRole);
        formPanel.add(new JLabel("Açıklama:"));
        formPanel.add(new JScrollPane(taDescription));
        formPanel.add(new JLabel("Başlangıç Tarihi:"));
        formPanel.add(tfStartDate);
        formPanel.add(new JLabel("Bitiş Tarihi:"));
        formPanel.add(tfEndDate);

        if (projectToEdit != null) {
            tfProjectName.setText(projectToEdit.getProjectName());
            tfRole.setText(projectToEdit.getRole());
            taDescription.setText(projectToEdit.getDescription());
            tfStartDate.setText(projectToEdit.getStartDate());
            tfEndDate.setText(projectToEdit.getEndDate());
        }

        JButton btnSave = new JButton("Kaydet");
        btnSave.addActionListener(this::saveProject);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSave);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void saveProject(ActionEvent e) {
        String name = tfProjectName.getText().trim();
        String role = tfRole.getText().trim();

        if (name.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Proje adı ve rol zorunlu!", "Hata", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (project == null) {
            project = new Project(name, role, taDescription.getText().trim(),
                    tfStartDate.getText().trim(), tfEndDate.getText().trim());
        } else {
            project.setProjectName(name);
            project.setRole(role);
            project.setDescription(taDescription.getText().trim());
            project.setStartDate(tfStartDate.getText().trim());
            project.setEndDate(tfEndDate.getText().trim());
        }

        saved = true;
        dispose();
    }

    public boolean isSaved() {
        return saved;
    }

    public Project getProject() {
        return project;
    }
}