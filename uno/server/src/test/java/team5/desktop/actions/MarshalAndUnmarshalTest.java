/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.desktop.actions;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author chanta
 */
public class MarshalAndUnmarshalTest {
    private WorkWithFiles workWithFiles;
    private String fileName;
 
    @Before
    public void setUp() throws Exception {
        workWithFiles = new WorkWithFiles();
        fileName = "marshalData_WorkUser_Test.xml";
        
    }
 
    
     /**
     * Test of marshalData method, of class WorkWithFiles.
     * @throws java.lang.Exception
     */
    @Test
    public void testMarshalData() throws Exception {
        WorkUser workUser = WorkUser.getWork();
        workUser.addUser("bob4", "Clark", "Usa", "XX", "sfsdf", "1234", "email@gmail.com", "19.12.2012");
        workUser.addUser("bob", "Cl", "UCanada", "XxxxX", "sffscsdf", "1ds234", "email1234@gmail.com", "13.07.2002");
        workWithFiles.marshalData(fileName, workUser);
    }
    
    /**
     * Test of unmarshalData method, of class WorkWithFiles.
     * @throws java.lang.Exception
     */
    @Test
    public void testUnmarshalData() throws Exception {
        WorkUser workUser = workWithFiles.unmarshalData(fileName);
        System.out.println("print:" + workUser.viewUsers());
    }
 
   
}
    

