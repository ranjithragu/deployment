package com.quiz.QuizApp.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.QuizApp.dao.UserDao;
import com.quiz.QuizApp.model.Details;
import com.quiz.QuizApp.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public User createUser(User user) {

		user.setRole(user.getRole());
		return userDao.saveUser(user);

	}

	public User doLogin(User user) {
		user.getUserName();
		user.getPassword();

		return userDao.doLogin(user);
	}

	public int updateScore(User user, Long userId) {
		user.getUserName();
		user.getPassword();

		return userDao.updateScore(user, userId);
	}
	
	public List<User> userList(Long userId) {
		
		return userDao.userList( userId);
	}
	
	public List<User> userListByFilter(String college,String department) {
		
		UserService exporter = new UserService();
		/*List<User> filtered =userDao.userListByFilter(college,department);
		System.err.println("filtered============>"+filtered);*/
        ArrayList<Object[]> dataList = exporter.getTableData(college,department);
        if(dataList != null && dataList.size() > 0){
            exporter.doExport(dataList);
        }else{
            System.out.println("There is no data available in the table to export");
        }
		
		return null;
	}

	private static Connection getConnection(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/quizApp";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","root");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Driver class not found. Please add MySQL connector jar in classpath");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Exception occured while getting Database connection");
        }
        return con;
    }
     
    public ArrayList<Object[]> getTableData(String college,String department){
        ArrayList<Object[]> tableDataList = null;
        Connection con = getConnection();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement("SELECT ID,name,score from USER where college='"+college+"' and department ='"+department+"'");
                ResultSet result = ps.executeQuery();
                tableDataList = new ArrayList<Object[]>();
                while(result.next()){
                    Object[] objArray = new Object[3];
                    objArray[0] = result.getInt(1);
                    objArray[1] = result.getString(2);
                    objArray[2] = result.getString(3);
                    tableDataList.add(objArray);
                    System.err.println("result.getString(2);=>"+result.getString(2));
                }
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Unable to create PreparedStatement");
            }
        }
        return tableDataList;
    }
     
    public void doExport(ArrayList<Object[]> dataList){
        if(dataList != null && !dataList.isEmpty()){
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet();
            HSSFRow headingRow = sheet.createRow(0);
            headingRow.createCell((short)0).setCellValue("ID");
            headingRow.createCell((short)1).setCellValue("studentsName");
            headingRow.createCell((short)2).setCellValue("score");
          //  headingRow.createCell((short)3).setCellValue("GENDER");
            short rowNo = 1;
            for (Object[] objects : dataList) {
                HSSFRow row = sheet.createRow(rowNo);
                row.createCell((short)0).setCellValue(objects[0].toString());
                row.createCell((short)1).setCellValue(objects[1].toString());
                row.createCell((short)2).setCellValue(objects[2].toString());
               // row.createCell((short)3).setCellValue(objects[3].toString());
                rowNo++;
            }
             
            String file = "D:/Student_details.xls";
            try{
                FileOutputStream fos = new FileOutputStream(file);
                workBook.write(fos);
                fos.flush();
            }catch(FileNotFoundException e){
                e.printStackTrace();
                System.out.println("Invalid directory or file not found");
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error occurred while writting excel file to directory");
            }
        }
    }
	
	

	public List<User> findAllUsersToAdmin() {
		
		List<User> list = userDao.findAllUsersToAdmin();

		return list;

	}

}
