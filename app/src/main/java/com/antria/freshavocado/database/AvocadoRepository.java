package com.antria.freshavocado.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class AvocadoRepository {

    private AvocadoDao mAvocadoDao;
    private LiveData<List<Avocado>> mAllAvocados;

    AvocadoRepository(Application application) {
        AvocadoDatabase db = AvocadoDatabase.getDatabase(application);
        mAvocadoDao = db.avocadoDao();
        mAllAvocados = mAvocadoDao.getAll();
    }

    LiveData<List<Avocado>> getAllAvocados() {
        return mAllAvocados;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Avocado avocado) {
        AvocadoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mAvocadoDao.insert(avocado);
            }
        });
    }
}