package uk.profitscan.mobile;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.*;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Environment;

public class MainActivity extends Activity {
 private WebView web;
 private PermissionRequest pending;
 private static final String HOME="https://leeshush78-glitch.github.io/profitscan/index.html?v=15";
 @Override public void onCreate(Bundle state){super.onCreate(state);web=new WebView(this);setContentView(web);WebSettings settings=web.getSettings();settings.setJavaScriptEnabled(true);settings.setDomStorageEnabled(true);settings.setMediaPlaybackRequiresUserGesture(false);web.setWebViewClient(new WebViewClient(){@Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest request){Uri u=request.getUrl();if("leeshush78-glitch.github.io".equals(u.getHost())&&u.getPath()!=null&&u.getPath().startsWith("/profitscan/"))return false;try{startActivity(new Intent(Intent.ACTION_VIEW,u));}catch(Exception e){Toast.makeText(MainActivity.this,"No app available to open link",Toast.LENGTH_SHORT).show();}return true;}});web.setWebChromeClient(new WebChromeClient(){@Override public void onPermissionRequest(PermissionRequest req){runOnUiThread(()->{pending=req;if(checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){req.grant(new String[]{PermissionRequest.RESOURCE_VIDEO_CAPTURE});pending=null;}else requestPermissions(new String[]{Manifest.permission.CAMERA},11);});}});web.setDownloadListener((url,userAgent,contentDisposition,mimeType,contentLength)->{try{DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);request.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS,URLUtil.guessFileName(url,contentDisposition,mimeType));((DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);Toast.makeText(this,"Downloading file",Toast.LENGTH_SHORT).show();}catch(Exception e){Toast.makeText(this,"Download unavailable",Toast.LENGTH_SHORT).show();}});web.loadUrl(HOME);}
 @Override public void onRequestPermissionsResult(int code,String[] permissions,int[] results){super.onRequestPermissionsResult(code,permissions,results);if(code==11&&pending!=null){if(results.length>0&&results[0]==PackageManager.PERMISSION_GRANTED)pending.grant(new String[]{PermissionRequest.RESOURCE_VIDEO_CAPTURE});else pending.deny();pending=null;}}
 @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}
