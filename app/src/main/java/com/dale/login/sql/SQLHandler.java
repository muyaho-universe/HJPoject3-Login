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
	
	public static boolean isIDExisted(String id) { // 아이디 중복 확인용
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isExisted = false;
		try{
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            String sql = "SELECT user_id FROM users where user_id = \'" + id + "\'";
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            resultSet = statement.executeQuery(sql);
            
         // 6. 실행결과 출력하기
            isExisted = resultSet.next();
            
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			
			connection = DriverManager.getConnection(url, root, myPW);


			// 3. SQL 쿼리 준비
			// 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
			// 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
			// stmt = conn.createStatement(); 를 작성하지 않고
			// pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
			// 물론 sql 쿼리 내에서 + 연산자로 한 줄로 작성할 수 있지만 가독성이 너무 떨어지게 되므로
			// 이 방법을 권합니다.
			String sql = "INSERT INTO users (user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate) VALUES (?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);

			// 4. 데이터 binding
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, gender);
			preparedStatement.setString(5, phoneNumber);
			preparedStatement.setString(6, birthDate);
			
			
			// 5. 쿼리 실행 및 결과 처리
			// SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
			// ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
			// INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
			// SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
			// @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
			int count = preparedStatement.executeUpdate();
			if( count == 0 ){
				System.out.println("데이터 입력 실패");
			}
			else{
				System.out.println("데이터 입력 성공");
			}
		} catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
		} catch( SQLException e){
			System.out.println("에러 " + e);
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
            
            System.out.println("연결 성공");
            String sql = "SELECT user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate FROM users where user_id != 'admin'";
            
           
            resultSet = statement.executeQuery(sql);
            
            
            while (resultSet.next()) {
            	LoadedData load = new LoadedData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4) ,resultSet.getString(5), resultSet.getString(6));
               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
            	MyData.loadedData.add(load);
            	String u_id = resultSet.getString(1);
            	System.out.println(u_id);
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
            
            System.out.println("연결 성공");
            String sql = "SELECT user_id, user_password, user_name, user_gender, user_phonenumber, user_birthdate FROM users where user_id = \'" + id + "\'";
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            resultSet = statement.executeQuery(sql);
            
         // 6. 실행결과 출력하기
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
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			
			connection = DriverManager.getConnection(url, root, myPW);


			// 3. SQL 쿼리 준비
			// 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
			// 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
			// stmt = conn.createStatement(); 를 작성하지 않고
			// pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
			// 물론 sql 쿼리 내에서 + 연산자로 한 줄로 작성할 수 있지만 가독성이 너무 떨어지게 되므로
			// 이 방법을 권합니다.
			String sql = "UPDATE users SET user_password ='"+ password +"',user_name ='" + name+ "',user_gender ='"+ gender 
					+"', user_phonenumber ='" + phoneNumber +"',user_birthdate ='" + birthday + "' WHERE user_id ='"+id+"';";
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			// 4. 데이터 binding
			
			
			// 5. 쿼리 실행 및 결과 처리
			// SELECT와 달리 INSERT는 반환되는 데이터들이 없으므로
			// ResultSet 객체가 필요 없고, 바로 pstmt.executeUpdate()메서드를 호출하면 됩니다.
			// INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며
			// SELECT에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
			// @return     int - 몇 개의 row가 영향을 미쳤는지를 반환
			
			
		} catch( ClassNotFoundException e){
	           System.out.println("드라이버 로딩 실패");
	       }
	       catch( SQLException e){
	           System.out.println("에러 " + e);
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
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            String sql = "DELETE from users where user_id = '"+id+"';";  
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
           statement.executeUpdate(sql);
            
         // 6. 실행결과 출력하기
//            isExisted = resultSet.next();
            
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            String sql = "DELETE from users where user_id != 'admin';";  
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
           statement.executeUpdate(sql);
            
         // 6. 실행결과 출력하기
//            isExisted = resultSet.next();
            
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            System.out.println(name + " " +phoneNumber); 
            String sql = "SELECT user_id FROM users where user_name = \'" + name + "\' and user_phonenumber ='"+phoneNumber+"';";
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            resultSet = statement.executeQuery(sql);
            
         // 6. 실행결과 출력하기
            isExisted = resultSet.next();
            if(isExisted) {
            	return_id = resultSet.getString(1).trim();
            }
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            System.out.println(name + " " +phoneNumber); 
            String sql = "SELECT user_password FROM users where user_id ='"+ id+"'and user_name ='" + name + "\' and user_phonenumber ='"+phoneNumber+"';";
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
            resultSet = statement.executeQuery(sql);
            
         // 6. 실행결과 출력하기
            isExisted = resultSet.next();
            if(isExisted) {
            	return_password = resultSet.getString(1).trim();
            }
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
            // 1. 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            // mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
            // mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
            // 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연결하기
            // 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
            // Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
            // mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
//            String url = "jdbc:mysql://localhost/login";

            // @param  getConnection(url, userName, password);
            // @return Connection
            connection = DriverManager.getConnection(url, root, myPW);
            statement = connection.createStatement();
            
            System.out.println("연결 성공");
            String sql = "DELETE from users where user_id = '"+id+"';";  
            
            // 5. 쿼리 수행
            // 레코드들은 ResultSet 객체에 추가된다.
           statement.executeUpdate(sql);
            
         // 6. 실행결과 출력하기
//            isExisted = resultSet.next();
            
//            while (resultSet.next()) {
//               // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
//               // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
//            	String u_id = resultSet.getString(2);
//            	System.out.println(u_id);
//            }
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
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
