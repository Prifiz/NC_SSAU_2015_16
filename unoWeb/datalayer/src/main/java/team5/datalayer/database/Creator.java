/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.datalayer.database;

import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 *
 * @author Dmitry
 */
public class Creator {

    private Logger logger;
    private DataBaseWorker dbworker;

    public void createAddresses() {

        try {
            dbworker = new DataBaseWorker();
            dbworker.openConnectionDataBase();
            dbworker.execute(dbworker.createQueryfromScript("scripts/create/CREATE_ADDRESSES.sql"));
        } catch (FileNotFoundException ex) {
            logger.debug(ex.getMessage());
        }
        dbworker.closeConnectionDataBase();
    }
}
