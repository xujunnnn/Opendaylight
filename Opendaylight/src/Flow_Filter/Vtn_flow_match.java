package Flow_Filter;

import net.sf.json.JSONObject;



public class Vtn_flow_match {
	private long index;
	private String srcip;
	private String destip;
	public long getIndex() {
		return index;
	}
	public Vtn_flow_match setIndex(long index) {
		this.index = index;
		return this;
	}
	public String getSrcip() {
		return srcip;
	}
	public Vtn_flow_match setSrcip(String srcip) {
		this.srcip = srcip;
		return this;
	}
	public String getDestip() {
		return destip;
	}
	public Vtn_flow_match setDestip(String destip) {
		this.destip = destip;
		return this;
	}
	public JSONObject build(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("index", index);
		JSONObject jsonObject2=new JSONObject();
		jsonObject2.put("source-network", srcip);
		jsonObject2.put("destination-network", destip);
		jsonObject.put("vtn-inet-match",jsonObject2);
		return jsonObject;
	}
	

}
