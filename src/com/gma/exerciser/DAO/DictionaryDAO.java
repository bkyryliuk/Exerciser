package com.gma.exerciser.DAO;

import java.util.ArrayList;

import com.gma.exerciser.Pojo.Dictionary;
import com.gma.exerciser.Pojo.Word;

public class DictionaryDAO {

	public ArrayList<Word> getCurrentWordChunk(int size) {
		//TODO get frompreferences currect dict
		ArrayList<Word> words = new ArrayList<Word>();
		for(int i=0;i<size;i++){
			Word word = new Word();
			word.setWord("word"+i);
			word.setMain_translation("translation"+i);
			words.add(word);
		}
		return words;
	}

	public ArrayList<Word> getAllWords(Dictionary dict) {
		return null;
	}

	public ArrayList<Word> getLearnedWords(Dictionary dict) {
		return null;
	}
	
	public ArrayList<Dictionary> getAllDictionaries(){
		return null;
	}
	
	//TODO keep data in shared preferences to get it
	public Dictionary getCurrentDictionary(){
		return null;
	}


}
