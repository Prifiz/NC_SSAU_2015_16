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
public class Droper {

    private Logger logger;
    private DataBaseWorker dbworker;

    public void dropDataBase() {

        dbworker = new DataBaseWorker();
        dbworker.openConnectionDataBase();
        dbworker.execute("DROP TABLE IF EXISTS COUNTRY CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS CITY CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS ADDRESSES CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS SERVISE_INFO CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS PRIVATE_INFORMATION CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS COLOR CASCADE;\n"
                + "\n"
                + "DROP TABLE IF EXISTS CARDS CASCADE;");
        dbworker.closeConnectionDataBase();
    }
}
