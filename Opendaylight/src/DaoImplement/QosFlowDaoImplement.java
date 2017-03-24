package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.QosFlowDao;
import MyUtil.Util;
import Qos.QosFlow;

public class QosFlowDaoImplement implements QosFlowDao{

	@Override
	public void insert(QosFlow qosFlow) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="insert into qosflowtable (qosid,node,tableid,flowid,hosta,hostb) values(?,?,?,?,?,?)";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setLong(1,qosFlow.getQosid());
		pst.setString(2, qosFlow.getNode());
		pst.setString(3, qosFlow.getTableid());
		pst.setInt(4, qosFlow.getFlowid());
		pst.setString(5,qosFlow.getHosta());
		pst.setString(6, qosFlow.getHostb());
		pst.execute();
		connection.close();
	}

	@Override
	public void update(QosFlow qosFlow) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<QosFlow> querry(long qosid) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<QosFlow> qosFlows=new ArrayList<>();
		Connection connection=Util.connect_to_sql("qos");
		String sql="select qosid,node,tableid,flowid,hosta,hostb from qosflowtable where qosid = ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setLong(1, qosid);
		ResultSet resultSet=pst.executeQuery();
		if(resultSet.next()==false){
			connection.close();
			return null;
			
		}
		else{
			while(resultSet.next()){
				QosFlow qosFlow=new QosFlow().setQosid(resultSet.getInt(1)).setNode(resultSet.getString(2)).setTableid(resultSet.getString(3)).setFlowid(resultSet.getInt(4)).setHosta(resultSet.getString(5)).setHostb(resultSet.getString(6));
			    qosFlows.add(qosFlow);
			}
			connection.close();
			return qosFlows;
			
		}
		
		
	}

	@Override
	public void delete(long qosid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="delete from qosflowtable where qosid= ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setLong(1, qosid);
		pst.executeUpdate();
		connection.close();
	}

}
