//package code.Testing;
//
//import code.algorithms.Graph_Algo;
//import code.dataStructure.DGraph;
//import code.dataStructure.Node;
//import code.dataStructure.Node_data;
//import code.utils.GUI_graph;
//import code.utils.Point3D;
//
//import java.io.Serializable;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//public class GUI_Testing implements Serializable {
//
//public static void main(String[] args) throws InterruptedException {
//        DGraph g = new DGraph();
//        g.addNode(new Node(new Point3D(30, 500)));
//        g.addNode(new Node(new Point3D(270, 80)));
//        g.addNode(new Node(new Point3D(50, 100)));
//      g.addNode(new Node(new Point3D(250, 250)));
////       g.addNode(new Node(new Point3D(500, 250)));
////      g.addNode(new Node(new Point3D(450, 550)));
//        g.connect(1  , 2, 14);
//       g.connect(2, 3, 9);
//        g.connect(3, 2, 7);
//       g.connect(3, 4, 9);
//        g.connect(4, 2, 2);
//////        g.connect(3, 4, 2);
////        g.connect(2, 1, 11);
////        g.connect(4, 5, 10);
////        g.connect(5, 2, 6);
////        g.connect(6, 5, 15);
//    System.out.println();
//    Graph_Algo ga = new Graph_Algo();
//    ga.init(g);
//    System.out.println(ga.isConnected());
//   List <Node_data> a= new LinkedList<Node_data>();
//   a=ga.shortestPath(1,5);
////    Iterator u = a.iterator();
////    if (a!=null && u!=null) {
////        while (u.hasNext()) {
////            Node r = (Node) u.next();
////            System.out.println(r.getKey());
////        }
////    }
//    System.out.println(ga.shortestPathDist(1,5));
//    GUI_graph gui = new GUI_graph( g);
//
//        }}
package Tests;
//
//import dataStructure.DGraph;
//import dataStructure.graph;
//import dataStructure.Node;
//import utils.Point3D;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import utils.Point3D;

public class GUI_Testing {

    public static void main(String[] args) throws InterruptedException {
        // Node.resetUUID();
        DGraph g = new DGraph();
        Graph_Algo ga = new Graph_Algo();
        int mc = g.getMC();
        g.addNode(new Node(new Point3D(30, 500)));
        g.addNode(new Node(new Point3D(270, 80)));
        g.addNode(new Node(new Point3D(50, 100)));
        g.addNode(new Node(new Point3D(250, 250)));
        g.addNode(new Node(new Point3D(500, 250)));
        g.addNode(new Node(new Point3D(450, 550)));
        g.connect(1, 3, 14);
        g.connect(1, 4, 9);

        g.connect(1, 6, 7);
        g.connect(3, 2, 9);
        g.connect(3, 4, 2);
        g.connect(4, 1, 2);
        g.connect(4, 3, 2);
        g.connect(4, 5, 11);
        g.connect(4, 6, 10);
        g.connect(5, 2, 6);
        g.connect(6, 5, 15);
        //  g.connect(2, 4, 15);
        ga.init(g);
//        GUI_graph b = new GUI_graph((DGraph) g);
//        b.setVisible(true);
//        List <Integer> y = new LinkedList<>();
//        y.add(4);
//        y.add(3);
//
//        Collection <node_data> o = ga.TSP(y);
//        if(o!=null) {
//            Iterator it = o.iterator();
//            while (it.hasNext()) {
//                node_data e = (node_data) it.next();
//                System.out.println(e.getKey());
//            }
//        }
//        else{
//            System.out.println("no path");
//        }
//    }

    }
}