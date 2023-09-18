package graphing.plotting;

import java.awt.Color;
import java.awt.Graphics2D;

import graphing.data.Dataset;

public class CoordinatePlaneWindow implements PlotCanvas {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    private static final int X_LABEL_OFFSET = 15;
    private static final int Y_LABEL_OFFSET = 5;

    public CoordinatePlaneWindow() {
        this.xMin = -20;
        this.xMax = 20;
        this.yMin = -20;
        this.yMax = 20;
    }

    public CoordinatePlaneWindow(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = this.round(xMin, 3);
        this.xMax = this.round(xMax, 3);
        this.yMin = this.round(yMin, 3);
        this.yMax = this.round(yMax, 3);
    }

    public void updateBounds(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = this.round(xMin, 3);
        this.xMax = this.round(xMax, 3);
        this.yMin = this.round(yMin, 3);
        this.yMax = this.round(yMax, 3);
    }

    public void updateBounds(Plot plot) {
        Dataset data = plot.dataset();
        this.xMin = this.round(data.minX(), 3);
        this.xMax = this.round(data.maxX(), 3);
        this.yMin = this.round(data.minY(), 3);
        this.yMax = this.round(data.maxY(), 3);
    }

    private double round(double num, int decimalPlaces) {
        return (int) (num * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
    }

    @Override
    public void drawOn(Graphics2D g2d, int plotWidth, int plotHeight) {
        int originX = this.originX(plotWidth);
        int originY = this.originY(plotHeight);

        // for cases where min and max have the same sign
        originX = this.originX(plotWidth);
        originY = this.originY(plotHeight);

        // set color for the axes
        g2d.setColor(Color.BLACK);

        // draw axes
        g2d.drawLine(-plotWidth / 2, originY, plotWidth / 2, originY);
        g2d.drawLine(originX, -plotHeight / 2, originX, plotHeight / 2);

        // set font for the labels
        g2d.setFont(g2d.getFont().deriveFont(12f));

        // draw x-axis labels
        g2d.drawString(String.valueOf(this.xMin), -plotWidth / 2 + Y_LABEL_OFFSET, originY + X_LABEL_OFFSET);

        int xMaxLabelWidth = g2d.getFontMetrics().stringWidth(String.valueOf(xMax));
        g2d.drawString(String.valueOf(this.xMax), plotWidth / 2 - Y_LABEL_OFFSET - xMaxLabelWidth,
                originY + X_LABEL_OFFSET);

        // draw y-axis labels
        int yMinLabelWidth = g2d.getFontMetrics().stringWidth(String.valueOf(yMin));
        g2d.drawString(String.valueOf(this.yMin), originX - yMinLabelWidth - Y_LABEL_OFFSET,
                plotHeight / 2 - Y_LABEL_OFFSET);

        int yMaxLabelWidth = g2d.getFontMetrics().stringWidth(String.valueOf(yMax));
        g2d.drawString(String.valueOf(this.yMax), originX - yMaxLabelWidth - Y_LABEL_OFFSET, -plotHeight / 2 + 12);
    }

    @Override
    public int originX(int plotWidth) {
        int originX = (int) (-(this.xMin + this.xMax) / (this.xMax - this.xMin) * plotWidth / 2);
        return Math.max(-plotWidth / 2, Math.min(plotWidth / 2, originX));
    }

    @Override
    public int originY(int plotHeight) {
        int originY = (int) ((this.yMin + this.yMax) / (this.yMax - this.yMin) * plotHeight / 2);
        return Math.max(-plotHeight / 2, Math.min(plotHeight / 2, originY));
    }

    @Override
    public double xScale(int plotWidth) {
        return plotWidth / (this.xMax - this.xMin);
    }

    @Override
    public double yScale(int plotHeight) {
        return plotHeight / (this.yMax - this.yMin);
    }



}
