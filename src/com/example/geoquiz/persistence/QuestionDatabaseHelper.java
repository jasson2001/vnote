package com.example.geoquiz.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionDatabaseHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "cal.sqlite";
	public QuestionDatabaseHelper(Context context) {
		super(context,DB_NAME , null, 1);
		// 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 
		db.execSQL("create table question(" + 
		           " _id integer primary key autoincrement, voiceUrl varchar(100), imageUrl varchar(100),  " +
		           "tip varchar(100), id_optionals varchar(100), id_answer varchar(100) )"  
				);
	
		db.execSQL("create table learndetail(" + 
		           " questionID varchar(100), learnDate date,  " +
		           "totol integer, failure integer )"  
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
