/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;

import project.ConnectionManager;
import java.util.*;
import java.sql.*;
import project.team;
import project.dao.TournamentDaoImp;

/**
 *
 * @author a_pramanik
 */
public class TableUpdateDaoImp implements TableUpdateDao {

    @Override
    public void updatePointsTable(String teamName, int g1, int g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        TournamentDaoImp x = new TournamentDaoImp();
        if(x.isStarted()==false){
            System.out.print("**** Tournament hasn't been started yet!!!\n");
            return;
        }
        team obj = new team();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from pointstable");
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                //System.out.print("DBG1  : "+resultSet.getString(1)+"\n");
                if(teamName.equals(resultSet.getString(1))){
                    //System.out.print("Paise PT   : "+teamName+"\n");
                    obj.setTeamName(teamName);
                    obj.setGamesPlayed(resultSet.getInt(2)+1);     //Increment of gamesPlayed
                    obj.setGamesWon(resultSet.getInt(3));
                    obj.setGamesLost(resultSet.getInt(4));
                    obj.setGamesDrawn(resultSet.getInt(5));
                    obj.setGoalsAgainst(resultSet.getInt(7)+g2);      //Update of goalsAgainst
                    obj.setGoalsFor(resultSet.getInt(8)+g1);          //Update of goalsFor
                    obj.setPoints(resultSet.getInt(9));
                    break;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        if(g1>g2){
            obj.setGamesWon(obj.getGamesWon()+1);         // Update of gamesWon
            obj.setPoints(obj.getPoints()+3);             // Update of points
        }         
    
        else if(g2>g1){
            obj.setGamesLost(obj.getGamesLost()+1);       // Update of gamesLost
        }  
        else {
            obj.setGamesDrawn(obj.getGamesDrawn()+1);     // Update of gamesDrawn
            obj.setPoints(obj.getPoints()+1);             // Update of points
        }         
       
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE pointsTable SET Played=?, Won=?,Lost=?,Drawn=?,GD=?,GA=?,GF=?,Points=? WHERE Team_Name=?");
            preparedStatement.setInt(1, obj.getGamesPlayed());
            preparedStatement.setInt(2, obj.getGamesWon());
            preparedStatement.setInt(3, obj.getGamesLost());
            preparedStatement.setInt(4, obj.getGamesDrawn());
            preparedStatement.setInt(5, obj.getGoalsFor()-obj.getGoalsAgainst());
            preparedStatement.setInt(6, obj.getGoalsAgainst());
            preparedStatement.setInt(7, obj.getGoalsFor());
            preparedStatement.setInt(8, obj.getPoints());
            preparedStatement.setString(9, obj.getTeamName());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        
        
    }

    @Override
    public void updateFairPlayTable(String teamName, int reds, int yellows) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        TournamentDaoImp x = new TournamentDaoImp();
        if(x.isStarted()==false){
            System.out.print("****  Tournament hasn't been started yet!!!\n");
            return;
        }
        
        team obj = new team();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from fairPlayTable");
            resultSet = preparedStatement.executeQuery();
            ///System.out.print("DBG2\n");
            while(resultSet.next()){
                if(teamName.equals(resultSet.getString(1))){
                    //System.out.print("Paise FPT  : "+teamName+"\n");
                    obj.setTeamName(teamName);
                    obj.setRedCards(resultSet.getInt(2)+reds);       //Updated
                    obj.setYellowCards(resultSet.getInt(3)+yellows);   // Updated
                    obj.setDemeritPoints(resultSet.getInt(4)+(reds*20+yellows*10));   //Updated
                    break;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE fairPlayTable SET Red_Cards=?,Yellow_Cards=?,Demerit_Points=? WHERE Team_Name=?");
            preparedStatement.setInt(1, obj.getRedCards());;
            preparedStatement.setInt(2, obj.getYellowCards());
            preparedStatement.setInt(3, obj.getDemeritPoints());
            preparedStatement.setString(4, obj.getTeamName());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }

    @Override
    public void updateFixture(String t1, String t2, int g1, int g2) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        String tmp=null;
        if(g1>g2)tmp=t1;
        else if(g2>g1)tmp=t2;
        else tmp="Drawn";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE fixture SET GoalsT1=?,GoalsT2=?,Winner=?,is_played=? WHERE Team1=? AND Team2=?");
            preparedStatement.setInt(1, g1);
            preparedStatement.setInt(2, g2);
            preparedStatement.setString(3, tmp);
            preparedStatement.setInt(4, 1);
            preparedStatement.setString(5, t1);
            preparedStatement.setString(6, t2);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
    }
    
}
