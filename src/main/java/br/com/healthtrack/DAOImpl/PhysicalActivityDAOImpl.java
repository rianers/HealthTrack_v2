package br.com.healthtrack.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.healthtrack.DAO.PhysicalActivityDAO;
import br.com.healthtrack.model.UserActivityCategoryModel;
import br.com.healthtrack.model.UserActivityModel;
import br.com.healthtrack.util.DBManager;

public class PhysicalActivityDAOImpl implements PhysicalActivityDAO {

	private Connection connection;
	PreparedStatement pstmt = null;

	@Override
	public UserActivityModel get(int userActivityId) {
		UserActivityModel userActivity = null;
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT * FROM T_ATIVIDADE_USUARIO WHERE ID_ATIVIDADE_USUARIO = ? AND ROWNUM = 1");
			pstmt.setInt(1, userActivityId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				userActivity = new UserActivityModel();
				userActivity.setCalories(rs.getDouble("QT_CALORIA"));
				userActivity.setActivityDescription(rs.getString("DS_ATIVIDADE"));
				userActivity.setStartTime(rs.getString("HR_INICIAL"));
				userActivity.setEndTime(rs.getString("HR_FINAL"));
				userActivity.setIdUserActivity(userActivityId);
				userActivity.setDate(rs.getString("DT_ATIVIDADE"));
				userActivity.setActivityType(rs.getString("TP_ATIVIDADE"));
				userActivity.setUserId(rs.getInt("ID_USUARIO"));

				return userActivity;
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

		return userActivity;
	}

	@Override
	public List<UserActivityModel> getAll(int userId) {
		List<UserActivityModel> userActivities = new ArrayList<UserActivityModel>();

		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM T_ATIVIDADE_USUARIO WHERE ID_USUARIO = ?");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserActivityModel userActivity = new UserActivityModel();
				userActivity.setCalories(rs.getDouble("QT_CALORIA"));
				userActivity.setActivityDescription(rs.getString("DS_ATIVIDADE"));
				userActivity.setStartTime(rs.getString("HR_INICIAL"));
				userActivity.setEndTime(rs.getString("HR_FINAL"));
				userActivity.setIdUserActivity(rs.getInt("ID_ATIVIDADE_USUARIO"));
				userActivity.setDate(rs.getString("DT_ATIVIDADE"));
				userActivity.setActivityType(rs.getString("TP_ATIVIDADE"));
				userActivity.setUserId(rs.getInt("ID_USUARIO"));
				userActivity.setCategoryId(rs.getInt("ID_CATEGORIA"));

				userActivities.add(userActivity);
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

		return userActivities;
	}

	@Override
	public void update(int userActivityId, UserActivityModel model) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"UPDATE T_ATIVIDADE_USUARIO SET QT_CALORIA = ?, DS_ATIVIDADE = ?, HR_INICIAL = ?, HR_FINAL = ?, DT_ATIVIDADE = ?, TP_ATIVIDADE = ?, ID_CATEGORIA = ? WHERE ID_ATIVIDADE_USUARIO = ?");

