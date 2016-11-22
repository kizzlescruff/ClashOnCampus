package com.example.wilki.clashoncampus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wilki on 21/11/2016.
 */

public class Quest implements Serializable {
    private String name;
    private int rating;
    private float distance;
    private String description;
    private List<QuestStop> questStops;

    public Quest(String name, int rating, float distance, String description){
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public int getRating(){
        return rating;
    }

    public float getDistance(){
        return distance;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return name + " " + distance + " " + rating;
    }
}
