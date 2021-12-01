package br.com.healthtrack.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthtrack.DAO.FoodDAO;
import br.com.healthtrack.model.FoodModel;
import br.com.healthtrack.util.DBManager;

public class FoodDAOImpl implements FoodDAO {

	private Connection connection;
	PreparedStatement pstmt = null;

	@Override
	public FoodModel get(int foodId) {
		FoodModel food = null;
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM T_ALIMENTO WHERE ID_ALIMENTO = ? AND ROWNUM = 1");
			pstmt.setInt(1, foodId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				food = new FoodModel();
				food.setFoodId(foodId);
				food.setFoodName(rs.getString("NM_ALIMENTO"));
				food.setQuantityCalories(rs.getDouble("QT_CALORIAS"));
				food.setFoodDescription(rs.getString("DS_ALIMENTO"));
				food.setHour(rs.getString("HR_ALIMENTO"));
				food.setDate(rs.getString("DT_ALIMENTO"));
				return food;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return food;
	}

	@Override
	public List<FoodModel> getAll(int userId) {
		List<FoodModel> foods = new ArrayList<FoodModel>();

		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM T_ALIMENTO WHERE ID_USUARIO = ?");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodModel food = new FoodModel();
				food.setFoodId(rs.getInt("ID_ALIMENTO"));
				food.setFoodName(rs.getString("NM_ALIMENTO"));
				food.setQuantityCalories(rs.getDouble("QT_CALORIAS"));
				food.setFoodDescription(rs.getString("DS_ALIMENTO"));
				food.setHour(rs.getString("HR_ALIMENTO"));
				food.setDate(rs.getString("DT_ALIMENTO"));
				foods.add(food);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foods;
	}

	@Override
	public void update(int foodId, FoodModel food) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"UPDATE T_ALIMENTO SET NM_ALIMENTO = ?, QT_CALORIAS = ?, HR_ALIMENTO = ?, DS_ALIMENTO = ?, DT_ALIMENTO = ? WHERE ID_ALIMENTO = ?");
			pstmt.setString(1, food.getFoodName());
			pstmt.setDouble(2, food.getQuantityCalories());
			pstmt.setString(3, food.getHour());
			pstmt.setString(4, food.getFoodDescription());
			pstmt.setString(5, food.getDate());
			pstmt.setInt(6, foodId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insert(FoodModel food) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"INSERT INTO T_ALIMENTO (NM_ALIMENTO, QT_CALORIAS, DS_ALIMENTO, HR_ALIMENTO, ID_USUARIO, DT_ALIMENTO) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, food.getFoodName());
			pstmt.setDouble(2, food.getQuantityCalories());
			pstmt.setString(3, food.getFoodDescription());
			pstmt.setString(4, food.getHour());
			pstmt.setInt(5, food.getUserId());
			pstmt.setString(6, food.getDate());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remove(int foodId) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM T_ALIMENTO WHERE ID_ALIMENTO = ?");
			pstmt.setInt(1, foodId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getLastFood(int userId) {
		String lastFood = null;
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"select dt_alimento from t_alimento where id_usuario = ? and rownum = 1 order by dt_alimento desc");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lastFood = rs.getString("DT_ALIMENTO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lastFood;
	}
}
