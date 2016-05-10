package tests;

import functionality.controllers.RoomController;

public class RoomControllerTest {

	public static void main(String[] args) {
		RoomController con = new RoomController(null);
		con.getUserRoomKeyList("martin");
	}

}
