package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class PayDetailDAO {
	
		private static final String sql_insert="INSERT INTO PAYDETAIL (PAYNUM,PRODUCTNUM,PAYCOUNT) VALUES(?,?,?)";
		
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		public boolean insert(PayDetailVO pVO) {
			try {
				conn=JDBCUtil.connect();
				try {
					pstmt=conn.prepareStatement(sql_insert);
					pstmt.setInt(1, pVO.getPayNum());
					pstmt.setInt(2, pVO.getProductNum());
					pstmt.setInt(3, pVO.getPayCount());
					int res = pstmt.executeUpdate();
					if(res <= 0) {
						return false;
					}
				}
				catch (SQLIntegrityConstraintViolationException e) {
					return false;
				}
				catch (SQLException e){
					e.printStackTrace();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					return false;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return true;
		}

}
