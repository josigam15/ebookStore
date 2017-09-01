/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.formcontrollers.ClientProfileController;
import com.josianegamgo.formcontrollers.LoginBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author Josiane Gamgo
 */

@RunWith(Arquillian.class)
public class ClientrecordsJpaControllerTest {
  

        private final Logger logger = Logger.getLogger(ClientrecordsJpaControllerTest.class.getName());
   @Deployment
    public static WebArchive deploy() {

        // Use an alternative to the JUnit assert library called AssertJ
        // Need to reference MySQL driver as it is not part of either
        // embedded or remote
        final File[] dependencies = Maven
                .resolver()
                .loadPomFromFile("pom.xml")
                .resolve("mysql:mysql-connector-java",
                        "org.assertj:assertj-core").withoutTransitivity()
                .asFile();

        final WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addPackage(ClientrecordsJpaController.class.getPackage())
                .addPackage(Clientrecords.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File("src/main/setup/glassfish-resources.xml"), "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml")
                 .addAsResource("ebooksstoreDBCreate.sql")
                .addAsResource("ebooksstoreTableCreate.sql")
                .addAsLibraries(dependencies);

        return webArchive;
    }
   
    @Inject 
            private ClientrecordsJpaController clientjpa;
    @Inject
    private LoginBean loginbean;
   
    private ClientProfileController clientcontroller;
    
     @Resource(name = "java:app/jdbc/ebooksstore")
    private DataSource ds;


    /**
     * Test of create method, of class ClientrecordsJpaController.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Clientrecords clientrecords = null;
        ClientrecordsJpaController instance = new ClientrecordsJpaController();
        instance.create(clientrecords);
       
    }

  

    /**
     * Test of findClientrecordsByUsernameAndPass method, of class ClientrecordsJpaController.
     */
    @Test
    public void testFindClientrecordsByUsernameAndPass() {
        System.out.println("findClientrecordsByUsernameAndPass");
        String username = "steph03";
        String password = "steph03";
        ClientrecordsJpaController instance = new ClientrecordsJpaController();
        Clientrecords expResult = clientjpa.findClientrecordsByUsername("steph03");
        Clientrecords result = instance.findClientrecordsByUsernameAndPass(username, password);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of findClientrecordsByUsername method, of class ClientrecordsJpaController.
     */
    @Test
    public void testFindClientrecordsByUsername() {
        System.out.println("findClientrecordsByUsername");
        String username = "consumer";
        ClientrecordsJpaController instance = new ClientrecordsJpaController();
            Clientrecords expResult = clientjpa.findClientrecords(2);
        Clientrecords result = instance.findClientrecordsByUsername(username);
      assertEquals(expResult,result.getClientnum()); 
       
    }

    /**
     * Test of getClientrecordsCount method, of class ClientrecordsJpaController.
     */
    @Test
    public void testGetClientrecordsCount() {
       
        ClientrecordsJpaController instance = new ClientrecordsJpaController();
        int expResult = 8;
        int result = instance.getClientrecordsCount();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getMyPerson method, of class ClientrecordsJpaController.
     */
    @Test
    public void testGetMyPerson() throws Exception {
        System.out.println("getMyPerson");
        ClientrecordsJpaController instance = new ClientrecordsJpaController();
         String expResult = loginbean.getUsername();//loggedin user
        Clientrecords result = instance.getMyPerson().get(0);
      assertEquals(expResult,result.getUsername()); 
       
    }
    ////end of test
    
       /**
     * This routine is courtesy of Bartosz Majsak who also solved my Arquillian
     * remote server problem
     */
    @Before
    public void seedDatabase() {
        final String seedDataScript = loadAsString("ebooksstoreTableCreate.sql");

        try (Connection connection = ds.getConnection()) {
            for (String statement : splitStatements(new StringReader(
                    seedDataScript), ";")) {
                connection.prepareStatement(statement).execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed seeding database", e);
        }
        System.out.println("Seeding works");
    }
    
      /**
     * The following methods support the seedDatabse method
     */
    private String loadAsString(final String path) {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(path)) {
            return new Scanner(inputStream).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close input stream.", e);
        }
    }

    private List<String> splitStatements(Reader reader,
            String statementDelimiter) {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final StringBuilder sqlStatement = new StringBuilder();
        final List<String> statements = new LinkedList<>();
        try {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || isComment(line)) {
                    continue;
                }
                sqlStatement.append(line);
                if (line.endsWith(statementDelimiter)) {
                    statements.add(sqlStatement.toString());
                    sqlStatement.setLength(0);
                }
            }
            return statements;
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing sql", e);
        }
    }

    private boolean isComment(final String line) {
        return line.startsWith("--") || line.startsWith("//")
                || line.startsWith("/*");
    }

    private void assertTrue(String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
