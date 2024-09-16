package org.dam.models;

import java.time.LocalDate;

public class DinoModels {
    private int id;
    private String name;
    private String feeding;
    private String attack;
    private boolean flying;
    private double weigth;
    private LocalDate date;

    public DinoModels() {
        this.id = 0;
        this.name = "";
        this.feeding = "";
        this.attack = "";
        this.flying = false;
        this.weigth = 0.0;
        this.date = LocalDate.now();
    }

    public DinoModels(int id, String name, String feeding, String attack, boolean flying, double weigth, LocalDate date) {
        this.id = id;
        this.name = name;
        this.feeding = feeding;
        this.attack = attack;
        this.flying = flying;
        this.weigth = weigth;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
