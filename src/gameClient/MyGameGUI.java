package gameClient;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import com.sun.java.swing.plaf.gtk.GTKConstants;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyGameGUI extends Thread {

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
                String robot_json = log.get(i);
                try {
                    JSONObject line = new JSONObject(robot_json);
                    JSONObject ttt = line.getJSONObject("Robot");
                    int rid = ttt.getInt("id");
                    int src = ttt.getInt("src");
                    int dest = ttt.getInt("dest");

                    if (dest == -1) {
                        dest = nextNode(gg, src);
                        game.chooseNextEdge(rid, dest);
                        System.out.println("Turn to node: " + dest + "  time to end:" + (t / 1000));
                        System.out.println(ttt);
                    }
                    game.move();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    private void F_GUI(){
////        List<String> s = this.game.getFruits();
////        Fruit y = new Fruit();
////        for ( String t: s) {
////            List <Fruit> i=y.initFromJson(t);
////            this.fruits.addAll(i);
//        }
//
//        for (Fruit t: fruits) {
//            if(t.getType()==-1) {
//                StdDraw.picture(t.get_fruit_point().x(), t.get_fruit_point().y(), "banana.png", 0.0008, 0.0006);
//
//            }
//
//            else{
//
//                StdDraw.picture(t.get_fruit_point().x(), t.get_fruit_point().y(),"apple.jpg",0.0008,0.0006);
//            }
//        }
//
//    }

    private node_data findEdgeFruit(Fruit a) {
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
                    if ((dis2 + dis1) - dis <= Robot_Algo.EPS1) {
                        return dest;
                    }
                }
            }
        }
        return (Node) g.allnode.get(0);

    }

    /**
     * deside in random way witch node the robot will go
     *
     * @param g-the   graph we work on
     * @param src-the node that the robot is located in the biginning
     * @return num of dest node
     */
    private int nextNode(DGraph g, int src) {
        int ans = -1;
        Collection<edge_data> ee = g.getE(src);
        Iterator<edge_data> itr = ee.iterator();
        int s = ee.size();
        int r = (int) (Math.random() * s);
        int i = 0;
        while (i < r) {
            itr.next();
            i++;
        }
        ans = itr.next().getDest();
        return ans;
    }

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
    public void start_game() throws InterruptedException, JSONException {
        StdDraw.setCanvasSize(2000, 1500);
        StdDraw.setXscale(-51, 50);
        StdDraw.setYscale(-51, 50);
        StdDraw.picture(0, 0, "ddd.jpg");
        String s = JOptionPane.showInputDialog(new ImageIcon("robot.jpg"), "Choose a fild game");
        num_game = Integer.parseInt(s);
        this.game = Game_Server.getServer(num_game);
        System.out.println(this.game.getFruits());
        this.g.init(this.game.getGraph());
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
            for (int i = 0; i < How_Many_Robot; i++) {
                node_data n = Robot_Algo.placeRobot(this.fruits.get(i), this.g);
                this.game.addRobot(n.getKey());
            }

            this.robot = Robot_Algo.initListRobot(this.game.getRobots());
            this.std.setListRobot(this.robot);
            for (Robot_Algo r : this.robot) {
                StdDraw.picture(r.getPos().x(), r.getPos().y(),
                        "robot.png", 0.0008, 0.0006);
            }
            this.game.startGame();
            //this.start();
            Thread t1 = new Thread(new Runnable() {
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
    public void run() {
        while (this.game.isRunning()) {
            try {
                fruit_GUI();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                StdDraw.R_GUI(this.game, this.robot);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //move robots.
            moveRobots(this.game, this.g);
            //Repaint.
            this.G.uptadeGraph(this.g);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(35.21310882485876, 32.10788938151261 + 0.0015, "Time to End: " + this.game.timeToEnd() / 1000);
            StdDraw.show();

            int score = 0;

            StdDraw.show();
            try {
                sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //  System.out.println("game is over" + this.game1.toString());
        }

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








