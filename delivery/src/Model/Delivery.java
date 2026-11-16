package Model;

public abstract class Delivery implements Chargeable {
    private final String trackingId;
    private final double distanceKm;

    public Delivery(String trackingId, double distanceKm) {
        this.trackingId = trackingId;
        this.distanceKm = distanceKm;
    }

    public String getTrackingId() { return trackingId; }
    public double getDistanceKm() { return distanceKm; }

    public double baseCharge() { return 40.0; }

    protected abstract double ratePerKm();

    @Override
    public String label() { return "Envío a domicilio"; }

    @Override
    public final double calculateCharge() {
        return baseCharge() + distanceKm * ratePerKm();
    }
}
