package com.sist.member.dao;
import java.util.*; // ArrayList
import java.sql.*; // Connection,PreparedStatement,ResultSet
import javax.naming.*;// Context
import javax.sql.*;// DataSource => Database의 정보 
// DBCP : Connection을 미리 생성후에 주소를 얻어서 오라클에 접근하는 방식 
/*
 *    1) 생성된 Connection 가지고 오다  : getConnection()
 *    2) 사용
 *    3) 반환 
 *    =======> Connection의 갯수를 제한하면서 재사용이 가능하게 만들어 준다 
 */
public class MemberDAO {
    private Connection conn;
    private PreparedStatement ps;
    // 주소를 얻는다 (POOL:Connection을 관리하는 영역)
    /*
     *    POOL
     *    =====================================
     *     java://comp/env (c:\\)
     *    =====================================
     *     이름                           주소            사용여부
     *     jdbc/oracle     100       false
     *     jdbc/oracle     200       false
     *     jdbc/oracle     300       false
     *     jdbc/oracle     400       false
     *     jdbc/oracle     500       false
     *    =====================================
     *      사용중 : true, 반환 : false
     *    =====================================
     *    
     *    class A
     *    class B
     *     => jndi.bind("aaa",new A())
     *     => lookup() ===================> Spring
     *     
     *     Spring => 설정 파일 : 사용자가 만든 클래스를 관리 => Spring Boot
     *     =======  Container
     *     
     *     기존의 프로젝트 => Boot (마이그레이션)
     */
    public void getConnection()
    {
    	// Context c;
    	try
    	{
    		// POOL연결 
    		Context init=new InitialContext();
    		// 탐색기를 연다 ==> JNDI
    		// C:\\드라이버에 연결
    		Context c=(Context)init.lookup("java://comp/env");
    		// 저장된 데이터베이스의 정보(DataSource)를 얻어온다
    		DataSource ds=(DataSource)c.lookup("jdbc/oracle");
    		
    		conn=ds.getConnection();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    // 반환 
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(conn!=null) conn.close(); // 사용중이라면..
    	}catch(Exception ex){}
    	
    }
    // 기능 
    public int idcheck(String id)
    {
    	int count=0;
    	try
    	{
    		// 주소값 얻기
    		getConnection();
    		// SQL문장 전송
    		String sql="SELECT COUNT(*) FROM food_member "
    				  +"WHERE id=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, id);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		count=rs.getInt(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();// 반환
    	}
    	return count;
    }
    public ArrayList<ZipcodeVO> postfind(String dong)
    {
    	ArrayList<ZipcodeVO> list=
    			   new ArrayList<ZipcodeVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT zipcode,sido,gugun,dong,"
    				  +"NVL(bunji,' ') "
    				  +"FROM zipcode "
    				  +"WHERE dong LIKE '%'||?||'%'";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, dong);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			ZipcodeVO vo=new ZipcodeVO();
    			vo.setZipcode(rs.getString(1));
    			vo.setSido(rs.getString(2));
    			vo.setGugun(rs.getString(3));
    			vo.setDong(rs.getString(4));
    			vo.setBunji(rs.getString(5));
    			list.add(vo);
    		}
    		rs.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    public int postfindCount(String dong)
    {
    	int count=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM zipcode "
    				  +"WHERE dong LIKE '%'||?||'%'";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, dong);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		count=rs.getInt(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	finally
    	{
    		disConnection();
    	}
    	return count;
    }
    	public void memberJoin(MemberVO vo)
    	{
    		
    	}
}











