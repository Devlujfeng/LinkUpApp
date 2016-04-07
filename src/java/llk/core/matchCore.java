/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.core;

import javax.jms.Session;


/**
 *
 * @author tictaclu
 */
public class matchCore {
    int plate[][] = new int[8][21];
    
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
                System.out.print(plate[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public void linkToZero(int[] A,int[] B){
        if(plate[A[0]][A[1]] == plate[B[0]][B[1]]){
         plate[A[0]][A[1]] = 0;
         plate[B[0]][B[1]] = 0;
         printOut();}
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
                            sum += plate[B[0]+j][A[1]];
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
                         System.out.println("2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("Two Corner : Something stopped the path. diffHori < 0 ");
                    }
                }
                if(plate[i][A[1]] == 0 && diffHori > 0 && plate[i][A[1]-diffHori] == 0){
                    int sum  = 0;
                    int diff1 = i - A[0];
                    
                    if(diff1 > 0){
                        for(int j = 1; j < diff1; j++){
                            sum += plate[A[0]+j][A[1]];
                            sum += plate[B[0]+j][A[1]];
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
                        System.out.println("2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("Two Corner : Something stopped the path. diffHori < 0 ");
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
                         System.out.println("2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("Two Corner : Something stopped the path. diffHori < 0 ");
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
                         System.out.println("2 Corner getting into link20");
                        linkToZero(A,B);
                        break;
                    }
                    else{
                         System.out.println("Two Corner : Something stopped the path. diffHori < 0 ");
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
                                   sum += plate[A[0]-diffHori][i+k];
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
                                   sum += plate[A[0]-diffHori][i-k];
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
                                   sum += plate[A[0]][i+j];
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
                                   sum += plate[A[0]-diffHori][i+k];
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
                                   sum += plate[A[0]-diffHori][i-k];
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
                                   sum += plate[A[0]-diffHori][i+k];
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
                           else if(i > B[1] && A[1] > i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffHori][i+k];
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
                                     System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i < B[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i < B[0] Something Stopped The Path");
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
                                     System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > A[0] getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 纵向遍历 - i > A[0] Something Stopped The Path");
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
                                   sum += plate[A[0]-diffHori][i+k];
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
                                   sum += plate[A[0]-diffHori][i-k];
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
                           else if(i > B[1] && A[1] > i){
                               int diffToA =  A[1] - i;
                               int diffToB =  i - B[1];
                               for(int j = 1; j < diffToA; j++){
                                   sum += plate[A[0]][i+j];
                               }
                               for(int k = 1; k < diffToB; k++){
                                   sum += plate[A[0]-diffHori][i+k];
                               }
                               for(int l = 1; l < diffVert*-1; l++){
                                   sum += plate[A[0]][i+l];
                               }
                                if(sum == 0){
                                     System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i > B[1] && A[1] > i getting into link20");
                                    linkToZero(A,B);
                                    break;
                                }
                                else{
                                    //CALL TO NEXT METHOD TO VERIFY
                                    System.out.println("2 corner - A 在 B 的左上方 横向遍历 - i > B[1] && A[1] > i Something Stopped The Path");
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
                   sumHori =+ plate[A[0]][i];
               }
            // D is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[B[0]+i][B[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   linkToZero(A,B);
                   System.out.println("1 Corner for D getting into link20");
               }
               else{
                   System.out.println("Something stopped the path. Go to double corner check");
                   logicHub("GoToTwoCorner", A, B);
               }
             }
             // D is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[D[0]+i][D[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   linkToZero(A,B);
                   System.out.println("1 Corner for D getting into link20");
               }
               else{
                  System.out.println("Something stopped the path. Go to double corner check");
                  logicHub("GoToTwoCorner", A, B);
               }
             }
         }
         // D is at the left of A
         else{
            int sumHori = 0;
            for(int i = 1; i < diffHori; i++){
                   sumHori =+ plate[D[0]][D[1]+i];
               }
            // D is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[B[0]+i][B[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("1 Corner for D getting into link20");
                   linkToZero(A,B);
               }
               else{
                  System.out.println("Something stopped the path. Go to double corner check");
                  logicHub("GoToTwoCorner", A, B);
               }
             }
             // D is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[D[0]+i][D[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("1 Corner for D getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("Something stopped the path. Go to double corner check");
                   logicHub("GoToTwoCorner", A, B);
               }
             }
         }
    }
     else{
         System.out.println("The Point is not 0");
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
                   sumHori =+ plate[A[0]][i];
               }
            // C is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[B[0]+i][B[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                    System.out.println("1 Corner getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
             // C is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[C[0]+i][C[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("1 Corner getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
         }
         // C is at the left of A
         else{
            int sumHori = 0;
            for(int i = 1; i < diffHori; i++){
                   sumHori =+ plate[C[0]][C[1]+i];
               }
            // C is at down of B
             if(diffVert > 0){
                 int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[B[0]+i][B[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("1 Corner getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
             // C is at up of B
             else if(diffVert < 0){
                  int sumVert = 0;
                 for(int i = 1; i < diffVert; i++){
                   sumVert =+ plate[C[0]+i][C[1]];
               }
               if(sumHori == 0 && sumVert == 0){
                   System.out.println("1 Corner getting into link20");
                   linkToZero(A,B);
               }
               else{
                   System.out.println("Something stopped the path.Check for Point D");
                   oneCornerForD(A,B);
               }
             }
         }
    }
     else{
         System.out.println("The Point is not 0");
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
                        sum =+ plate[B[0]][B[1]+i];
                    }
                    if(sum == 0){
                         System.out.println("no Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY 
                        System.out.println("Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
                else{
                    int diff = B[1] - A[1];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum =+ plate[A[0]][i];
                    }
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("Something Stopped The Path");
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
                        sum =+ plate[B[0]+i][B[1]];
                    }
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
                else{
                    int diff = B[0] - A[0];
                    int sum = 0;
                    for(int i = 1; i < diff; i++){
                        sum =+ plate[A[0]+i][A[1]];
                    }
                    if(sum == 0){
                         System.out.println("NO Corner getting into link20");
                        linkToZero(A,B);
                    }
                    else{
                        //CALL TO NEXT METHOD TO VERIFY
                        System.out.println("Something Stopped The Path");
                        logicHub("GoToOneCorner",A,B);
                    }
                }
            }
            else{
                System.out.println("same value but not same axis");
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
    
//    public static void main(String [] argis){
//        matchCore matchCore = new matchCore();
//        matchCore.assignValue();
        //A B noCorner
//        matchCore.plate[1][2] = 3;
//        matchCore.plate[1][3] = 3;
//        //A0 B0 oneCorner
//        matchCore.plate[2][2] = 4;
//        matchCore.plate[1][4] = 4;
//        //A1 B1 oneCorner
//        matchCore.plate[3][2] = 5;
//        matchCore.plate[1][5] = 5;
//        //A2 B2 oneCorner
//        matchCore.plate[4][2] = 6;
//        matchCore.plate[1][6] = 6;
//        //A3 B3 twoCorner under same vertical or horizontal
//        matchCore.plate[2][19] = 7;
//        matchCore.plate[5][19] = 7;
//        
//        matchCore.plate[3][19] = 0;
//        matchCore.plate[4][19] = 0;
//        matchCore.plate[3][18] = 0;
//        matchCore.plate[2][18] = 0;
//        int[] A = {1,2};
//        int[] B = {1,3};
//        
//        int[] A0 = {2,2};
//        int[] B0 = {1,4};
//        
//        int[] A1 = {3,2};
//        int[] B1 = {1,5};
//        
//        int[] A2 = {4,2};
//        int[] B2 = {1,6};
//        
//        int[] A3 = {2,19};
//        int[] B3 = {5,19};
//        // A 在 B 的右下方
//        matchCore.plate[2][3] = 8;
//        matchCore.plate[1][1] = 8;
//        matchCore.plate[4][3] = 8;
//        matchCore.plate[2][1] = 8;
//        int[] A4 = {2,3};
//        int[] B4 = {1,1};
//        
//        int[] A5 = {4,3};
//        int[] B5 = {2,1};
//        // A 在 B 的右上方
//        matchCore.plate[2][4] = 9;
//        matchCore.plate[4][1] = 9;
//        matchCore.plate[2][5] = 9;
//        matchCore.plate[3][1] = 9;
//        int[] A6 = {2,4};
//        int[] B6 = {3,1};
//        
//        int[] A7 = {2,5};
//        int[] B7 = {4,1};
//        // A 在 B 的左下方
//        matchCore.plate[5][1] = 1;
//        matchCore.plate[3][6] = 1;
//        matchCore.plate[5][2] = 1;
//        matchCore.plate[2][6] = 1;
//        int[] A8 = {5,1};
//        int[] B8 = {3,6};
//        
//        int[] A9 = {5,2};
//        int[] B9 = {2,6};
//        // A 在 B 的左上方
//        matchCore.plate[6][19] = 2;
//        matchCore.plate[2][17] = 2;
//        matchCore.plate[2][16] = 2;
//        matchCore.plate[4][18] = 2;
//        int[] A10 = {2,17};
//        int[] B10 = {6,19};
//        
//        int[] A11 = {2,16};
//        int[] B11 = {4,18};
//        
//        
//        //random test
//        int[] A12 = {5,17};
//        int[] B12 = {1,6};
//        
//        matchCore.printOut();
//        matchCore.noCorner(A, B);
//        matchCore.oneCorner(A0, B0);
//        matchCore.oneCorner(A1, B1);
//        matchCore.oneCorner(A2, B2);
//        matchCore.twoCorner(A3, B3);
//        // A 在 B 的右下方
//        matchCore.twoCorner(A4, B4);
//        matchCore.twoCorner(A5, B5);
//        // A 在 B 的右上方
//        matchCore.twoCorner(A6, B6);
//        matchCore.twoCorner(A7, B7);
//        // A 在 B 的左下方
//        matchCore.twoCorner(A8, B8);
//        matchCore.twoCorner(A9, B9);
//        // A 在 B 的左上方
//        matchCore.twoCorner(A10, B10);
//        matchCore.twoCorner(A11, B11);
//        // random
//        matchCore.noCorner(A12, B12);
//    }
}
