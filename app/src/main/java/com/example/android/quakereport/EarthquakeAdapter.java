package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Character.compare;
import android.graphics.drawable.GradientDrawable;
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context,0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            // inflate the view
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        double mag = currentEarthquake.getMagnitude();
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(mag);
        magnitudeView.setText(magnitude);

        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);
        TextView locationOffset = listItemView.findViewById(R.id.location_offset);

        String location_offset = formatString(currentEarthquake.getLocation());
        String primary_location = getPrimaryLocation(currentEarthquake.getLocation());

        primaryLocationView.setText(primary_location);
        locationOffset.setText(location_offset);

        TextView dateView = listItemView.findViewById(R.id.date);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        String dateToDisplay = dateFormatter.format(dateObject);

        dateView.setText(dateToDisplay);

        Date timeObject = new Date(currentEarthquake.getTimeInMilliseconds());
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a", Locale.US);
        String timeToDisplay = timeFormatter.format(timeObject);

        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(timeToDisplay);

        return listItemView;
    }
    boolean hasDigit(String location){
        for(int i=0 ; i<location.length() ; i++){
            if(Character.isDigit(location.charAt(i))) return true;
        }
        return false;
    }
    String formatString(String location){
        if(hasDigit(location)){
            int spacesCount = 0, i=0;
            for( ; spacesCount<=3 ; i++){
                if(compare(location.charAt(i), ' ') == 0) spacesCount++;
            }
            return location.substring(0,i-1);
        }
        return "Near the";
    }
    String getPrimaryLocation(String location){
        if(hasDigit(location)){
            int spacesCount = 0, i=0;
            for( ; spacesCount<=3 ; i++){
                if(compare(location.charAt(i), ' ') == 0) spacesCount++;
            }
            return location.substring(i,location.length());
        }
        return location;
    }
    int getMagnitudeColor(double magnitude){
        int mag = (int)Math.floor(magnitude);
        int backgroundColor;
        switch(mag){
            case 0: case 1: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                            break;
            case 2: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default: backgroundColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
        return backgroundColor;
    }
}
