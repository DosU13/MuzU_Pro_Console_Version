import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BufferedImage img = chooseImage();
        File output = chooseSaveAs();

        Project project = new Project();
        VideoProperties videoProperties = new VideoProperties(project, img.getWidth(), img.getHeight());
        videoProperties.setCodec(avcodec.AV_CODEC_ID_H264);
        TimeProperties timeProperties = new TimeProperties(60, 0, 10);
        RenderProperties renderProperties = new RenderProperties(RenderType.FINAL);

        project.setVariables(videoProperties, timeProperties, renderProperties, null);

        ArrayList<Integer> timeStamps = new BassTimeStampsCreator().create(timeProperties);
        ImageLayer imageLayer = new ImageLayer(project, img);
        imageLayer.addEffect(new ZoomEffect(project, timeStamps));
        project.videoLayers.add(imageLayer);
        project.renderVideo(output);
    }

    private BufferedImage chooseImage(){
        BufferedImage bufferedImage = null;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException ignored) { }
        return bufferedImage;
    }

    private File chooseSaveAs(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterMP4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        fileChooser.getExtensionFilters().addAll(extFilterMP4);
        return fileChooser.showSaveDialog(null);
    }

    public static void main(String[] args) {
        launch();
    }
}
