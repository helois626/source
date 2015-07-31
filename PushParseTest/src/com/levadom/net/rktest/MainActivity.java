package com.levadom.net.rktest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.levadom.net.R;
import com.parse.PushService;


public class MainActivity extends ActionBarActivity {

	LinearLayout scrlContainer;
	
	
	String CHANNEL1 = "Group1";
	String CHANNEL2 = "Group2";
	MainActivity context;
	public ProgressDialog dlg;
	CheckBox cb_pGroup1, cb_pGroup2;
	public static TextView noti_text;
	HorizontalScrollView menuScroll;
	
	DataHandler parsedDataHandler;
	
	int rssCellColor = Color.rgb(172, 38, 38);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  
  
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
          }
         
    	context = this;
    	
    	noti_text = (TextView) findViewById(R.id.noti_text);
    	noti_text.setTextColor(Color.BLUE);
    	noti_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.PUSH_LINK_URL));
				context.startActivity(browserIntent);
				} catch (Exception ex) {
					
				}
			}
		});
    	
    	addPushNotificationUI();
    	createScrollContainer();
    	
    	menuScroll = (HorizontalScrollView) findViewById(R.id.scrollViewTopRss);
    	menuScroll.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				menuScroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
			}
		});
    	addListenerToDummyMenu();
    	LoadDataFromServer();
    }
    
    //For test part
    private void addListenerToDummyMenu() {
    	OnClickListener dummyListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String categoryParamUrl = "?utm_source=app&utm_medium=category&utm_campaign=TitleName";
					String final_url = "";
					
					
					
					switch (v.getId()) {
					case R.id.dummy1:
						final_url = AppConstants.damUrl1 + categoryParamUrl;
						break;
					case R.id.dummy2:
						final_url = AppConstants.damUrl2 + categoryParamUrl;
						break;
					case R.id.dummy3:
						final_url = AppConstants.damUrl3 + categoryParamUrl;
						break;
					case R.id.dummy4:
						final_url = AppConstants.damUrl4 + categoryParamUrl;
						break;
					case R.id.dummy5:
						final_url = AppConstants.damUrl5 + categoryParamUrl;
						break;
					case R.id.dummy6:
						final_url = AppConstants.damUrl6 + categoryParamUrl;
						break;
					case R.id.dummy7:
						//final_url = AppConstants.damUrl6 + categoryParamUrl;
						Intent sendIntent = new Intent();
						sendIntent.setAction(Intent.ACTION_SEND);
						sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
						sendIntent.setType("text/plain");
						startActivity(Intent.createChooser(sendIntent, "Share to..."));
						return;
					

					default:
						break;
					}
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(final_url));
					context.startActivity(browserIntent);
				} catch (Exception ex){
					
				}
			}
		};
    	TextView dum1 = (TextView) findViewById(R.id.dummy1);
    	TextView dum2 = (TextView) findViewById(R.id.dummy2);
    	TextView dum3 = (TextView) findViewById(R.id.dummy3);
    	TextView dum4 = (TextView) findViewById(R.id.dummy4);
    	TextView dum5 = (TextView) findViewById(R.id.dummy5);
    	TextView dum6 = (TextView) findViewById(R.id.dummy6);
    	View dum7     = findViewById(R.id.dummy7);
    
    	dum1.setOnClickListener(dummyListener);
    	dum2.setOnClickListener(dummyListener);
    	dum3.setOnClickListener(dummyListener);
    	dum4.setOnClickListener(dummyListener);
    	dum5.setOnClickListener(dummyListener);
    	dum6.setOnClickListener(dummyListener);
    	dum7.setOnClickListener(dummyListener);
    }
    
    private void LoadDataFromServer() {
    	dlg = new ProgressDialog(context);
		dlg.setTitle("Loading...");
		dlg.setMessage("Please wait while loading data...");
		dlg.setCancelable(false);
		dlg.show();
    	
		
		
		try
        {
            parsedDataHandler = new DataHandler(this);
            parsedDataHandler.getData();
        }
        catch(Exception pce) { Log.e("Android Activity", "PCE "+pce.getMessage()); }
    }
    
    private void createScrollContainer() {
    	scrlContainer = (LinearLayout) findViewById(R.id.scrolContainer);
    }
    
    private void setCheckedControllers() {
    	Set<String> chanelInfo = PushService.getSubscriptions(context);
    	if(chanelInfo.size() > 0)
    	{
    	String curChanel = chanelInfo.toString();
    	curChanel = curChanel.substring(1, curChanel.length() - 1);
    	if(curChanel.equals(CHANNEL1))
    	{
    		cb_pGroup1.setChecked(true);
        	cb_pGroup2.setChecked(false);
        	PushService.subscribe(getApplicationContext(), CHANNEL1, MainActivity.class);
    	}
    	else
    	{
	    	cb_pGroup1.setChecked(false);
	    	cb_pGroup2.setChecked(true);
	    	PushService.subscribe(getApplicationContext(), CHANNEL2, MainActivity.class);
    	}
    	}
    	else
    	{
    		cb_pGroup1.setChecked(true);
        	cb_pGroup2.setChecked(false);
        	PushService.subscribe(getApplicationContext(), CHANNEL1, MainActivity.class);
    	}
    }
    
    //Create the CheckBoxes.
    private void addPushNotificationUI() {
    	cb_pGroup1 = (CheckBox) findViewById(R.id.pushGroup1);
    	cb_pGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					PushService.subscribe(getApplicationContext(), CHANNEL1, MainActivity.class);
					PushService.unsubscribe(getApplicationContext(), CHANNEL2);
					cb_pGroup1.setChecked(true);
					cb_pGroup2.setChecked(false);
				}
			}
		});
    	cb_pGroup2 = (CheckBox) findViewById(R.id.pushGroup2);
    	cb_pGroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					PushService.subscribe(getApplicationContext(), CHANNEL2, MainActivity.class);
					PushService.unsubscribe(getApplicationContext(), CHANNEL1);
					cb_pGroup1.setChecked(false);
					cb_pGroup2.setChecked(true);
				}
			}
		});
    	
    	setCheckedControllers();
    }
    public void checkLaunchByPush() {
   	 if (AppConstants.PUSH_LINK_URL.length() != 0) {
   		 Handler handle = new Handler();
   		 handle.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.PUSH_LINK_URL));
					context.startActivity(browserIntent);
					AppConstants.PUSH_LINK_URL = "";
					} catch (Exception ex) {
						
					}	
			}
		});
        	
        }
   }
   
    public void onFinishParseData() {
//    	TextView btmRssTitle = (TextView) findViewById(R.id.bottomRssTitle);
    	//btmRssTitle.setText(parsedDataHandler.getRootTitle());
    	displayRssInfo();
    }
    
    int numsOfItems;
    private void displayRssInfo() {
    	final ArrayList<HashMap<String, String>> itemsData = parsedDataHandler.getRssDataOfEachItem();
    	numsOfItems = itemsData.size();
    	Handler han = new Handler();
    	han.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int phot_height = scrlContainer.getMeasuredHeight();
				LinearLayout firstCellPaddingLay = new LinearLayout(context);
	            int pading_heights = (int)(phot_height * 0.15f);
	            firstCellPaddingLay.setBackgroundColor(Color.WHITE);
	            LayoutParams fpParam = new LayoutParams(pading_heights, pading_heights);
	            firstCellPaddingLay.setLayoutParams(fpParam);
	            scrlContainer.addView(firstCellPaddingLay, 0);
				for (int i = 0; i < numsOfItems; i++)
		    	{
		    		final HashMap<String, String> curItem = itemsData.get(i);
		    		Bitmap phot_bitmap = null;
		    		try {
						phot_bitmap = PhotoLoadClass.getBitmapFromUrl(new URL(curItem.get(AppConstants.keywordPHOTOURL)));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		ImageView cur_imgView = new ImageView(context);
		    		if (phot_bitmap != null)
		    			cur_imgView.setImageBitmap(phot_bitmap);
			           
		          //This is the container which can contain the rss photos 
		            LinearLayout cellLay = new LinearLayout(context);

		            
		            
		            cellLay.setBackgroundColor(rssCellColor);
		            cellLay.setOrientation(LinearLayout.VERTICAL);
		            
		            LayoutParams lParam = new LayoutParams(phot_height, phot_height);
		            cellLay.setLayoutParams(lParam);
		            
		            //PhotoContainer Terminal
		            LinearLayout imgCellLay = new LinearLayout(context);
		            imgCellLay.setOrientation(LinearLayout.VERTICAL);
		            int img_height = (int)(phot_height * 0.7f);
		            LayoutParams imgParams = new LayoutParams(phot_height, img_height);
		            imgCellLay.setLayoutParams(imgParams);
		            AutoResizeTextView cur_postTitle = new AutoResizeTextView(context);
		            
		            String postTitle = curItem.get(AppConstants.keywordTITLE);
		            cur_postTitle.setText(postTitle);
		            cur_postTitle.setTextColor(Color.WHITE);
		            
		            LayoutParams curImgViewParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		            
		            cur_postTitle.setLayoutParams(curImgViewParam);
		            
		            cur_imgView.setLayoutParams(curImgViewParam);
		            cur_imgView.setScaleType(ScaleType.FIT_XY);
		            imgCellLay.addView(cur_imgView);
		            cellLay.addView(imgCellLay);
		            cellLay.addView(cur_postTitle);
		            
		            LinearLayout cellPaddingLay = new LinearLayout(context);
		            int pading_height = (int)(phot_height * 0.15f);
		            cellPaddingLay.setBackgroundColor(Color.WHITE);
		            LayoutParams pParam = new LayoutParams(pading_height, pading_height);
		            cellPaddingLay.setLayoutParams(pParam);
		            
		            cellLay.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String rss_param_url = "?utm_source=app&utm_medium=rss&utm_campaign=TitleName";
							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(curItem.get(AppConstants.keywordLINK_RSSCEL) + rss_param_url));
							context.startActivity(browserIntent);
						}
					});
		            
		            scrlContainer.addView(cellLay, 0);
		            scrlContainer.addView(cellPaddingLay, 0);
		    	}
				dlg.dismiss();
				final HorizontalScrollView scrlViewBottom = (HorizontalScrollView) findViewById(R.id.scrollViewBottomRss);
				scrlViewBottom.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						scrlViewBottom.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
					}
				});
			}
		});
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		checkLaunchByPush();
		
		super.onPostResume();
	}
    
    
}
