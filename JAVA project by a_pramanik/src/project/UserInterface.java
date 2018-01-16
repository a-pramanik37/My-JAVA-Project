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
public class UserInterface {
    
    public static void showHeader(String msg){
        System.out.println("###################################################################");
        System.out.println("\t\t" + msg + "\t\t");
        System.out.println("###################################################################");
    }
    
    public static void showStartMenu() {
        System.out.println("\nPlease select any one from the options bellow:\n");
        System.out.println("     1. Manage App(Admin Only)");
        System.out.println("     2. User Services");
        System.out.println("     3. Quit");
    }
    public static void tryAgainMsg() {
        System.out.println("**** Please try again...");
    }
    
    public static void adminServiceMenu() {
        System.out.println("\nSelect one from the following options : ");
        System.out.println("     1. Input Match Result");
        System.out.println("     2. Start Tournament");
        System.out.println("     3. Dismiss Current Tournament");
        System.out.println("     4. Change Player");
        System.out.println("     5. See Players");
        System.out.println("     6. Back");
    }
    
    public static void userLoginMenu(){
        System.out.println("\nSelect one from the following options : ");
        System.out.println("     1. Login");
        System.out.println("     2. Sign Up");
        System.out.println("     3. Change Password");
        System.out.println("     4. Back");
    }
    
    public static void userServiceMenu() {
        System.out.println("\nSelect one from the following options : ");
        System.out.println("     1. See Fixture");
        System.out.println("     2. See Points Table");
        System.out.println("     3. See Top Scorers Table");
        System.out.println("     4. See Fair Play Table");
        System.out.println("     5. Back");
    }
}
