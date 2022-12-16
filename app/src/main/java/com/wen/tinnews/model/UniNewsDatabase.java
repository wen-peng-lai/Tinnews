package com.wen.tinnews.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class UniNewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
