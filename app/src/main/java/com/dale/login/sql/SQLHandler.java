package com.dale.login.sql;

import java.sql.*;

//String u_id = resultSet.getString(2);
//String password = resultSet.getString(3);
//String name = resultSet.getString(4);
//String gender = resultSet.getString(5);
//int phoneNumber = resultSet.getInt(6);
//Date date = resultSet.getDate(7);
//System.out.println(u_id + " " + password + " " + name + " " + gender + " " + phoneNumber + " " + date);

public class SQLHandler {
	private static String url = "jdbc:mysql://localhost/login";
	private static String root = "root";
	private static String myPW = "Dale9804!";
	
	public static boolean selectID(String id) { // ���̵� �ߺ� Ȯ�ο�
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
	}
	
	public static void insert(String id, String password, String name, String gender, int phoneNumber, Date birthDate){
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
			String sql = "INSERT INTO pet VALUES (?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);


			// 4. ������ binding
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, gender);
			preparedStatement.setInt(6, phoneNumber);
			preparedStatement.setDate(7, birthDate);
			
			
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
}
