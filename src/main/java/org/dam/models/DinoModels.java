package org.dam.models;

import java.time.LocalDate;

public class DinoModels {
    private int id;
    private String name;
    private int feeding_id;
    private String attack;
    private boolean flying;
    private double weigth;
    private LocalDate date;

    public DinoModels() {

    }



    public DinoModels(int id, String name, int feeding_id, String attack, boolean flying, double weigth, LocalDate date) {
        this.id = id;
        this.name = name;
        this.feeding_id = feeding_id;
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

    public int getFeeding_id() {
        return feeding_id;
    }

    public void setFeeding_id(int feeding_id) {
        this.feeding_id = feeding_id;
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
