package com.gma.exerciser;

import com.gma.exerciser.LearningLogic.LessonLogic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.Random;

public class MainMenu extends FragmentActivity implements OnClickListener {
	private LessonLogic logic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		this.logic = LessonLogic.getInstance(this.getApplicationContext(),
				this.getSharedPreferences("lesson", 0),
				this.getResources());
		
		ImageView img1 = (ImageView) findViewById(R.id.start_menu_dict);
		img1.setOnClickListener(this);
		
		ImageView img2 = (ImageView) findViewById(R.id.start_menu_help);
		img2.setOnClickListener(this);

		ImageView img3 = (ImageView) findViewById(R.id.start_menu_settings);
		img3.setOnClickListener(this);

		ImageView img4 = (ImageView) findViewById(R.id.start_menu_start);
		img4.setOnClickListener(this);

		ImageView img5 = (ImageView) findViewById(R.id.start_menu_stat);
		img5.setOnClickListener(this);

		ImageView img6 = (ImageView) findViewById(R.id.start_menu_test);
		img6.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_exercise, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
        switch (v.getId()){
			case R.id.start_menu_start:
				this.logic.startLesson();
                Random r = new Random();
                switch (r.nextInt(2)){
                    case 1:
                        Intent myIntent = new Intent(this, SingleChoiceExercise.class);
                        this.startActivity(myIntent);
                        break;
                    case 0:
                        myIntent = new Intent(this, MatchExercise.class);
                        this.startActivity(myIntent);
                        break;
                }
			break;
			case R.id.start_menu_dict:
				this.logic.startLesson();
			break;
			case R.id.start_menu_stat:
				this.logic.startLesson();
			break;
			case R.id.start_menu_test:
				this.logic.startLesson();
			break;
			case R.id.start_menu_settings:
				this.logic.startLesson();
			break;
			case R.id.start_menu_help:
				this.logic.startLesson();
			break;
		}		
	}
}