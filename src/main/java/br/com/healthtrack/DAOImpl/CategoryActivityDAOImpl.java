package br.com.healthtrack.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.healthtrack.DAO.CategoryActivityDAO;
import br.com.healthtrack.model.CategoryActivityModel;
import br.com.healthtrack.util.DBManager;

public class CategoryActivityDAOImpl implements CategoryActivityDAO {

	private Connection connection;
	PreparedStatement pstmt = null;
	
	@Override
	public CategoryActivityModel get(int categoryId) {
		CategoryActivityModel categoryActivity = new CategoryActivityModel();
		
		try {
			connection = DBManager.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM T_CATEGORIA_ATIVIDADE WHERE ID_CATEGORIA = ? AND ROWNUM = 1");
			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categoryActivity = new CategoryActivityModel();
				categoryActivity.setCategoryId(rs.getInt("ID_CATEGORIA"));
				categoryActivity.setCategoryName(rs.getString("NM_CATEGORIA"));

				return categoryActivity;
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

		return categoryActivity;
	}
}
