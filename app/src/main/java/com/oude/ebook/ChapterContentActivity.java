package com.oude.ebook;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



public class ChapterContentActivity extends AppCompatActivity
{
    public static void actionStart(Context context,String chapterTitle,String chapterContent){
        Intent intent = new Intent(context,ChapterContentActivity.class);
        intent.putExtra("chapter_title",chapterTitle);
        intent.putExtra("chapter_content",chapterContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_content);
        String chapterTitle = getIntent().getStringExtra("chapter_title");
        String chapterContent = getIntent().getStringExtra("chapter_content");
        ChapterContentFragment chapterContentFragment = (ChapterContentFragment) getSupportFragmentManager().findFragmentById(R.id.chapter_content_fragment);
        chapterContentFragment.refresh(chapterTitle,chapterContent);
        
    }
    
}
