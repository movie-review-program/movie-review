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
 
 public String getChoice() {
     System.out.print("  선택: ");
     return scanner.nextLine().trim();
 }
 
 public void waitForEnter() {
     scanner.nextLine();
 }
}
