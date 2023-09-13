package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImagesDAO {
	
		private static final String sql_selectAll="SELECT IMAGENUM,PATH,PRODUCTNUM FROM IMAGES WHERE PRODUCTNUM=? AND PATH NOT LIKE '%(1)%';";
		
		Connection conn;
		PreparedStatement pstmt;
		ResultSet rs;
		
		public ArrayList<ImagesVO> selectAll(ImagesVO iVO){
			ArrayList<ImagesVO> idatas = new ArrayList<ImagesVO>();
			try {
				conn=JDBCUtil.connect();
				try {
					pstmt=conn.prepareStatement(sql_selectAll);
					pstmt.setInt(1, iVO.getProductNum());
					rs=pstmt.executeQuery();

					while(rs.next()) {
						ImagesVO data=new ImagesVO();
						data.setImageNum(rs.getInt("IMAGENUM"));
						data.setPath(rs.getString("PATH"));
						data.setProductNum(rs.getInt("PRODUCTNUM"));
						idatas.add(data);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
						return null;
					}
					return null;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return idatas;
		}

	
	
}
