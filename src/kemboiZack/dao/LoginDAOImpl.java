package kemboiZack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kemboiZach.entity.Login;
import kemboiZack.util.DBConnectionUtil;

public class LoginDAOImpl implements LoginDAO{

	@Override
	//this is a DAO method which is authenthicate and passing login  object which contain email and password
	public String authenticate(Login login) {
		String sql = "SELECT * FROM tbl_login WHERE email= ? and password= ?";
		
		try {
			Connection connection = DBConnectionUtil.openConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, login.getEmail());
			ps.setString(2, login.getPassword());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return "true";
				
			}else {
				return "false";
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
		
	}

}
