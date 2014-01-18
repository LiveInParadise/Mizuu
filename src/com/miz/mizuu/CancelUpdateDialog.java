package com.miz.mizuu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.miz.service.UpdateMovieService;
import com.miz.service.UpdateShowsService;

import android.widget.Toast;

public class CancelUpdateDialog extends Activity {

	public static final int MOVIE = 1, TVSHOWS = 2;
	private int type = MOVIE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		type = getIntent().getExtras().getInt("type", MOVIE);

		setContentView(R.layout.empty_layout);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.areYouSure))
		.setTitle(getString(R.string.stringCancelUpdate))
		.setCancelable(false)
		.setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {	
				if (type == MOVIE) {
					stopService(new Intent(CancelUpdateDialog.this, UpdateMovieService.class));
				} else {
					stopService(new Intent(CancelUpdateDialog.this, UpdateShowsService.class));
				}
				Toast.makeText(CancelUpdateDialog.this, getString(R.string.stringUpdateCancelled), Toast.LENGTH_LONG).show();
				finish();
				return;
			}
		})
		.setNegativeButton(getString(android.R.string.no), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				finish();
			}
		})
		.create().show();
	}
}