package com.oude.ebook;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

public class ChapterContentFragment extends Fragment
{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.chapter_content_frag,container,false);
        return view;
    }
    
    public void refresh(String chapterTitle,String chapterContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView chapterTitleText = (TextView)view.findViewById(R.id.chapter_title);
        TextView chapterContentText = (TextView)view.findViewById(R.id.chapter_content);
        chapterTitleText.setText(chapterTitle);
        chapterContentText.setText(chapterContent);
    }
    
}
