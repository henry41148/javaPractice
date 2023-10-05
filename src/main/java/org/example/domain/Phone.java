package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "color", nullable = false, length = 128)
    private String color;

    @Column(name = "country", nullable = false, length = 128)
    private String country;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public Phone(int id, String name, String color, String country, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.country = country;
        this.price = price;
        this.quantity =  quantity ;
    }

    @Override
    public String toString() {
        return "|\n"+ "id = " + id + ", name = " + name + ", price = "
                + price + ", color = " + color + ", country = " + country + ", quantity = " + quantity+"|";
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
