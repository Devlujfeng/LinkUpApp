/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.core;

import java.util.HashMap;
import javax.jms.Session;
import javax.servlet.http.HttpSession;


/**
 *
 * @author tictaclu
 */
public class matchCore {
    int plate[][] = new int[8][21];
    int counter = 1;
    private String roomName;
    private String limitation;
    private String userName;
    HashMap<Integer, Boolean> userCounter = new HashMap<Integer, Boolean>();
    HashMap<String, HttpSession> roomSessionList = new HashMap<>();
    
    public void updateRoomPlayers(String email,HttpSession userSession){
        System.out.println("Login user updated");
        roomSessionList.put(email, userSession);
    }
    public HashMap<String, HttpSession> getRoomPlayers(){
        System.out.println("pull out users list");
        return roomSessionList;
    }

    public void assignValue(){
        for(int i = 0; i < plate.length; i++){
            for(int j=0; j< plate[i].length; j++){
                if(i==0 || i==7 || j==0 || j==20){
                    plate[i][j] = 0;
                }
                else{
                plate[i][j] = (int)(Math.random()*7 + 1);
                }
            }
        }
    }
    public void printOut(){
        for(int i = 0; i < plate.length; i++){
            for(int j=0; j < plate[i].length; j++){
                System.out.print(plate[i][j]);
            }
            //System.out.println("");
        }
    }
    
    public void linkToZero(int[] A,int[] B){
        if(plate[A[0]][A[1]] == plate[B[0]][B[1]]){
         plate[A[0]][A[1]] = 0;
         plate[B[0]][B[1]] = 0;
         //printOut();
            System.out.println("Successfully Match");
         userCounter.put(counter, true);
        }
        else{
            System.out.println("Not Equal");
        }
    }
    
