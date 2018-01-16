/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author a_pramanik
 */
public class team {
    private String teamName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;
    private int redCards;
    private int yellowCards;
    private int demeritPoints;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    
    public team(){
     teamName="";
     gamesPlayed=0;
     gamesWon=0;
     gamesLost=0;
     gamesDrawn=0;
     redCards=0;
     yellowCards=0;
     demeritPoints=0;
     points=0;
     goalsFor=0;
     goalsAgainst=0;
    }
    
    public void setTeamName(String nm){
        this.teamName=nm;
    }
    public String getTeamName()
    {
        return teamName;
    }
    public void setGamesPlayed(int gp){
        this.gamesPlayed=gp;
    }
    public int getGamesPlayed()
    {
        return gamesPlayed;
    }
    public void setGamesWon(int gw){
        this.gamesWon=gw;
    }
    public int getGamesWon(){
        return gamesWon;
    }
    public void setGamesLost(int gl){
        this.gamesLost=gl;
    }
    public int getGamesLost()
    {
        return gamesLost;
    }
    public void setGamesDrawn(int gd){
        this.gamesDrawn=gd;
    }
    public int getGamesDrawn()
    {
        return gamesDrawn;
    }
    public void setRedCards(int rc){
        this.redCards=rc;
    }
    public int getRedCards()
    {
        return redCards;
    }
    public void setYellowCards(int yc)
    {
        this.yellowCards=yc;
    }
    public int getYellowCards()
    {
        return yellowCards;
    }
    public void setDemeritPoints(int dp)
    {
        this.demeritPoints=dp;
    }
    public int getDemeritPoints(){
        return demeritPoints;
    }
    public void setPoints(int p)
    {
        this.points=p;
    }
    public int getPoints(){
        return points;
    }
    public void setGoalsFor(int gf){
        this.goalsFor=gf;
    }
    public int getGoalsFor(){
        return goalsFor;
    }
    public void setGoalsAgainst(int ga){
        this.goalsAgainst=ga;
    }
    public int getGoalsAgainst(){
        return goalsAgainst;
    }
            
}
