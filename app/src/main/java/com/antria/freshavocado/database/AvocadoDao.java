package com.antria.freshavocado.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AvocadoDao {

    @Query("SELECT * FROM avocado")
    LiveData<List<Avocado>> getAll();

    @Insert
    void insert(Avocado avocado);

    @Delete
    void delete(Avocado avocado);
}
