package graphing;

import java.awt.Graphics2D;

public class BlankPlaneWindow implements PlotCanvas {

    @Override
    public void drawOn(Graphics2D g2d, int plotWidth, int plotHeight) {
        // do nothing
    }

    @Override
    public int originX(int plotWidth) {
        return 0;
    }

    @Override
    public int originY(int plotHeight) {
        return 0;
    }

    @Override
    public double xScale(int plotWidth) {
        return plotWidth;
    }

    @Override
    public double yScale(int plotHeight) {
        return plotHeight;
    }
    
}
