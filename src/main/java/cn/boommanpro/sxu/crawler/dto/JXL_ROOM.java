package cn.boommanpro.sxu.crawler.dto;

public class JXL_ROOM {
	public String getJXL() {
		return JXL;
	}
	public void setJXL(String jXL) {
		JXL = jXL;
	}
	public String getROOM() {
		return ROOM;
	}
	public void setROOM(String rOOM) {
		ROOM = rOOM;
	}
	String JXL=null;
	String ROOM=null;
	String RoomName=null;
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public JXL_ROOM(String JXL,String RoomName,String ROOM){
		this.JXL=JXL;
		this.RoomName=RoomName;
		this.ROOM=ROOM;
	}
}
