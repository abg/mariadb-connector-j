package org.drizzle.jdbc;

import org.junit.Test;
import org.junit.Before;
import org.drizzle.jdbc.packet.buffer.WriteBuffer;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import static junit.framework.Assert.assertEquals;

/**
 * User: marcuse
 * Date: Jan 14, 2009
 * Time: 7:58:11 AM
 */
public class DriverTest {
    private Connection connection;
    @Before
    public void setup()
    {
        try {
            Class.forName("org.drizzle.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    //@Test
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:drizzle://localhost:4427/aaa");
    }

    @Test
    public void doQuery() throws SQLException{
        connect();
        Statement stmt = connection.createStatement();
        stmt.execute("insert into test.t values (1)");
    }
    @Test
    public void intOperations() {
        byte [] a = WriteBuffer.intToByteArray(99*256 + 77);

        assertEquals(a[0],77);
        assertEquals(a[1],99);
    }
    @Test
    public void longOperations() {
        byte [] a = WriteBuffer.longToByteArray(56*256*256*256 + 11*256*256 + 77*256 + 99);
        assertEquals(a[0],99);
        assertEquals(a[1],77);
        assertEquals(a[2],11);
        assertEquals(a[3],56);
    }

}