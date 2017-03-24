package NetMonitor;

import java.util.HashSet;
import java.util.Map;

import NetWork_Topology.Node;
import NetWork_Topology.TopoUtil;
import NetWork_Topology.Topology;

public class NetMonTest {
	public static void main(String []args){
		Topology topology=new Topology().update();
		NetMonitor netMonitor=new NetMonitor();
		HashSet<Node> nodes=TopoUtil.get_access_node();
		netMonitor.setNodes(nodes);
		Thread thread=new Thread(netMonitor);
		thread.start();
		for(int i=0;i<1000;i++){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<Pair<String, Protocol_Type>, Long> map=netMonitor.getNetSpeedMap();
		for(Pair<String, Protocol_Type> p:map.keySet()){
			System.out.println(p.getLeft()+"<<<<<>>>>"+p.getRight()+"   "+map.get(p));
		}
		}
	}

}
