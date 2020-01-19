package gameClient;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class StdDrow_GUI  {
    private StdDraw gui;
    public StdDrow_GUI (){
        this.gui=null;
    }

    public StdDrow_GUI(DGraph g, List<Fruit> fruits, List<Robot_Algo> robot) {
        StdDraw.setCanvasSize(1200,1300);
        double r_minx = 999999998;
        double r_maxx = -999999998;
        ArrayList<node_data> a = new ArrayList<node_data>(g.getV());
        for (int i = 0; i < a.size(); i++) {
            if (r_maxx < a.get(i).getLocation().x())
                r_maxx = a.get(i).getLocation().x();
            if (r_minx > a.get(i).getLocation().x())
                r_minx = a.get(i).getLocation().x();

        }
        double r_miny = 999999998;
        double r_maxy = -999999998;
        for (int i = 0; i < a.size(); i++) {
            if (r_maxy < a.get(i).getLocation().y())
                r_maxy = a.get(i).getLocation().y();
            if (r_miny > a.get(i).getLocation().y())
                r_miny = a.get(i).getLocation().y();
        }
        Range ansX = new Range(r_minx,r_maxx);
        Range ansY = new Range(r_miny,r_maxy);

        StdDraw.setXscale(ansX.get_min()-0.005,ansX.get_max()+0.002);
        StdDraw.setYscale(ansY.get_min()-0.005,ansY.get_max()+0.002);
StdDraw.clear();
StdDraw.enableDoubleBuffering();
        Iterator it = g.getV().iterator();
        while (it.hasNext()) {
            node_data v = (node_data) it.next();
            Point3D p = v.getLocation();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.10);
            StdDraw.circle(p.x(),p.y(),0.000009);
            StdDraw.text(p.x(),p.y(),""+(v.getKey()),0);
            Collection<edge_data> edge = g.getE(v.getKey());
            if (edge != null) {
                Iterator iterator = edge.iterator();
                while (iterator.hasNext()) {
                    edge_data e = (edge_data) iterator.next();
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.setFont(new Font("David", 1, 12));
                    node_data y = g.getNode(e.getDest());
                    StdDraw.setPenRadius(0.003);
                    StdDraw.line(p.x(), p.y(), y.getLocation().x(), y.getLocation().y());
                    String p1=String.format("%.3f",e.getWeight());
                    // String p2=String.format("%.3f",((p.y()+y.getLocation().y())/2));
                    StdDraw.setFont(new Font("David", 1, 15));
                    StdDraw.textRight((p.x() + y.getLocation().x())/2 ,(p.y() + y.getLocation().y())/2 ,"" + Double.parseDouble(p1));
                    StdDraw.setPenColor(Color.GRAY);
                    StdDraw.filledCircle((p.x() *0.1) + (0.9 * y.getLocation().x()) ,(p.y() * 0.1) +
                            (0.9* y.getLocation().y()),0.00010);
                }
            }}






    }


    public void show() {
        StdDraw.show();

    }

    /**
     * this function update all the time the graph when game is running
     * @param g
     */
    public void uptadeGraph(DGraph g){
    StdDraw.picture(0,0,"ddd.jpg");
    StdDraw.show();
StdDraw.enableDoubleBuffering();
    double r_minx = 999999998;
    double r_maxx = -999999998;
    ArrayList<node_data> a = new ArrayList<node_data>(g.getV());
    for (int i = 0; i < a.size(); i++) {
        if (r_maxx < a.get(i).getLocation().x())
            r_maxx = a.get(i).getLocation().x();
        if (r_minx > a.get(i).getLocation().x())
            r_minx = a.get(i).getLocation().x();

    }
    double r_miny = 999999998;
    double r_maxy = -999999998;
    for (int i = 0; i < a.size(); i++) {
        if (r_maxy < a.get(i).getLocation().y())
            r_maxy = a.get(i).getLocation().y();
        if (r_miny > a.get(i).getLocation().y())
            r_miny = a.get(i).getLocation().y();
    }
    Range ansX = new Range(r_minx,r_maxx);
    Range ansY = new Range(r_miny,r_maxy);

    StdDraw.setXscale(ansX.get_min()-0.005,ansX.get_max()+0.002);
    StdDraw.setYscale(ansY.get_min()-0.005,ansY.get_max()+0.002);

    StdDraw.enableDoubleBuffering();
    Iterator it = g.getV().iterator();
    while (it.hasNext()) {
        node_data v = (node_data) it.next();
        Point3D p = v.getLocation();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.10);
        StdDraw.circle(p.x(),p.y(),0.000009);
        StdDraw.text(p.x(),p.y(),""+(v.getKey()),0);
        Collection<edge_data> edge = g.getE(v.getKey());
        if (edge != null) {
            Iterator iterator = edge.iterator();
            while (iterator.hasNext()) {
                edge_data e = (edge_data) iterator.next();
                StdDraw.setPenColor(Color.RED);
                StdDraw.setFont(new Font("David", 1, 12));
                node_data y = g.getNode(e.getDest());
                StdDraw.setPenRadius(0.003);
                StdDraw.line(p.x(), p.y(), y.getLocation().x(), y.getLocation().y());
                String p1=String.format("%.3f",e.getWeight());
                // String p2=String.format("%.3f",((p.y()+y.getLocation().y())/2));
                StdDraw.setFont(new Font("David", 1, 15));
                StdDraw.textRight((p.x() + y.getLocation().x())/2 ,(p.y() + y.getLocation().y())/2 ,"" + Double.parseDouble(p1));
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.filledCircle((p.x() *0.1) + (0.9 * y.getLocation().x()) ,(p.y() * 0.1) +
                        (0.9* y.getLocation().y()),0.00010);
            }
        }}






}
public Range ansX;
    public Range ansY;
