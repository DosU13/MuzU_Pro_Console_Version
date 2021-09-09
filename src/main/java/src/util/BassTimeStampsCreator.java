package src.util;

import src.data.TimeProperties;

import java.util.ArrayList;

public class BassTimeStampsCreator {
    public ArrayList<Integer> create(TimeProperties timeProp){
        ArrayList<Integer> res = new ArrayList<>();
        double bpm = timeProp.getBpm();
        double fps = timeProp.getFps();
        int frameCount = timeProp.getFrameCount();
        int beatInd =0;
        int frameInd = 0;
        while(frameInd<frameCount){
            res.add(frameInd);
            beatInd++;
            frameInd = beatToFrameInd(beatInd, bpm, fps);
        }
        return res;
    }

    private int beatToFrameInd(int beatInd, double bpm, double fps){
        double durSec = beatInd/bpm*60.0;
        return (int) (durSec*fps);
    }
}
