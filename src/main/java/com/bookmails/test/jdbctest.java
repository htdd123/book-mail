package com.bookmails.test;

import com.bookmails.utils.jdbcutils;
import org.junit.Test;

import java.sql.Connection;

public class jdbctest {
    @Test
    public void tesTjdbcUtils()
    {
        for(int i=0;i<100;i++)
        {
            Connection con=jdbcutils.getConnecttion();
            System.out.println(con);
            jdbcutils.close(con);
        }
    }

}
