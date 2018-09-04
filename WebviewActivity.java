package com.example.delamey.myapplication5;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebviewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.js)
    Button js;
    private WebSettings webSettings;
    private String cachedir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //webview=new WebView(myapplication.getContext());
        //webview.setLayoutParams(params);
        initWebview();
    }

    private void initWebview() {
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);//开启与js交互

        webSettings.setUseWideViewPort(true);//调整图片到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕大小

        webSettings.setSupportZoom(true);//支持缩放
        webSettings.setBuiltInZoomControls(true);//设置内置缩放控件
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中的缓存 本地有就直接加载
        webSettings.setAllowFileAccess(true);//是指可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置默认的编码格式

        webSettings.setDomStorageEnabled(true);//开启dom api功能
        webSettings.setDatabaseEnabled(true);//开启 database api功能
        webSettings.setAppCacheEnabled(true);//开启 application caches

        cachedir = getFilesDir().getAbsolutePath();
        webSettings.setAppCachePath(cachedir);

        //client类处理通知和事件
        webview.setWebViewClient(new WebViewClient() {
            //本地加载不调用系统浏览器,在webview中显示
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            //在载入页面调用，在这可以添加loading页面

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            //加载结束后调用，可以关闭loading

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            //在加载资源时候调用，每个资源调用一次

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }
            //加载页面出现问题的时候嗲用

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
            //处理https请求，由于webview默认不处理https请求故添加

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//等待证书验证
            }
        });
        //辅助webview 处理js对话框、图标、标题
        webview.setWebChromeClient(new WebChromeClient() {
            //获得网页加载进度并显示

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String pro = newProgress + "%";
                    //porgress.set(pro)
                }
            }
            //获取web页面的标题

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
            //支持js警告框

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
            //支持js确认框

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
            //支持js输入框

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });


        webview.canGoBack();
        webview.canGoForward();
        webview.loadUrl("file:///android_asset/my.html");
        webview.addJavascriptInterface(new JiaoHu(), "hello");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webSettings.setJavaScriptEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }

    @OnClick(R.id.js)
    public void onViewClicked() {
        webview.loadUrl("javascript:android(true)");
    }

    public class JiaoHu {
        @JavascriptInterface
        public void showAndroid() {
            Toast.makeText(getApplicationContext(), "js调用android方法", Toast.LENGTH_LONG).show();
        }
    }
}
