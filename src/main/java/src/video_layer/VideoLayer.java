package src.video_layer;

import src.effect.Effect;
import src.main.Project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public abstract class VideoLayer {
    private final Project project;
    private ArrayList<Effect> effects;

    public VideoLayer(Project project) {
        this.project = project;
        effects = new ArrayList<>();
    }

    public void addEffect(Effect effect){
        effects.add(effect);
    }

    protected abstract BufferedImage baseRender(int frameIndex);

    public BufferedImage render(int frameIndex){
        AtomicReference<BufferedImage> img = new AtomicReference<>(baseRender(frameIndex));
        effects.forEach((effect) ->
                img.set(effect.render(frameIndex, img.get()))
        );
        return img.get();
    }
}
