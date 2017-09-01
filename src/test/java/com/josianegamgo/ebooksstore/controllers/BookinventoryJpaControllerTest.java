/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.controllers.exceptions.NonexistentEntityException;
import com.josianegamgo.ebooksstore.controllers.exceptions.RollbackFailureException;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.formcontrollers.BookinventoryBean;
import com.josianegamgo.formcontrollers.LoginBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
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
public class BookinventoryJpaControllerTest {
    

        private final Logger logger = Logger.getLogger(BookinventoryJpaControllerTest.class.getName());
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
                .addPackage(BookinventoryJpaController.class.getPackage())
                .addPackage(Bookinventory.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File("src/main/setup/glassfish-resources.xml"), "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml")
                .addAsResource("ebooksstoreDBCreate.sql")
                .addAsResource("ebooksstoreTableCreate.sql")
                .addAsLibraries(dependencies);

        return webArchive;
    }

    
    @Inject 
            private BookinventoryJpaController bjpa;
    @Inject
    private LoginBean loginbean;
    @Inject
    private BookinventoryBean bookinventorybean;
    
     @Resource(name = "java:app/jdbc/ebooksstore")
    private DataSource ds;



    /**
     * Test of findBookinventory method, of class BookinventoryJpaController.
     */
    @Test
    public void testFindBookinventory() {
       
        Integer id = 1;//find the first book in inventory
        BookinventoryJpaController instance = new BookinventoryJpaController();
        Bookinventory expResult = bjpa.getBookByIsbn("9780262255448").get(0);
        Bookinventory result = instance.findBookinventory(id);
        assertEquals(expResult, result);
     
    }


    /**
     * Test of getAll method, of class BookinventoryJpaController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAll() throws SQLException {
       
        BookinventoryJpaController instance = new BookinventoryJpaController();
        int expResult = 51;
        List<Bookinventory> result = instance.getAll();
         assertThat(result).hasSize(expResult);//there are 51 books in the inventory
    }

    /**
     * Test of getAllgenre method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetAllgenre() throws Exception {
        
        BookinventoryJpaController instance = new BookinventoryJpaController();
        int expResult = 5;
        List<String> result = instance.getAllgenre();
        assertThat(result).hasSize(expResult); //there are 5 genre 
        
    }

    /**
     * Test of getBookByGenre method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookByGenre() {
        logger.info("getBookByGenre");
        String genre = "Bioengineering";
        BookinventoryJpaController instance = new BookinventoryJpaController();
        int expResult =10 ;
        List<Bookinventory> result = instance.getBookByGenre(genre);
        assertThat(result).hasSize(expResult);
       
    }

    /**
     * Test of getBookByIsbn method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookByIsbn() {
        
        String isbn = "9781598290394";
        BookinventoryJpaController instance = new BookinventoryJpaController();
        List<Bookinventory> expResult = bjpa.getBookBytitle("Signal Processing of Random Physiological Signals");
        List<Bookinventory> result = instance.getBookByIsbn(isbn);
        assertThat(expResult).isEqualTo(result);
        
       
    }

    /**
     * Test of getBookByPrice method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookByPrice() {
        
        double listprice = 10.39;
        double wholesaleprice = 5.39;
        BookinventoryJpaController instance = new BookinventoryJpaController();
        List<Bookinventory> expResult = bjpa.getBookByGenre("Bioengineering");
        List<Bookinventory> result = instance.getBookByPrice(listprice, wholesaleprice);
        assertThat(expResult).doesNotHaveDuplicates();//there is only one of this book
    }

 

    /**
     * Test of getBookByauthor method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookByauthor() {
    
        String author = "Graetz, M.";
        BookinventoryJpaController instance = new BookinventoryJpaController();
        int expResult = 1;
        List<Bookinventory> result = instance.getBookByauthor(author);
        assertThat(result ).hasSize(expResult);
    }

    /**
     * Test of getBookBytitle method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookBytitle() {
        
        String title = "Tectonic Faults";
        BookinventoryJpaController instance2 = new BookinventoryJpaController(); 
        BookinventoryJpaController instance = new BookinventoryJpaController();
        List<Bookinventory> expResult = instance2.getBookByauthor("Handy, M. Hirth, G. Hovius, N.");
        List<Bookinventory> result = instance.getBookBytitle(title);
       assertThat(expResult).isEqualTo(result);
    }

    /**
     * Test of getBookBypublisher method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookBypublisher() {
        System.out.println("getBookBypublisher");
        String publisher = "MIT Press";
        BookinventoryJpaController instance = new BookinventoryJpaController();
       // List<Bookinventory> expResult = null;
        List<Bookinventory> result = instance.getBookBypublisher(publisher);
        assertThat(result).hasSize(20);
    }

    /**
     * Test of getTrackedBook method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetTrackedBook() {
       Bookinventory actualbook = bookinventorybean.getActualinView();
        BookinventoryJpaController instance = new BookinventoryJpaController();
        
        List<Bookinventory> result = instance.getTrackedBook();
        assertThat(result).contains(actualbook);
    }

    /**
     * Test of getLatestBooks method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetLatestBooks() throws SQLException {
        Date today = new Date();
        BookinventoryJpaController instance = new BookinventoryJpaController();
        List<Bookinventory> expResult = bjpa.getBookByDate(today);
        List<Bookinventory> result = instance.getLatestBooks();
        assertThat(result).containsNull();
    }

    /**
     * Test of getRecentBooks method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetRecentBooks() throws SQLException {
       
        BookinventoryJpaController instance = new BookinventoryJpaController();
        List<Bookinventory> expResult = null;
        List<Bookinventory> result = instance.getRecentBooks();
        assertThat(result).doesNotContainNull();
    }

    /**
     * Test of getBookInCart method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetBookInCart() {
      BookinventoryJpaController instance = new BookinventoryJpaController();
        
        List<Bookinventory> result = instance.getBookInCart();
        if(loginbean.isLoggedIn()==true){
      int expResult = bjpa.getBookInCart().size();
      
        assertThat(result).hasSize(expResult);
      }
      else
        assertThat(result).hasSize(0);
        
    }

    /**
     * Test of getActualinView method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetActualinView() {
        
        BookinventoryJpaController instance = new BookinventoryJpaController();
       int expResult;
        Bookinventory result = instance.getActualinView();
        if(loginbean.isLoggedIn()==true)
        assertThat(result).isNotNull();
        else
           assertThat(result).isNull(); 
       
    }

    /**
     * Test of getMyBook method, of class BookinventoryJpaController.
     */
    @Test
    public void testGetMyBook() throws Exception {
        
        BookinventoryJpaController instance = new BookinventoryJpaController();
       
        List<Bookinventory> result = instance.getMyBook();
       if(loginbean.isLoggedIn()==true)
        assertThat(result).isNotNull();
        else
           assertThat(result).isNull(); 
       
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
