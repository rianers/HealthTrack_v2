package br.com.healthtrack.DAO;

import br.com.healthtrack.model.UserModel;

public interface UserDAO {

	public UserModel get(Integer userId);

	public UserModel getByEmail(String email);

	public boolean checkPassword(Integer id, String password);

	public void update(UserModel user, Integer userID);
	
	public void update(UserModel user, Integer userID, String password);

	public void insert(UserModel user);
}
