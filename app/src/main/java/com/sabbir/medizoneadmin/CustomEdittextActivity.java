package com.sabbir.medizoneadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class CustomEdittextActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private WebView webview1;
	private EditText edittext1;
	private Button button1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.custom_edittext);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		edittext1 = findViewById(R.id.edittext1);
		button1 = findViewById(R.id.button1);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//long press to dl image
				
				webview1.getSettings().setJavaScriptEnabled(true); webview1.setWebViewClient(new WebViewClient()); registerForContextMenu(webview1);
				webview1.loadUrl("https://www.google.com/search?q=".concat(edittext1.getText().toString().concat("&tbm=isch&hl=en&tbs=il:cl")));
			}
		});
	}
	
	private void initializeLogic() {
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Medizone"))) {
			
		}
		else {
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/Medizone"));
		}
	}
	
	public void _longpressimg() {
		
	}
	
	@Override public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){ super.onCreateContextMenu(contextMenu, view, contextMenuInfo); final WebView.HitTestResult webViewHitTestResult = webview1.getHitTestResult(); if (webViewHitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || webViewHitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) { contextMenu.setHeaderTitle("Download Image From Below"); contextMenu.add(0, 1, 0, "Save - Download Image") .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { @Override public boolean onMenuItemClick(MenuItem menuItem) { String DownloadImageURL = webViewHitTestResult.getExtra(); if(URLUtil.isValidUrl(DownloadImageURL)){ DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadImageURL)); request.allowScanningByMediaScanner();
						
						request.setTitle("download.png");
						
						request.setDestinationInExternalPublicDir("/Medizone", "download.png"); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE); downloadManager.enqueue(request); Toast.makeText(CustomEdittextActivity.this,"Image Downloaded Successfully.",Toast.LENGTH_LONG).show(); } else { Toast.makeText(CustomEdittextActivity.this,"Sorry.. Something Went Wrong.",Toast.LENGTH_LONG).show(); } return false; } }); }
		
		
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}