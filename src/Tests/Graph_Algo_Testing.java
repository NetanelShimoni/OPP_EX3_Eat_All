//package code.Testing;
//import code.algorithms.Graph_Algo;
//import code.dataStructure.DGraph;
//import code.dataStructure.Node;
//import code.dataStructure.graph;
//import code.dataStructure.node_data;
//import code.utils.Point3D;
///*import dataStructure.DGraph;
//import dataStructure.Node;
//import dataStructure.graph;
//import dataStructure.Node;*/
//import org.junit.Test;
////import utils.Point3D;
////import algorithms.graph_algorithms;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class Graph_AlgoTest {
//
//    @Test
//    public void init() {
//        Graph_Algo g1 = new Graph_Algo();
//        DGraph DG = new DGraph();
//        Point3D a = new Point3D(1,2,3);
//        Node n1 = new Node(a);
//        Node n2 = new Node(a);
//        Node n3 = new Node(a);
//        Node n4 = new Node(a);
//        Node n5 = new Node(a);
//        Node n6 = new Node(a);
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,5);
//        DG.connect(1,3,5);
//        DG.connect(1,4,5);
//        DG.connect(6,2,5);
//        DG.connect(6,5,5);
//        DG.connect(6,4,5);
//        g1.init(DG);
//        assertEquals(DG,g1.getGraph());
//    }
//
//    @Test
//    public void testInit() {
//        Graph_Algo g = new Graph_Algo();
//        DGraph DG = new DGraph();
//        Point3D a = new Point3D(1,2,3);
//        Node n1 = new Node(a);
//        Node n2 = new Node(a);
//        Node n3 = new Node(a);
//        Node n4 = new Node(a);
//        Node n5 = new Node(a);
//        Node n6 = new Node(a);
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,5);
//        DG.connect(1,3,5);
//        DG.connect(1,4,5);
//        DG.connect(6,2,5);
//        DG.connect(6,5,5);
//        DG.connect(6,4,5);
//        g.init(DG);
//        g.save("TestSave");
//        Graph_Algo g2 = new Graph_Algo();
//        g2.init("TestSave");
//        Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
//        Boolean flag2 = g.isConnected() == g2.isConnected();
//        assertEquals(true,flag);
//        assertEquals(true,flag2);
//
//    }
//
//    @Test
//    public void save() {
//        Graph_Algo g = new Graph_Algo();
//        DGraph DG = new DGraph();
//        Node n1 = new Node(new Point3D(100,200,3) );
//        Node n2 = new Node(new Point3D (150,200,3));
//        Node n3 = new Node(new Point3D(300,450,3));
//        Node n4 = new Node(new Point3D (450,500,3));
//        Node n5 = new Node(new Point3D (320,600,3));
//        Node n6 = new Node(new Point3D (226,260,3));
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,5);
//        DG.connect(1,3,5);
//        DG.connect(1,4,5);
//        DG.connect(6,2,5);
//        DG.connect(6,5,5);
//        DG.connect(6,4,5);
//        g.init(DG);
//        g.save("TestSave");
//        Graph_Algo g2 = new Graph_Algo();
//        g2.init("TestSave");
//        Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
//        Boolean flag2 = g.isConnected() == g2.isConnected();
//        assertEquals(true,flag);
//        assertEquals(true,flag2);
//    }
//
//    @Test
//    public void isConnected() {
//        Graph_Algo g = new Graph_Algo();
//        DGraph DG = new DGraph();
//        Node n1 = new Node(new Point3D (100,200,3));
//        Node n2 = new Node(new Point3D (150,200,3));
//        Node n3 = new Node(new Point3D (300,450,3));
//        Node n4 = new Node(new Point3D (450,500,3));
//        Node n5 = new Node(new Point3D (320,600,3));
//        Node n6 = new Node(new Point3D (226,260,3));
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,5);
//        DG.connect(2,3,5);
//        DG.connect(3,4,5);
//        DG.connect(4,5,5);
//        DG.connect(5,6,5);
//        DG.connect(6,1,5);
//        g.init(DG);
//        assertEquals(true,g.isConnected());
//        Graph_Algo g2 = new Graph_Algo();
//        DGraph DG2 = new DGraph();
//        Node n21 = new Node(new Point3D(100,200,3));
//        Node n22 = new Node(new Point3D (150,200,3));
//        Node n23 = new Node(new Point3D(300,450,3));
//        Node n24 = new Node(new Point3D (450,500,3));
//        Node n25 = new Node(new Point3D (320,600,3));
//        Node n26 = new Node(new Point3D (226,260,3));
//        DG.addNode(n21);
//        DG.addNode(n22);
//        DG.addNode(n23);
//        DG.addNode(n24);
//        DG.addNode(n25);
//        DG.addNode(n26);
//        DG.connect(1,2,5);
//        DG.connect(2,3,5);
//        DG.connect(3,4,5);
//        DG.connect(4,5,5);
//        DG.connect(5,6,5);
//        DG.connect(6,2,5);
//        g.init(DG);
//        assertEquals(false,g.isConnected());
//        Graph_Algo g3 = new Graph_Algo();
//        DGraph DG3 = new DGraph();
//        Node n31 = new Node(new Point3D (100,200,3));
//        Node n32 = new Node(new Point3D (150,200,3));
//        DG3.addNode(n31);
//        DG3.addNode(n32);
//        DG3.connect(1,2,4);
//        DG3.connect(2,1,4);
//        g3.init(DG3);
//        assertEquals(true,g3.isConnected());
//
//    }
//    //------------------- NEED TO LOOKOUT AT CONNECT!! DO NOT CHANGE ID THERE IS CONNECTION!
//    @Test
//    public void shortestPathDist() {
//        Graph_Algo g = new Graph_Algo();
//        DGraph DG = new DGraph();
//        Node n1 = new Node(new Point3D (100,200,3));
//        Node n2 = new Node(new Point3D (150,200,3));
//        Node n3 = new Node( new Point3D (300,450,3));
//        Node n4 = new Node(new Point3D (450,500,3));
//        Node n5 = new Node(new Point3D (320,600,3));
//        Node n6 = new Node(new Point3D (226,260,3));
//        DG.addNode(n1);
//        DG.addNode(n2);
//        DG.addNode(n3);
//        DG.addNode(n4);
//        DG.addNode(n5);
//        DG.addNode(n6);
//        DG.connect(1,2,5);
//        DG.connect(2,3,5);
//        DG.connect(3,4,5);
//        DG.connect(4,5,5);
//        DG.connect(5,6,5);
//        DG.connect(6,1,5);
//        g.init(DG);
//        boolean flag = 25 == g.shortestPathDist(1,6);
//        assertEquals(true,flag);
//        DG.connect(1,2,1);
//        DG.connect(2,3,2);
//        DG.connect(3,4,3);
//        DG.connect(4,5,4);
//        DG.connect(5,6,5);
//        DG.connect(1,6,10);
//        g.init(DG);
//        flag = 10 == g.shortestPathDist(1,6);
//        DG.reset();
//        assertEquals(true,flag);
//        Node b1 = new Node(new Point3D (100,200,3));
//        Node b2 = new Node(new Point3D (150,200,3));
//        Node b3 = new Node(new Point3D (300,450,3));
//        Node b4 = new Node(new Point3D (450,500,3));
//        Node b5 = new Node(new Point3D (320,600,3));
//        Node b6 = new Node(new Point3D (226,260,3));
//        DG.addNode(b1);
//        DG.addNode(b2);
//        DG.addNode(b3);
//        DG.addNode(b4);
//        DG.addNode(b5);
//        DG.addNode(b6);
//        DG.connect(1,2,1);
//        DG.connect(2,3,2);
//        DG.connect(3,4,3);
//        DG.connect(4,5,4);
//        DG.connect(5,6,5);
//        DG.connect(1,6,16);
//        g.init(DG);
//        flag = 15 == g.shortestPathDist(1,6);
//        assertEquals(true,flag);
//    }
//
//    @Test
//    public void shortestPath() {
//        Point3D x = new Point3D(14,4,0);
//        Point3D x2 = new Point3D(-75,14,0);
//        Point3D x3 = new Point3D(80,5,0);
//        Point3D x4 = new Point3D(1,4,0);
//        Point3D x5 = new Point3D(-5,1,0);
//        Point3D x6 = new Point3D(8,3,0);
//        Point3D x7 = new Point3D(4,1,0);
//        Point3D x8 = new Point3D(75,14,0);
//        node_data a1 = new Node(x);
//        Node a2 = new Node(x2);
//        Node a3 = new Node(x3);
//        Node a4 = new Node(x4);
//        Node a5 = new Node(x5);
//        Node a6 = new Node(x6);
//        Node a7 = new Node(x7);
//        Node a8 = new Node(x8);
//        DGraph d = new DGraph();
//        d.addNode(a1);
//        d.addNode(a2);
//        d.addNode(a3);
//        d.addNode(a4);
//        d.addNode(a5);
//        d.addNode(a6);
//        d.addNode(a7);
//        d.addNode(a8);
//        d.connect(a1.getKey(),a2.getKey(),5);
//        d.connect(a1.getKey(),a5.getKey(),2);
//        d.connect(a1.getKey(),a3.getKey(),6);
//        d.connect(a3.getKey(),a4.getKey(),7);
//        d.connect(a2.getKey(),a8.getKey(),8);
//        d.connect(a2.getKey(),a7.getKey(),3);
//        d.connect(a5.getKey(),a6.getKey(),2);
//        d.connect(a6.getKey(),a7.getKey(),3);
//        d.connect(a7.getKey(),a6.getKey(),3);
//        Graph_Algo p = new Graph_Algo();
//        p.init(d);
//        List<Node> ans = new LinkedList<Node>();
//        ans = p.shortestPath(1,7);
//        List<Node> test = new LinkedList<Node>();
//        test.add(a1);
//        test.add(a5);
//        test.add(a6);
//        test.add(a7);
//        assertEquals(test.get(0).getKey(),ans.get(0).getKey());
//        assertEquals(test.get(1).getKey(),ans.get(1).getKey());
//        assertEquals(test.get(2).getKey(),ans.get(2).getKey());
//        assertEquals(test.get(3).getKey(),ans.get(3).getKey());
//    }
//
//    @Test
//    public void TSP() {
//
//    }
//
//    @Test
//    public void copy() {Point3D x = new Point3D(14,4,0);
//        Point3D x2 = new Point3D(-75,14,0);
//        Point3D x3 = new Point3D(80,5,0);
//        Point3D x4 = new Point3D(1,4,0);
//        Point3D x5 = new Point3D(-5,1,0);
//        Point3D x6 = new Point3D(8,3,0);
//        Point3D x7 = new Point3D(4,1,0);
//        Point3D x8 = new Point3D(75,14,0);
//        Node a1 = new Node(x);
//        Node a2 = new Node(x2);
//        Node a3 = new Node(x3);
//        Node a4 = new Node(x4);
//        Node a5 = new Node(x5);
//        Node a6 = new Node(x6);
//        Node a7 = new Node(x7);
//        Node a8 = new Node(x8);
//        DGraph d = new DGraph();
//        d.addNode(a1);
//        d.addNode(a2);
//        d.addNode(a3);
//        d.addNode(a4);
//        d.addNode(a5);
//        d.addNode(a6);
//        d.addNode(a7);
//        d.addNode(a8);
//        d.connect(a1.getKey(),a2.getKey(),5);
//        d.connect(a1.getKey(),a5.getKey(),2);
//        d.connect(a1.getKey(),a3.getKey(),6);
//        d.connect(a3.getKey(),a4.getKey(),7);
//        d.connect(a2.getKey(),a8.getKey(),8);
//        d.connect(a2.getKey(),a7.getKey(),3);
//        d.connect(a5.getKey(),a6.getKey(),2);
//        d.connect(a6.getKey(),a7.getKey(),3);
//        d.connect(a7.getKey(),a6.getKey(),3);
//        Graph_Algo p = new Graph_Algo();
//        p.init(d);
//        graph g = new DGraph();
//        g = p.copy();
//        assertNotEquals(g,p.getGraph());
//    }
//}

