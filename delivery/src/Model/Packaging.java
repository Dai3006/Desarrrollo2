package Model;

public class Packaging implements Chargeable {
    private final int items;
    private final double chargePerItem;

    public Packaging(int items, double chargePerItem) {
        this.items = items;
        this.chargePerItem = chargePerItem;
    }
    public int getItems() { return items; }
    public double getChargePerItem() { return chargePerItem; }

    @Override
    public String label() { return "Empaque"; }

    @Override
    public double calculateCharge() { return items * chargePerItem; }
}
