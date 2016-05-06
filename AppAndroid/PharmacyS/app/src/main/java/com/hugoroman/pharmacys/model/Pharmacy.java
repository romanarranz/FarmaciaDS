package com.hugoroman.pharmacys.model;


public class Pharmacy {

    private String cif;
    private String name;
    private Integer phoneNumber;
    private String description;
    private Integer startSchedule;
    private Integer endSchedule;

    public Pharmacy(String cif, String name, Integer phoneNumber, String description, Integer startSchedule, Integer endSchedule) {

        this.cif = cif;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.startSchedule = startSchedule;
        this.endSchedule = endSchedule;
    }

    public String getCif() {
        return cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStartSchedule() {
        return startSchedule;
    }

    public Integer getEndSchedule() {
        return endSchedule;
    }
}
