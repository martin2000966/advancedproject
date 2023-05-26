import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
public class Draw {



    public static Rectangle createsquare(double x, double y, double w,double h) {
        Rectangle r = new Rectangle(x,y,w,h);
        r.setFill(Color.WHITE);
        r.setStroke(Color.BLACK);
        return r;
    }
    public static Line createline(double xs, double ys, double xe,double ye) {
        Line l = new Line(xs,ys,xe,ye);
        return l;
    }
    public static Polygon createtriangle(double xe,double ye) {
        Polygon p = new Polygon();
        p.getPoints().addAll(
            xe,ye,
                xe-5,ye-5,
                xe-5,ye+5);
        return p;
    }
    public static Polygon createtrianglel(double xe,double ye) {
        Polygon p = new Polygon();
        p.getPoints().addAll(
                xe,ye,
                xe+5,ye-5,
                xe+5,ye+5);
        return p;
    }







}