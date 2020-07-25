package com.antria.freshavocado.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters({DateConverter.class, RipenessConverter.class})
@Database(entities = {Avocado.class}, version = 1, exportSchema = false)
public abstract class AvocadoDatabase extends RoomDatabase {

    public abstract AvocadoDao avocadoDao();

    private static volatile AvocadoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AvocadoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AvocadoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AvocadoDatabase.class, "avocado_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                AvocadoDao dao = INSTANCE.avocadoDao();
                Avocado avocado = new Avocado(Ripeness.RIPE);
                dao.insert(avocado);
            });
        }
    };
}
