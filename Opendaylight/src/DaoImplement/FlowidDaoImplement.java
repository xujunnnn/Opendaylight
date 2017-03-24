package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.FlowidDao;
import Flow_entry.Flowid;
import MyUtil.Util;

public class FlowidDaoImplement implements FlowidDao {

	public FlowidDaoImplement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Flowid flowid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="insert into flowidtable (node,tableid,flowid) values(?,?,?)";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setString(1, flowid.getNode());
		pst.setString(2, flowid.getTableid());
		pst.setInt(3,flowid.getFlowid());
		pst.execute();
		connection.close();	
	}

	@Override
	public int querry(String node, String table) throws SQLException {
		// TODO Auto-generated method stub
		int flowid=0;
		Connection connection=Util.connect_to_sql("qos");
		String sql="select flowid from flowidtable where node = ? and tableid = ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setString(1,node);
		pst.setString(2, table);
		ResultSet resultSet=pst.executeQuery();
		while(resultSet.next()){
			flowid=resultSet.getInt(1);
		}
		return flowid;
	}

	@Override
	public void update(Flowid flowid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="update flowidtable set flowid= ? where node = ? and tableid = ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setInt(1, flowid.getFlowid());
		pst.setString(2, flowid.getNode());
		pst.setString(3, flowid.getTableid());
		pst.executeUpdate();
		connection.close();
		
	}

	@Override
	public void flush() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="delete from flowidtable";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.executeUpdate();
		connection.close();
		
	}

}
