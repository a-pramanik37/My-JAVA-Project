/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.util.*;
import project.dao.TournamentDaoImp;
import project.dao.UserServiceDaoImp;
import project.services.AdminServiceImp;
import project.services.UserServiceImp;
/**
 *
 * @author a_pramanik
 */
public class tournament {
   
    private AdminServiceImp adminServiceImp;
    private UserServiceImp userServiceImp;
    
    public tournament() {
        adminServiceImp = new AdminServiceImp();
        userServiceImp = new UserServiceImp();
    }

    public void run() {
        UserInterface.showHeader("JU Football Tournament Application");
       int option;
        Scanner s = new Scanner(System.in);
        
        do {
            
            UserInterface.showStartMenu();
            option = s.nextInt();
            switch (option) {
                case 1:
                    this.adminLogin();
                    
                    break;
                case 2:
                    this.userLogin();
                    break;
                case 3:
                    System.out.println("\t\t\t***Thank You***\t\t\t");
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }

        } while (option != 3);
    }

    
    
    public void adminLogin() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Scanner s = new Scanner(System.in);
        String ss;
        
        System.out.print("Enter admin password  : ");
        ss = s.nextLine();
        
        
        if(ss.equals("1234")==true)this.adminServiceImp.run();
        else UserInterface.tryAgainMsg();
        
        
    }
    
    public void userLogin(){
        UserServiceDaoImp obj = new UserServiceDaoImp();
        Scanner s = new Scanner(System.in);
        String email, pass, newPass;
        
        int op;
        
        
        do {
            
            UserInterface.userLoginMenu();
            op = s.nextInt();
            s.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Enter user email  : \n");
                    email = s.nextLine();
                    System.out.print("Enter user password  :\n");
                    pass = s.nextLine();
                    if(obj.hasAccount(email, pass)==true)userServiceImp.run();
                    else UserInterface.tryAgainMsg();
                    break;
                case 2:
                    System.out.print("Enter your email  : \n");
                    email = s.nextLine();
                    System.out.print("Enter your password  : \n");
                    pass = s.nextLine();
                    obj.userSignUp(email, pass);
                    break;
                case 3:
                    System.out.print("Enter your email  : \n");
                    email = s.nextLine();
                    System.out.print("Enter old password  : \n");
                    pass = s.nextLine();
                    System.out.print("Enter new password  : \n");
                    newPass = s.nextLine();
                    obj.changePassword(email, pass, newPass);
                    break;    
               
                case 4:
                    System.out.println("\t\t\t***Thank You***\t\t\t");
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }

        } while (op != 4);
        
    }
    
    
}
