package com.gma.exerciser.Pojo;

import java.util.Date;

public class WordStats {

	private int id;
	private int word_id;
	private int dict_id;
	private int user_comlpexity;
	private int calc_complexity;
	private int guessed;
	private int errors;
	private int points;
	private boolean learned;
	private Date date_learned;
	private int times_forgot;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWord_id() {
		return word_id;
	}
	public void setWord_id(int word_id) {
		this.word_id = word_id;
	}
	public int getDict_id() {
		return dict_id;
	}
	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}
	public int getUser_comlpexity() {
		return user_comlpexity;
	}
	public void setUser_comlpexity(int user_comlpexity) {
		this.user_comlpexity = user_comlpexity;
	}
	public int getCalc_complexity() {
		return calc_complexity;
	}
	public void setCalc_complexity(int calc_complexity) {
		this.calc_complexity = calc_complexity;
	}
	public int getGuessed() {
		return guessed;
	}
	public void setGuessed(int guessed) {
		this.guessed = guessed;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public boolean isLearned() {
		return learned;
	}
	public void setLearned(boolean learned) {
		this.learned = learned;
	}
	public Date getDate_learned() {
		return date_learned;
	}
	public void setDate_learned(Date date_learned) {
		this.date_learned = date_learned;
	}
	public int getTimes_forgot() {
		return times_forgot;
	}
	public void setTimes_forgot(int times_forgot) {
		this.times_forgot = times_forgot;
	}

	
}