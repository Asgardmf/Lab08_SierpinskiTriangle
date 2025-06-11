package triangle;

import resizable.ResizableImage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static resizable.Debug.print;

/**
 * Implement your Sierpinski Triangle here.
 *
 *
 * You only need to change the drawTriangle
 * method!
 *
 *
 * If you want to, you can also adapt the
 * getResizeImage() method to draw a fast
 * preview.
 *
 */
public class Triangle implements ResizableImage {
    int drawTriangle = 0;
    /**
     * change this method to implement the triangle!
     * @param size the outer bounds of the triangle
     * @return an Image containing the Triangle
     */
    private BufferedImage drawTriangle(Dimension size) {
        print("drawTriangle: " + ++drawTriangle + "size: " + size);
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.black);
        /*int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        gBuffer.setColor(Color.darkGray);
        border = 8;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        gBuffer.drawString("Triangle goes here", border * 2, border * 4);*/
        int [] x = {400, 112, 688};
        int [] y = {100, 600, 600};
        int numPoints = 3;
        int runningIteration = 0;
        gBuffer.drawPolygon(x, y, numPoints);
        while(runningIteration <1){
            int x1 = (x[0]+x[1])/2;
            int x2 = x[0];
            int x3 = (x[1]+x[2])/2;
            int y1 = (y[0]+y[1])/2;
            int y2 = y[0];
            int y3 = (y[1]+y[2])/2;
            x[0] = x1;
            x[1] = x2;
            x[2] = x3;
            y[0] = y1;
            y[1] = y2;
            y[2] = y3;
            runningIteration++;
            drawTriangle(size);

        }
        int [] testx = {256, 400, 544};
        int [] testy = {350, 600, 350};
        gBuffer.drawPolygon(testx, testy, numPoints);
        return bufferedImage;
    }

    BufferedImage bufferedImage;
    Dimension bufferedImageSize;

    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = drawTriangle(triangleSize);
        bufferedImageSize = triangleSize;
        return bufferedImage;
    }
    @Override
    public Image getResizeImage(Dimension size) {
        BufferedImage bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gBuffer = (Graphics2D) bufferedImage.getGraphics();
        gBuffer.setColor(Color.pink);
        int border = 2;
        gBuffer.drawRect(border, border, size.width - 2 * border, size.height - 2 * border);
        return bufferedImage;
    }
}
