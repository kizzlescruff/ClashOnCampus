package com.example.wilki.clashoncampus;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wilki on 21/11/2016.
 */

public class QuestHolder implements Serializable{

    private String[] questNames = {"Across Vecna's Village", "Spirit of the Tower",
            "The Blaze of Winterbole Forest", "The Fury of Sigil", "The Tortured Dragon Bribe",
            "The Hungering Elf from Beyond", "The Terrible Shield Rage",
            "Corellon's Blacksmith", "Myconid of the Careless Tomb", "Above the Kingdom"};
    private List<Quest> questList;

    public QuestHolder(){
        Random rand = new Random();
        questList = new ArrayList<>();
        for(String name : questNames){
            questList.add(new Quest(name, rand.nextInt(5)+1, rand.nextFloat() * 5, name));
        }
    }

    public List<Quest> getQuestList(){
        Log.d("Number of quests", Integer.toString(questList.size()));
        return questList;
    }
}
