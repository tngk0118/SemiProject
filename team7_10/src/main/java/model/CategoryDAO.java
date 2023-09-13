package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static final private String SQL_SELECTALL="SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY";
	static final private String SQL_SELECTALL_LIMIT="SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY LIMIT 0,?";
	static final private String SQL_SELECTONE="SELECT CATEGORYNUM,CATEGORYNAME FROM CATEGORY WHERE CATEGORYNUM=?";

	public ArrayList<CategoryVO> selectAll(CategoryVO cVO){
		conn=JDBCUtil.connect();

		ArrayList<CategoryVO> cdatas= new ArrayList<CategoryVO>();
		try {
//				pstmt=conn.prepareStatement(SQL_SELECTALL_LIMIT);                  
//				pstmt.setInt(1,cVO.getListcnt());
//				rs=pstmt.executeQuery();
				pstmt=conn.prepareStatement(SQL_SELECTALL); 
				rs=pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryVO cdata=new CategoryVO();
				cdata.setCategoryNum(rs.getInt("CATEGORYNUM"));
				cdata.setCategoryName(rs.getString("CATEGORYNAME"));
				cdatas.add(cdata);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {      
			JDBCUtil.disconnect(rs,pstmt, conn);
		}
		return cdatas;
	}


	public CategoryVO selectOne(CategoryVO cVO) {
		conn=JDBCUtil.connect();

		CategoryVO cdata=null;

		try {
			pstmt=conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, cVO.getCategoryNum());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cdata=new CategoryVO();
				cdata.setCategoryNum(rs.getInt("CATEGORYNUM"));
				cdata.setCategoryName(rs.getString("CATEGORYNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return cdata;
	}

}
