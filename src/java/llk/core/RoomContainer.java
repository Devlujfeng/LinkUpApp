/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpSession;



/**
 *
 * @author tictac
 */
@ApplicationScoped
public class RoomContainer {
    static{
//        SessionCounter sc = new SessionCounter();
//        Timer timer = new Timer();
//        timer.schedule( new TimerTask() {
//            public void run() {
//               sc.refreshSession();
//            }
//         }, 0, 60*100);
    }
    int roomid = 1;
    HashMap<String, HttpSession> sessionList = new HashMap<>();
    HashMap<String, matchCore> roomList = new HashMap<>();
    public String createGame(String roomName, String limitation, String userName ){
        try{
        System.out.println("Let me check");
        //Random r = new Random();
        //int x = r.nextInt(1000000);
        String roomNo = "RoomA" + roomid++;
        matchCore plateNew = new matchCore();
        plateNew.setRoomName(roomName);
        plateNew.setLimitation(limitation);
        plateNew.setUserName(userName);
        plateNew.assignValue();
        roomList.put(roomNo, plateNew);
        return roomNo;
        }
        catch(Exception e){
            System.out.println("Problem occurred in creating room: "+ e.toString());
            return null;
        }
    }
    public HashMap<String, matchCore> getAllRoom(){
        return roomList;
    }
    
    public int [][] initGame(String roomID){
        try{
        matchCore gameObject = roomList.get(roomID);
        
        return gameObject.plate;
        }
        catch(Exception e){
            return null;
        }
    }
   
    public boolean ReturnResult(String roomID, int [] A, int [] B){

        matchCore gameObject = roomList.get(roomID);
        System.out.println(gameObject.counter + "<<<<>>>>" + gameObject.userCounter.get(gameObject.counter));
        gameObject.userCounter.put(gameObject.counter, false);
        gameObject.noCorner(A,B);
        System.out.println(gameObject.counter + "<<<<>>>>" + gameObject.userCounter.get(gameObject.counter));
        gameObject.counter ++;
        return gameObject.userCounter.get(gameObject.counter-1);
    }
    
    public int [][] getNowGame(String roomID){
        try{
        matchCore gameObject = roomList.get(roomID);
        return gameObject.plate;
        }
        catch(Exception e){
            return null;
        }
    }
    public void updateOnlinePlayers(String email,HttpSession userSession){
        System.out.println("Login user updated");
        sessionList.put(email, userSession);
    }
    public HashMap<String, HttpSession> getOnlinePlayers(){
        System.out.println("pull out users list");
        return sessionList;
    }

}
