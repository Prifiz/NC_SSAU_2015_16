/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

/**
 *
 * @author Dmitry
 */
public class BD {

    public static void main(String[] args) {
        Creator creator = new Creator();
        Droper droper=new Droper();
        Searcher searcher=new Searcher();
       // DataBaseWorker dbworker = new DataBaseWorker();

      //  dbworker.openConnectionDataBase();
        //creator.createAddresses();
      //  droper.dropDataBase();
       // dbworker.closeConnectionDataBase();
       //creator.createAddresses();
        System.out.println(searcher.search("Bob")); 
    }

}
