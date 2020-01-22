package Tests;

;
import Server.Game_Server;
import Server.game_service;
import gameClient.Fruit;
import org.json.JSONObject;
import org.junit.Test;
import utils.Point3D;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

 public class FruitTesting {
    @Test
    public void initFromListFruit() {
        game_service game = Game_Server.getServer(1);
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
            a = new Fruit(value, type, location,0);
            temp.add(0,a);
            assertEquals(a.get_fruit_point(),location);
            assertEquals(a.getType(),type);
            assertEquals(value,5.0,0.0000001);
        } catch (Exception E) {
            E.printStackTrace();
        }


    }



    @Test
    public void setPos() {
        Fruit f1 = new Fruit();
        Fruit f2 = new Fruit();
        Fruit f3 = new Fruit();
        Point3D p1 = new Point3D(1, 2, 0);
        Point3D p2 = new Point3D(2, 4, 0);
        Point3D p3 = new Point3D(5, 6, 0);
        f1.setPos(p1);
        f2.setPos(p2);
        f3.setPos(p3);
        assertEquals(p3, f3.get_fruit_point());
        assertEquals(p2, f2.get_fruit_point());
        assertEquals(p1, f1.get_fruit_point());
    }

    @Test
    public void getPos() {
        Fruit f1 = new Fruit();
        Fruit f2 = new Fruit();
        Fruit f3 = new Fruit();
        Point3D p1 = new Point3D(1, 2, 0);
        Point3D p2 = new Point3D(2, 4, 0);
        Point3D p3 = new Point3D(5, 6, 0);
        f1.setPos(p1);
        f2.setPos(p2);
        f3.setPos(p3);
        assertEquals(p3, f3.get_fruit_point());
        assertEquals(p2, f2.get_fruit_point());
        assertEquals(p1, f1.get_fruit_point());
    }

}