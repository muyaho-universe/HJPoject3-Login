package com.dale.login.sql;

import java.sql.*;

import com.dale.login.data.LoadedData;
import com.dale.login.data.MyData;

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
            String sql = "SELECT user_id FROM users where user_id = \'" + id + "\'";
            
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
	
	public static void insert(String id, String password, String name, String gender, String phoneNumber, String birthDate){
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{
			// 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. �����ϱ�
			
			connection = DriverManager.getConnection(url, root, myPW);


			// 3. SQL ���� �غ�
			// �߰��Ϸ��� �������� ���� ���޵� ���ڸ� ���� �������� �Ҵ�Ǵ� ���̴�.
			// �� � ���� ���޵��� �𸣹Ƿ� Select �� ���� �޸�
			// stmt = conn.createStatement(); �� �ۼ����� �ʰ�
			// pstmt = conn.prepareStatement(sql); �� �ۼ��Ͽ� �����͸� �߰��� ������ �˸��ϴ�.
			// ���� sql ���� ������ + �����ڷ� �� �ٷ� �ۼ��� �� ������ �������� �ʹ� �������� �ǹǷ�
			// �� ����� ���մϴ�.
			String sql = "INSERT INTO users (user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate) VALUES (?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);

			// 4. ������ binding
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, gender);
			preparedStatement.setString(5, phoneNumber);
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

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("���� ����");
            String sql = "SELECT user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate FROM users where user_id != 'admin'";
            
           
            resultSet = statement.executeQuery(sql);
            
            
            while (resultSet.next()) {
            	LoadedData load = new LoadedData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4) ,resultSet.getString(5), resultSet.getString(6));
               // ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
               // �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
            	MyData.loadedData.add(load);
            	String u_id = resultSet.getString(1);
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
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("���� ����");
            String sql = "SELECT user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate FROM users where user_id = \'" + id + "\'";
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
            resultSet = statement.executeQuery(sql);
            
         // 6. ������ ����ϱ�
            if(resultSet.next()) {
            	result = -1;
            	System.out.println(resultSet.getString(2));
            	if(password.equals(resultSet.getString(2))) {
            		result = 1;
            		if(id.equals("admin")) {
            			result = 2;
            		}
            	}
            }
            
            if(result == 1) {
            	LoadedData data = new LoadedData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            	MyData.loadedData.add(data);
            }
            
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
		//SELECT u_id, u_password FROM users where u_id = id;
		//if(password.equals(restul.gettoString(2) c = 1; else c =-1;
		
		return result;
	} //END POINT: loginSelect
	
	public static void update(String id, String password, String name, String gender, String phoneNumber, String birthday) {
		Connection connection = null;
		Statement statement = null;

		try{
			// 1. ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. �����ϱ�
			
			connection = DriverManager.getConnection(url, root, myPW);


			// 3. SQL ���� �غ�
			// �߰��Ϸ��� �������� ���� ���޵� ���ڸ� ���� �������� �Ҵ�Ǵ� ���̴�.
			// �� � ���� ���޵��� �𸣹Ƿ� Select �� ���� �޸�
			// stmt = conn.createStatement(); �� �ۼ����� �ʰ�
			// pstmt = conn.prepareStatement(sql); �� �ۼ��Ͽ� �����͸� �߰��� ������ �˸��ϴ�.
			// ���� sql ���� ������ + �����ڷ� �� �ٷ� �ۼ��� �� ������ �������� �ʹ� �������� �ǹǷ�
			// �� ����� ���մϴ�.
			String sql = "UPDATE users SET user_password ='"+ password +"',user_name ='" + name+ "',user_gender ='"+ gender 
					+"', user_phonenumber ='" + phoneNumber +"',user_birthdate ='" + birthday + "' WHERE user_id ='"+id+"';";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			// 4. ������ binding
			
			
			// 5. ���� ���� �� ��� ó��
			// SELECT�� �޸� INSERT�� ��ȯ�Ǵ� �����͵��� �����Ƿ�
			// ResultSet ��ü�� �ʿ� ����, �ٷ� pstmt.executeUpdate()�޼��带 ȣ���ϸ� �˴ϴ�.
			// INSERT, UPDATE, DELETE ������ �̿� ���� �޼��带 ȣ���ϸ�
			// SELECT������ stmt.executeQuery(sql); �޼��带 ����߾����ϴ�.
			// @return     int - �� ���� row�� ������ ���ƴ����� ��ȯ
			
			
		} catch( ClassNotFoundException e){
	           System.out.println("����̹� �ε� ����");
	       }
	       catch( SQLException e){
	           System.out.println("���� " + e);
	       }
	       finally{
	           try{
	               if( connection != null && !connection.isClosed()){
	            	   connection.close();
	               }
	               if( statement != null && !statement.isClosed()){
	            	   statement.close();
	               }
	         
	           }
	           catch( SQLException e){
	               e.printStackTrace();
	           }
	       }

	} //END POINT: loginSelect
	
	public static void signOut(String id) {
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
            String sql = "DELETE from users where user_id = '"+id+"';";  
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
           statement.executeUpdate(sql);
            
         // 6. ������ ����ϱ�
//            isExisted = resultSet.next();
            
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
	}//END POINT: signOut
	
	public static void loadUser(String id) {
		//SELECT * FROM users where u_id = id;
	}//END POINT: loadUser
	
	public static void deleteAll() {
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
            String sql = "DELETE from users where user_id != 'admin';";  
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
           statement.executeUpdate(sql);
            
         // 6. ������ ����ϱ�
//            isExisted = resultSet.next();
            
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
	}//END POINT: deleteAll
	
	public static String findID(String name, String phoneNumber) {
		String return_id = "null";
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
            System.out.println(name + " " +phoneNumber); 
            String sql = "SELECT user_id FROM users where user_name = \'" + name + "\' and user_phonenumber ='"+phoneNumber+"';";
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
            resultSet = statement.executeQuery(sql);
            
         // 6. ������ ����ϱ�
            isExisted = resultSet.next();
            if(isExisted) {
            	return_id = resultSet.getString(1).trim();
            }
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
		
		return return_id;
	}//END POINT: findID
	
	public static String findPassword(String id, String name, String phoneNumber) {
		String return_password = "null";
		
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
            System.out.println(name + " " +phoneNumber); 
            String sql = "SELECT user_password FROM users where user_id ='"+ id+"'and user_name ='" + name + "\' and user_phonenumber ='"+phoneNumber+"';";
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
            resultSet = statement.executeQuery(sql);
            
         // 6. ������ ����ϱ�
            isExisted = resultSet.next();
            if(isExisted) {
            	return_password = resultSet.getString(1).trim();
            }
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
		return return_password;
	}//END POINT: findPassword
	
	public static void deleteOne(String id) {
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
            String sql = "DELETE from users where user_id = '"+id+"';";  
            
            // 5. ���� ����
            // ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
           statement.executeUpdate(sql);
            
         // 6. ������ ����ϱ�
//            isExisted = resultSet.next();
            
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
	}//END POINT: delete one
	
}
