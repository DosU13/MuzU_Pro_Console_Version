package src.data;

public class RenderProperties {
    private RenderType currentRenderType;

    public RenderProperties() {
        this(RenderType.FINAL);
    }

    public RenderProperties(RenderType currentRenderType) {
        this.currentRenderType = currentRenderType;
    }

    public RenderType getCurrentRenderType() {
        return currentRenderType;
    }

    public void setCurrentRenderType(RenderType currentRenderType) {
        this.currentRenderType = currentRenderType;
    }
}
