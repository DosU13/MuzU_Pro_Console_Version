package src.effect;

import src.main.Project;

import java.awt.image.BufferedImage;

public abstract class Effect {
    protected final Project project;
    public Effect(Project project) {
        this.project = project;
    }

    public abstract BufferedImage render(int frameIndex, BufferedImage img);
}
