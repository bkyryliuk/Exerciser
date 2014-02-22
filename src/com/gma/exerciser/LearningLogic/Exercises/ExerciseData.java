package com.gma.exerciser.LearningLogic.Exercises;

import android.content.res.Resources;

import com.gma.exerciser.DAO.DictionaryDAO;
import com.gma.exerciser.Pojo.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ExerciseData {
 private ArrayList<Word> words = new ArrayList<Word>(); // can be from 1 to N
 private Map<Word, Integer> errors;
 private int points;
 private int timeToAnswer;
 protected DictionaryDAO dao;
 protected int length = 1; //number of words used in exercise
 
	public ExerciseData() {
		this.dao = new DictionaryDAO();
	}
	
	public ExerciseData(DictionaryDAO dao) {
		this.dao = dao;
	}
 
	public abstract ExerciseData getNextExerciseData(Resources resources);

	/**
	 * 
	 * @param choice
	 */
	
	public abstract String getExerciseType(); // TODO change type to enum

	public String getCorrectAnswer() {
		throw new UnsupportedOperationException();
	}
 
	public ArrayList<Word> getWords() {
		return words;
	}
	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
	public int getLength() {
		return length;
	}
	public Map<Word, Integer> getErrors() {
		if ((this.errors == null) || (this.errors.size() == 0)) {
			this.errors = new HashMap<Word, Integer>();
			for (Word w: this.words)
				this.errors.put(w, 0);
		}
		return this.errors;
	}
	public void setErrors(Map<Word, Integer> errors) {
		this.errors = errors;
	}
	public ArrayList<String> getWordsTranslations(){
		ArrayList<String> translations = new ArrayList<String>();
		for(Word w: this.words)
			translations.add(w.getMain_translation());
		return translations;
	}
	
	public ArrayList<String> getWordsNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(Word w: this.words)
			names.add(w.getWord());
		return names;
	}
	
	public int getNumberOfErrors(){
		int sum = 0;
		for(Entry<Word, Integer> e : this.errors.entrySet())
			sum+=e.getValue();
		return sum;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTimeToAnswer() {
		return timeToAnswer;
	}
	public void setTimeToAnswer(int timeToAnswer) {
		this.timeToAnswer = timeToAnswer;
	}
	
	public Word getWordObjectFromTranslation(String translation) {
		for(Word w: this.words) 
			if (w.getMain_translation().equals(translation)) 
				return w;
		return null;
	}
	
	public Word getWordObjectFromName(String wordName) {
		for(Word w: this.words)
			if (w.getWord().equals(wordName)) 
				return w;
		return null;
	}
	
	public void removeWordByName(String wordName) {
		Word w = this.getWordObjectFromName(wordName);
		this.words.remove(w);
	}
	
	public boolean isTranslationCorrect(String wordName, String translation) {
		for(Word w: this.words)
			if (w.getWord().equals(wordName)) 
				if(w.getMain_translation().equals(translation))
					return true;
		return false;
	}
	
	public void incrementErrorForWord(Word w) {
		int e = this.errors.get(w)+1;
		this.errors.put(w, e);
	}
}
	
	