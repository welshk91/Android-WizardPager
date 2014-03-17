package com.example.wizardexample;

import com.example.android.wizardpager.wizard.WizardActivity;
import com.example.android.wizardpager.wizard.model.AbstractWizardModel;
import com.example.android.wizardpager.wizard.ui.StepPagerStrip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;

public class MainActivity extends WizardActivity {

	private ViewPager mPager;

	private StepPagerStrip mStepPagerStrip;

	private Button mNextButton;
	private Button mPrevButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPager = (ViewPager) findViewById(R.id.pager);
		mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
		mNextButton = (Button) findViewById(R.id.next_button);
		mPrevButton = (Button) findViewById(R.id.prev_button);
		setControls(mPager, mStepPagerStrip, mNextButton, mPrevButton);
	}

	@Override
	public AbstractWizardModel onCreateModel() {
		return new SandwichWizardModel(this);
	}

	@Override
	public void onSubmit() {
		DialogFragment dialog = new DialogFragment() {

			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				return new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.submit_confirm_title)
						.setMessage(R.string.submit_confirm_message)
						.setPositiveButton(R.string.submit_confirm_button, null)
						.setNegativeButton(android.R.string.cancel, null)
						.create();
			}
		};
		dialog.show(getSupportFragmentManager(), "place_order_dialog");
	}

    @Override
    public boolean useBackForPrevious() {
        return true;
    }

}