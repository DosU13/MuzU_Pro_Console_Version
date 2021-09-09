package src.video_layer.custom;

import src.main.Project;
import src.video_layer.VideoLayer;

import java.awt.image.BufferedImage;

public class SimpleGradient extends VideoLayer {
    private final Project project;
    public SimpleGradient(Project project) {
        super(project);
        this.project = project;
    }

    @Override
    public BufferedImage baseRender(int frameIndex) {
        int w = project.videoProperties.getWidth();
        int h = project.videoProperties.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for(int x=0; x<w; x++){
            for(int y=0; y<h; y++){
                int v = (frameIndex+x)%255;
                int color = (255<<24)|(0<<16)|(v<<8)|v;
                img.setRGB(x, y, color);
            }
        }
        return img;
    }
}
