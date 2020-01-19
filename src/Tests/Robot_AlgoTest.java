package Tests;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import gameClient.Fruit;
import gameClient.Robot_Algo;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class Robot_AlgoTest extends Graph_Algo {
DGraph gg=new DGraph();


    @Test
    void initFromJson_howmanyrobot() throws JSONException {
        int exepted=1;
        game_service game = Game_Server.getServer(1);
        String y = game.toString();
        JSONObject obj = new JSONObject(y);
        JSONObject array_robots = obj.getJSONObject("GameServer");
        int how_many_robot = array_robots.getInt("robots");
            assertEquals(exepted,how_many_robot);
    }

    @Test
    void initListRobot() throws JSONException {
        game_service game = Game_Server.getServer(1);
        game.addRobot(1);
        game.startGame();
        List <String> s1 = game.move();
        String s=s1.get(0);
        JSONObject obj = new JSONObject(s);
        JSONObject array_robots = obj.getJSONObject("Robot");
        int id = array_robots.getInt("id");
        double value =array_robots.getInt("value");
        int src =array_robots.getInt("src");
        int des = array_robots.getInt("dest");
        String s_p =array_robots.getString("pos");
        Point3D p = new Point3D(s_p);
        Robot_Algo r= new Robot_Algo(id,p," ",value);
        assertEquals(r.getId(),id);
        assertEquals(r.getPos(),p);
        assertEquals(r.getRank(),value,0.0000001);

    }


    @Test
    void initFromjson() {

    }

    @Test
    void getSrc() {
    }



    @Test
    void placeRobot() {
        game_service game = Game_Server.getServer(1);
        game.addRobot(1);
        game.startGame();
        List <String> frStrings = game.getFruits();
        String t = frStrings.get(0);
        List <Fruit> temp =new LinkedList<>();
        Fruit a =new Fruit();
        try {
            JSONObject obj = new JSONObject(t);
            JSONObject array_fruit = obj.getJSONObject("Fruit");
            int type;
            double value;
            String location_s;
            Point3D location;
            value = array_fruit.getDouble("value");
            location_s = array_fruit.getString("pos");
            location = new Point3D(location_s);
            type = array_fruit.getInt("type");
            a = new Fruit(value, type, location);
            temp.add(0,a);
            assertEquals(a.get_fruit_point(),location);
            assertEquals(a.getType(),type);
            assertEquals(value,5.0,0.0000001);
        } catch (Exception E) {
            E.printStackTrace();
        }
gg.init(game.getGraph());
        node_data n=Robot_Algo.placeRobot(a,gg);
        assertEquals(8,n.getKey());
        assertNotEquals(a.get_fruit_point(),n.getLocation());

    }

    @Test
    void getPos() throws JSONException {
        game_service game = Game_Server.getServer(1);
        game.addRobot(1);
        game.startGame();
        List <String> s1 = game.move();
        String s=s1.get(0);
        JSONObject obj = new JSONObject(s);
        JSONObject array_robots = obj.getJSONObject("Robot");
        int id = array_robots.getInt("id");
        double value =array_robots.getInt("value");
        int src =array_robots.getInt("src");
        int des = array_robots.getInt("dest");
        String s_p =array_robots.getString("pos");
        Point3D p = new Point3D(s_p);
        Robot_Algo r= new Robot_Algo(id,p," ",value);
        gg.init(game.getGraph());
        assertEquals(r.getPos(),gg.getNode(1).getLocation());


    }


}