package com.antria.freshavocado.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AvocadoViewModel extends AndroidViewModel {

    private AvocadoRepository mRepository;

    private LiveData<List<Avocado>> mAllAvocados;

    public AvocadoViewModel (Application application) {
        super(application);
        mRepository = new AvocadoRepository(application);
        mAllAvocados = mRepository.getAllAvocados();
    }

    public LiveData<List<Avocado>> getAllAvocados() { return mAllAvocados; }

    public void insert(Avocado avocado) { mRepository.insert(avocado); }
}