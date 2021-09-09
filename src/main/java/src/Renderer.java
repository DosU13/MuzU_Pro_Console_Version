package src;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import src.main.Project;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Renderer {
    private final Project project;
    public Renderer(Project project) {
        this.project = project;
    }

    public void render(File outputPath){
        render(outputPath, project.videoProperties.getWidth(), project.videoProperties.getHeight());
    }

    private void renderFinal(File outputPath){
        render(outputPath, project.videoProperties.getWidth(), project.videoProperties.getHeight());
    }

    private void renderLowResPreview(File outputPath){
        render(outputPath, project.videoProperties.getWidth(), project.videoProperties.getHeight());
    }

    private void renderRhythmPreview(File outputPath){

    }

    private void render(File outputPath, int width, int height){
        FFmpegFrameRecorder recorder = null;
        FrameGrabber audioGrabber = null;
        try {
            System.out.println("render start");
            audioGrabber = new FFmpegFrameGrabber(project.music);
            audioGrabber.setFormat("mp3");
            audioGrabber.start();
            recorder = new FFmpegFrameRecorder(outputPath,
                    width, height, audioGrabber.getAudioChannels());
            recorder.setFormat("mp4");
            recorder.setVideoCodec(project.videoProperties.getCodec());
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.setFrameRate(project.timeProperties.getFps());
            recorder.setSampleRate(audioGrabber.getSampleRate());

            //recorder.setMaxBFrames(5);

            recorder.start();
            FrameMaker frameMaker = project.frameMaker;
            Java2DFrameConverter converter = new Java2DFrameConverter();
            Frame frame = null;

            Map<Object, Object> hints = new HashMap<>();
            hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            for(int i = 0;  i< project.timeProperties.getFrameCount() ; i++){
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();
                g.setRenderingHints(hints);
                g.drawImage(frameMaker.makeFrame(i), 0,0,null);
                g.dispose();
                recorder.record(converter.getFrame(image));
                System.out.println("Rendering: "
                        + i*100/project.timeProperties.getFrameCount() +"% "
                        +i+"/"+project.timeProperties.getFrameCount());
            }

//            for(int i = 0 ; i < project.timeProperties.getFrameCount() ; i++){
//                recorder.record(converter.getFrame(frameMaker.makeFrame(i)));
//                System.out.println("Rendering: "
//                        + i*100/project.timeProperties.getFrameCount() +"% "
//                        +i+"/"+project.timeProperties.getFrameCount());
//            }
            int audioMarginFramesCount = (int)(project.timeProperties.getMidiPaddingSec()*audioGrabber.getSampleRate()/1152.0);
            System.out.println("AUDIO_MARGIN_SAMPLES:=>"+audioMarginFramesCount);
            long audioSamples = 0;
            while ((frame = audioGrabber.grabFrame()) != null) {
                if(audioMarginFramesCount>0) audioMarginFramesCount--;
                else recorder.record(frame);
                audioSamples++;
            }
            System.out.println("AUDIO_SAMPLES:=>"+audioSamples);
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        } finally {
            try {
                if(audioGrabber!=null){
                    audioGrabber.stop();
                    audioGrabber.release();
                }
                if(recorder!=null){
                    recorder.stop();
                    recorder.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Merged OMG");
    }
}
