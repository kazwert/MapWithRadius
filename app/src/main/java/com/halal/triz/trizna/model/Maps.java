package com.halal.triz.trizna.model;

/**
 * Created by User on 22-May-17.
 */

public class Maps {

    private int id;
    private String name;
    private double lat;
    private double lng;
    private String img;
    private String desc;

    public Maps(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public Maps(int id, String name, double lat, double lng, String img, String desc) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.img = img;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
