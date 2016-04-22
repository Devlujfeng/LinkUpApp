/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;



/**
 *
 * @author tictac
 */
@ApplicationScoped
public class RoomContainer {
    
    HashMap<String, matchCore> roomList = new HashMap<>();
    public String createGame(){
        try{
        System.out.println("Let me check");
        Random r = new Random();
        int x = r.nextInt(1000000);
        String roomName = "Room" + x;
        matchCore plateNew = new matchCore();
        plateNew.assignValue();
        roomList.put(roomName, plateNew);
        return roomName;
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
    
}
