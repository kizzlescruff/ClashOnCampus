package com.example.wilki.clashoncampus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wilki on 21/11/2016.
 */

public class QuestAdapter extends ArrayAdapter<Quest> {

    private Activity activity;
    private List<Quest> quests;
    private static LayoutInflater inflater = null;

    public QuestAdapter(Activity activity, int textViewResourceId, List<Quest> quests){
        super(activity, textViewResourceId, quests);
        this.activity = activity;
        this.quests = quests;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Quest quest = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView questName = (TextView) convertView.findViewById(R.id.questNameTxt);
        TextView questLength = (TextView) convertView.findViewById(R.id.questDistTxt);
        TextView questRating = (TextView) convertView.findViewById(R.id.questRatingTxt);

        questName.setText(quest.getName());
        questLength.setText(String.format("%.1g km%n", quest.getDistance()));
        questRating.setText(Integer.toString(quest.getRating()));

        return convertView;
    }

}
