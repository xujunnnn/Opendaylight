package NetMonitor;

import java.util.HashMap;
import java.util.Map;

public class ProtocolPortTest {
	public static void main(String []args){
		ProtocolPortMonitor.begin();
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Map<Pair<String, Protocol_Type>, Pair<Long, Long>> map=ProtocolPortMonitor.getNetSpeedMap();
		
		for(Pair<String, Protocol_Type> pair:map.keySet()){
			System.out.println(pair.getLeft()+"<><><><><>"+pair.getRight()+"<><>pktspeed= "+map.get(pair).getLeft()+"<><>bytespeed ="+map.get(pair).getRight());
		}
	}
}

}
