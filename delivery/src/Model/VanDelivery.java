package Model;

public class VanDelivery extends Delivery {
    public VanDelivery(String trackingId, double distanceKm) {
        super(trackingId, distanceKm);
    }
    @Override
    protected double ratePerKm() { return 14.0; }
    @Override
    public String label() { return "Envío en furgoneta"; }
}
