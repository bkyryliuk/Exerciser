package com.gma.exerciser.LearningLogic;

import com.gma.exerciser.MainMenu;
import com.gma.exerciser.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class FinishLessonDialog extends DialogFragment implements OnClickListener  {
    private Button close;
    private Activity activity;

    public FinishLessonDialog(Activity activity) {
    	super();
    	this.activity = activity;
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_finish_lesson, container);
        close = (Button) view.findViewById(R.id.fuck_button);
        getDialog().setTitle(getResources().getString(R.string.lessonFinishedDialogHeader));
        close.setOnClickListener(this);

        
        return view;
    }

	@Override
	public void onClick(View v) {
		Intent myIntent = new Intent(activity, MainMenu.class);
		this.startActivity(myIntent);
		this.dismiss();
	}
}
