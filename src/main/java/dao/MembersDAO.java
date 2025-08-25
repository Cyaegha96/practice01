package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MembersDTO;

public class MembersDAO {
	private static MembersDAO instance;

	public synchronized static MembersDAO getInstance() {
		if(instance==null)
		{
			instance = new MembersDAO();
		}	
		return instance;
	}

	public Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	public void addMember(MembersDTO membersDTO) throws Exception{
		//dto 받아서 멤버 추가하는 메서드
		String sql = "insert into members values(?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";

		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			pstat.setString(1, membersDTO.getId());
			pstat.setString(2, membersDTO.getPw());
			pstat.setString(3, membersDTO.getName());
			pstat.setString(4, membersDTO.getPhone());
			pstat.setString(5, membersDTO.getEmail());
			pstat.setString(6, membersDTO.getZipcode());
			pstat.setString(7, membersDTO.getAddress1());
			pstat.setString(8, membersDTO.getAddress2());
			pstat.executeUpdate();
		}
	}

	public boolean checkDuplicateId(String id) throws Exception{
		//id로 중복 체크하는 메서드
		String sql = "select id from members where id=?";

		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			pstat.setString(1, id);
			ResultSet rs = pstat.executeQuery();
			if(rs.next())
			{
				//rs.next()가 true면 값이 있다는 소리니까 true를 리턴
				return true;
			}
			else
			{
				//else면 없다는 소리여서 true 리턴
				return false;
			}
		}


	}

	public boolean checkLogin(String id, String pw) throws Exception{
		//로그인 가능한지 확인하는 메서드
		String sql = "select * from members where id=? and pw=?";

		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			pstat.setString(1, id);
			pstat.setString(2, pw);
			ResultSet rs = pstat.executeQuery();
			if(rs.next())
			{
				//rs.next()가 true면 값이 있다는 소리니까 true를 리턴
				return true;
			}
			else
			{
				//else면 없다는 소리여서 true 리턴
				return false;
			}
		}
	}

	public int deleteMember(String id) throws Exception{
		//회원 삭제 메서드
		String sql = "delete from members where id=?";

		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			pstat.setString(1, id);

			int result = pstat.executeUpdate();
			return result;
		}
	}

	public MembersDTO myPagePrint(String id) throws Exception{
		//마이 페이지 정보 가져오는 메서드
		System.out.println(id);
		String sql = "select * from members where id=?";

		try ( Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{

			pstat.setString(1,id);

			try(
					ResultSet result = pstat.executeQuery();)
			{

				List<MembersDTO> list = new ArrayList<>();

				if(result.next())
				{
					String name = result.getString("name");
					String phone = result.getString("phone");
					String email = result.getString("email");
					String zipcode =result.getString("zipcode");

					String address1 =result.getString("address1");

					String address2 =result.getString("address2");
					
					Timestamp timestamp = result.getTimestamp("join_date");

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
					String formatted = sdf.format(timestamp);
					MembersDTO dto = new MembersDTO(id,"",name,phone,email,zipcode,address1,address2,formatted);
					
					return dto;	
				}
				else
				{
					System.out.println("mypage 데이터가 없음? 말안됨.");
					return null;
				}
			}
		}
	}

	public int myPageUpdate(MembersDTO dto) throws Exception{
		//dto를 받아서 myPage 정보 수정해주는 메서드
		String sql = "update members set phone = ?, email = ?, zipcode = ?, address1 = ?, address2 = ? where id = ?";
		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			pstat.setString(1, dto.getPhone());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getZipcode());
			pstat.setString(4, dto.getAddress1());
			pstat.setString(5, dto.getAddress2());
			pstat.setString(6, dto.getId());
			

			int result = pstat.executeUpdate();

			return result;
		}
		catch (Exception e) {
			e.printStackTrace();  // 반드시 예외 메시지 출력
			return 0;
		}

	}
}
