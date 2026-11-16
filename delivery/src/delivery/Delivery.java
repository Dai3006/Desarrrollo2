package delivery;

import View.ChargeableView;
import javax.swing.SwingUtilities;

public class Delivery {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChargeableView().setVisible(true));
    }
}
