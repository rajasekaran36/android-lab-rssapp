package com.kgisl.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView title, content;
    Button feedget;
    List<News> allNews;
    TOIFeedTask toiFeedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.feedcontent);
        feedget = (Button)findViewById(R.id.feedget);

        toiFeedTask = new TOIFeedTask();

        try {
            allNews = toiFeedTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        feedget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SpannableStringBuilder builder = new SpannableStringBuilder();

                    String contentToDisplay = "";
                    for(News news:allNews){
                        SpannableString content= new SpannableString(news.getDate()+"\n");
                        content.setSpan(new ForegroundColorSpan(Color.BLACK), 0, content.length(), 0);
                        builder.append(content);

                        content= new SpannableString(news.getTitle()+"\n");
                        content.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, content.length(), 0);
                        builder.append(content);
                        content= new SpannableString(news.getContent()+"\n\n");
                        content.setSpan(new ForegroundColorSpan(Color.BLUE), 0, content.length(), 0);
                        builder.append(content);
                        builder.append("\n\n");
                    }
                    content.setText(builder,TextView.BufferType.SPANNABLE);
            }
        });



    }
}
