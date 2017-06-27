package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mlgrier on 8/17/16.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    // Resource ID for the background color for this list of words
    private int mColorResourceId;

    // context is the current context (i.e. Activity) that the adapter is being created in
    // words is the list of words to be displayed.
    // colorResourceId is the resource ID for the background color for this list of words
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image.

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Set the ImageView to the image resource specified in the current Word

        imageView.setImageResource(currentWord.getImageResourceId());

        // Check if an image is provided for this word or not

        if (currentWord.hasImage()) {

            // If an image is available, display the provided image based on the resource ID

            imageView.setImageResource(currentWord.getImageResourceId());

            // Make sure the view is visible

            imageView.setVisibility(View.VISIBLE);

        } else {

            // Otherwise hide the ImageView (set visibility to GONE)

            imageView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        //find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        //set the background color of the text container view
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.

        return listItemView;
    }
}
