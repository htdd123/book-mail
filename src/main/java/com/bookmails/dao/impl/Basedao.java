package com.bookmails.dao.impl;

import com.bookmails.utils.jdbcutils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public  abstract class Basedao {
    //使用Dbutils操作数据库
    private QueryRunner queryRunner =new QueryRunner();

    //update方法用来执行Insert/update/delete语句
    public int update(String sql,Object ... args)
    {
        Connection con = jdbcutils.getConnecttion();
        try {
           return queryRunner.update(con,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            jdbcutils.close(con);
        }
        return -1;
    }
    //查询返回的javabean的sql语句
    //@param type  返回的对象类型
    //@param sql 执行的sql语句
    //@param args sql对应的参数值
    //@param <T> 返回的类型的泛型
         public <T> T  queryForOne(Class<T> type,String sql,Object ... args)
         {
             Connection con =jdbcutils.getConnecttion();
             try {
                return queryRunner.query(con,sql,new BeanHandler<T>(type),args);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             finally {
                 jdbcutils.close(con);
             }
             return null;
         }

               //查询返回多个javabean的sql语句
         public <T>List<T> queryForList(Class<T> type,String sql,Object ... args)
         {
             Connection con=jdbcutils.getConnecttion();
             try {
                return queryRunner.query(con,sql,new BeanListHandler<T>(type),args);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
             finally {
                 jdbcutils.close(con);
             }
             return null;
         }
         //执行返回一行一列的sql语句
          public Object queryForSingleValue(String sql,Object ... args)
          {
              Connection con =jdbcutils.getConnecttion();
              try {
                 return  queryRunner.query(con,sql,new ScalarHandler(),args);
              } catch (SQLException e) {
                  e.printStackTrace();
              }
              finally {
                  jdbcutils.close(con);
              }
              return null;
          }
}
