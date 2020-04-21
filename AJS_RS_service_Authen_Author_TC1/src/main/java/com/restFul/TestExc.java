/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.restFul;

/**
 *
 * @author girish
 */
public class TestExc {
    
    public static void main(String args[]){
        TestExc t=new TestExc();
        try{
        t.deleteOperation1();
        t.deleteOperation2();
        }
         catch(Exception e){
              System.out.println("deleteOperation1  333..................");
         }
    }
    
    
     private void deleteOperation1() throws Exception{
         System.out.println("deleteOperation1  1111..................");
         int i=9/0;
         System.out.println("deleteOperation1  2222..................");
        
 }

 private void deleteOperation2() {
     System.out.println("deleteOperation1  1111..................");
     
  }
    
}


