package br.com.healthtrack.DAO;

import java.util.List;

import br.com.healthtrack.model.FoodModel;

public interface FoodDAO {

	public FoodModel get(int foodId);

	public List<FoodModel> getAll(int userId);

	public void update(int foodId, FoodModel food);

	public void insert(FoodModel food);

	public void remove(int foodId);
	
	public String getLastFood(int userId);
}
