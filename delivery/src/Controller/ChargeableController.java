package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChargeableController {
    private final List<Chargeable> elementos = new ArrayList<>();

    public void registrarBikeDelivery(String trackingId, double distanceKm) {
        elementos.add(new BikeDelivery(trackingId, distanceKm));
    }

    public void registrarVanDelivery(String trackingId, double distanceKm) {
        elementos.add(new VanDelivery(trackingId, distanceKm));
    }

    public void registrarPackaging(int items, double chargePerItem) {
        elementos.add(new Packaging(items, chargePerItem));
    }

    public double calcularTotalGeneral() {
        double total = 0;
        for (Chargeable elemento : elementos) {
            total += elemento.calculateCharge();
        }
        return total;
    }

    public List<Chargeable> getElementos() {
        return Collections.unmodifiableList(elementos);
    }
}
