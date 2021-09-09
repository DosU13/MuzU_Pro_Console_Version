package src.data;

import org.bytedeco.ffmpeg.global.avcodec;
import src.main.Project;

public class VideoProperties {
    private Project project;
    private int finalWidth;
    private int finalHeight;
    private int widthLowRes;
    private int heightLowRes;
    private int widthRhythmPreview;
    private int heightRhythmPreview;
    private int codec = avcodec.AV_CODEC_ID_MPEG4;

    public VideoProperties(Project project, int finalWidth, int finalHeight, int widthLowRes, int heightLowRes, int widthRhythmPreview, int heightRhythmPreview) {
        this.project= project;
        this.finalWidth = finalWidth;
        this.finalHeight = finalHeight;
        this.widthLowRes = widthLowRes;
        this.heightLowRes = heightLowRes;
        this.widthRhythmPreview = widthRhythmPreview;
        this.heightRhythmPreview = heightRhythmPreview;
    }

    public VideoProperties(Project project, int finalWidth, int finalHeight) {
        this(project, finalWidth, finalHeight, finalWidth /16, finalHeight /16, 256,256);
    }

    public int getWidth(){
        switch (project.renderProperties.getCurrentRenderType()){
            case FINAL: return finalWidth;
            case LOW_RESOLUTION: return widthLowRes;
            case RHYTHM_PREVIEW: return widthRhythmPreview;
        }
        return 0;
    }

    public int getHeight(){
        switch (project.renderProperties.getCurrentRenderType()){
            case FINAL: return finalHeight;
            case LOW_RESOLUTION: return heightLowRes;
            case RHYTHM_PREVIEW: return heightRhythmPreview;
        }
        return 0;
    }

    public void setRes(int width, int height){
        this.finalWidth = width;
        this.finalHeight = height;
    }

    public void setLowRes(int width, int height){
        this.widthLowRes = width;
        this.heightLowRes = height;
    }

    public void setLowRes(int howManyTimesLower){
        this.widthLowRes = finalWidth /howManyTimesLower;
        this.heightLowRes = finalHeight /howManyTimesLower;
    }

    public void setRhythmPreview(int width, int height){
        this.widthRhythmPreview = width;
        this.heightRhythmPreview = height;
    }

    public int getCodec() {
        return codec;
    }

    public void setCodec(int codec) {
        this.codec = codec;
    }
}
