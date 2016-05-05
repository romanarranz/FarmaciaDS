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

    public void setCif(String cif) {
        this.cif = cif;
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

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartSchedule() {
        return startSchedule;
    }

    public void setStartSchedule(Integer startSchedule) {
        this.startSchedule = startSchedule;
    }

    public Integer getEndSchedule() {
        return endSchedule;
    }

    public void setEndSchedule(Integer endSchedule) {
        this.endSchedule = endSchedule;
    }
}
