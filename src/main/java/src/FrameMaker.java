package src;

import src.main.Project;
import src.video_layer.VideoLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FrameMaker {
    private final Project project;

    public FrameMaker(Project project) {
        this.project = project;
    }

    public BufferedImage makeFrame(int frameIndex) throws IOException {
        ArrayList<VideoLayer> videoLayers = project.videoLayers;
        if(videoLayers.size()==1){
            return videoLayers.get(0).render(frameIndex);
        }
        return ImageIO.read(new File(""));
    }
}
