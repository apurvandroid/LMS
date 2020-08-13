package work.newproject.asus.apurv.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewPdfOnlineAcivity extends AppCompatActivity {

    private WebView wv1;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf_online_acivity);
        wv1 = (WebView) findViewById(R.id.webview);
        url = getIntent().getStringExtra("url");
        loadUrl(url);

    }
    private void loadUrl(String url) {
        wv1.setWebViewClient(new MyBrowserr());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl("https://docs.google.com/viewer?embedded=true&url="+url);


    }

    private class MyBrowserr extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}