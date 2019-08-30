package com.example.recylcler;

import java.util.ArrayList;
import java.util.List;

public class Details {
     private String name;
     private String occupation;
     private static int count = 0;

    public Details(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Details.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }



}