			pstmt.setDouble(1, model.getCalories());
			pstmt.setString(2, model.getActivityDescription());
			pstmt.setString(3, model.getStartTime());
			pstmt.setString(4, model.getEndTime());
			pstmt.setString(5, model.getDate());
			pstmt.setString(6, model.getActivityType());
			pstmt.setInt(7, model.getCategoryId());
			pstmt.setInt(8, userActivityId);
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
	public void insert(UserActivityModel model) {

		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"INSERT INTO T_ATIVIDADE_USUARIO (QT_CALORIA, DS_ATIVIDADE, HR_INICIAL, HR_FINAL, DT_ATIVIDADE, TP_ATIVIDADE, ID_USUARIO, ID_CATEGORIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setDouble(1, model.getCalories());
			pstmt.setString(2, model.getActivityDescription());
			pstmt.setString(3, model.getStartTime());
			pstmt.setString(4, model.getEndTime());
			pstmt.setString(5, model.getDate());
			pstmt.setString(6, model.getActivityType());
			pstmt.setInt(7, model.getUserId());
			pstmt.setInt(8, model.getCategoryId());
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
	public void remove(int userActivityId) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM T_ATIVIDADE_USUARIO WHERE ID_ATIVIDADE_USUARIO = ?");
			pstmt.setInt(1, userActivityId);
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
	public List<UserActivityCategoryModel> getAllWithCategory(int userId) {
		List<UserActivityCategoryModel> userActivityCategories = new ArrayList<UserActivityCategoryModel>();

		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT tca.NM_CATEGORIA, tau.* FROM T_ATIVIDADE_USUARIO tau INNER JOIN T_CATEGORIA_ATIVIDADE tca ON tau.ID_CATEGORIA = tca.ID_CATEGORIA WHERE tau.ID_USUARIO = ?");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserActivityCategoryModel userActivityCategory = new UserActivityCategoryModel();
				userActivityCategory.setCalories(rs.getDouble("QT_CALORIA"));
				userActivityCategory.setCategoryName(rs.getString("NM_CATEGORIA"));
				userActivityCategory.setDescription(rs.getString("DS_ATIVIDADE"));
				userActivityCategory.setStartTime(rs.getString("HR_INICIAL"));
				userActivityCategory.setEndTime(rs.getString("HR_FINAL"));
				userActivityCategory.setUserActivityId(rs.getInt("ID_ATIVIDADE_USUARIO"));
				userActivityCategory.setDate(rs.getString("DT_ATIVIDADE"));
				userActivityCategory.setActivityType(rs.getString("TP_ATIVIDADE"));
				userActivityCategory.setUserId(rs.getInt("ID_USUARIO"));
				userActivityCategory.setCategoryId(rs.getInt("ID_CATEGORIA"));

				userActivityCategories.add(userActivityCategory);
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

		return userActivityCategories;
	}

	@Override
	public UserActivityCategoryModel getWithCategory(int userActivityId) {
		UserActivityCategoryModel userActivityCategory = null;
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT tca.NM_CATEGORIA, tau.* FROM T_ATIVIDADE_USUARIO tau INNER JOIN T_CATEGORIA_ATIVIDADE tca ON tau.ID_CATEGORIA = tca.ID_CATEGORIA WHERE tau.ID_ATIVIDADE_USUARIO = ? AND ROWNUM = 1");
			pstmt.setInt(1, userActivityId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				userActivityCategory = new UserActivityCategoryModel();
				userActivityCategory.setCalories(rs.getDouble("QT_CALORIA"));
				userActivityCategory.setCategoryName(rs.getString("NM_CATEGORIA"));
				userActivityCategory.setDescription(rs.getString("DS_ATIVIDADE"));
				userActivityCategory.setStartTime(rs.getString("HR_INICIAL"));
				userActivityCategory.setEndTime(rs.getString("HR_FINAL"));
				userActivityCategory.setUserActivityId(rs.getInt("ID_ATIVIDADE_USUARIO"));
				userActivityCategory.setDate(rs.getString("DT_ATIVIDADE"));
				userActivityCategory.setActivityType(rs.getString("TP_ATIVIDADE"));
				userActivityCategory.setUserId(rs.getInt("ID_USUARIO"));
				userActivityCategory.setCategoryId(rs.getInt("ID_CATEGORIA"));

				return userActivityCategory;
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

		return userActivityCategory;
	}

	@Override
	public String getLastActivity(int userId) {
		String lastActivity = null;
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT DT_ATIVIDADE FROM T_ATIVIDADE_USUARIO where ID_USUARIO = ? AND ROWNUM = 1 ORDER BY DT_ATIVIDADE DESC");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lastActivity = rs.getString("DT_ATIVIDADE");
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

		return lastActivity;
	}
}