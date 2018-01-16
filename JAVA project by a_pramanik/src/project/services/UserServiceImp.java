/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.services;
import java.util.Scanner;
import project.UserInterface;
import project.dao.UserServiceDaoImp;
/**
 *
 * @author a_pramanik
 */
public class UserServiceImp implements UserService{
    private UserServiceDaoImp userServiceDaoImp;
    
    public UserServiceImp(){
        userServiceDaoImp = new UserServiceDaoImp();
    }
    
    public void run(){
        int op;
        Scanner s = new Scanner(System.in);
        
        do {
            
            UserInterface.userServiceMenu();
            op = s.nextInt();
            switch (op) {
                case 1:
                    this.showFixture();
                    break;
                case 2:
                   this.showPointsTable();
                    break;
                case 3:
                    this.showTopScorersTable();
                    break;    
                case 4:
                   this.showFairPlayTable();
                    break;
                case 5:
                    System.out.println("\t\t\t***Thank You***\t\t\t");
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }

        } while (op != 5);
    }
    
    @Override
    public void showFixture() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        userServiceDaoImp.showFixture();
    }

    @Override
    public void showPointsTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        userServiceDaoImp.showPointsTable();
    }

    @Override
    public void showTopScorersTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        userServiceDaoImp.showTopScorersTable();
    }

    @Override
    public void showFairPlayTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        userServiceDaoImp.showFairPlayTable();
    }

   
    
}
