package functionality.controllers.interfaces;

import java.io.IOException;

import model.UserDTO;

public interface IUserController {
	
	UserDTO login(String username, String password) throws IOException;
	void logout();
	void updateUser(UserDTO u) throws IOException;
	void createUser(UserDTO u);
	
}
