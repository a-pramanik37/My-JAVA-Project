/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dao;
import project.dao.PlayerDaoImp;
import project.dao.TournamentDaoImp;
import project.dao.AdminServiceDao;
import project.dao.TournamentDao;
import project.ConnectionManager;
import project.dao.TableUpdateDaoImp;
import project.dao.ClearTablesDaoImp;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author a_pramanik
 */
public class AdminServiceDaoImp implements AdminServiceDao{

    @Override
    public void inputMatchResult() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        if(obj.gamesHeld()==obj.totalGames()){
            System.out.print("*****  Tournament is finished!!\n\n");
            return;
        }
        
        String t1=null;
        String t2 = null;
        
        int cnt=0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("Select * from fixture where is_played=0");
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next() && cnt<1){
                cnt++;
                t1 = resultSet.getString(1);
                t2 = resultSet.getString(2);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        
        
        ArrayList<String>players1 = new ArrayList<String>();
        ArrayList<String>jerseyNo1 = new ArrayList<String>();
        ArrayList<String>players2 = new ArrayList<String>();
        ArrayList<String>jerseyNo2 = new ArrayList<String>();
        
        int[] goals1 = new int[100];
        ArrayList<String> names1 = new ArrayList<String>();
        ArrayList<String> jersey1 = new ArrayList<String>();
        int[] goals2 = new int[100];
        ArrayList<String> names2 = new ArrayList<String>();
        ArrayList<String> jersey2 = new ArrayList<String>();
        
        System.out.print(t1+" vs "+t2+"\n");
        Scanner s = new Scanner(System.in);
        int g1, g2, r1, y1, r2, y2;
        System.out.print("Enter goals scored by team "+ t1+" : ");
        g1 = s.nextInt();
        
        
        if(g1>0){
            PlayerDaoImp obj111 = new PlayerDaoImp();
            players1 = obj111.getPlayers(t1);
            
            jerseyNo1 = obj111.getJerseyNo(t1);
            System.out.print("\nPlayers of team "+t1+" are : \n");
        System.out.print("      SL       Name\n");
        for(int i=0; i<players1.size(); i++){
            System.out.print("      "+(i+1)+"          "+players1.get(i)+"\n");
        }
        
        int totalGoals=0;
        int koyta=0;
        int xx, yy;
        String str;
        
        System.out.print("Now enter serial number of a player and number of goals scored by him one by one :\n");
        
        while(true){
            System.out.print("Enter Serial number of Scorer "+(koyta+1)+" : ");
            xx = s.nextInt();
            names1.add(players1.get(xx-1));
            jersey1.add(jerseyNo1.get(xx-1));
            System.out.print("Enter number of goals scored by "+players1.get(xx-1) +": ");
            yy = s.nextInt();
            totalGoals+=yy;
            goals1[koyta++]=yy;
            if(totalGoals==g1)break;
            if(totalGoals>g1){
                System.out.print("****  Invalid Input!!!  Try again later.\n\n");
                return;
            }
        }
        
        }
        
        
        System.out.print("Enter number of red cards : ");
        r1 = s.nextInt();
        System.out.print("Enter number of yellow cards : ");
        y1 = s.nextInt();
        
        
        
        System.out.print("\n\nEnter goals scored by team "+ t2+" : ");
        g2 = s.nextInt();
        
        if(g1>0){
            PlayerDaoImp obj222 = new PlayerDaoImp();
            players2 = obj222.getPlayers(t2);
            jerseyNo2 = obj222.getJerseyNo(t2);
            System.out.print("\nPlayers of team "+t1+" are : \n");
        System.out.print("      SL       Name\n");
        for(int i=0; i<players2.size(); i++){
            System.out.print("      "+(i+1)+"          "+players2.get(i)+"\n");
        }
        
        int totalGoals=0;
        int koyta=0;
        int xx, yy;
        String str;
        
        System.out.print("Now enter serial number of player and number of goals scored by him one by one :\n");
        
        while(true){
            System.out.print("Enter Serial number of Scorer "+(koyta+1)+" : ");
            xx = s.nextInt();
            names2.add(players2.get(xx-1));
            jersey2.add(jerseyNo2.get(xx-1));
            System.out.print("Enter number of goals scored by "+players2.get(xx-1) +": ");
            yy = s.nextInt();
            totalGoals+=yy;
            goals2[koyta++]=yy;
            if(totalGoals==g2)break;
            if(totalGoals>g2){
                System.out.print("**** Invalid Input!!!  Try again later.\n\n");
                return;
            }
        }
        
        }
        
        
        System.out.print("Enter number of red cards : ");
        r2 = s.nextInt();
        System.out.print("Enter number of yellow cards : ");
        y2 = s.nextInt();
        
        
        PlayerDaoImp obj333 = new PlayerDaoImp();
        
        if(g1>0){
            for(int i=0; i<names1.size(); i++){
                obj333.updateScorersList(t1, names1.get(i), jersey1.get(i), goals1[i]);
                obj333.goalsUpdateOfPlayer(t1, names1.get(i), jersey1.get(i), goals1[i]);
            }
        }
        
        if(g2>0){
            for(int i=0; i<names1.size(); i++){
                obj333.updateScorersList(t2, names2.get(i), jersey2.get(i), goals2[i]);
                obj333.goalsUpdateOfPlayer(t2, names2.get(i), jersey2.get(i), goals2[i]);
            }
        }
        
        
        TableUpdateDaoImp obj2 = new TableUpdateDaoImp();
        obj2.updateFixture(t1, t2, g1, g2);
        obj2.updatePointsTable(t1, g1, g2);
        obj2.updateFairPlayTable(t1, r1, y1);
        obj2.updatePointsTable(t2, g2, g1);
        obj2.updateFairPlayTable(t2, r2, y2);
        
    }

    @Override
    public void changePlayer() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        Scanner s = new Scanner(System.in);
        int n, ind;
        System.out.print("Teams playing in the tournament :  \n");
        
        //TournamentDaoImp obj = new TournamentDaoImp();
        ArrayList<String> teams = obj.getPlayingTeams();
        
        for(int i=0; i<teams.size(); i++){
            System.out.print("        "+(i+1)+". "+teams.get(i)+"\n");
        }
        System.out.print("** Choose a team to change it's player!!\n** Enter the serial number of the team : ");
        n = s.nextInt();
        if(n<1 || n>teams.size()){
            System.out.print("****  Invalid Input!!! Try again later\n\n");
            return;
        }
        
        System.out.println();
        PlayerDaoImp obj2 = new PlayerDaoImp();
        ArrayList<String>players = new ArrayList<String>();
        ArrayList<String>jerseyNo = new ArrayList<String>();
        players = obj2.getPlayers(teams.get(n-1));
        jerseyNo = obj2.getJerseyNo(teams.get(n-1));
        System.out.print("\nPlayers of team "+teams.get(n-1)+" are : \n");
        System.out.print("      SL       Name\n");
        for(int i=0; i<players.size(); i++){
            System.out.print("      "+(i+1)+"          "+players.get(i)+"\n");
        }
        String newPlayer=null;
        System.out.print("Now Enter the serial no. of the player to change : ");
        ind = s.nextInt();
        if(ind<1|| ind>11){
            System.out.print("**** Invalid Input!!! Try again later\n\n");
            return;
        }
        s.nextLine();
        System.out.print("Now enter the new player's name : ");
        newPlayer = s.nextLine();
        //System.out.print("Notun player  :  "+ newPlayer+"\n");
        obj2.changePlayer(teams.get(n-1), players.get(ind-1), newPlayer, jerseyNo.get(ind-1));
    
    }

    @Override
    public void seePlayers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         TournamentDaoImp obj = new TournamentDaoImp();
        if(obj.isStarted()==false){
            System.out.print("*****  Tournament hasn't started yet!!\n");
            return;
        }
        
        Scanner s = new Scanner(System.in);
        int ind;
        ArrayList<String> teams = new ArrayList<String>();
        teams = this.getTeamsOfCurrentTournament();
        System.out.println("    SL       Team Name\n");
        for(int i=0; i<teams.size(); i++){
            System.out.print("     "+(i+1)+"       "+teams.get(i)+"\n");
        }
        
        System.out.print("Enter serial number of the team : ");
        ind = s.nextInt();
        if(ind<1 || ind>teams.size()){
            System.out.print("***** Invalid input!  Try again Later\n\n");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String[][] data = new String[15][6];
        
        //System.out.print("\n     Player Informations of team "+teams.get(ind-1)+"  :\n");
        JFrame f = new JFrame("Player Info of Team "+teams.get(ind-1));
        //System.out.print("     Name    Jersey No    Playing Position      Goals Scored\n");
        try{
            connection = ConnectionManager.getConnection();
            String ss = "SELECT * from "+teams.get(ind-1);
            preparedStatement = connection.prepareStatement(ss);
            resultSet = preparedStatement.executeQuery();
            int k=0;
            while(resultSet.next()){
            //System.out.print("    "+resultSet.getString(1)+"       "+resultSet.getString(2)+"       "+resultSet.getString(3)+"          "+resultSet.getInt(4)+"\n");
            
            data[k][0] = resultSet.getString(1);
            data[k][1] = resultSet.getString(2);
            data[k][2] = resultSet.getString(3);
            data[k][3] = String.valueOf(resultSet.getInt(4));
            k++;
            }
            
            String column[] = {"Name", "Jersey No", "Playing Position", "Goals Scored"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30,40,400, 300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(700, 300);
            f.setVisible(true);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        
        
    }
    

    @Override
    public void dismissCurrentTournament() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    ClearTablesDaoImp xx = new ClearTablesDaoImp();
        xx.initializePlayersToTeams();
        xx.clearPointsTable();
        xx.clearScorersTable();
        xx.clearFairPlayTable();
        xx.clearFixture();
        System.out.print("*****  Current Tournament is successfully dismissed!!!\n");
    }
    
    @Override
    public void startTournament() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TournamentDaoImp zz = new TournamentDaoImp();
        if(zz.gamesHeld()==zz.totalGames() && zz.gamesHeld()!=0){
            System.out.print("*****   Current Tournament is Finished!! :D \n*****  You can start a new tournament now!\n\n");
            
        }
        
        else if(zz.isStarted()==true){
            System.out.print("*****  Tournament has already started!!!\n");
            return;
        }
        
        dismissCurrentTournament();
        
        TournamentDaoImp obj = new TournamentDaoImp();
        ArrayList<String>teams = null;
        teams=obj.getTeams();
        
        System.out.print("Available teams are : \n");
        System.out.print("       SL       Team Name\n\n");
        for(int i=0; i<teams.size(); i++){
            System.out.print("       "+(i+1)+"        "+teams.get(i)+"\n");
        }
        
        int n;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter how many teams : ");
        n = s.nextInt();
        
        if(n<1 || n>teams.size()){
            System.out.println("****  Sorry!! Invalid Input!!!");
            return;
        }
        
        ArrayList<String>t1=new ArrayList<String>();
        ArrayList<String>t2=new ArrayList<String>();
        ArrayList<String>tmp= new ArrayList<String>();
        System.out.print("Now enter the Serial Numbers of "+n+" teams : \n");
        
        for(int i=1; i<=n; i++){
            System.out.print("Enter team "+i+" : ");
            int x = s.nextInt();
            if(x>=1 && x<=teams.size()){
                x--;
                tmp.add(teams.get(x));
            }
            else {
                System.out.print("**** Invalid input!!\nTry again\n\n");
                i--;
            }
        }
        
        /*for(int i=0; i<tmp.size(); i++){
            System.out.print(tmp.get(i)+" ");
        }
        System.out.print("\n");*/
        ClearTablesDaoImp xx = new ClearTablesDaoImp();
        xx.initializePointsTable(tmp);
        xx.intitializeFairPlayTable(tmp);
        
        for(int i=0; i<tmp.size()-1; i++){
            for(int j=i+1; j<tmp.size(); j++){
                t1.add(tmp.get(i));
                t2.add(tmp.get(j));
            }
        }
        
        
        
        int half = t1.size()/2;
        
        int i=0;
        int j = t1.size()-1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            while(i<half){
                try{
                connection = ConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO fixture VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, t1.get(i));
                preparedStatement.setString(2, t2.get(i));
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4, 0);
                preparedStatement.setString(5, "N/A");
                preparedStatement.setInt(6, 0);
                i++;
                preparedStatement.executeUpdate();
            }catch(Exception e){
                    e.printStackTrace();
                    }finally{
                    
                    ConnectionManager.close(connection);
                    ConnectionManager.close(preparedStatement);
                }
                
               
                 try{
                connection = ConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO fixture VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, t1.get(j));
                preparedStatement.setString(2, t2.get(j));
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4, 0);
                preparedStatement.setString(5, "N/A");
                preparedStatement.setInt(6, 0);
                j--;
                preparedStatement.executeUpdate();
            }catch(Exception e){
                    e.printStackTrace();
                    }finally{
                    
                    ConnectionManager.close(connection);
                    ConnectionManager.close(preparedStatement);
                }
                
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        
        if((t1.size())%2==1){
            
             try{
                connection = ConnectionManager.getConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO fixture VALUES(?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, t1.get(half));
                preparedStatement.setString(2, t2.get(half));
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4, 0);
                preparedStatement.setString(5, "N/A");
                preparedStatement.setInt(6, 0);
                i++;
                preparedStatement.executeUpdate();
            }catch(Exception e){
                    e.printStackTrace();
                    }finally{
                    
                    ConnectionManager.close(connection);
                    ConnectionManager.close(preparedStatement);
                }
            
        }
        
        
    }

    @Override
    public ArrayList<String> getTeamsOfCurrentTournament() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        ArrayList<String> teams = new ArrayList<String>();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        
        try{
            
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * from pointstable");
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                teams.add(resultSet.getString(1));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ConnectionManager.close(connection);
            ConnectionManager.close(preparedStatement);
        }
        return teams;
    }
   
    
}
