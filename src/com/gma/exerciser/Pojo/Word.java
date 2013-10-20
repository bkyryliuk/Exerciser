package com.gma.exerciser.Pojo;

public class Word {

	private int id;
	private int dictId;
	private String word;
	private int state;
	private String main_translation;
	private String[] translations;
	private String example;
	private String example_translation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dict_id) {
		this.dictId = dict_id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMain_translation() {
		return main_translation;
	}
	public void setMain_translation(String main_translation) {
		this.main_translation = main_translation;
	}
	public String[] getTranslations() {
		return translations;
	}
	public void setTranslations(String[] translations) {
		this.translations = translations;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getExample_translation() {
		return example_translation;
	}
	public void setExample_translation(String example_translation) {
		this.example_translation = example_translation;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}