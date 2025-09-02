package org.example.view;
import java.util.Scanner;

//============== INPUT UI 클래스 ==============
class MovieReviewInputUI {
 private Scanner scanner;
 
 public MovieReviewInputUI() {
     this.scanner = new Scanner(System.in);
 }
 
 public String getLoginChoice() {
     System.out.print("  선택하세요 (0-3): ");
     return scanner.nextLine().trim();
 }
 
 public String getMainMenuChoice() {
     System.out.print("  선택: ");
     return scanner.nextLine().trim();
 }
 
 public String getMovieChoice() {
     System.out.print("  선택: ");
     return scanner.nextLine().trim();
 }
 
 public String getMyInfoChoice() {
     System.out.print("  선택: ");
     return scanner.nextLine().trim();
 }
 
 public String getGeneralChoice() {
     System.out.print("  선택: ");
     return scanner.nextLine().trim();
 }
 
 public void waitForEnter() {
     scanner.nextLine();
 }
}
