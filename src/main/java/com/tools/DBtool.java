package com.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by Administrator on 2017/2/17.
 */
public class DBtool {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static SqlSession sqlSession;
    static{
        try{
            reader    = Resources.getResourceAsReader("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession=sqlSessionFactory.openSession();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        return sqlSession;
    }

}
