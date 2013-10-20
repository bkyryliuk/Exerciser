package com.gma.exerciser.LearningLogic.Exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.content.res.Resources;
import android.util.Log;

import com.gma.exerciser.R;
import com.gma.exerciser.DAO.DictionaryDAO;
import com.gma.exerciser.Pojo.Word;


public class MatchExerciseData extends ExerciseData {
	private ArrayList<String> firstList;
	private ArrayList<String> secondList;
	
	public MatchExerciseData(){
		this.length = 6; 
		// TODO get from shared preferences
	}
	
	// for testing only
	public MatchExerciseData(DictionaryDAO dao){
		super(dao);
		this.length = 6;
	}
	
	public ArrayList<String> getOriginalWords() {
		return firstList;
	}
	public void setOriginalWords(ArrayList<String> first_list) {
		this.firstList = first_list;
	}
	public ArrayList<String> getTranslations() {
		return secondList;
	}
	public void setTranslations(ArrayList<String> second_list) {
		this.secondList = second_list;
	}
	
	@Override
	public ExerciseData getNextExerciseData(Resources resources) {

		int wordsNumber = resources.getInteger(R.integer.exercise_match_words_number);
		ArrayList<Word> wordChunk = this.dao.getCurrentWordChunk(wordsNumber);
		
		if(wordChunk.size() < wordsNumber)
			Log.v("MatchingChoiceImpl", "Current dictionary has no words to learn");
			
		MatchExerciseData ex = new MatchExerciseData();
		
		Random r = new Random();
		ArrayList<Word> words = new ArrayList<Word>();
		
		for(int i=0; i<wordsNumber;i++){  //TODO get variants size from preferences
			Word word = wordChunk.get(r.nextInt(wordChunk.size()));
			words.add(word);
			wordChunk.remove(word);
		}
		
		ArrayList<String> originals = new ArrayList<String>();
		ArrayList<String> translations = new ArrayList<String>();
	
		ArrayList<Word> wordsCopy = (ArrayList<Word>) words.clone();
		for(Word w: words){
			int i = r.nextInt(wordsCopy.size());
			originals.add(w.getWord());
			translations.add(wordsCopy.get(i).getMain_translation());			
			wordsCopy.remove(i);
		}
		
		ex.setWords(words);;
		ex.setErrors(new HashMap<Word,Integer>());
		
		((MatchExerciseData) ex).setOriginalWords(originals);
		((MatchExerciseData) ex).setTranslations(translations);
		
		return ex;
	}

	@Override
	public String getExerciseType() {
		// TODO change type to enum
		return "MatchingExercise";
	}
	
}
