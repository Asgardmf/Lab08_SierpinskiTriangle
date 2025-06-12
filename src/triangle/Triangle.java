package triangle;

import resizable.ResizableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
    Dimension triSize = new Dimension(922, 950);
    BufferedImage triBufferedImage = new BufferedImage(triSize.width, triSize.height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gBuffer = (Graphics2D) triBufferedImage.getGraphics();
    int [] x = {0, 0, 0};
    int [] y = {0, 0, 0};
    int numPoints = 3;
    int iterations = 2;
    /**
     * change this method to implement the triangle!
     * @param px the x coordinate of the startpoint
     * @param py the y coordinate of the startpoint
     * @param s the size of triangle
     * @param r the number of recursions
     * @return an Image containing the Triangle
     */
    private Image drawTriangle(int px, int py, int s, int r) {

        if(r<=0) {
            return triBufferedImage;
        }
        //Sichtbarkeit ist nur teilweise gegeben, da die neuen Dreiecke die alten überdecken und so die Farben auch überdecken
        if(r == iterations){
            gBuffer.setColor(Color.black);
        }
        else if(r == iterations -1){
            gBuffer.setColor(Color.red);
        }
        else if(r == iterations -2){
            gBuffer.setColor(Color.blue);
        }
        else if(r == iterations -3){
            gBuffer.setColor(Color.green);
        }
        else{
            gBuffer.setColor(Color.pink);
        }
        int x1 = px;
        int x2 = x1 + s;
        int x3 = (x1+x2)/2;
        int y1 = py;
        int y2 = y1;
        int y3 = y1 + (int)(Math.sqrt(3)*s/2);
        x[0] = x1;
        x[1] = x2;
        x[2] = x3;
        y[0] = y1;
        y[1] = y2;
        y[2] = y3;
        gBuffer.drawPolygon(x, y, numPoints);
        drawTriangle(x1, y1, s/2, r-1);
        drawTriangle((x1 + x2)/2, (y1 + y2)/2, s/2, r-1);
        drawTriangle((x1 + x3)/2, (y1+y3)/2, s/2, r-1);
        return triBufferedImage;
    }

    BufferedImage bufferedImage;
    Dimension bufferedImageSize;

    @Override
    public Image getImage(Dimension triangleSize) {
        if (triangleSize.equals(bufferedImageSize))
            return bufferedImage;
        bufferedImage = (BufferedImage) drawTriangle(0, 0, 500, iterations);
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
