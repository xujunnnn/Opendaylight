package DAO;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * db operation about Flowid
 * @author xu
 *
 */
import DB.DBAccess;
import FLow.FlowId;
public class FlowIdDao {

	public List<FlowId> getFlowId(String node,String table){
		DBAccess dbAccess=new DBAccess();
		SqlSession session=null;
		List <FlowId> flowIds=new ArrayList<>();
		try {
			FlowId flowId=new FlowId();
			session=dbAccess.getSqlSession();		
			flowIds=session.selectList("FlowId.querryFlowId",flowId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(session!=null)
				session.close();
		}
		return flowIds;
	}
	public static void main(String []args){
		FlowIdDao flowIdDao=new FlowIdDao();
		for(FlowId flowId:flowIdDao.getFlowId("", "")){
			System.out.println(flowId.getFlowid());
		}
	}
}
