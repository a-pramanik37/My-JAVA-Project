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
public class player {
    private String name;
    private String jerseyNo;
    private String playingPosition;
    private int goalsScored;
    
    public player(){
    
        
        this.name="N/A";
        this.jerseyNo="N/A";
        this.playingPosition="N/A";
        this.goalsScored=0;
    }
    
    public player(String nm, String jrsyN, String plyP, int gls){
        this.name=nm;
        this.jerseyNo=jrsyN;
        this.playingPosition=plyP;
        this.goalsScored=gls;
    }
    
    public void setName(String nm){
        this.name=nm;
    }
    public String getName()
    {
        return name;
    }
    public void setJerseyNo(String jrsyN)
    {
        this.jerseyNo=jrsyN;
    }
    public String getJerseyNo(){
        return jerseyNo;
    }
    public void setPlayingPosition(String pp){
        this.playingPosition=pp;
    }
    public String getPlayingPosition(){
        return playingPosition;
    }
    public void setGoalsScored(int x){
        this.goalsScored=x;
    }
    public int getGoalsScored(){
        return goalsScored;
    }
    
    
}
