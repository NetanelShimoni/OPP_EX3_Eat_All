package gameClient;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import com.sun.java.swing.plaf.gtk.GTKConstants;
import com.sun.tools.xjc.reader.xmlschema.RawTypeSetBuilder;
import dataStructure.*;
import javazoom.jl.player.Player;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.FREE_MEM;
import utils.Point3D;
import utils.StdDraw;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MyGameGUI extends Thread {
    private node_data n;
    private DGraph g = new DGraph();
    private List<Fruit> fruits = new LinkedList<>();
    private List<Robot_Algo> robot = new LinkedList<>();
    private game_service game;
    private StdDrow_GUI G;
    private StdDraw std;
    private MyGameGui_Manual Manual;
    private Graph_Algo ga = new Graph_Algo();
    private static KML_Logger kml = new KML_Logger();
    static String[] p = {"Manual game", "Automatic game"};
    static String gameSelct;
    public static int num_game = 0;
    private Robot_Algo r;
    private edge_data mindes;


    public static void main(String[] args) throws InterruptedException, JSONException {
        MyGameGUI game = new MyGameGUI();
        game.start_game();
    }

    /**
     * Moves each of the robots along the edge,
     * in case the robot is on a node the next destination (next edge) is chosen (randomly).
     *
     * @param game
     * @param gg
     */
    void moveRobots(game_service game, DGraph gg) {
        List<String> log = game.move();
        if (log != null) {
            long t = game.timeToEnd();
            for (int i = 0; i < log.size(); i++) {
//             while(game.getRobots().get(i).lastIndexOf("-")<0)
//                   game.move();
                String robot_json = log.get(i);
                try {
                    JSONObject line = new JSONObject(robot_json);
                    JSONObject ttt = line.getJSONObject("Robot");
                    int rid = ttt.getInt("id");
                    int src = ttt.getInt("src");
                    int dest = ttt.getInt("dest");
                    int speed = ttt.getInt("speed");
                    // System.out.println("speed:"+speed);
                    this.r = Robot_Algo.initFromjson(log.get(i));
                    if (dest == -1) {
                        System.out.println(game.getRobots() + "this");
                        dest = nextNode(this.g,src);
//                        if (src == mindes.getSrc()) {
//                            Fruit r = getFruit(mindes);
//                            //System.out.println(r.get_fruit_point()+"this my locetion");
//                            node_data src_node = g.getNode(src);
//                            node_data dest1 = g.getNode(mindes.getDest());
//                           double disA_B = Math.sqrt(Math.pow(src_node.getLocation().x() - dest1.getLocation().x(), 2) +
//                                    Math.pow(dest1.getLocation().y() - src_node.getLocation().y(), 2));
//                           double disF_B = Math.sqrt(Math.pow(r.get_fruit_point().x() - src_node.getLocation().x(), 2) +
//                                    Math.pow(r.get_fruit_point().y() - src_node.getLocation().y(), 2));
//                            double ans = disF_B / disA_B;
//                        }
                    /*    if (src == mindes.getDest()) {
                            Fruit r = getFruit(mindes);
                            //System.out.println(r.get_fruit_point()+"this my locetion");
                            node_data src_node = g.getNode(src);
                            node_data dest1 = g.getNode(mindes.getSrc());
                            double disA_B = Math.sqrt(Math.pow(src_node.getLocation().x() - dest1.getLocation().x(), 2) +
                                    Math.pow(dest1.getLocation().y() - src_node.getLocation().y(), 2));
                            double disF_B = Math.sqrt(Math.pow(r.get_fruit_point().x() - src_node.getLocation().x(), 2) +
                                    Math.pow(r.get_fruit_point().y() - src_node.getLocation().y(), 2));
                            double ans = disF_B / disA_B;
                        }*/
                        //dt=(Math.random()*1)/speed;
//                        if (num_game==9) {
//                            for (Fruit y : this.fruits) {
//                                List<node_data> list = this.ga.shortestPath
//                                        (src, Robot_Algo.placeRobot(y, g).getKey() +1);
//                                if (list==null ) {
//                                    list = this.ga.shortestPath(src,23);
//                                }
//                                Iterator it = list.iterator();
//                                int vv = 0;
//                                while (it.hasNext()) {
//                                    node_data o = (node_data) it.next();
//                                    vv++;
//                                    if (vv > 1) {
//                                        game.chooseNextEdge(rid, o.getKey());
//                                    }
//                                }
//                                vv=0;
//
//
//                            }
//                            break;
//
//                        }
                        game.chooseNextEdge(rid, dest);
                        System.out.println("Turn to node: " + dest + "  time to end:" + (t / 1000));
                        System.out.println(ttt);
                    }
                    // game.move();
                    count++;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int count = 0;

    /**
     * get a Fruit and find the node that close to the Fruit
     */
    private node_data findNodeFruit(Fruit a) {
        Point3D p = Point3D.ORIGIN;
        Iterator it = g.getV().iterator();
        if (it != null) {
            while (it.hasNext()) {
                node_data n = (node_data) it.next();
                List<edge_data> e = new LinkedList<>(g.getE(n.getKey()));
                Iterator iter_edge = e.iterator();
                while (iter_edge.hasNext()) {
                    edge_data eg = (edge_data) iter_edge.next();
                    Node src = (Node) g.getNode(eg.getSrc());
                    Node dest = (Node) g.getNode(eg.getDest());
                    double dis = Math.sqrt(Math.pow(src.getLocation().x() - dest.getLocation().x(), 2) +
                            Math.pow(src.getLocation().y() - dest.getLocation().y(), 2));
                    double dis1 = Math.sqrt(Math.pow(src.getLocation().x() - a.get_fruit_point().x(), 2) +
                            Math.pow(src.getLocation().y() - a.get_fruit_point().y(), 2));
                    double dis2 = Math.sqrt(Math.pow(a.get_fruit_point().x() - dest.getLocation().x(), 2) +
                            Math.pow(a.get_fruit_point().y() - dest.getLocation().y(), 2));
                    if ((dis2 + dis1) - dis <= 0.00001) {
                        return dest;
                    }
                }
            }
        }
        return (Node) g.allnode.get(0);

    }

    /**
     * function that return all the edges that there is fruit on them
     *
     * @return
     */
    public List<edge_data> edges_have_fruit() {
        List<edge_data> l = new LinkedList<>();
        this.fruits = Fruit.initFromListFruit(this.game.getFruits());
        for (Fruit f : this.fruits) {
            l.add(reciveEdge(f));
        }
        return l;
    }

    //    /**
//     * this function return the egde the fruit located on
//     * @param F
//     * @return
//     */
    public Fruit getFruit(edge_data e) {
        node_data src = this.g.getNode(e.getSrc());
        node_data dest = this.g.getNode(e.getDest());
        double dis = Math.sqrt(Math.pow(src.getLocation().x() - dest.getLocation().x(), 2) +
                Math.pow(src.getLocation().y() - dest.getLocation().y(), 2));
        for (Fruit fruit : this.fruits) {
            double dis1 = Math.sqrt(Math.pow(src.getLocation().x() - fruit.get_fruit_point().x(), 2) +
                    Math.pow(src.getLocation().y() - fruit.get_fruit_point().y(), 2));
            double dis2 = Math.sqrt(Math.pow(dest.getLocation().x() - fruit.get_fruit_point().x(), 2) +
                    Math.pow(dest.getLocation().y() - fruit.get_fruit_point().y(), 2));
            if (Math.abs(dis1 + dis2 - dis) < 0.005) {
                return fruit;
            }
        }
        return this.fruits.get(0);


    }

    public edge_data reciveEdge(Fruit F) {

        List<node_data> l = new LinkedList<>(this.g.getV());
        for (node_data n : l) {
            if (this.g.getE(n.getKey()) != null) {
                Iterator iter = this.g.getE(n.getKey()).iterator();
                if (iter != null) {
                    while (iter.hasNext()) {
                        edge_data e = (edge_data) iter.next();
                        node_data src = this.g.getNode(e.getSrc());
                        node_data des = this.g.getNode(e.getDest());
                        double des1 = Math.sqrt(Math.pow(src.getLocation().x() - F.get_fruit_point().x(), 2) +
                                Math.pow(src.getLocation().y() - F.get_fruit_point().y(), 2));
                        double des2 = Math.sqrt(Math.pow(des.getLocation().x() - F.get_fruit_point().x(), 2) +
                                Math.pow(des.getLocation().y() - F.get_fruit_point().y(), 2));
                        double des3 = Math.sqrt(Math.pow(src.getLocation().x() - des.getLocation().x(), 2) +
                                Math.pow(src.getLocation().y() - des.getLocation().y(), 2));
                        double ans = (des1 + des2) - des3;
                        if (Math.abs(ans) <= Robot_Algo.EPS1) {

                            if (F.getType() == -1 && src.getKey() > des.getKey())
                                return e;
                            if (F.getType() == 1 && src.getKey() < des.getKey())
                                return e;
                        }
                    }


                }


            }

        }

        return null;
    }


    /**
     * deside in random way witch node the robot will go
     *
     * @param g-the   graph we work on
     * @param src-the node that the robot is located in the biginning
     * @return num of dest node
     */
    private int nextNode(DGraph g, int src) {
        double temp = 10000;
        List<edge_data> edge_fruit = new LinkedList<>();
        this.mindes = new Edge();
        double min = Integer.MAX_VALUE;
        node_data n = g.getNode(src);
        Fruit o = null;
//        this.fruits = Fruit.initFromListFruit(this.game.getFruits());
        edge_fruit = (LinkedList<edge_data>) edges_have_fruit();

        for (edge_data e : edge_fruit) {
            if (e != null) {
                temp = this.ga.shortestPathDist(src, e.getDest());
                if (temp < min) {
                    min = temp;
                    mindes = e;
                }
            }
        }

        int dest_src = mindes.getSrc();
        if (mindes.getSrc() == src) {
            dest_src = mindes.getDest();
            return mindes.getDest();
        }
        if (mindes.getDest()==src){
            dest_src=mindes.getSrc();
            return mindes.getSrc();

        }
        List<node_data> short1 = this.ga.shortestPath(src, dest_src);

//        for (int i = 0; i < short1.size(); i++) {
//            System.out.println(" this is my vertix: " + short1.get(i).getKey());
//        }

        if (num_game == 1) {
            dt = 35;
            game.move();
            count++;
        }
        if (num_game == 3) {
            dt = 125;
        }

        if (short1.get(0).getKey() == 2 && short1.size() == 2) {
            how_many++;
            if (how_many > 2) {
                how_many = 0;
                return 1;
            }

        }
        if (short1.get(0).getKey() == 3 && short1.size() == 2) {
            how_many++;
            if (how_many > 2) {
                how_many = 0;
                return 4;
            }
        }


        if (num_game == 9) {
            if (count <= 2) {
                return 23;
            }
            if (count > 400 && count < 407) {
                return 28;
            }
            if (src == 28) {
                return 29;
            }
            dt = 0;
            game.move();
            count++;

            if (short1.get(0).getKey() == 9 && short1.size() < 3) {
                how_many++;
                game.move();
                count++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 23;
                }
            }
            if (short1.get(0).getKey() == 28 && short1.size() == 2) {
                how_many++;
                game.move();
                count++;
                if (how_many >= 2) {
                    how_many = 0;

                    return 4;
                }
            }
            if (short1.get(0).getKey() == 0 && short1.size() == 2) {
                how_many++;
                game.move();
                count++;
                if (how_many >= 2) {
                    how_many = 0;

                    return 21;
                }
            }
            if (short1.get(0).getKey() == 5 && short1.size() == 2) {
                how_many++;

                if (how_many >= 2) {
                    how_many = 0;

                    return 6;
                }
            }
            if (short1.get(0).getKey() == 4 && short1.size() == 3) {
                how_many++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 28;
                }
            }
            if (short1.get(0).getKey() == 4 && short1.size() == 2) {
                how_many++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 28;
                }
            }
            if (short1.get(0).getKey() == 4 && short1.size() == 1) {
                how_many++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 28;
                }
            }
            if (short1.get(0).getKey() == 5 && short1.size() == 2) {
                how_many++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 6;
                }
            }
        }
        if (num_game == 0) {
            dt = 32;
            if (short1.get(0).getKey() == 0 && short1.size() == 2) {
                how_many++;
                if (how_many >= 2) {
                    how_many = 0;
                    return 10;
                }
            }
        }
        if (num_game == 5) {
            if (short1.get(0).getKey() == 5 && short1.size() == 2) {
                how_many++;
                if (how_many >= 10)
                    how_many = 0;
                return 4;
            }
            if (short1.get(0).getKey() == 1 && short1.size() == 2) {
                how_many++;
                if (how_many >= 4) {
                    return 2;
                }
            }

            dt = 22;
            //this.game.move();
            //count++;
            //&& short1.get(0).getKey()==4
            if (short1.size() == 2) {
                dt = 9;
                return short1.get(1).getKey();
            }
            if (how_many >= 3) {
                how_many = 0;
                return 5;
            }

        }
        if (num_game == 1 && short1.size() == 2 || num_game == 3 && short1.size() == 2) {

            how_many++;
            short1 = this.ga.shortestPath(dest_src, src);
            if (how_many >= 3 && short1.get(1).getKey() == 0) {
                how_many = 0;
                dt = 40;
                return 10;
            }

            return short1.get(0).getKey();


        }


        //how_many = 0;

        if (mindes != null) {
            short1.add(this.g.getNode(mindes.getDest()));

        }

        if (short1 != null)
            return short1.get(1).getKey();
        else
            return -1;


//            for (int i = 0; i <path.size() ; i++) {
//                System.out.println(path.get(i).getKey());
//            }
//            System.out.println("finish");
//            if (path != null)
//                return path.get(1).getKey();
//        }

//
//
//        return -1;
//    }
    }
    public int how_many = 0;

    /**
     * init all the game. the user choose wich graph he want to play(we have 23 diffrent graphs).
     * then, he choose if he want to play in manual verison or automatic verison.
     * if the user choose the automatic verison-the game will open amd the robots will move automatically and eat fruit
     * until the game will end.
     * if the user choose the manual verison, the game will start with robots and fruits that already located on the graph,
     * and by one click on one of the robots, and one click on a vertex that close to the robot, the robot will  move.
     *
     * @throws InterruptedException
     * @throws JSONException
     */
    public static Thread t1;
