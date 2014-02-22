package com.gma.exerciser.DAO;

import android.content.Context;

import com.gma.exerciser.LearningLogic.Exercises.ExerciseData;
import com.gma.exerciser.Pojo.ExerciseLog;
import com.gma.exerciser.Pojo.Word;

import java.util.Calendar;
import java.util.Date;

public class StatisticsDAO {
	private DBAccess db;

	public StatisticsDAO(Context applcationContext){
		db = new DBAccess(applcationContext);
	}
	/**
	 * 
	 * @param lesson_id
	 */
	public ExerciseLog[] getExerciseLogsForLesson(int lesson_id) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param start
	 * @param end
	 */
	public ExerciseLog[] getExerciseLogsForDateRange(Date start, Date end) {
		throw new UnsupportedOperationException();
	}
	
	public void submitExerciseResult(ExerciseData data, String exerciseType, int dictId, int lessonId){
		Calendar c = Calendar.getInstance();
		long time = c.getTimeInMillis();

		for(Word w: data.getWords()){
			ExerciseLog log = new ExerciseLog();
			log.setTime(time);
			log.setDictId(dictId);
			log.setExerciseType(exerciseType);
			log.setLesson(lessonId);
			int errors = 0;
			if(data.getErrors().containsKey(w))
				errors = data.getErrors().get(w);
			log.setNumberErrors(errors);
			log.setPoints(data.getPoints());
			log.setTimeToAnswer(data.getTimeToAnswer());
			log.setWordId(w.getId());
			
			db.writeExerciseLog(log);
		}
		
	}
	

}