package br.com.healthtrack.DAOImpl;

import java.sql.*;

import br.com.healthtrack.DAO.UserDAO;
import br.com.healthtrack.model.UserModel;
import br.com.healthtrack.util.DBManager;

public class UserDAOImpl implements UserDAO {

	private Connection connection;
	PreparedStatement pstmt = null;

	@Override
	public UserModel get(Integer userId) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT ID_USUARIO, DS_EMAIL, NM_USUARIO, DS_SEXO, VL_PESO, VL_ALTURA FROM T_USUARIO WHERE ID_USUARIO = ? AND ROWNUM = 1");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("ID_USUARIO"));
				user.setUserName(rs.getString("NM_USUARIO"));
				user.setUserMail(rs.getString("DS_EMAIL"));
				user.setUserGender(rs.getString("DS_SEXO"));
				user.setUserWeight(rs.getDouble("VL_PESO"));
				user.setUserHeight(rs.getDouble("VL_ALTURA"));

				return user;
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

		return null;
	}

	public UserModel getByEmail(String email) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"SELECT ID_USUARIO, DS_EMAIL, NM_USUARIO, DS_SEXO, VL_PESO, VL_ALTURA FROM T_USUARIO WHERE DS_EMAIL = ? AND ROWNUM = 1");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("ID_USUARIO"));
				user.setUserName(rs.getString("NM_USUARIO"));
				user.setUserMail(rs.getString("DS_EMAIL"));
				user.setUserGender(rs.getString("DS_SEXO"));
				user.setUserWeight(rs.getDouble("VL_PESO"));
				user.setUserHeight(rs.getDouble("VL_ALTURA"));

				return user;
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

		return null;
	}

	@Override
	public boolean checkPassword(Integer id, String password) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("SELECT NM_USUARIO FROM T_USUARIO WHERE ID_USUARIO = ? AND SENHA = ?");
			pstmt.setInt(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
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

		return false;
	}
	
	@Override
	public void update(UserModel user, Integer userId) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"UPDATE T_USUARIO SET NM_USUARIO = ?, DS_EMAIL = ?, DS_SEXO = ?, VL_PESO = ?, VL_ALTURA = ? WHERE ID_USUARIO = ?");
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserMail());
			pstmt.setString(3, user.getUserGender());
			pstmt.setDouble(4, user.getUserWeight());
			pstmt.setDouble(5, user.getUserHeight());
			pstmt.setInt(6, userId);
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
	public void update(UserModel user, Integer userId, String password) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"UPDATE T_USUARIO SET NM_USUARIO = ?, DS_EMAIL = ?, DS_SEXO = ?, VL_PESO = ?, VL_ALTURA = ?, SENHA = ? WHERE ID_USUARIO = ?");
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserMail());
			pstmt.setString(3, user.getUserGender());
			pstmt.setDouble(4, user.getUserWeight());
			pstmt.setDouble(5, user.getUserHeight());
			pstmt.setString(6, password);
			pstmt.setInt(7, userId);
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
	public void insert(UserModel user) {
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement(
					"INSERT INTO T_USUARIO (NM_USUARIO, DS_EMAIL, DS_SEXO, VL_PESO, VL_ALTURA, SENHA) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserMail());
			pstmt.setString(3, user.getUserGender());
			pstmt.setDouble(4, user.getUserWeight());
			pstmt.setDouble(5, user.getUserHeight());
			pstmt.setString(6, user.getUserPassword());
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
}
