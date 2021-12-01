package br.com.healthtrack.DAO;

import java.util.List;

import br.com.healthtrack.model.UserActivityCategoryModel;
import br.com.healthtrack.model.UserActivityModel;

public interface PhysicalActivityDAO {

	public UserActivityModel get(int userActivityId);
	
	public UserActivityCategoryModel getWithCategory(int userActivityId);

	public List<UserActivityModel> getAll(int userId);
	
	public List<UserActivityCategoryModel> getAllWithCategory (int userId);

	public void update(int userActivityId, UserActivityModel userActivity);

	public void insert(UserActivityModel userActivity);

	public void remove(int userActivityId);
	
	public String getLastActivity(int userId);
	
}
