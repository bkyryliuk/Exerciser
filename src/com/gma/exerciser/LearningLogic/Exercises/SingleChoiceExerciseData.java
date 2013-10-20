package com.gma.exerciser.LearningLogic.Exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.res.Resources;
import android.util.Log;

import com.gma.exerciser.R;
import com.gma.exerciser.DAO.DictionaryDAO;
import com.gma.exerciser.Pojo.Word;

public class SingleChoiceExerciseData extends ExerciseData {
	// are in the same order as words
	private ArrayList<Word> variants;
	private int correctAnswerIndex;

	public SingleChoiceExerciseData() {
		this.length = 1;
	}

	// for testing only
	public SingleChoiceExerciseData(DictionaryDAO dao) {
		super(dao);
		this.length = 1;
	}

	public ArrayList<Word> getVariants() {
		return variants;
	}

	public void setVariants(ArrayList<Word> variants) {
		this.variants = variants;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public void setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex;
	}

	public ArrayList<String> getVariantsMainTranslations() {
		ArrayList<String> translation = new ArrayList<String>();
		for (Word w : this.variants)
			translation.add(w.getMain_translation());
		return translation;
	}

	public String getWordTranslation(int i) {
		return variants.get(i).getMain_translation();
	}

	public String getWordName(int i) {
		return variants.get(i).getWord();
	}

	@Override
	public ExerciseData getNextExerciseData(Resources resources) {
		ExerciseData ex;
		int variantsNumberFromConfig = resources
				.getInteger(R.integer.exercise_single_choice_variants_number);
		ArrayList<Word> words = this.dao
				.getCurrentWordChunk(variantsNumberFromConfig);
		// if one word is there, then no sense to show the exercise
		if (words.size() < 2)
			Log.v("SingleChoiceImpl",
					"Current dictionary has no words to learn");

		ex = new SingleChoiceExerciseData();

		Random r = new Random();
		Word answer = words.get(r.nextInt(words.size()));
		ex.setWords(new ArrayList<Word>());
		ex.getWords().add(answer);
		ex.setErrors(new HashMap<Word, Integer>());
		words.remove(answer);

		// getting random variants for word
		ArrayList<Word> variants = new ArrayList<Word>();
		// can be less than variants number from config, if dictionary is almost
		// learnt
		int wordsNumber = words.size();
		while (words.size()>0) { 
			Word word = words.get(r.nextInt(words.size()));
			variants.add(word);
			words.remove(word);
		}
		int correctAnswerIndex = r.nextInt(variants.size());
		variants.add(correctAnswerIndex, answer);

		((SingleChoiceExerciseData) ex).setVariants(variants);
		((SingleChoiceExerciseData) ex)
				.setCorrectAnswerIndex(correctAnswerIndex);

		return ex;
	}

	@Override
	public String getExerciseType() {
		// TODO Auto-generated method stub
		return "SingleChoiceExercise";
	}
}
