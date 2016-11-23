package shifatkhan.com.dinoapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

/**
 * Created by Shifat Khan on 22-Nov-16.
 */

public class FieldNoteWebViewFragment extends WebViewFragment {
    private int fieldNoteUrlIndex = -1;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Should be in the database, but for the sake of this small project we will hardcode in array.
        String [] fieldNoteUrls ={
                "http://www.sciencekids.co.nz/pictures/dinosaurs/antarctosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/ankylosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/anchiceratops.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/allosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/albertosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/alamosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/afrovenator.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/achelousaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/abrosaurus.html",
                "http://www.sciencekids.co.nz/pictures/dinosaurs/abrictosaurus.html"
        };

        WebView webview = getWebView();
        webview.setPadding(0,0,0,0);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);

        if(fieldNoteUrlIndex != -1){
            String fieldNoteUrl = fieldNoteUrls[fieldNoteUrlIndex];
            webview.loadUrl(fieldNoteUrl);
        } else {
            webview.loadUrl("http://www.sciencekids.co.nz/pictures/dinosaurs.html");
        }
    }

    public static FieldNoteWebViewFragment newInstance(int fieldNoteUrlIndex){
        FieldNoteWebViewFragment f = new FieldNoteWebViewFragment();
        f.fieldNoteUrlIndex = fieldNoteUrlIndex;
        return f;
    }

    public int getShownIndex(){
        return this.fieldNoteUrlIndex;
    }
}
