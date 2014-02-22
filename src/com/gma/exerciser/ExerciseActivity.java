package com.gma.exerciser;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gma.exerciser.LearningLogic.Exercises.ExerciseData;
import com.gma.exerciser.LearningLogic.LessonLogic;

public abstract class ExerciseActivity extends FragmentActivity implements OnItemClickListener {

	protected LessonLogic logic;
	protected ExerciseData data;
	protected ProgressBar bar;

	protected void onCreate(Bundle savedInstanceState, int layout) {
		super.onCreate(savedInstanceState);
		setContentView(layout);
		this.logic = LessonLogic.getInstance(this.getApplicationContext(),
				this.getSharedPreferences("lesson", 0),
				this.getResources());
		
		bar = (ProgressBar) this.findViewById(R.id.stat_lesson_progress);
		bar.setMax(this.getResources().getInteger(R.integer.exercise_in_lesson));
		bar.setProgress(this.logic.getExerciseNumber());
		
		this.setUpExerciseStats();
	}

	protected void setUpExerciseStats(){
    	TextView correct_answers = (TextView) this.findViewById(R.id.stat_correct_value);
    	correct_answers.setText(Integer.toString(this.logic.getCorrectAnswers()));
    	
    	TextView incorrect_answers = (TextView) this.findViewById(R.id.stat_incorrect_value);
    	incorrect_answers.setText(Integer.toString(this.logic.getInCorrectAnswers()));
    
		final TextView timer = (TextView) this.findViewById(R.id.stat_timer);
		int exercise_time = this.getResources().getInteger(R.integer.exercise_time) * 1000;
		new CountDownTimer(exercise_time, 1000) {

		     public void onTick(long millisUntilFinished) {
		         timer.setText(Long.toString((millisUntilFinished / 1000)));
		     }

		     public void onFinish() {
		    	 //time out, load next exercise
		         logic.failExercise(data);
		     }
		  }.start();
    }
}