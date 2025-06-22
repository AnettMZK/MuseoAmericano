package com.mycompany.interfazmuseo;

import javafx.beans.property.*;

public class Hall {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final StringProperty museumType = new SimpleStringProperty();

    public Hall(int id, String name, String description, double price, String museumType) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.price.set(price);
        this.museumType.set(museumType);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getDescription() { return description.get(); }
    public StringProperty descriptionProperty() { return description; }

    public double getPrice() { return price.get(); }
    public DoubleProperty priceProperty() { return price; }

    public String getMuseumType() { return museumType.get(); }
    public StringProperty museumTypeProperty() { return museumType; }

    @Override
    public String toString() {
        return name.get(); 
    }
}
