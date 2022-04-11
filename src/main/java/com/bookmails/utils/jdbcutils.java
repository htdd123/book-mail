package com.bookmails.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcutils {

    private static DruidDataSource dataSource;

    static {
        Properties pro=new Properties();

        try {
            //读取jdbc.properties属性
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            pro.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
//            System.out.println("asdadad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnecttion()
    {
           Connection conn = null;
        try {
            conn=dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static void close(Connection con)
    {
       if(con !=null)
       {
           try {
               con.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }
}
