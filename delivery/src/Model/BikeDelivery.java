package Model;

public class BikeDelivery extends Delivery {
    public BikeDelivery(String trackingId, double distanceKm) {
        super(trackingId, distanceKm);
    }
    @Override
    protected double ratePerKm() { return 8.0; }
    @Override
    public String label() { return "Envío en bicicleta"; }
}