    public void twoCorner(int[] A,int[] B){
        System.out.println(A[0]+","+A[1]+"--value:"+plate[A[0]][A[1]]);
        System.out.println(B[0]+","+B[1]+"--value:"+plate[B[0]][B[1]]);
        //if A B in the same horizontal line
        if(A[0] == B[0] && A[1] != B[1]){
                int diffHori = A[1] - B[1];
             
            for(int i = 0; i < plate.length; i++){
                if(plate[i][A[1]] == 0 && diffHori < 0 && plate[i][A[1]-diffHori] == 0){
                    int sum  = 0;
                    int diff1 = i - A[0];
                    
                    if(diff1 > 0){
                        for(int j = 1; j < diff1; j++){
                            sum += plate[A[0]+j][A[1]];
                            sum += plate[i][A[1]-diffHori+j];
                        }
                    }
                    else if(diff1 < 0){
                        for(int j = 1; j < diff1*-1; j++){
                            sum += plate[i+j][A[1]];
                            sum += plate[i+j][A[1]-diffHori];
                        }
                    }
                    for(int k = 1; k < diffHori*-1; k++){
                            sum += plate[i][A[1]+k];
                    }
                    if(sum == 0){
                         System.out.println("if A B in the same horizontal line 2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("if A B in the same horizontal line Two Corner : Something stopped the path. diffHori < 0 ");
                    }
                }
                if(plate[i][A[1]] == 0 && diffHori > 0 && plate[i][A[1]-diffHori] == 0){
                    int sum  = 0;
                    int diff1 = i - A[0];
                    
                    if(diff1 > 0){
                        for(int j = 1; j < diff1; j++){
                            sum += plate[A[0]+j][A[1]];
                            sum += plate[B[0]+j][B[1]];
                        }
                    }
                    else if(diff1 < 0){
                        for(int j = 1; j < diff1*-1; j++){
                            sum += plate[i+j][A[1]];
                            sum += plate[i+j][A[1]-diffHori];
                        }
                    }
                    for(int k = 1; k < diffHori; k++){
                            sum += plate[i][A[1]-diffHori+k];
                    }
                    if(sum == 0){
                        System.out.println("if A B in the same horizontal line 2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("if A B in the same horizontal line Two Corner : Something stopped the path. diffHori < 0 ");
                    }
                }
            } 
        } 
        //if A B in the same Vertical line
        else if(A[1] == B[1] && A[0] != B[0]){
            int diffVert = A[0] - B[0];
            for(int i = 0; i < plate[0].length; i++){
                if(plate[A[0]][i] == 0 && diffVert < 0 && plate[A[0]-diffVert][i] == 0){
                //
                    int sum  = 0;
                    int diff1 = i - A[1];
                    
                    if(diff1 > 0){
                        for(int j = 1; j < diff1; j++){
                            sum += plate[A[0]][A[1]+j];
                            sum += plate[B[0]][B[1]+j];
                        }
                    }
                    else if(diff1 < 0){
                        for(int j = 1; j < diff1*-1; j++){
                            sum += plate[A[0]][i+j];
                            sum += plate[A[0]-diffVert][i+j];
                        }
                    }
                    for(int k = 1; k < diffVert*-1; k++){
                            sum += plate[A[0]+k][i];
                    }
                    if(sum == 0){
                         System.out.println("if A B in the same Vertical line 2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("if A B in the same Vertical line Two Corner : Something stopped the path. diffHori < 0 ");
                    }
                //
                }
                else if(plate[A[0]][i] == 0 && diffVert > 0 && plate[A[0]-diffVert][i] == 0){
                    //
                    int sum  = 0;
                    int diff1 = i - A[1];
                    
                    if(diff1 > 0){
                        for(int j = 1; j < diff1; j++){
                            sum += plate[A[0]][A[1]+j];
                            sum += plate[B[0]][B[1]+j];
                        }
                    }
                    else if(diff1 < 0){
                        for(int j = 1; j < diff1*-1; j++){
                            sum += plate[A[0]][i+j];
                            sum += plate[A[0]-diffVert][i+j];
                        }
                    }
                    for(int k = 1; k < diffVert; k++){
                            sum += plate[A[0]-diffVert+k][i];
                    }
                    if(sum == 0){
                         System.out.println("if A B in the same Vertical line 2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("if A B in the same Vertical line Two Corner : Something stopped the path. diffHori < 0 ");
                    }
                //
                }
            }
        }
        else{
            int diffHori = A[1] - B[1];
            int diffVert = A[0] - B[0];
            // differHori > 0 -- 水平线，A点在B点右边
            // differHori < 0 -- 水平线，A点在B点左边
            // diffVert > 0 -- 垂直线，A点在B点下面
            // diffVert < 0 -- 垂直线，A点在B点上面
            
            
            if(diffHori > 0){
                if(diffVert > 0){
                    //A在B的右下方 纵向遍历
                     for(int i = 0; i < plate.length; i++){
                            if(plate[i][A[1]] == 0 && plate[i][A[1]-diffHori] == 0){
                           int sum  = 0;
                           if(i < B[0]){
                               int diffToA = A[0] - i;
                               int diffToB = B[0] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 纵向遍历 - i < B[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 纵向遍历 - i < B[0] Something Stopped The Path");
                                }
                               
                           }
                           else if(i > A[0]){
                               int diffToA =  i - A[0];
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 纵向遍历 - i > A[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 纵向遍历 - i > A[0] Something Stopped The Path");
                                }
                           }
                           else if(i > B[0] && A[0] > i){
                               int diffToA =  A[0] - i;
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 纵向遍历 - i > B[0] && A[0] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 纵向遍历 - i > B[0] && A[0] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                     //A在B的右下方 横向遍历
                     for(int i = 0; i < plate[0].length; i++){
                            if(plate[A[0]][i] == 0 && plate[A[0]-diffVert][i] == 0 && plate[A[0]][A[1]] != 0 && plate[B[0]][B[1]] != 0){
                           int sum  = 0;
                           if(i < B[1]){
                               int diffToA = A[1] - i;
                               int diffToB = B[1] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffHori+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 横向遍历 - i < B[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 横向遍历 - i < B[1] Something Stopped The Path");
                                }
                           }
                           else if(i > A[1]){
                               int diffToA =  i - A[1];
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i-k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffHori+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 横向遍历 - i > A[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 横向遍历 - i > A[1] Something Stopped The Path");
                                }
                           }
                           else if(i > B[1] && A[1] > i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffHori][i+k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffHori+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右下方 横向遍历 - i > B[1] && A[1] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右下方 横向遍历 - i > B[1] && A[1] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                    //
                }
                
                
                
                else if(diffVert < 0){ //A在B的右上方 *************************
                    for(int i = 0; i < plate.length; i++){
                            if(plate[i][A[1]] == 0 && plate[i][A[1]-diffHori] == 0){
                           int sum  = 0;
                           if(i < A[0]){
                               int diffToA = A[0] - i;
                               int diffToB = B[0] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                    System.out.println("Sum: "+ sum);
                                     System.out.println("2 corner - A在B的右上方 纵向遍历 - i < A[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("Sum: "+ sum);
                                    System.out.println("2 corner - A在B的右上方 纵向遍历 - i < A[0] Something Stopped The Path");
                                }
                               
                           }
                           else if(i > B[0]){
                               int diffToA =  i - A[0];
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右上方 纵向遍历 - i > B[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右上方 纵向遍历 - i > B[0]  Something Stopped The Path");
                                }
                           }
                           else if(i > A[0] && B[0] > i){
                               int diffToA =  A[0] - i;
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA*-1; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB*-1; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori; l++){
                                   sum += plate[i][A[1]-diffHori+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右上方 纵向遍历 - i > A[0] && B[0] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右上方 纵向遍历 - i > A[0] && B[0] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                     //A在B的右上方 横向遍历
                     for(int i = 0; i < plate[0].length; i++){
                            if(plate[A[0]][i] == 0 && plate[A[0]-diffVert][i] == 0 && plate[A[0]][A[1]] != 0 && plate[B[0]][B[1]] != 0){
                           int sum  = 0;
                           if(i < B[1]){
                               int diffToA = A[1] - i;
                               int diffToB = B[1] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右上方 横向遍历 - i < B[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右上方 横向遍历 - i < B[1] Something Stopped The Path");
                                }
                           }
                           else if(i > A[1]){
                               int diffToA =  i - A[1];
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i-k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右上方 横向遍历 - i > A[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右上方 横向遍历 - i > A[1] Something Stopped The Path");
                                }
                           }
                           else if(i > B[1] && A[1] > i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i-k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A在B的右上方 横向遍历 - i > B[1] && A[1] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A在B的右上方 横向遍历 - i > B[1] && A[1] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                }
            }
            if(diffHori < 0){
                if(diffVert > 0){
                    //A 在B 的左下方
                    for(int i = 0; i < plate.length; i++){
                            if(plate[i][A[1]] == 0 && plate[i][A[1]-diffHori] == 0){
                           int sum  = 0;
                           if(i < B[0]){
                               int diffToA = A[0] - i;
                               int diffToB = B[0] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i < B[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i < B[0] Something Stopped The Path");
                                }
                               
                           }
                           else if(i > A[0]){
                               int diffToA =  i - A[0];
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i > A[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i > A[0] Something Stopped The Path");
                                }
                           }
                           else if(i > B[0] && A[0] > i){
                               int diffToA =  A[0] - i;
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i > B[0] && A[0] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 纵向遍历 - i > B[0] && A[0] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                     //A 在B 的左下方 横向遍历
                     for(int i = 0; i < plate[0].length; i++){
                            if(plate[A[0]][i] == 0 && plate[A[0]-diffVert][i] == 0 && plate[A[0]][A[1]] != 0 && plate[B[0]][B[1]] != 0){
                           int sum  = 0;
                           if(i < A[1]){
                               int diffToA = A[1] - i;
                               int diffToB = B[1] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffVert+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 横向遍历 - i < A[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 横向遍历 - i < A[1] Something Stopped The Path");
                                }
                           }
                           else if(i > B[1]){
                               int diffToA =  i - A[1];
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i-k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffVert+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 横向遍历 - i > B[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 横向遍历 - i > B[1] Something Stopped The Path");
                                }
                           }
                           else if(i < B[1] && A[1] < i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA*-1; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert; l++){
                                   sum += plate[A[0]-diffVert+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在B 的左下方 横向遍历 - i > B[1] && A[1] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在B 的左下方 横向遍历 - i > B[1] && A[1] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                }
                else if(diffVert < 0){
                   //A 在 B 的左上方
                    for(int i = 0; i < plate.length; i++){
                            if(plate[i][A[1]] == 0 && plate[i][A[1]-diffHori] == 0){
                           int sum  = 0;
                           if(i < A[0]){
                               int diffToA = A[0] - i;
                               int diffToB = B[0] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i+j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i < B[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i < B[0] Something Stopped The Path");
                                }
                               
                           }
                           else if(i > B[0]){
                               int diffToA =  i - A[0];
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i-k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > A[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > A[0] Something Stopped The Path");
                                }
                           }
                           else if(i < B[0] && A[0] < i){
                               int diffToA =  A[0] - i;
                               int diffToB =  i - B[0];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[i-j][A[1]];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[i+k][A[1]-diffHori];
                               }
                               for(int l = 1; l < diffHori*-1; l++){
                                   sum += plate[i][A[1]+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > B[0] && A[0] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > B[0] && A[0] > i Something Stopped The Path");
                                }
                           }
                        }
                     }
                     //A 在B 的左上方 横向遍历
                     for(int i = 0; i < plate[0].length; i++){
                            if(plate[A[0]][i] == 0 && plate[A[0]-diffVert][i] == 0 && plate[A[0]][A[1]] != 0 && plate[B[0]][B[1]] != 0){
                           int sum  = 0;
                           if(i < A[1]){
                               int diffToA = A[1] - i;
                               int diffToB = B[1] - i;
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]][i+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i < A[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i < A[1] Something Stopped The Path");
                                }
                           }
                           else if(i > B[1]){
                               int diffToA =  i - A[1];
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffVert][i-k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i > B[1] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i > B[1] Something Stopped The Path");
                                }
                           }
                           else if(i < B[1] && A[1] < i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA*-1; j++){
                                   sum += plate[A[0]][i-j];
                               }
                               for(int k = 1; k < diffToB*-1; k++){
                                   sum += plate[A[0]-diffVert][i+k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]+l][i];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i < B[1] && A[1] < i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i < B[1] && A[1] < i Something Stopped The Path");
                                }
                           }
                        }
                     }
                }
            }
        }
    }
    public void oneCornerForD(int[] A,int[] B){
        
     int[] D = {B[0],A[1]};
      if(plate[D[0]][D[1]] == 0){
         
         int diffHori = D[1] - B[1];
         int diffVert = D[0] - A[0];
         // D is at the right of A
         if(diffHori > 0){
            int sumHori = 0;
            for(int i = 1; i < diffHori; i++){
                   sumHori = sumHori + plate[D[0]][D[1]-i];
               }
            // D is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert = sumVert + plate[A[0]][A[1]+i];
               }
               if(sumHori == 0 && sumVert == 0){
                   linkToZero(A,B);
                   System.out.println("diffHori > 0 diffVert > 0 Corner for D getting into link20");
               }
               else{
                   System.out.println("diffHori > 0 diffVert > 0 Something stopped the path. Go to double corner check");
                   logicHub("GoToTwoCorner", A, B);
               }
             }
             // D is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert*-1; i++){
                   sumVert = sumVert + plate[A[0]][A[1]+i];
               }
               if(sumHori == 0 && sumVert == 0){
                   linkToZero(A,B);
                   System.out.println("diffHori > 0 diffVert < 0 Corner for D getting into link20");
               }
               else{
                  System.out.println("diffHori > 0 diffVert < 0 Something stopped the path. Go to double corner check");
                  logicHub("GoToTwoCorner", A, B);
               }
             }
         }
         // D is at the left of A
         else{
            int sumHori = 0;
            for(int i = 1; i < diffHori*-1; i++){
                   sumHori = sumHori + plate[D[0]][D[1]+i];
               }
            // D is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert = sumVert + plate[A[0]][A[1]+i];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("diffHori < 0 diffVert > 0 Corner for D getting into link20");
                   linkToZero(A,B);
               }
               else{
                  System.out.println("diffHori < 0 diffVert > 0 Something stopped the path. Go to double corner check");
                  logicHub("GoToTwoCorner", A, B);
               }
             }
             // D is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert*-1; i++){
                   sumVert = sumVert + plate[A[0]-i][A[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("diffHori < 0 diffVert < 0  Corner for D getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("diffHori < 0 diffVert < 0 Something stopped the path. Go to double corner check");
                   logicHub("GoToTwoCorner", A, B);
               }
             }
         }
    }
     else{
         System.out.println("oneCornerForD The Point is not 0");
         System.out.println(plate[D[0]][D[1]]+"\n"+D[0]+",D,"+D[1]);
     }
    }
    
    public void oneCorner(int[] A,int[] B){
     int[] C = {A[0],B[1]};
        System.out.println("Get in one corner");
     if(plate[C[0]][C[1]] == 0){
         
         int diffHori = C[1] - A[1];
         int diffVert = C[0] - B[0];
         // C is at the right of A
         if(diffHori > 0){
             
            int sumHori = 0;
            for(int i = 1; i < diffHori; i++){
                   sumHori = sumHori + plate[C[0]][C[1]- i];
               }
            // C is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert = sumVert + plate[B[0]+i][B[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                    System.out.println(" oneCorner diffHori > 0 diffVert > 0 getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("diffHori > 0 diffVert > 0 Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
             // C is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert*-1; i++){
                   sumVert = sumVert + plate[C[0]+i][C[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("diffHori > 0 diffVert < 0 getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("diffHori > 0 diffVert < 0 Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
         }
         // C is at the left of A
         else{
            int sumHori = 0;
            for(int i = 1; i < diffHori*-1; i++){
                   sumHori = sumHori + plate[C[0]][C[1]+i];
               }
            // C is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert = sumVert + plate[C[0]-i][C[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("diffHori < 0 diffVert > 0 getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("diffHori < 0 diffVert > 0 Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
             // C is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert*-1; i++){
                   sumVert = sumVert + plate[C[0]+i][C[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("diffHori < 0 diffVert < 0 getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("diffHori < 0 diffVert < 0 Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
         }
    }
     else{
         System.out.println("oneCorner The Point is not 0");
         System.out.println(plate[C[0]][C[1]]+"\n"+C[0]+",C,"+C[1]);
         oneCornerForD(A,B);
     }
     
     
    }
    
    public void noCorner(int[] A,int[] B){
        System.out.println(plate[A[0]][A[1]]);
        System.out.println(plate[B[0]][B[1]]);
        if(plate[A[0]][A[1]] == plate[B[0]][B[1]] && plate[A[0]][A[1]] != 0 && plate[B[0]][B[1]] != 0){
        // condition 1 axis x or axis y is equal.
            if(A[0] == B[0]){
                // condition 2 in between, sum up of crossed by should be 0.
                if(A[1] > B[1]){ // find the bigger number and loop the horizontal digits.
                    int diff = A[1] - B[1];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum = sum + plate[B[0]][B[1]+i];
                    }
                    System.out.println(">>>>"+sum);
                    if(sum == 0){
                         System.out.println("no Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY 
                        System.out.println(" A[0] == B[0] A[1] > B[1] Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
                else{
                    int diff = B[1] - A[1];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum = sum + plate[A[0]][A[1]+i];
                    }
                    System.out.println("sum: >>>"+sum);
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("A[0] == B[0] A[1] <= B[1] Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
            }
            else if(A[1] == B[1]){
                // condition 2 in between, sum up of crossed by should be 0.
                if(A[0] > B[0]){ // find the bigger number and loop the horizontal digits.
                    int diff = A[0] - B[0];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum = sum + plate[B[0]+i][B[1]];
                    }
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("A[1] == B[1] A[0] > B[0] Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
                else{
                    int diff = B[0] - A[0];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum = sum + plate[A[0]+i][A[1]];
                    }
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("A[1] == B[1] A[0] <= B[0]Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
            }
            else{
                System.out.println("same value but not same axis");
                logicHub("GoToOneCorner",A,B);
            }
        }
        else{
            if(plate[A[0]][A[1]] == 0 || plate[B[0]][B[1]] == 0)
                System.out.println("Both value you selected should not be 0");    
            else
            System.out.println("Not Equal");
        }
    }
    
    public void logicHub(String flag,int[] A,int[] B){
        switch(flag){
            case "GoToOneCorner":
                oneCorner(A, B)
                ;
            case "GoToTwoCorner":
                twoCorner(A, B)
                ;
            default:
                System.out.println("No Door");
                ;
        }
        
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the limitation
     */
    public String getLimitation() {
        return limitation;
    }

    /**
     * @param limitation the limitation to set
     */
    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
