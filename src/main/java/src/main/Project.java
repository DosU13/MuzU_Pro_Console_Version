package src.main;

import src.FrameMaker;
import src.Renderer;
import src.data.RenderProperties;
import src.data.TimeProperties;
import src.data.VideoProperties;
import src.video_layer.VideoLayer;

import java.io.File;
import java.util.ArrayList;

public class Project {
    public VideoProperties videoProperties;
    public TimeProperties timeProperties;
    public RenderProperties renderProperties;
    public Renderer renderer;
    public ArrayList<VideoLayer> videoLayers = new ArrayList<>();
    public FrameMaker frameMaker;
    public File music;

    public Project(){}
    public void setVariables(VideoProperties videoProperties, TimeProperties timeProperties,
                             RenderProperties renderProperties, File music) {
        this.videoProperties = videoProperties;
        this.timeProperties = timeProperties;
        this.renderProperties = renderProperties;
        renderer = new Renderer(this);
        videoLayers = new ArrayList<>();
        frameMaker = new FrameMaker(this);
        this.music = music;
    }

    public void renderVideo(File output){
        renderer.render(output);
    }
}
