package com.gma.exerciser.Pojo;

import java.util.Date;

public class ExerciseLog {

	private int id;
	private int lesson;
	private String exerciseType;
	private int numberErrors;
	private int timeToAnswer;
	private long time;
	private int points;
	private int wordId;
	private int dictId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLesson() {
		return lesson;
	}
	public void setLesson(int lesson) {
		this.lesson = lesson;
	}
	public String getExercise_type() {
		return exerciseType;
	}
	public void setExerciseType(String exercise_type) {
		this.exerciseType = exercise_type;
	}
	public int getNumberErrors() {
		return numberErrors;
	}
	public void setNumberErrors(int numberErrors) {
		this.numberErrors = numberErrors;
	}
	public int getTimeToAnswer() {
		return timeToAnswer;
	}
	public void setTimeToAnswer(int timeToAnswer) {
		this.timeToAnswer = timeToAnswer;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public int getDictId() {
		return dictId;
	}
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}

}