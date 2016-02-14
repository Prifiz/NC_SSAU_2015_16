/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team5.server.actions;

import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import team5.datamodel.user.*;
import team5.datamodel.user.admin.Admin;
import team5.datamodel.user.adress.Address;

/**
 *
 * @author chanta
 */
public class AdminTest {

    private ServiceInfo serviceInfo;
    private PrivateInformation privateInformation;
    private Address address;

    public AdminTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        serviceInfo = new ServiceInfo("a", "1", "admin@gmail.com");
        privateInformation = new PrivateInformation("AA", "X", LocalDate.of(1995, Month.MARCH, 12));
        address = new Address("Russia", "Samara");

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of addCard method, of class WorkCard.
     */
   /* @Test
    public void setServiceInfoTest() {
        System.out.println("setServiceInfo: ");
        Admin expected = new Admin(privateInformation, address, serviceInfo);
        Admin result= new Admin(new PrivateInformation("AA", "X1", LocalDate.of(1995, Month.MARCH, 12)), new Address("Russia", "Samara"), new ServiceInfo("a", "1", "admin@gmail.com"));
        ServiceInfo info = new ServiceInfo("aaaaaaa", "ø", "ga");
        result.setServiceInfo(info);
        assertEquals(expected.getServiceInfo(), result.getServiceInfo());
        System.out.println(expected.equals(result));
    }*/
}
