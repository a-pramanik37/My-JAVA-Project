/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.services;
import java.util.Scanner;
import project.UserInterface;
import project.dao.AdminServiceDaoImp;

/**
 *
 * @author a_pramanik
 */
public class AdminServiceImp implements AdminService{
    
    private AdminServiceDaoImp adminServiceDaoImp;
    
    public AdminServiceImp(){
        adminServiceDaoImp = new AdminServiceDaoImp();
    }
    
    public void run(){
        
        
        int op;
        Scanner s = new Scanner(System.in);
        
        do {
            
            UserInterface.adminServiceMenu();
            op = s.nextInt();
            switch (op) {
                case 1:
                    this.inputMatchResult();
                    break;
                case 2:
                    this.startTournament();
                    break;
                case 3:
                    this.dismissCurrentTournament();
                    break;    
                case 4:
                    this.changePlayer();
                    break;
                case 5:
                    this.seePlayers();
                    break;
                case 6:
                    System.out.println("\t\t\t***Thank You***\t\t\t");
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }

        } while (op != 6);
    }
    
    @Override
    public void inputMatchResult() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        adminServiceDaoImp.inputMatchResult();
    }

    @Override
    public void dismissCurrentTournament() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        adminServiceDaoImp.dismissCurrentTournament();
    }

    @Override
    public void startTournament() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        adminServiceDaoImp.startTournament();
    }

    @Override
    public void changePlayer() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        adminServiceDaoImp.changePlayer();
    }

    @Override
    public void seePlayers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        adminServiceDaoImp.seePlayers();
    }

    
    
    
}
