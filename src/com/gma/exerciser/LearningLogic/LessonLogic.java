package com.gma.exerciser.LearningLogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;

import com.gma.exerciser.DAO.DictionaryDAO;
import com.gma.exerciser.DAO.StatisticsDAO;
import com.gma.exerciser.LearningLogic.Exercises.ExerciseData;
import com.gma.exerciser.R;

public class LessonLogic {

	// activity should set up corresponding implementation
	private ExerciseData exercise;
	private DictionaryDAO dictDao;
	private StatisticsDAO statDao;
	private Resources resources;
	private Context applicationContext;
	private SharedPreferences preferences;
	private Editor editor;

	private static LessonLogic instance;

	protected LessonLogic(Context applicationContext, SharedPreferences preferences, Resources resources) {
		this.applicationContext = applicationContext;
		this.preferences = preferences;
		this.resources = resources;

		this.dictDao = new DictionaryDAO();
		this.statDao = new StatisticsDAO(applicationContext);

		this.editor = this.preferences.edit();
	}

	public static LessonLogic getInstance(Context applicationContext, SharedPreferences preferences, Resources resources) {
		if (instance == null) {
			instance = new LessonLogic(applicationContext, preferences, resources);
		}
		return instance;
	}

	public void startLesson() {
		this.setExerciseNumber(0);
		this.setCorrectAnswers(0);
		this.setIncorrectAnswers(0);
	}

	public void handleError() {
		this.setIncorrectAnswers(this.getInCorrectAnswers() + 1);
	}

	// returns exercise data
	// accepts empty exercise data object, is used only to call the method
	// getNextExerciseData on it
	// in future should be nicer way to implement that
	public ExerciseData getNextExercise(ExerciseData exerciseData) {
		// counter starts from 1 to number of exercises
		int counter = this.getExerciseNumber();
		counter++;
		this.setExerciseNumber(counter);

		this.exercise = exerciseData.getNextExerciseData(this.resources);

		return this.exercise;
	}

	public boolean isLessonFinished() {
		int counter = this.getExerciseNumber();
		int num_exercises = this.resources.getInteger(
				R.integer.exercise_in_lesson);
		counter++;

		return counter >= num_exercises + 1;
	}

	public void submitExercise(ExerciseData data) {

		this.setCorrectAnswers(this.getCorrectAnswers() + data.getLength());
		this.statDao.submitExerciseResult(data, data.getExerciseType(), this.getDictId(),
				this.getLessonId());
	}

	// TODO
	public void failExercise(ExerciseData data) {

	}

	public int getExerciseNumber() {
		return this.preferences.getInt("exercise_number", 0);
	}

	private void setExerciseNumber(int exerciseNumber) {
		this.editor.putInt("exercise_number", exerciseNumber);
		this.editor.commit();
		this.editor = this.preferences.edit();
	}

	public int getCorrectAnswers() {
		return this.preferences.getInt("correct_answers", 0);
	}

	private void setCorrectAnswers(int correctAnswers) {
		this.editor.putInt("correct_answers", correctAnswers);
		this.editor.commit();
		this.editor = this.preferences.edit();
	}

	public int getInCorrectAnswers() {
		return this.preferences.getInt("incorrect_answers", 0);
	}

	private void setIncorrectAnswers(int incorrectAnswers) {
		this.editor.putInt("incorrect_answers", incorrectAnswers);
		this.editor.commit();
		this.editor = this.preferences.edit();
	}

	public int getDictId() {
		return this.preferences.getInt("dict_id", 0);
	}

	private void setDictID(int dictId) {
		this.editor.putInt("dict_id", dictId);
		this.editor.commit();
		this.editor = this.preferences.edit();
	}

	public int getLessonId() {
		return this.preferences.getInt("lesson_id", 0);
	}

	private void incrementLessonId() {
		this.editor.putInt("lesson_id", this.getLessonId() + 1);
		this.editor.commit();
		this.editor = this.preferences.edit();
	}
}
