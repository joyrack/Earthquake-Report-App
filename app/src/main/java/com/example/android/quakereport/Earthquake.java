package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private final double m_magnitude;
    private final String m_location;
    private final long m_timeInMilliseconds;
    private final String m_url;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        m_magnitude = magnitude;
        m_location = location;
        m_timeInMilliseconds = timeInMilliseconds;
        m_url = url;
    }

    public double getMagnitude(){return m_magnitude;}
    public String getLocation(){return m_location;}
    public long getTimeInMilliseconds(){return m_timeInMilliseconds;}
    public String getUrl(){return m_url;}
}
