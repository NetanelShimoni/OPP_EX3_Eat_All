package gameClient;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import de.micromata.opengis.kml.v_2_2_0.*;

import org.json.JSONException;
import utils.StdDraw;

public class KML_Logger {

    /**
     * create a kml file so we can see the path that the robot go in google earth
     * @throws ParseException
     * @throws InterruptedException
     * @throws JSONException
     */
    public void objKML() throws ParseException, InterruptedException, JSONException {
        Kml kml = new Kml();
        Document d = kml.createAndSetDocument();
        int i = 0;
        if (StdDraw.gameGui!=null && StdDraw.gameGui!=null){
            while (StdDraw.gameGui.isRunning()){
                Thread.sleep(100);
                i++;
                List<Robot_Algo> r = Robot_Algo.initListRobot(StdDraw.gameGui.getRobots());
                List<Fruit> f =Fruit.initFromListFruit(StdDraw.gameGui.getFruits());
                for(Robot_Algo rb : r){
                    Placemark p = d.createAndAddPlacemark();
                    Icon ic = new Icon();
                    ic.setHref("robot.png");
                    ic.setViewBoundScale(1);
                    ic.setViewRefreshTime(1);
                    ic.withRefreshInterval(1);
                    IconStyle is = new IconStyle();
                    is.setScale(1);
                    is.setHeading(1);
                    is.setColor("ff007db3");
                    is.setIcon(ic);
                    p.createAndAddStyle().setIconStyle(is);
                    p.withDescription("Mac: "+"\nType: Robot").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(rb.getPos().x(), rb.getPos().y());
                    String t1 = millisToString(stringToMillis(timeNoe())+i*1000);
                    String t2 = millisToString(stringToMillis(timeNoe())+(i+1)*1000);
                    String[] s1 = t1.split(" ");
                    t1 = s1[0] + "T" + s1[1] + "Z";
                    String[] s2 = t2.split(" ");
                    t2 = s2[0] + "T" + s2[1] + "Z";
                    TimeSpan t = p.createAndSetTimeSpan();
                    t.setBegin(t1);
                    t.setEnd(t2);
                }
                for(Fruit rb : f){
                    Placemark p = d.createAndAddPlacemark();
                    Icon ic = new Icon();
                    if (rb.getType()==-1) {
                        ic.setHref("banana.png");
                        ic.setViewBoundScale(1);
                        ic.setViewRefreshTime(1);
                        ic.withRefreshInterval(1);
                        IconStyle is = new IconStyle();
                        is.setScale(1);
                        is.setHeading(1);
                        is.setColor("ff007db3");
                        is.setIcon(ic);
                        p.createAndAddStyle().setIconStyle(is);
                        p.withDescription("Mac: " + "\nType: Banana").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(rb.get_fruit_point().x(), rb.get_fruit_point().y());
                        String t1 = millisToString(stringToMillis(timeNoe()) + i * 1000);
                        String t2 = millisToString(stringToMillis(timeNoe()) + (i + 1) * 1000);
                        String[] s1 = t1.split(" ");
                        t1 = s1[0] + "T" + s1[1] + "Z";
                        String[] s2 = t2.split(" ");
                        t2 = s2[0] + "T" + s2[1] + "Z";
                        TimeSpan t = p.createAndSetTimeSpan();
                        t.setBegin(t1);
                        t.setEnd(t2);
                    }else{
                        ic.setHref("apple.jpg");
                        ic.setViewBoundScale(1);
                        ic.setViewRefreshTime(1);
                        ic.withRefreshInterval(1);
                        IconStyle is = new IconStyle();
                        is.setScale(1);
                        is.setHeading(1);
                        is.setColor("ff007db3");
                        is.setIcon(ic);
                        p.createAndAddStyle().setIconStyle(is);
                        p.withDescription("Mac: " + "\nType: Apple").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(rb.get_fruit_point().x(), rb.get_fruit_point().y());
                        String t1 = millisToString(stringToMillis(timeNoe()) + i * 1000);
                        String t2 = millisToString(stringToMillis(timeNoe()) + (i + 1) * 1000);
                        String[] s1 = t1.split(" ");
                        t1 = s1[0] + "T" + s1[1] + "Z";
                        String[] s2 = t2.split(" ");
                        t2 = s2[0] + "T" + s2[1] + "Z";
                        TimeSpan t = p.createAndSetTimeSpan();
                        t.setBegin(t1);
                        t.setEnd(t2);
                    }
                }
            }
        }
        try{
            kml.marshal(new File(MyGameGUI.num_game+".kml"));
            System.out.println("Create");
        }
        catch (Exception e){
            System.out.println("Fail create");
        }
    }

    /**
     * seal the contemporary time
     * @param
     * @return
     */
    private String millisToString(Long m){
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(new Date((m)));
    }
    /**
     * seal the contemporary time
     * @param
     * @return
     */
    private Long stringToMillis(String m)throws ParseException{
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
        Date d = s.parse(m.toString());
        long l = d.getTime();
        return l;
    }

    private String timeNoe(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}