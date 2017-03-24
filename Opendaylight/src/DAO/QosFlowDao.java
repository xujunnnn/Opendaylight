package DAO;

import java.sql.SQLException;
import java.util.ArrayList;



import Qos.QosFlow;

public interface QosFlowDao {
    public void insert(QosFlow qosFlow) throws SQLException;
    public void update(QosFlow qosFlow) throws SQLException;
    public ArrayList<QosFlow> querry(long qosid) throws SQLException;
    public void delete(long qosid) throws SQLException;
}
