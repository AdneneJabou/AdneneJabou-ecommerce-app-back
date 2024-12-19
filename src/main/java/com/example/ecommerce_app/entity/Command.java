package com.example.ecommerce_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "commands")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idClient;
    private String idProduct;
    private String date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override


    public String toString() {
        return "Command{" +
                "id=" + id +
                ", idClient='" + idClient + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
