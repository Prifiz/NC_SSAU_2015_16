/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import team5.datamodel.actions.WorkUser;
import team5.datamodel.transmissions.FileHandler;

/**
 *
 * @author chanta
 */
public class MarshalAndUnmarshalTest {

    private FileHandler workWithFiles;
    private String fileName;

    @Before
    public void setUp() throws Exception {
        workWithFiles = FileHandler.getFileHandler();
        fileName = "marshalData_WorkUser_Test.xml";

    }

    /**
     * Test of marshalData method, of class WorkWithFiles.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMarshalData() throws Exception {
        WorkUser workUser = WorkUser.getWork();
//        workUser.addUser("bob4", "Clark", "Usa", "XX", "1", "1", "email@gmail.com", LocalDate.parse("2012-12-19",DateTimeFormatter.ISO_LOCAL_DATE));
//        workUser.addUser("bob", "Cl", "UCanada", "XxxxX", "sffscsdf", "1ds234", "email1234@gmail.com", LocalDate.parse("2002-07-13",DateTimeFormatter.ISO_LOCAL_DATE));
//        workWithFiles.marshalData(fileName, workUser);//FIXME
    }

    /**
     * Test of unmarshalData method, of class WorkWithFiles.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUnmarshalData() throws Exception {
        WorkUser workUser = workWithFiles.unmarshalData(fileName);
        System.out.println("print:" + workUser.viewUsers());
    }

}
