package com.dale.login.sql;

import java.sql.*;

//String u_id = resultSet.getString(1);
//String password = resultSet.getString(2);
//String name = resultSet.getString(3);
//String gender = resultSet.getString(4);
//int phoneNumber = resultSet.getInt(5);
//String date = resultSet.getDate(6);
//System.out.println(u_id + " " + password + " " + name + " " + gender + " " + phoneNumber + " " + date);

public class SQLHandler {
	private static String url = "jdbc:mysql://localhost/login";
	private static String root = "root";
	private static String myPW = "Dale9804!";
	
	public static boolean isIDExisted(String id) { // ���̵� �ߺ� Ȯ�ο�
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isExisted = false;
		try{
            // 1. ����̹� �ε�
            // ����̹� �������̽��� ������ Ŭ������ �ε�
            // mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
            // mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
            // ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. �����ϱ�
            // ����̹� �Ŵ������� Connection ��ü�� �޶�� ��û�Ѵ�.
            // Connection�� ��� ���� �ʿ��� url ����, �����縶�� �ٸ���.
            // mysql�� "jdbc:mysql://localhost/�����db�̸�" �̴�.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("���� ����");
            String sql = "SELECT user_id FROM users where user_id = " + id;
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
            resultSet = statement.executeQuery(sql);
            
         // 6. ������ ����ϱ�
            isExisted = resultSet.next();
            
//            while (resultSet.next()) {
//               // ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
//               // �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }
        catch(SQLException e){
            System.out.println("����: " + e);
        }
        finally{
            try{
                if( connection != null && !connection.isClosed()){
                	connection.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
		return isExisted;
	}//END POINT: isIDExisted
	
	public static void insert(String id, String password, String name, String gender, int phoneNumber, String birthDate){
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");

			// 2. �����ϱ�
			
			connection = DriverManager.getConnection(url, root, myPW);


			// 3. SQL ���� �غ�
			// �߰��Ϸ��� �������� ���� ���޵� ���ڸ� ���� �������� �Ҵ�Ǵ� ���̴�.
			// �� � ���� ���޵��� �𸣹Ƿ� Select �� ���� �޸�
			// stmt = conn.createStatement(); �� �ۼ����� �ʰ�
			// pstmt = conn.prepareStatement(sql); �� �ۼ��Ͽ� �����͸� �߰��� ������ �˸��ϴ�.
			// ���� sql ���� ������ + �����ڷ� �� �ٷ� �ۼ��� �� ������ �������� �ʹ� �������� �ǹǷ�
			// �� ����� ���մϴ�.
			String sql = "INSERT INTO users VALUES (?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);


			// 4. ������ binding
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, gender);
			preparedStatement.setInt(5, phoneNumber);
			preparedStatement.setString(6, birthDate);
			
			
			// 5. ���� ���� �� ��� ó��
			// SELECT�� �޸� INSERT�� ��ȯ�Ǵ� �����͵��� �����Ƿ�
			// ResultSet ��ü�� �ʿ� ����, �ٷ� pstmt.executeUpdate()�޼��带 ȣ���ϸ� �˴ϴ�.
			// INSERT, UPDATE, DELETE ������ �̿� ���� �޼��带 ȣ���ϸ�
			// SELECT������ stmt.executeQuery(sql); �޼��带 ����߾����ϴ�.
			// @return     int - �� ���� row�� ������ ���ƴ����� ��ȯ
			int count = preparedStatement.executeUpdate();
			if( count == 0 ){
				System.out.println("������ �Է� ����");
			}
			else{
				System.out.println("������ �Է� ����");
			}
		} catch( ClassNotFoundException e){
			System.out.println("����̹� �ε� ����");
		} catch( SQLException e){
			System.out.println("���� " + e);
		} finally{
			try{
				if( connection != null && !connection.isClosed()){
					connection.close();
				}
				if( preparedStatement != null && !preparedStatement.isClosed()){
					preparedStatement.close();
				}
			} catch( SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void selectAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
            // 1. ����̹� �ε�
            // ����̹� �������̽��� ������ Ŭ������ �ε�
            // mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
            // mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
            // ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. �����ϱ�
            // ����̹� �Ŵ������� Connection ��ü�� �޶�� ��û�Ѵ�.
            // Connection�� ��� ���� �ʿ��� url ����, �����縶�� �ٸ���.
            // mysql�� "jdbc:mysql://localhost/�����db�̸�" �̴�.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("���� ����");
            String sql = "SELECT * FROM users";
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
            resultSet = statement.executeQuery(sql);
            
         // 6. ������ ����ϱ�
            // ��ü ��� loadedData�� ���� �ְ� �װ� ��½�Ű��
            
            while (resultSet.next()) {
               // ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
               // �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
            	String u_id = resultSet.getString(2);
            	System.out.println(u_id);
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }
        catch(SQLException e){
            System.out.println("����: " + e);
        }
        finally{
            try{
                if( connection != null && !connection.isClosed()){
                	connection.close();
                }
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
	} //END POINT: selectAll
	
	public static int loginSelect(String id, String password) {
		int result = 0;
		
		//SELECT u_id, u_password FROM users where u_id = id;
		//if(password.equals(restul.gettoString(2) c = 1; else c =-1;
		
		return result;
	} //END POINT: loginSelect
	
	public static void update(String password, String name) {
		//UPDATE
	} //END POINT: loginSelect
	
	public static void signOut(String id) {
		//DELETE * FROM users where u_id = id;
	}//END POINT: signOut
	
	public static void loadUser(String id) {
		//SELECT * FROM users where u_id = id;
	}//END POINT: loadUser
	
	public static void deleteAll() {
		//DELET * FROM users where not u_id = "admin";
	}//END POINT: deleteAll
	
	public static String findID(String name, String phoneNumber) {
		String return_id = "null";
		return return_id;
	}//END POINT: findID
	
	public static String findPassword(String id, String name, String phoneNumber) {
		String return_password = "null";
		return return_password;
	}//END POINT: findPassword
}
