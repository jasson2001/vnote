package com.example.geoquiz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.geoquiz.persistence.Question;
import com.example.geoquiz.persistence.QuestionDAO;

public class AddActivity extends Activity {
	
	private Button mSaveButton;
	private Button mReturnButton;
	
	private Uri fileUri = null;
	
	private QuestionDAO questionDAO = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		questionDAO = new QuestionDAO(this);
		
		mSaveButton = (Button)findViewById(R.id.save_button);
		mSaveButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {
				// 保存
				 
				 ((EditText)findViewById(R.id.answerEditControl)).getText();
				//
				takeCamera();
				long rowID = questionDAO.save(
						new Question(
								((EditText)findViewById(R.id.questionEditControl)).getText().toString(),
								fileUri.getPath()));
				if (rowID > -1){
					Toast.makeText(null, "Question saved to:\n" +
		        			String.valueOf(rowID), Toast.LENGTH_LONG).show();
				}
			}
		});
		mReturnButton = (Button)findViewById(R.id.return_button);
		mReturnButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View arg0) {
				
			}
		});
		
		// 拍照，开始录入
		takeCamera();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void takeCamera(){


		 // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    this.fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, this.fileUri); // set the image file name

	    // start the image capture Intent
	    
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);		
	}
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	
	

	
	public static final int MEDIA_TYPE_IMAGE = 1;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		try{
			if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
		        if (resultCode == RESULT_OK) {
		            // Image captured and saved to fileUri specified in the Intent
		           
			        	Toast.makeText(this, "Image saved to:\n" +
			        			this.fileUri.getPath(), Toast.LENGTH_LONG).show();
		           
		        	Log.d("MyCameraApp", "Image View!");
		        	((ImageView)findViewById(R.id.imageView1)).setImageURI(this.fileUri);
		            
		            
		        } else if (resultCode == RESULT_CANCELED) {
		            // User cancelled the image capture
		        } else {
		            // Image capture failed, advise user
		        }
		    }
	
		  
		}   
		 catch(Exception ignore){
		    Toast.makeText(this, "Excetion:" + ignore.getMessage(), Toast.LENGTH_LONG).show();
	       	Log.d("MyCameraApp", ignore.getMessage());
	     
		 }
	}

	public static final int MEDIA_TYPE_VIDEO = 2;

	/** Create a file Uri for saving an image or video */
	private  Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private  File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.
		String state = Environment.getExternalStorageState();
		
		if(!state.equals(Environment.MEDIA_MOUNTED)){
		    Log.d("MyCameraApp", "SDCard is not mounted");
		    Toast.makeText(this, "SDCard is not mounted:"+ state, Toast.LENGTH_LONG).show();
		    //return null;
	    }
		
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	           Log.d("MyCameraApp", "failed to create directory");
	           Toast.makeText(this, "failed to create directory", Toast.LENGTH_LONG).show();
	           //return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
}
