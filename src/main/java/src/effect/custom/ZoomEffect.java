package src.effect.custom;

import src.effect.Effect;
import src.main.Project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ZoomEffect extends Effect {
    private final ArrayList<Float> zoomVals = new ArrayList<>();
    private final float maxZoom = 1.33f;
    private final float zoomOutPercent = 0.9f;

    public ZoomEffect(Project project, ArrayList<Integer> timeStamps) {
        super(project);
        int timeStampsIterator = 0;
        float zoomPercent = 0.0f;
        for(int i=0; i<project.timeProperties.getFrameCount(); i++){
            if(timeStampsIterator<timeStamps.size() && timeStamps.get(timeStampsIterator)==i){
                timeStampsIterator++;
                zoomPercent = 1.0f;
            }
            zoomVals.add(1.0f+((maxZoom-1.0f)*zoomPercent));
            zoomPercent *= zoomOutPercent;
        }
    }

    @Override
    public BufferedImage render(int frameIndex, BufferedImage img) {
        int w = project.videoProperties.getWidth();
        int h = project.videoProperties.getHeight();
        BufferedImage resImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                float v = zoomVals.get(frameIndex);
                int imgX = w / 2 - (int) ((w / 2 - x) / v);
                int imgY = h / 2 - (int) ((h / 2 - y) / v);
                Color c = new Color(img.getRGB(imgX, imgY));
                int color = (c.getRed()<<24)|(c.getGreen()<<16)|(c.getBlue()<<8)|c.getAlpha();
                resImg.setRGB(x, y, color);
            }
        }
        return resImg;
    }
}