public static int id_integer=0;
    public void start_game() throws InterruptedException, JSONException {
        StdDraw.setCanvasSize(2000, 1500);
        StdDraw.setXscale(-51, 50);
        StdDraw.setYscale(-51, 50);
        StdDraw.picture(0, 0, "ddd.jpg");
        String id =JOptionPane.showInputDialog(new ImageIcon("robot.jpg"), "Input your Id to login game");
         id_integer = Integer.parseInt(id);
        Game_Server.login(id_integer);
        String s = JOptionPane.showInputDialog(new ImageIcon("robot.jpg"), "Choose a fild game");
        num_game = Integer.parseInt(s);
        System.out.println(num_game);

        this.game = Game_Server.getServer(num_game);
        System.out.println(this.game.getFruits());
        this.g.init(this.game.getGraph());
//        this.g.connect(8,4,1);
//        this.g.connect(4,8,1);
        this.ga.init(this.g);
        Object game_select = JOptionPane.showInputDialog(null, "Choose a mood", "Note", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("robot_tec.jpg"), p, p[0]);
        gameSelct = (String) game_select;
        this.fruits = Fruit.initFromListFruit(this.game.getFruits());
        this.std = new StdDraw(this.g, this.game, this.fruits);
        //StdDraw.clear();
        this.G = new StdDrow_GUI(this.g, this.fruits);
        //StdDraw.picture(0, 0, "back.jpg");
        this.G.show();
        int How_Many_Robot = Robot_Algo.initFromJson_howmanyrobot(this.game.toString());

        if (game_select == "Manual game") {
            this.Manual = new MyGameGui_Manual(this.game, this.g);
            this.Manual.moveRobotManual(this.game, this.g);
            return;

            /// this.robot=Robot_Algo.initListRobot();
            //this.game.addRobot();
        } else {//this game Auto
       /*     for (int i = 0; i < How_Many_Robot; i++) {
                node_data n = Robot_Algo.placeRobot(this.fruits.get(i), this.g);
                this.game.addRobot(n.getKey());
            }*/
            int size = this.g.allnode.size();
            String[] have_point = new String[size];
            for (int i = 0; i < size; i++) {
                have_point[i] = "" + this.g.allnode.get(i).getKey();
            }
            int How_Many_Robot1 = Robot_Algo.initFromJson_howmanyrobot(this.game.toString());
            for (int i = 0; i < How_Many_Robot; i++) {
                String put_Robot = (String) JOptionPane.showInputDialog(null, "Choose a point you want to put the Robot", "You have: " + How_Many_Robot1 + " Robot to put", JOptionPane.INFORMATION_MESSAGE, null, have_point, have_point[0]);
                int point = Integer.parseInt(put_Robot);
                node_data loc = this.g.getNode(point);
                this.n = loc;
                game.addRobot(loc.getKey());
                StdDraw.picture(loc.getLocation().x(), loc.getLocation().y(),
                        "robot.png", 0.0008, 0.0006);
                StdDraw.show();
            }


            this.robot = Robot_Algo.initListRobot(this.game.getRobots());
            this.std.setListRobot(this.robot);
            for (Robot_Algo r : this.robot) {
                StdDraw.picture(r.getPos().x(), r.getPos().y(),
                        "robot.png", 0.0008, 0.0006);
            }
            //this.start();
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start thread to KML");
                    try {
                        kml.objKML();
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            this.start();


        }

    }


    /**
     * this function get all the frutis for the specific game from the server and located them on the graph.
     * if the type of the fruit is "-1", a banana will appear on the graph. else, an apple will appear on the graph.
     * the fruits will located in the location that we get from the server for each one.
     *
     * @throws JSONException
     */

    private void fruit_GUI() throws JSONException {
        this.fruits = Fruit.initFromListFruit(this.game.getFruits());
        //StdDraw.clear();
        for (Fruit f : this.fruits) {
            if (f.getType() == -1) {
                StdDraw.picture(f.get_fruit_point().x(), f.get_fruit_point().y(), "banana.png", 0.0008, 0.0006);
            } else {
                StdDraw.picture(f.get_fruit_point().x(), f.get_fruit_point().y(), "apple.jpg", 0.0008, 0.0006);
            }
            StdDraw.disableDoubleBuffering();

        }
    }

    /**
     * this runnable refresh the gui
     */
    public double dt = 138;

    public void run() {
        this.game.startGame();

        while (this.game.isRunning()) {
            moveRobots(this.game, this.g);

            try {
                fruit_GUI();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (num_game == 3) {
                    game.move();
                    count++;
                }
                if (num_game == 9 && count < 300) {
                    game.move();
                    count++;
                }
                fruit_GUI();
                StdDraw.show();
                StdDraw.R_GUI(this.game, this.robot);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //move robots.
            //Repaint.
            this.G.uptadeGraph(this.g);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(35.21310882485876, 32.10788938151261 - 0.0015, "Move: " + count);

            StdDraw.text(35.21310882485876, 32.10788938151261 + 0.0015, "Time to End: " + this.game.timeToEnd() / 1000);
            StdDraw.show();

            StdDraw.show();
            try {
                System.out.println("dt is: " + dt);
                sleep((long) dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("moves: " + count);
        System.out.println("game is over" + this.game.toString());
        SimpleDB.printLog();
    }


    public static DGraph gg;

    /**
     * this function place all the robots  that we get from the server on the graph.
     * all the robots are located by the location that we get from the server.
     *
     * @param game
     * @param robots
     * @throws JSONException
     */
    public static void R_GUI(game_service game, List<Robot_Algo> robots) throws JSONException {
        //StdDraw.clear();
        StdDraw.Mystd.enableDoubleBuffering();
        // StdDraw.Mystd.clear();
        // StdDraw.uptadeGraph(gg);
        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        List<String> log = game.getRobots();
        robots = Robot_Algo.initListRobot(log);
        for (Robot_Algo r : robots) {
            StdDraw.picture(r.getPos().x(), r.getPos().y(),
                    "robot.png", 0.0008, 0.0006);
        }

    }
}