package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;


import jdk.nashorn.internal.runtime.ECMAException;
import utils.Point3D;
import org.junit.jupiter.api.Test;


public class Graph_Algo_Testing {
   private Graph_Algo ga=new Graph_Algo();
    private graph g=new DGraph();
    private int mc=0;

    @Test
    void copyGraph() {

        graph currGraph = ga.copy();
        currGraph.removeNode(1);
        assertTrue(currGraph.nodeSize() != g.nodeSize());

        assertTrue(currGraph.edgeSize() != g.edgeSize());
        assertTrue(currGraph != g);
    }

    @Test
    void saveInitFile() {
                g = new DGraph();
        ga = new Graph_Algo();
        mc=this.g.getMC();
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
        g.connect(2,4,20);
        ga.init(g);
        g.removeEdge(6, 5);
        ga.init(g);
        ga.save("junit_test1.txt");
        g.connect(6, 5, 15);
        ga.init("junit_test1.txt");
        System.out.println(g.edgeSize());
        graph currGraph = ga.copy();
        System.out.println(currGraph.edgeSize());
       // assertTrue(currGraph.edgeSize() != g.edgeSize());
        assertTrue(currGraph.getEdge(6, 5) == null);
    }

    @Test
    void shortestPathDist() throws InterruptedException {
      // createGraph();
                g = new DGraph();
        ga = new Graph_Algo();
        mc=this.g.getMC();
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
    //    g.connect(2,4,20);
        ga.init(g);
        double spd = 20; // shortest path distance between 1 and 2
//        GUI_graph a = new GUI_graph((DGraph) g);
//        a.setVisible(true);
        double sho = ga.shortestPathDist(1, 2);
        assertEquals(spd, ga.shortestPathDist(1, 2));

        spd = Double.MAX_VALUE; // shortest path distance between 5 and 3 (No path)
//                GUI_graph b = new GUI_graph((DGraph) g);
//        b.setVisible(true);
//        try {
//            Thread t = new Thread();
//            t.sleep(100000);
//        }catch (Exception E){
//            System.out.println(E);
//        }
        assertEquals(spd, ga.shortestPathDist(5, 3));
        spd = 11; // shortest path distance between 4 and 2
        assertEquals(spd, ga.shortestPathDist(4, 2));
    }

