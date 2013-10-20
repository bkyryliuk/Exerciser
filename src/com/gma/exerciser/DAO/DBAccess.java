package com.gma.exerciser.DAO;

import com.gma.exerciser.Pojo.ExerciseLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAccess extends SQLiteOpenHelper {

    public DBAccess(Context context) {
		super(context, "StatDB", null, DATABASE_VERSION);
	}

	private static final int DATABASE_VERSION = 1;
    private static final String EXERCISELOG_TABLE_NAME = "exerciseLog";
    private static final String EXERCISELOG_TABLE_CREATE =
                "CREATE TABLE " + EXERCISELOG_TABLE_NAME + " (" +
                		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +  "lessonId INTEGER, " +
                		"dictId INTEGER, " + "numberErrors INTEGER,  " + "wordId INTEGER, " + 
                		"timeToAnswer INTEGER, " + "points INTEGER, " + "exerciseType TEXT, " + 
                		"day DATE " + ");";
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(EXERCISELOG_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void writeExerciseLog(ExerciseLog log){
		SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		   cv.put("lessonId", log.getLesson());
		   cv.put("exerciseType", log.getExercise_type());
		   cv.put("dictId", log.getDictId());
		   cv.put("numberErrors", log.getNumberErrors());
		   cv.put("points", log.getPoints());
		   cv.put("timeToAnswer", log.getTimeToAnswer());
		   cv.put("wordId", log.getWordId());
		   cv.put("day", log.getTime());
		   db.insert(EXERCISELOG_TABLE_NAME, "lessonId", cv);
	}
}