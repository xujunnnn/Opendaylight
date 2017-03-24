package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import Flow_entry.Flowid;
import Flow_entry.NextId;

public interface FlowidDao {
//public void setConnection(Connection connection) throws SQLException;
public void insert(Flowid flowid) throws SQLException;
public int querry(String node,String table) throws SQLException;
public void update(Flowid flowid) throws SQLException;
public void flush() throws SQLException;
}
