/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;

import project.ConnectionManager;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author a_pramanik
 */
public class UserServiceDaoImp implements UserServiceDao {

    @Override
    public void showFixture() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String[][] data = new String[50][10];
        JFrame f = new JFrame("Fixture");
        int k=0;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from fixture");
            resultSet = preparedStatement.executeQuery();
            //System.out.print("Team1   Team2   Goals_Team1  Goals_Team2  Winner\n\n");
            while(resultSet.next()){
                //System.out.print(resultSet.getString(1)+"  "+resultSet.getString(2)+"  "+resultSet.getInt(3)+"  "+resultSet.getInt(4)+"  "+resultSet.getString(5)+"\n");
            
                data[k][0]=resultSet.getString(1);
                data[k][1]=resultSet.getString(2);
                data[k][2]=String.valueOf(resultSet.getInt(3));
                data[k][3]=String.valueOf(resultSet.getInt(4));
                data[k][4]=resultSet.getString(5);
                k++;
            }
            
            String column[] = {"Team-1", "Team-2", "Goals(Team-1)","Goals(Team-2)", "Winner"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30,40,400, 300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(700, 250);
            f.setVisible(true);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    @Override
    public void showPointsTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JFrame f = new JFrame("Points Table");
        String[][] data = new String[50][12];
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM pointstable order by points desc, GD desc, Team_Name asc");
            resultSet = preparedStatement.executeQuery();
            
            //System.out.print("\nTeam Name  Played  Won  Lost  Drawn  GD  GA  GF   Points\n\n");
            int k=0;
            while(resultSet.next()){
                //System.out.print(resultSet.getString(1)+"   "+resultSet.getInt(2)+"   "+resultSet.getInt(3)+"   "+resultSet.getInt(4)+"   "+resultSet.getInt(5)+"   "+resultSet.getInt(6)+"   "+resultSet.getInt(7)+"   "+resultSet.getInt(8)+"   "+resultSet.getInt(9)+"\n");
            
                data[k][0]=resultSet.getString(1);
                data[k][1]=String.valueOf(resultSet.getInt(2));
                data[k][2]=String.valueOf(resultSet.getInt(3));
                data[k][3]=String.valueOf(resultSet.getInt(4));
                data[k][4]=String.valueOf(resultSet.getInt(5));
                data[k][5]=String.valueOf(resultSet.getInt(6));
                data[k][6]=String.valueOf(resultSet.getInt(7));
                data[k][7]=String.valueOf(resultSet.getInt(8));
                data[k][8]=String.valueOf(resultSet.getInt(9));
                k++;
            }
            String column[] = {"Team Name", "Played", "Won","Lost", "Drawn", "Goal Diff.","Goals Against", "Goals For", "Points" };
            JTable jt = new JTable(data, column);
            jt.setBounds(30,40,400, 300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(900, 250);
            f.setVisible(true);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    
    }

    @Override
    public void showTopScorersTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String[][] data = new String[100][10];  

       
        
        JFrame f = new JFrame("Top Scorers Table");
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM topscorerstable order by Goals desc, Name asc, Team_Name asc");
            resultSet = preparedStatement.executeQuery();
            
            //System.out.print("  Name     Team Name    Goals\n\n");
            int k=0;
            while(resultSet.next()){
                //System.out.print(resultSet.getString(1)+"    "+resultSet.getString(3)+"        "+resultSet.getInt(4)+"\n");
                data[k][0]=resultSet.getString(1);
                data[k][1]=resultSet.getString(3);
                String tmp = String.valueOf(resultSet.getString(4));
                data[k][2]=tmp;
                k++;
            }
            
            String column[] = {"Name", "Team Name", "Goals"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30,40,400, 300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(600, 250);
            f.setVisible(true);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    
    }
   

    @Override
    public void showFairPlayTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        JFrame f = new JFrame("Fair Play Table");
        String[][] data = new String[50][10];
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM fairplaytable order by Demerit_Points asc, Team_Name asc");
            resultSet = preparedStatement.executeQuery();
            
            //System.out.print("\nTeam Name   Red Cards   Yellow Cards   Demerit Points\n");
            int k=0;
            while(resultSet.next()){
                //System.out.print(resultSet.getString(1)+"          "+resultSet.getInt(2)+"          "+resultSet.getInt(3)+"          "+resultSet.getInt(4)+"\n");
            
                data[k][0] = resultSet.getString(1);
                data[k][1] = String.valueOf(resultSet.getInt(2));
                data[k][2] = String.valueOf(resultSet.getInt(3));
                data[k][3] = String.valueOf(resultSet.getInt(4));
                k++;
            }
            
             String column[] = {"Team Name", "Red Cards", "Yellow Cards", "Demerit Points"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30,40,400, 300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(600, 250);
            f.setVisible(true);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    

    @Override
    public boolean hasAccount(String email, String pass) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean ans=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from userlogin");
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if(email.equals(resultSet.getString(1))  &&  pass.equals(resultSet.getString(2))){
                    ans=true;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    return ans;
    }

    @Override
    public void changePassword(String email, String oldPass, String newPass) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(isValidEmail(email)==false){
            System.out.print("****  Your enterd email was not found!!\n**** Please try again later!!\n");
            return;
        }
        if(hasAccount(email, newPass)==true){
            System.out.print("**** Combination of your new password and email has already been used!!\n**** Try again later\n");
            return;
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userlogin SET Password=? WHERE Email=?");
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, email);
          
            preparedStatement.executeUpdate();
            System.out.print("**** Password change successfull!!\n\n");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    
    }


    

    @Override
    public void userSignUp(String email, String pass) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        if(hasAccount(email, pass)==true){
            System.out.print("You already have an account!! Please login!\n\n");
            return;
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("INSERT into userlogin values(?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
             System.out.print("**** Sign Up successfull!!\n\n");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    
    }

    @Override
    public boolean isValidEmail(String email) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    boolean ans=false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from userlogin");
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if(email.equals(resultSet.getString(1))){
                    ans=true;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
    return ans;
    
    }
    
}