    @Test
    void shortestPath() {
        g = new DGraph();
        ga = new Graph_Algo();
        mc=this.g.getMC();
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
        //    g.connect(2,4,20);
        ga.init(g);
        assertEquals(null, ga.shortestPath(5, 3));
        assertNotEquals(null, ga.shortestPath(1, 2));
    }

    @Test
    void isConnected() {
                g = new DGraph();
        ga = new Graph_Algo();
        mc=this.g.getMC();
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
        //g.connect(5, 2, 6);
        g.connect(6, 5, 15);
        g.connect(2,4,20);
        ga.init(g);


        assertFalse(ga.isConnected());
        g.connect(5, 2, 8);

    }

    @Test
    void TSP() {
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
       // g.connect(3, 1, 15);
        g.connect(6, 4, 15);
      //  g.connect(6, 1, 15);
        //  g.connect(2, 4, 15);
        ga.init(g);


        List <Integer> y = new LinkedList<>();
        y.add(1);
        y.add(2);
        y.add(5);
        y.add(6);

         Collection <node_data> o =    ga.TSP(y);
            assertEquals(null, ga.TSP(y));

            List <Integer> y2 = new LinkedList<>();
            y2.add(4);
            y2.add(3);
            y2.add(6);
            y2.add(1);
           //assertNotEquals(null, ga.TSP(y2));
    }



    }

