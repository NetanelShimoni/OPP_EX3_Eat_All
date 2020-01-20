package gameClient;

import Tests.Dgraph_Testing;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import Server.game_service;
import dataStructure.DGraph;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;
import utils.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyGameGui_Manual extends Thread {

    private DGraph g = new DGraph();
    private List<Fruit> fruits = new LinkedList<>();
    private List<Robot_Algo> robot = new LinkedList<>();
    private game_service game;
    private StdDrow_GUI G;
    private node_data n;
    private Robot_Algo r;
    private boolean flag=false;

    /**
     * setter for the node1
     * @param node
     */
    public void setNode(node_data node){
    this.n=node;
}
    public MyGameGui_Manual(game_service game, DGraph g) {
        this.game = game;
        this.g = g;
    }

    /**
     * this function
     * @param x
     * @param y
     * @return
     * @throws JSONException
     */
    private node_data getRobot(double x,double y) throws JSONException {
    this.robot=Robot_Algo.initListRobot(this.game.getRobots());
        for (Robot_Algo algo:this.robot) {
            double nX = algo.getPos().x();
            double nY = algo.getPos().y();
            if ((x < nX + 0.0004) && (x > nX - 0.0004))
                if ((y < nY + 0.0004) && (y > nY - 0.0004)){
                    Point3D ansP = new Point3D(nX, nY, 0);
                    node_data ans = this.g.getNode(algo.getSrc());
                    this.r = algo;
                    return ans;
                }
        }
        return null;
    }


    /**
     * the user press on vertix that he want the robot will go.
     * @param game
     * @param g
     * @throws JSONException
     */

    public void moveRobotManual(game_service game, DGraph g) throws JSONException {
        game = game;
        g = g;
        int size = this.g.allnode.size();
        String [] have_point = new String[size];
        for (int i = 0; i <size ; i++) {
            have_point[i]=""+this.g.allnode.get(i).getKey();
        }
        int How_Many_Robot = Robot_Algo.initFromJson_howmanyrobot(this.game.toString());
        for (int i = 0; i <How_Many_Robot ; i++) {
            String put_Robot = (String) JOptionPane.showInputDialog(null, "Choose a point you want to put the Robot", "You have: "+How_Many_Robot+" Robot to put",JOptionPane.INFORMATION_MESSAGE,null,have_point,have_point[0]);
            int point = Integer.parseInt(put_Robot);
            node_data loc = this.g.getNode(point);
            this.n=loc;
            game.addRobot(loc.getKey());
            StdDraw.picture(loc.getLocation().x(), loc.getLocation().y(),
                    "robot.png", 0.0008, 0.0006);
            StdDraw.show();
        }
        if(StdDraw.isMousePressed()){
            System.out.println("sdsdsdsd");
        }
        this.start();


    }


    /**
     *this function get all the frutis for the specific game from the server and located them on the graph.
     * if the type of the fruit is "-1", a banana will appear on the graph. else, an apple will appear on the graph.
     * the fruits will located in the location that we get from the server for each one.
     * @throws JSONException
     */
    private void fruit_GUI(){
    this.fruits=Fruit.initFromListFruit(this.game.getFruits());
    //StdDraw.clear();
        for (Fruit f:this.fruits) {
            if (f.getType() == -1) {
                StdDraw.picture(f.get_fruit_point().x(), f.get_fruit_point().y(), "banana.png", 0.0008, 0.0006);
            } else {
                StdDraw.picture(f.get_fruit_point().x(), f.get_fruit_point().y(), "apple.jpg", 0.0008, 0.0006);
            }
            StdDraw.disableDoubleBuffering();

        }

    }

    /**
     * this function move the robots in manual way. when the user click on the robot he want to move, there is a mouse
     * listener that know where the user press. than, the user need to press on new vertix that he want the robot will
     * go to. if the user not press on a vertix, he get message that he need to press.
     * again.
     * @param game
     * @param g
     */
    public void moveRobots_Manual(game_service game, DGraph g) {
        List<String> log = game.move();
        double x, y = 0;
        if (log != null) {
            long t = game.timeToEnd();      // we need to check if well run on time or log.size
            for (int i = 0; i < log.size(); i++) {
                String robot_json = log.get(i);
                try {
                    JSONObject line = new JSONObject(robot_json);
                    JSONObject tomove = line.getJSONObject("Robot");
                    int robID = tomove.getInt("id");
                   // System.out.println("rob id: " + robID);
                    int src = tomove.getInt("src");
                    int dest = tomove.getInt("dest");
                    System.out.println(tomove);

                    // which robot to move
                    if (StdDraw.isMousePressed()) {
                        x = StdDraw.mouseX();
                        y = StdDraw.mouseY();
                        Node n = (Node) getRobot(x, y);

                    }


                    if (StdDraw.isMousePressed()) {
                        x = StdDraw.mouseX();
                        y = StdDraw.mouseY();
                        Node n = (Node) findNode(x,y);
                        System.out.println(n.getKey());
                        edge_data p = this.g.getEdge(src, n.getKey());   // edge neighbors of the robot

                        if (n == null ||  p == null) {
                            System.out.println("Please try again");
                        } else {
                            if (dest == -1){
                                game.chooseNextEdge(this.r.getId(), n.getKey());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * this function run the game by a thread. as long as the gme does not end, the graph is constantly updated.
     */
    public void run() {
        this.game.startGame();
        JOptionPane.showMessageDialog(null, "You should press the robot you want to move, and than on the vertix you want to move in");
        StdDraw.uptadeGraph(this.g);
        fruit_GUI();
        StdDraw.show();
        while (this.game.isRunning()) {
            //StdDraw.uptadeGraph(this.g);
            //StdDraw.show();
            moveRobots_Manual(this.game,this.g);

            try {
                StdDraw.R_GUI(this.game, this.robot);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            fruit_GUI();
            StdDraw.show();


            fruit_GUI();
            StdDraw.uptadeGraph(this.g);
            StdDraw.show();
            try {
                StdDraw.uptadeGraph(this.g);
                StdDraw.show();
                sleep(10);
                StdDraw.uptadeGraph(this.g);
                StdDraw.show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            }



    /**
     * we get x and y that the user press, and find the vertix
     * @param x
     * @param y
     * @return
     */
    private node_data findNode(double x, double y){
        Collection<node_data> temp = this.g.getV();
        for (node_data node: temp) {
            if(x>=node.getLocation().x()-0.0004 && x<= node.getLocation().x()+0.0004 && y>=node.getLocation().y()-0.0004 && y<=node.getLocation().y()+0.0004) return node;
        }
        return null;
    }
}



