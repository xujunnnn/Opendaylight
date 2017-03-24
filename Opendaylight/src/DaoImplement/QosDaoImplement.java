package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.QosDao;
import Host.Host;
import MyUtil.Util;
import Qos.QosTask;
import Qos.QosType;

public class QosDaoImplement  implements QosDao {
	public QosDaoImplement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Insert(QosTask qosTask) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		if(qosTask.getQosType()==QosType.Meter_drop){
		String sql="insert into qostable (qosid,Qostype,droprate,hosta,hostb,nodea,nodeb) values (?,?,?,?,?,?,?)";
		PreparedStatement pst;
			pst = connection.prepareStatement(sql);
			pst.setLong(1,qosTask.getQosid());
			pst.setString(2, qosTask.getQosType().toString());
			pst.setInt(3, qosTask.getDropRate());
			pst.setString(4, qosTask.getHostA());
			pst.setString(5, qosTask.getHostB());
			if(("".equals(qosTask.getHostA()))){
				pst.setString(6, null);
				pst.setString(7, Util.getHostFromHostName(qosTask.getHostB()).getNode());
			}
			else if(("".equals(qosTask.getHostB()))){
			    pst.setString(6, Util.getHostFromHostName(qosTask.getHostA()).getNode());	
			    pst.setString(7, null);
			}
			else{
			pst.setString(6, Util.getHostFromHostName(qosTask.getHostA()).getNode());
			pst.setString(7, Util.getHostFromHostName(qosTask.getHostB()).getNode());
			}
			pst.execute();	
		}
		else if(qosTask.getQosType()==QosType.queue){
			String sql="insert into qostable (qosid,Qostype,queue hosta hostb) values (?,?,?,?,?)";
			PreparedStatement pst;
				pst = connection.prepareStatement(sql);
				pst.setLong(1,qosTask.getQosid());
				pst.setString(2, qosTask.getQosType().toString());
				pst.setInt(3, qosTask.getQueueid());
				pst.setString(4,qosTask.getHostA());
				pst.setString(5, qosTask.getHostB());
				pst.execute();	
		}
	}

	@Override
	public void update(QosTask qosTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long qosid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="delete from qostable where qosid = ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setLong(1, qosid);
		pst.executeUpdate();
		connection.close();
	}

	@Override
	public ArrayList<QosTask> querry() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<QosTask> qosTasks=new ArrayList<QosTask>();
		Connection connection=Util.connect_to_sql("qos");
		String sql="select qosid,qostype,droprate,queue,hosta,hostb from qostable";
		PreparedStatement pst=connection.prepareStatement(sql);
		ResultSet resultSet=pst.executeQuery();
		if(resultSet.next()==false){
				return null;
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
		else{
		while(resultSet.next()){
		   QosTask qosTask=new QosTask();
		   qosTask.setQosid(resultSet.getInt(1));
		   qosTask.setQosType(QosType.valueOf(resultSet.getString(2)));
		   qosTask.setDropRate(resultSet.getInt(3));
		   qosTask.setQueueid(resultSet.getInt(4));
		   //Host hostA=new Host().setName(resultSet.getNString(5));
		  // Host hostB=new Host().setName(resultSet.getString(6));
		   //hostA.setNode(resultSet.getNString(7));
		  // hostB.setNode(resultSet.getString(8));
		   qosTask.setHostA(resultSet.getString(5));
		   qosTask.setHostB(resultSet.getString(6));
		   qosTasks.add(qosTask);
		}
		
		connection.close();
		return qosTasks;
		}
	}

	@Override
	public QosTask querryFromId(long qosid) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=Util.connect_to_sql("qos");
		String sql="select qosid,qostype,droprate,queue,hosta,hostb from qostable where qosid = ?";
		PreparedStatement pst=connection.prepareStatement(sql);
		pst.setLong(1, qosid);
		ResultSet resultSet=pst.executeQuery();
	    QosTask qosTask=new QosTask();
	    if(resultSet.next()==false){
	    	return null;
	    }
	    else{
		while(resultSet.next()){
		
		   qosTask.setQosid(resultSet.getLong(1));
		   qosTask.setQosType(QosType.valueOf(resultSet.getString(2)));
		   qosTask.setDropRate(resultSet.getInt(3));
		   qosTask.setQueueid(resultSet.getInt(4));
		   qosTask.setHostA(resultSet.getString(5));
		   qosTask.setHostA(resultSet.getString(6));

		}
		connection.close();
		return qosTask;
	}
	}

	

}
