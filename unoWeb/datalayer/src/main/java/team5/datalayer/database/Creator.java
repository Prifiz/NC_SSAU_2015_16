/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

import org.apache.log4j.Logger;

/**
 *
 * @author Dmitry
 */
public class Creator {

    private Logger logger;
    private DataBaseWorker dbworker;

    public void createAddresses() {

        dbworker = new DataBaseWorker();
      //TODO  dbworker.executeQuery(dbworker.createQueryfromScript(CREATE_ADDRESSES.sql));
    }
}
