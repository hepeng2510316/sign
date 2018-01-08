package com.demo.hibernate;
  
import org.hibernate.dialect.MySQL5InnoDBDialect;  
  
/**
 * Extends MySQL5InnoDBDialect and sets the default charset to be UTF-8
 */
public class MysqlDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";  
    }
}
