package com.shuidun.book;

import com.shuidun.book.bean.City;
import com.shuidun.book.dao.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.junit.Test;

public class MyTest {

    @Test
    public void foo() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = sdf.parse("2020-12-13 07:30");
        Timestamp timestamp = new Timestamp(d.getTime());
        System.out.println(timestamp.toString());
    }
}
