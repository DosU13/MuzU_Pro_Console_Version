package src.video_layer.custom;

import src.main.Project;
import src.video_layer.VideoLayer;

import java.awt.image.BufferedImage;

public class ImageLayer extends VideoLayer {
    BufferedImage img;
    public ImageLayer(Project project,  BufferedImage img) {
        super(project);
        this.img = img;
    }

    @Override
    public BufferedImage baseRender(int frameIndex) {
        return img;
    }
}
