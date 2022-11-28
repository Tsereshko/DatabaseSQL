package com.example.databasesql.model;

import java.io.Serial;
import java.io.Serializable;

public class Furniture implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double price;
    private String material;
    private double weight;
    private double width;
    private double length;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Furniture(int id, String name, double price, String material, double weight, double width, double length) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.material = material;
        this.weight = weight;
        this.width = width;
        this.length = length;
    }
    public Furniture(String name, double price, String material, double weight, double width, double length) {
        //this.id = id;
        this.name = name;
        this.price = price;
        this.material = material;
        this.weight = weight;
        this.width = width;
        this.length = length;
    }
    public int getId(){return id;}
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMaterial() {
        return material;
    }

    public double getWeight() {
        return weight;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }


    @Override
    public String toString() {
        return id + " " + name + " "  + price + " " + material + " " + weight + " " + width + " " + length;
    }
}
