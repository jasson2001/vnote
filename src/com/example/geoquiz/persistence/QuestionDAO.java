package com.example.geoquiz.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class QuestionDAO {
	protected SQLiteDatabase database;
	private QuestionDatabaseHelper dbHelper;
	private Context mContext;
	
		
	public QuestionDAO(Context context){
		this.mContext = context;
		dbHelper = new QuestionDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
	}
	
	public long save(Question question){
		ContentValues values = new ContentValues();
		values.put("voiceUrl", question.getQuestion());
		values.put("imageUrl", question.getImageUrl());
		values.put("tip", question.getQuestion());
		//values.put("id_optionals", question.getId_optionals());
		
		return database.insert("question", null, values);
		
		
		
	}

}
