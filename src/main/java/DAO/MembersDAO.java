package DAO;

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

import DTO.MembersDTO;

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
					//				String join_date = result.getString("join_date");

					Timestamp timestamp = result.getTimestamp("join_date");  // DB에서 TIMESTAMP 컬럼 가져옴

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
					String formatted = sdf.format(timestamp);
					//			MembersDTO(String id, String pw, String name, String phone, String email, String zipcode, String address1,
					//					String address2, String join_date)
					MembersDTO dto = new MembersDTO(id,"",name,phone,email,zipcode,address1,address2,formatted);
					System.out.println(name);
					System.out.println(email);
					System.out.println(formatted);
					System.out.println(phone);
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
		String sql = "update members set phone = ?, email = ?, zipcode = ?, address1 = ?, address2 = ? where id = ?";
		try(
				Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				)
		{
			System.out.println("myPageUpdate");
			pstat.setString(1, dto.getPhone());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getZipcode());
			pstat.setString(4, dto.getAddress1());
			pstat.setString(5, dto.getAddress2());
			pstat.setString(6, dto.getId());
			System.out.println("ID:"+dto.getId());

			System.out.println("실행될 쿼리 파라미터:");
			System.out.println("phone: " + dto.getPhone());
			System.out.println("email: " + dto.getEmail());
			System.out.println("zipcode: " + dto.getZipcode());
			System.out.println("address1: " + dto.getAddress1());
			System.out.println("address2: " + dto.getAddress2());
			System.out.println("id: " + dto.getId());

			System.out.println("여기까지온다11123123.");

			int result = pstat.executeUpdate();

			System.out.println("여기까지온다111.");
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();  // 반드시 예외 메시지 출력
			return 0;
		}

	}
}