public StdDrow_GUI (DGraph g , List <Fruit> fruits){
        StdDraw.clear();
    this.gui.setCanvasSize(1200,1300);
    this.gui.disableDoubleBuffering();
    double r_minx = 999999998;
    double r_maxx = -999999998;
    ArrayList<node_data> a = new ArrayList<node_data>(g.getV());
    for (int i = 0; i < a.size(); i++) {
        if (r_maxx < a.get(i).getLocation().x())
            r_maxx = a.get(i).getLocation().x();
        if (r_minx > a.get(i).getLocation().x())
            r_minx = a.get(i).getLocation().x();

    }
    double r_miny = 999999998;
    double r_maxy = -999999998;
    for (int i = 0; i < a.size(); i++) {
        if (r_maxy < a.get(i).getLocation().y())
            r_maxy = a.get(i).getLocation().y();
        if (r_miny > a.get(i).getLocation().y())
            r_miny = a.get(i).getLocation().y();
    }
    ansX = new Range(r_minx,r_maxx);
     ansY = new Range(r_miny, r_maxy);
    System.out.println("ansX get ,in" + ansX.get_max());
    System.out.println("ansY get ,max" + ansY.get_max());

    this.gui.setXscale(ansX.get_min()-0.005,ansX.get_max()+0.002);
    this.gui.setYscale(ansY.get_min()-0.005,ansY.get_max()+0.002);
    StdDraw.picture(0,0,"back.jpg");
    StdDraw.picture(0,0,"ddd.jpg");
    StdDraw.show();

    this.gui.enableDoubleBuffering();
    Iterator it = g.getV().iterator();
    while (it.hasNext()) {
        node_data v = (node_data) it.next();
        Point3D p = v.getLocation();
        this.gui.setPenColor(Color.BLACK);
        this.gui.setPenRadius(0.10);
        this.gui.circle(p.x(),p.y(),0.000009);
        this.gui.text(p.x(),p.y(),""+(v.getKey()),0);
        Collection<edge_data> edge = g.getE(v.getKey());
        if (edge != null) {
            Iterator iterator = edge.iterator();
            while (iterator.hasNext()) {
                edge_data e = (edge_data) iterator.next();
                this.gui.setPenColor(Color.RED);
                this.gui.setFont(new Font("David", 1, 12));
                node_data y = g.getNode(e.getDest());
                this.gui.setPenRadius(0.003);
                this.gui.line(p.x(), p.y(), y.getLocation().x(), y.getLocation().y());
                String p1=String.format("%.3f",e.getWeight());
                // String p2=String.format("%.3f",((p.y()+y.getLocation().y())/2));
                this.gui.setFont(new Font("David", 1, 15));
                this.gui.textRight((p.x() + y.getLocation().x())/2 ,(p.y() + y.getLocation().y())/2 ,"" + Double.parseDouble(p1));
                this.gui.setPenColor(Color.GRAY);
                this.gui.filledCircle((p.x() *0.1) + (0.9 * y.getLocation().x()) ,(p.y() * 0.1) +
                        (0.9* y.getLocation().y()),0.00010);
            }
        }}
    for (Fruit t: fruits) {
        if(t.getType()==-1) {
            this.gui.picture(t.get_fruit_point().x(), t.get_fruit_point().y(), "banana.png", 0.0008, 0.0006);
            this.gui.picture(0,0,"back.jpg");

        }
        else{

            this.gui.picture(t.get_fruit_point().x(), t.get_fruit_point().y(),"apple.jpg",0.0008,0.0006);
        }
    }
}

}


