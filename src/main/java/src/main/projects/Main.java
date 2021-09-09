package src.main.projects;

import src.data.RenderProperties;
import src.data.RenderType;
import src.data.TimeProperties;
import src.data.VideoProperties;
import src.main.Project;
import src.video_layer.custom.SimpleGradient;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        VideoProperties videoProperties = new VideoProperties(project, 1440, 1080);
        videoProperties.setLowRes(32);
        TimeProperties timeProperties = new TimeProperties(128, 0, 90);
        RenderProperties renderProperties = new RenderProperties(RenderType.LOW_RESOLUTION);
        File music = new File("C:\\Users\\User\\Desktop\\no copyright songs\\Ahrix - Nova (souKo Remix).mp3");

        project.setVariables(videoProperties, timeProperties, renderProperties, music);
        project.videoLayers.add(new SimpleGradient(project));
        project.renderVideo(new File("C:\\Users\\User\\Desktop\\output.mp4"));
    }
}
