package Tests;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import utils.Point3D;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
//
//import dataStructure.DGraph;
//import dataStructure.graph;
//import dataStructure.node;
//import utils.Point3D;

public class Dgraph_Testing {

    @Test
    public void testAddNode() {
        Point3D p1 = new Point3D(0, 0, 0);
        Point3D p2 = new Point3D(0, 1, 2);
        Point3D p3 = new Point3D(1, 2, 0);
        Node n1 = new Node(p1);
        Node n2 = new Node(p2);
        Node n3 = new Node(p3);
        graph g = new DGraph();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        if (g.nodeSize()!=3) { fail(); }
    }

    @Test
    public   void testConnect() {
        Point3D p1 = new Point3D(1, 5, 0);
        Point3D p2 = new Point3D(4, 4, 0);
        Point3D p3 = new Point3D(2, 2, 0);
        Node n1 = new Node(p1);
        Node n2 = new Node(p2);
        Node n3 = new Node(p3);
        graph g = new DGraph();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1.getKey(), n2.getKey(), 2);
        g.connect(n2.getKey(), n3.getKey(), 3);

        if (g.edgeSize()!=2) { fail(); }
        if (g.getEdge(n2.getKey(), n3.getKey()).getWeight()!=3) { fail(); }
    }

    @Test
    public void testRemoveNode() {
        Point3D p1 = new Point3D(1, 5, 0);
        Point3D p2 = new Point3D(4, 4, 0);
        Point3D p3 = new Point3D(2, 2, 0);
        Node n1 = new Node(p1);
        Node n2 = new Node(p2);
        Node n3 = new Node(p3);
        graph g = new DGraph();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1.getKey(), n2.getKey(), 2);
        g.connect(n2.getKey(), n3.getKey(), 3);
        g.removeNode(n2.getKey());
     //  assertNotEquals(0,g.edgeSize());
        try {
            if (g.getEdge(n2.getKey(), n3.getKey())!=null)
                    fail();
        }catch (Exception e) {;}
        try {
            if(g.getEdge(n1.getKey(), n2.getKey())!=null)
                fail();
        }catch (Exception e) {;}
    }

    @Test
    public  void testRemoveEdge() {
        Point3D p1 = new Point3D(1, 5, 0);
        Point3D p2 = new Point3D(4, 4, 0);
        Point3D p3 = new Point3D(2, 2, 0);
        Node n1 = new Node(p1);
        Node n2 = new Node(p2);
        Node n3 = new Node(p3);
        graph g = new DGraph();
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.connect(n1.getKey(), n2.getKey(), 2);
        g.connect(n2.getKey(), n3.getKey(), 3);
      //  System.out.println(g.edgeSize());
        g.removeEdge(n2.getKey(), n3.getKey());

        if (g.edgeSize()!=1) { fail(); }
        if (g.getEdge(n2.getKey(), n3.getKey()) != null) { fail();}
        if (g.getEdge(n1.getKey(), n2.getKey()) == null) { fail();}
    }
}