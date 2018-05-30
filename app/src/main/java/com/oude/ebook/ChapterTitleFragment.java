package com.oude.ebook;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import android.widget.TextView;

public class ChapterTitleFragment extends Fragment
{
    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.chapter_title_frag,container,false);
        RecyclerView chapterTitleRecyclerView = (RecyclerView)view.findViewById(R.id.chapter_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        chapterTitleRecyclerView.setLayoutManager(layoutManager);
        ChapterAdapter adapter = new ChapterAdapter(getChapter());
        chapterTitleRecyclerView.setAdapter(adapter);
        return view;
        
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.chapter_content_layout) != null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }
 
    private List<Chapter> getChapter() {
        List<Chapter> chapterList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Chapter chapter = new Chapter();
            chapter.setTitle("This is Chapter title " + i);
            chapter.setContent(getRandomLengthContent("This is Chapter content " + i + ". "));
            chapterList.add(chapter);
        }
        return chapterList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

        private List<Chapter> mChapterList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView chapterTitleText;

            public ViewHolder(View view) {
                super(view);
                chapterTitleText = view.findViewById(R.id.chapter_title);
            }

        }

        public ChapterAdapter(List<Chapter> chapterList) {
            mChapterList = chapterList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Chapter chapter = mChapterList.get(holder.getAdapterPosition());
                        if (isTwoPane) {
                            ChapterContentFragment chapterContentFragment = (ChapterContentFragment)getFragmentManager().findFragmentById(R.id.chapter_content_fragment);
                            chapterContentFragment.refresh(chapter.getTitle(), chapter.getContent());
                        } else {
                            ChapterContentActivity.actionStart(getActivity(), chapter.getTitle(), chapter.getContent());
                        }
                    }
                });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Chapter chapter = mChapterList.get(position);
            holder.chapterTitleText.setText(chapter.getTitle());
        }

        @Override
        public int getItemCount() {
            return mChapterList.size();
        }
        

    } 
    
    
}
