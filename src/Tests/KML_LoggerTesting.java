package Tests;

import Server.Game_Server;
import Server.game_service;
import gameClient.KML_Logger;
import gameClient.MyGameGUI;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.StdDraw;

import java.awt.*;
import java.text.ParseException;

public class KML_LoggerTesting {

    @Test
    public void objKML() throws ParseException, InterruptedException, JSONException {
        KML_Logger kml = new KML_Logger();
        boolean flag = true;
    try {
        kml.objKML();
    }catch (Exception e){
        flag=false;
    }
    Assertions.assertEquals(flag,true);
    }
}
