package com.antria.freshavocado.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class RipenessConverter {
    @TypeConverter
    public static Ripeness toRipeness(Integer ripenessInt){
        return ripenessInt == null ? null: Ripeness.valueOf(ripenessInt);
    }

    @TypeConverter
    public static int fromRipeness(Ripeness ripeness){
        return ripeness == null ? null : ripeness.getValue();
    }
}
