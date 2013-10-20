package com.gma.exerciser;

import java.util.Map;
import java.util.Random;

import com.gma.exerciser.LearningLogic.FinishLessonDialog;
import com.gma.exerciser.LearningLogic.Exercises.SingleChoiceExerciseData;
import com.gma.exerciser.Pojo.Word;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleChoiceExercise extends ExerciseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState, R.layout.activity_single_choice_exercise);
		
		ListView listView = (ListView) findViewById(R.id.asc_list_choices);
		this.data = (SingleChoiceExerciseData) this.logic.getNextExercise(new SingleChoiceExerciseData());
		if(this.data!=null) {
			((SingleChoiceExerciseData)this.data).getVariants();
		} else {
			// TODO
			return;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1,
		  ((SingleChoiceExerciseData)this.data).getVariantsMainTranslations());
		listView.setAdapter(adapter); 
		listView.setOnItemClickListener(this);
		
		TextView word = (TextView) findViewById(R.id.asc_text_header);
		word.setText(this.data.getWords().get(0).getWord());
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(((SingleChoiceExerciseData)this.data).getVariantsMainTranslations().get(arg2).equals(
				this.data.getWords().get(0).getMain_translation())) {
			Toast.makeText(getApplicationContext(), "Clicked on item "+
				((SingleChoiceExerciseData)this.data).getVariants().get(arg2) , Toast.LENGTH_SHORT).show();
			
			this.logic.submitExercise(data);
			this.bar.setProgress(this.logic.getExerciseNumber());
			if(this.logic.isLessonFinished()){
				FragmentManager fm = this.getSupportFragmentManager();
				FinishLessonDialog finishLessonDialog = new FinishLessonDialog(this);
				finishLessonDialog.show(fm, "fragment_edit_name");
			} else {
				//launch next exercise
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
			}
		}
		else{
			Toast.makeText(getApplicationContext(), "Wrong choice", Toast.LENGTH_LONG).show();
			Map<Word, Integer> errors = data.getErrors();
			// word with selected translation
			errors.put(((SingleChoiceExerciseData)this.data).getVariants().get(arg2), 1);
			// original word
			errors.put(this.data.getWords().get(0), 1);
			
			this.logic.handleError();
		}
		this.setUpExerciseStats();
	}
}
