package com.antria.freshavocado.database;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Avocado {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public Date purchaseDate;
    public Ripeness ripeness;

    public Avocado(Ripeness ripeness) {
        Calendar calendar = Calendar.getInstance();
        purchaseDate = calendar.getTime();
        this.ripeness = ripeness;
    }
}
