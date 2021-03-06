/*
 *
 * PROJECT LICENSE
 *
 * This project was submitted by Tarek Bohdima as part of the Nanodegree At Udacity.
 *
 * As part of Udacity Honor code, your submissions must be your own work, hence
 * submitting this project as yours will cause you to break the Udacity Honor Code
 * and the suspension of your account.
 *
 * Me, the author of the project, allow you to check the code as a reference, but if
 * you submit it, it's your own responsibility if you get expelled.
 *
 * Copyright (c) 2018  Tarek Bohdima
 *
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.example.android.mytourguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AttractionAdapter extends ArrayAdapter<Attraction> {

    /**
     * Resource ID for the background color for this list of attractions
     */
    private int mColorResourceId;

    private Context context = getContext();


    public AttractionAdapter(@NonNull Context context, @NonNull ArrayList<Attraction> attractions, int colorResourceId) {
        super(context, 0, attractions);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Get the {@link Attraction} object located at this position in the list
        Attraction currentAttraction = getItem(position);

        assert currentAttraction != null;
        int nameId = currentAttraction.getmAttractionNameId();
        int descriptionId = currentAttraction.getmDescriptionId();
        int websiteId = currentAttraction.getmWebsiteId();
        int phoneId = currentAttraction.getmPhoneId();
        int businessHoursId = currentAttraction.getmBusinessHoursId();
        double longitudeId = currentAttraction.getmLongitudeId();
        double latitudeId = currentAttraction.getmLatitudeId();
        int imageResourceId = currentAttraction.getmImageResourceId();


        // Find the TextView in the list_item.xml layout with the ID attraction_name.
        TextView attractionName = (TextView) listItemView.findViewById(R.id.attraction_name);

        // Get the Attraction name from the currentAttraction object and set this text on
        // the attraction_name TextView
        attractionName.setText(nameId);

        // Find the ImageView in the list_item.xml layout with the ID image1.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image1);

        // Get the Attraction image from the currentAttraction object and set this text on
        // the image1 TextView
        imageView.setImageResource(imageResourceId);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(context, mColorResourceId);

        // Set the background color of the text container View
        attractionName.setBackgroundColor(color);

        //Intent to send Variables to Details Activity
        final Intent detailsIntent = new Intent(context, DetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("NAME", nameId);
        bundle.putInt("DESCRIPTION", descriptionId);
        bundle.putInt("WEBSITE", websiteId);
        bundle.putInt("PHONE", phoneId);
        bundle.putInt("BUSINESS", businessHoursId);
        bundle.putDouble("LONGITUDE", longitudeId);
        bundle.putDouble("LATITUDE", latitudeId);
        bundle.putInt("IMAGE", imageResourceId);

        detailsIntent.putExtras(bundle);

        //click on the list will start the implicit intent to Details Activity
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(detailsIntent);

            }
        });

        // Return the whole list item layout  so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
