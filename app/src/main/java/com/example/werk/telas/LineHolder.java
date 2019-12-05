package com.example.werk.telas;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.werk.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public Button moreButton;
    public Button deleteButton;

    public LineHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.main_line_title);
        moreButton = (Button) itemView.findViewById(R.id.main_line_more);
        deleteButton = (Button) itemView.findViewById(R.id.main_line_delete);
    }
}