package com.mobiquityinc.weather.ui.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobiquityinc.weather.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpScreenFragment extends Fragment {

    @BindView(R.id.webview)
    protected WebView webView;

    public static HelpScreenFragment getInstance() {
        return new HelpScreenFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        ButterKnife.bind(this, view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/help.html");
        return view;
    }

}
