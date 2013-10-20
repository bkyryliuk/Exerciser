package com.gma.exerciser;

import java.util.ArrayList;
import java.util.Random;

import com.gma.exerciser.LearningLogic.FinishLessonDialog;
import com.gma.exerciser.LearningLogic.LessonLogic;
import com.gma.exerciser.LearningLogic.Exercises.MatchExerciseData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MatchExercise extends ExerciseActivity {

	private LessonLogic logic;
	private MatchExerciseData data;
	private ProgressBar bar;
	private int selectedWordNamePosition = -1;
	private int selectedTranslationPosition = -1;
	
	private ArrayList<String> wordNames;
	private ArrayList<String> translations;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState, R.layout.activity_match_exercise);
		
		ListView listView1 = (ListView) findViewById(R.id.match_list_originals);
		ListView listView2 = (ListView) findViewById(R.id.match_list_translations);
		
		this.data = (MatchExerciseData) this.logic.getNextExercise(new MatchExerciseData());
		if(this.data==null){
		  // TODO
		  return;
		}
		
		this.wordNames = this.data.getOriginalWords();
		this.translations = this.data.getTranslations();
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, android.R.id.text1, this.wordNames);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, android.R.id.text1, this.translations);
		
		listView1.setAdapter(adapter1); 
		listView2.setAdapter(adapter2);
		listView1.setOnItemClickListener(this);
		listView2.setOnItemClickListener(this);
		listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// is answer correct
		ListView listView1 = (ListView) findViewById(R.id.match_list_originals);
		ListView listView2 = (ListView) findViewById(R.id.match_list_translations);
		String selectedWordName = null;
		String selectedTranslation = null;
		
		if(listView1.equals(arg0)) {
			this.selectedWordNamePosition = position;
			listView1.setItemChecked(position, true);
		}
		if(listView2.equals(arg0)) {
			this.selectedTranslationPosition = position;
			listView2.setItemChecked(position, true);
		}
		
		// in case word or translation were not selected do nothing
		if ((selectedWordNamePosition < 0) || (selectedTranslationPosition < 0))
			return;

		selectedWordName = this.wordNames.get(this.selectedWordNamePosition);
		selectedTranslation = this.translations.get(this.selectedTranslationPosition);

	
		if(this.data.isTranslationCorrect(selectedWordName, selectedTranslation)) {
			Toast.makeText(getApplicationContext(), "Clicked on item "+this.wordNames.get(selectedWordNamePosition) , Toast.LENGTH_SHORT).show();
			
			this.logic.submitExercise(data);
			this.bar.setProgress(this.logic.getExerciseNumber());
			
			listView1.setItemChecked(selectedWordNamePosition, false);
			listView2.setItemChecked(selectedTranslationPosition, false);
			this.wordNames.remove(selectedWordNamePosition);
			this.translations.remove(selectedTranslationPosition);
			this.selectedTranslationPosition = -1;
			this.selectedWordNamePosition = -1;
			((ArrayAdapter<String>) listView1.getAdapter()).notifyDataSetChanged();
			((ArrayAdapter<String>) listView2.getAdapter()).notifyDataSetChanged();
			if(this.wordNames.size()!=0)
				return;
			
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
		else {
			Toast.makeText(getApplicationContext(), "Wrong choice", Toast.LENGTH_LONG).show();
			this.data.incrementErrorForWord(this.data.getWordObjectFromName(selectedWordName));
			this.data.incrementErrorForWord(this.data.getWordObjectFromTranslation(selectedTranslation));
			this.logic.handleError();
			
			// release selections in the listviews
			listView1.setItemChecked(selectedWordNamePosition, false);
			listView2.setItemChecked(selectedTranslationPosition, false);
			this.selectedTranslationPosition = -1;
			this.selectedWordNamePosition = -1;
		}
		this.setUpExerciseStats();
	}
}
