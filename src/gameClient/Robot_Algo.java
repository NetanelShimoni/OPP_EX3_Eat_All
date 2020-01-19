package gameClient;

import dataStructure.*;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Robot_Algo {
    public static final double EPS1 = 0.001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private int id,src;
    private Point3D pos;
    private String pic;
    private double rank;



    public Robot_Algo(){
        this.pic=" ";
        this.rank=0;
        this.pos=Point3D.ORIGIN;
        this.id=0;
    }

    public Robot_Algo(int id,Point3D p, String pic,double rank){
        this.id=id;
        this.pic=pic;
        this.pos=p;
        this.rank=rank;
    }
    public static int initFromJson_howmanyrobot(String s) throws JSONException {
        JSONObject obj = new JSONObject(s);
        JSONObject array_robots = obj.getJSONObject("GameServer");
       int how_many_robot = array_robots.getInt("robots");
       return how_many_robot;
    }
    public static List<Robot_Algo> initListRobot (List <String> str) throws JSONException {
     List <Robot_Algo> robots = new LinkedList<>();
        for (String s:str) {
            robots.add(initFromjson(s));
        }
        return robots;
    }
    public void setSrc(int src){
        this.src=src;
    }
    public static Robot_Algo initFromjson(String s) throws JSONException {
        Robot_Algo r= new Robot_Algo();
        JSONObject obj = new JSONObject(s);
        JSONObject array_robots = obj.getJSONObject("Robot");
        int id = array_robots.getInt("id");
        double value =array_robots.getInt("value");
        int src =array_robots.getInt("src");
        int des = array_robots.getInt("dest");
        String s_p =array_robots.getString("pos");
        Point3D p = new Point3D(s_p);
        r.src=src;
        r.id=id;
        r.pos=p;
        r.pic="robot.png";
        r.rank=value;
        return r;
    }
public int getSrc(){
        return this.src;
}

    /**
     * this function locate the robot at the biggining near the fruits
     * @param a
     * @param g
     * @return
     */
    public static Node placeRobot(Fruit a, DGraph g){
        Point3D p= Point3D.ORIGIN;
        Iterator it = g.getV().iterator();
        if (it!=null){
        while (it.hasNext()){
            node_data n = (node_data) it.next();
            List <edge_data> e = new LinkedList<>(g.getE(n.getKey())) ;
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
                if ((dis2 + dis1) - dis <= EPS1) {
                    return src;
                }
            }
        }}
        return (Node) g.allnode.get(0);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point3D getPos() {
        return pos;
    }

    public void setPos(Point3D pos) {
        this.pos = pos;
    }

    public String getS() {
        return pic;
    }

    public void setS(String s) {
        this.pic = s;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }


    }