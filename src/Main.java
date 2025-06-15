public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginPanel login = new LoginPanel();
            login.setVisible(true);
        });
    }
}