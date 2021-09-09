package src.main.projects;

import org.bytedeco.ffmpeg.global.avcodec;
import src.data.RenderProperties;
import src.data.RenderType;
import src.data.TimeProperties;
import src.data.VideoProperties;
import src.effect.custom.ZoomEffect;
import src.main.Project;
import src.util.BassTimeStampsCreator;
import src.video_layer.custom.ImageLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BackgroundTest {
    public static void main(String[] args) throws IOException {
        Project project = new Project();
        File imgFile = new File("C:\\Users\\User\\Desktop\\photo.jpg");
        BufferedImage img = ImageIO.read(imgFile);
        VideoProperties videoProperties = new VideoProperties(project, img.getWidth(), img.getHeight());
        videoProperties.setCodec(avcodec.AV_CODEC_ID_H264);
        TimeProperties timeProperties = new TimeProperties(80.5, 0.3, 10);//2*60 + 56);
        RenderProperties renderProperties = new RenderProperties(RenderType.FINAL);
        File music = new File("C:\\Users\\User\\Music\\2021 01\\WOLF - Villain_256k.mp3");

        project.setVariables(videoProperties, timeProperties, renderProperties, music);

        ArrayList<Integer> timeStamps = new BassTimeStampsCreator().create(timeProperties);
        ImageLayer imageLayer = new ImageLayer(project, img);
        imageLayer.addEffect(new ZoomEffect(project, timeStamps));
        project.videoLayers.add(imageLayer);
        project.renderVideo(new File("C:\\Users\\User\\Desktop\\WOLF - Villain_test.mp4"));
    }
}
