import views.ClientView;

public class viewDebugger {
    public static void main (String args[]) {
        ClientView view = new ClientView();
        view.setVisible(true);
        view.showArguements();
    }
}