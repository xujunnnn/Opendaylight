package Qos;

import com.alibaba.fastjson.JSON;

import Exception.FlowFailException;
import Exception.MeterFailException;
import NetMonitor.Protocol_Type;

public class QosPolicyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QosPolicy qosPolicy=new QosPolicy();
	        qosPolicy.setUdp_srcport("1200").setUdp_destPort("1200");
			qosPolicy.setIp_Protocol(Protocol_Type.UDP)
					 .setDrop_rate("300")
					 .setQos_id("1");
		System.out.println(JSON.toJSON(qosPolicy));
		

	}

}
