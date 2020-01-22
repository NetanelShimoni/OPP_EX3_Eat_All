package gameClient;

import dataStructure.DGraph;
import org.json.JSONObject;
import utils.Point3D;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Fruit {
    private ArrayList<Fruit> f = new ArrayList<>();
    private DGraph g = new DGraph();
    private double value;
    private int type,tag=0;
    private Point3D loc;


    /**
     * constractor:
     * this constractor init a fruit
     *
     * @param value
     * @param type
     * @param loc
     */

    public Fruit(double value, int type, Point3D loc,int tag) {
        this.value = value;
        this.type = type;
        this.loc = loc;
        this.tag=tag;
    }


    public Fruit() {

    }
    public void setPos(Point3D p){
        this.loc=p;
    }
    public void setTag(int v){
        this.tag=v;
    }

    /**
     *
     * @param a
     * @return List of fruit from String
     */
    public static List <Fruit>  initFromListFruit(List <String> a){
        List <Fruit> temp =new LinkedList<>();

        for (String  s:a) {
            temp.add(initFromJson(s));
        }
        return temp;
    }

    /**
     * this function init from string a Fruit object. we create a Json object that containinng all the details of the
     * fruit and get all the parameters from the object the object
     * from the Json object.
     * @param t
     * @return a
     */
    public static Fruit initFromJson(String t) {
        Fruit a=new Fruit();
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

        } catch (Exception E) {
            E.printStackTrace();
        }
        return a;

    }

//    public ArrayList<Point2D> save_location_fruits()
//    {
//
//        for(int i=0;i<this.f.size();i++)
//        {
//            pos.add(this.f.get(i).get_fruit_point());
//        }
//        return pos;
//    }

    /**
     * this function return the list of all the Fruits
     * @return f
     */
    public ArrayList<Fruit> getF() {
        return f;
    }

    /**
     * this function set the list of the Fruit
     * @param f
     */
    public void setF(ArrayList<Fruit> f) {
        this.f = f;
    }

    /**
     * this function return the location of the Fruit
     * @return loc
     */

    /**
     * this function return the type of the fruit.
     * @return
     */
    public int getType(){
        return this.type;
    }
    public int getTag(){
        return this.tag;
    }
  public Point3D  get_fruit_point()
  {
      return this.loc;
  }

}
