package src.data;

public class TimeProperties {
    private double fps;
    private double bpm;
    private double midiPaddingSec;
    private final double videoDurationSec;

    public TimeProperties(double bpm, double midiPaddingSec, double videoDurationSec) {
        this(60, bpm, midiPaddingSec, videoDurationSec);
    }

    public TimeProperties(double fps, double bpm, double midiPaddingSec, double videoDurationSec) {
        this.fps = fps;
        this.bpm = bpm;
        this.midiPaddingSec = midiPaddingSec;
        this.videoDurationSec = videoDurationSec;
    }

    public int getFrameIndex(int msec){
        return (int)(fps*msec/1000.0);
    }

    public int getMiniSec(int frameIndex){
        return (int)(frameIndex/fps*1000.0);
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }

    public double getBpm() {
        return bpm;
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
    }

    public double getMidiPaddingSec() {
        return midiPaddingSec;
    }

    public void setMidiPaddingSec(double midiPaddingSec) {
        this.midiPaddingSec = midiPaddingSec;
    }

    public int getFrameCount() {
        return getFrameIndex((int)(videoDurationSec*1000))+1;
    }
}
