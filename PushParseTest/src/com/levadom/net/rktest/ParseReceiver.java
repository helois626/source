package com.levadom.net.rktest;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;

import com.levadom.net.R;

public class ParseReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {
		// TODO Auto-generated method stub
		// String action = intent.getAction();
		// String channel = intent.getExtras().getString("com.parse.Channel");
		try {
			final JSONObject json = new JSONObject(intent.getExtras()
					.getString("com.parse.Data"));
			Log.v("received", json.toString());
			Handler uiHandler = new Handler();
			uiHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {

						// MainActivity.noti_text.setText(json.getString("text"));

						SpannableString contentTx = new SpannableString(json
								.getString("text"));
						contentTx.setSpan(new UnderlineSpan(), 0,
								contentTx.length(), 0);
						makeNotification(context, json.getString("text"),
								intent);
						AppConstants.PUSH_LINK_URL = json.getString("link");
						MainActivity.noti_text.setText(contentTx);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			});

			// String st = json.getString("text");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void makeNotification(Context context, String message,
			Intent intent) {
		Intent mIntent = new Intent(context, MainActivity.class);

		mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mIntent.putExtras(intent.getExtras());
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, mIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(context.getString(R.string.app_name))
				.setContentText(message)
				.setTicker(context.getString(R.string.app_name))
				.setAutoCancel(true)
				.setStyle(
						new NotificationCompat.BigTextStyle().bigText(message))
				.setDefaults(Notification.DEFAULT_ALL)
				.setContentIntent(pIntent)
				.setVibrate(new long[] { 1000, 1000, 1000, 1000 });

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, mBuilder.build());

	}

	/*
	 * Check app is on foreground or not
	 */
	class ForegroundCheckTask extends AsyncTask<Context, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Context... params) {
			final Context context = params[0].getApplicationContext();
			return isAppOnForeground(context);
		}

		private boolean isAppOnForeground(Context context) {
			ActivityManager activityManager = (ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningAppProcessInfo> appProcesses = activityManager
					.getRunningAppProcesses();
			if (appProcesses == null) {
				return false;
			}
			final String packageName = context.getPackageName();
			for (RunningAppProcessInfo appProcess : appProcesses) {
				if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
						&& appProcess.processName.equals(packageName)) {
					return true;
				}
			}
			return false;
		}
	}
}
