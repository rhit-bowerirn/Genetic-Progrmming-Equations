package graphing;

import java.awt.Graphics2D;

public interface PlotCanvas {

    // assumes the g2d is translated to the origin
    void drawOn(Graphics2D g2d, int plotWidth, int plotHeight);
    int originX(int plotWidth);
    int originY(int plotHeight);
    double xScale(int plotWidth);
    double yScale(int plotHeight);
}
