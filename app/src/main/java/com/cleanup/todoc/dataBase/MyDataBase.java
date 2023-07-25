package com.cleanup.todoc.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.DAO.ProjectDao;
import com.cleanup.todoc.DAO.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;

import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class}, version = 3, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    //get DataBase instance
    private static MyDataBase INSTANCE;
    public static MyDataBase getInstance(Context context) {

        //ajouter la liste des projects a la création de la BDD
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDataBase.class, "MyDatabase.db")
                    .addCallback(addProject())
                    .build();
        }
        return INSTANCE;
    }

    private static Callback addProject(){
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(() -> {
                    INSTANCE.projectDao().insertProject(new Project(1L, "Projet Tartampion", 0xFFEADAD1));
                    INSTANCE.projectDao().insertProject(new Project(2L, "Projet Lucidia", 0xFFB4CDBA));
                    INSTANCE.projectDao().insertProject(new Project(3L, "Projet Circus", 0xFFA3CED2));
                });
            }
        };
    }
}


