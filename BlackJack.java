/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author martina
 */
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    static Game g = new Game();

    public static void startGame() {
        g.generateDick();
        g.setInfo();
    }

    public static void playGame(GUI gui) {
        for (int i = 0; i < 3; i++) {
            System.out.println();
            int flag;
            while (true) {
                System.out.println("Player" + (i + 1) + " his score now is " + g.player[i].getScore());
                System.out.println(g.player[i].getName() + " Enter your choice :" + "\n" + "1. Hit" + "\n" + "2. Stand");
                Scanner in = new Scanner(System.in);
                flag = in.nextInt();
                if (flag == 1) {
                    Card c = new Card(g.ramdomSelect());
                    g.player[i].addCard(c);
                    gui.updatePlayerHand(c, i);
                    if (g.player[i].getScore() == 21) {
                        g.player[i].blackjack = true;
                        System.out.println(g.player[i].getName() + " is Blackjack.");
                        break;
                    } else if (g.player[i].getScore() > 21) {
                        g.player[i].busted = true;
                        System.out.println(g.player[i].getName() + " is Busted.");
                        break;
                    }
                } else if (flag == 2) {
                    break;
                }
            }
        }
        g.keepTrackOfValidScore = g.maxScore();
    }

    public static void dealer(GUI gui) {
        while (g.player[3].getScore() <= g.keepTrackOfValidScore) { // or wla and ??? || g.player[3].getScore() < 21
            if (g.player[0].busted == true && g.player[1].busted == true && g.player[2].busted == true) {
                break;
            } else {
                Card c = new Card(g.ramdomSelect());
                g.player[3].addCard(c);
                gui.updateDealerHand(c, g.dick);

            }

            if (g.player[3].getScore() == 21) {
                g.player[3].blackjack = true;
                System.out.println(g.player[3].getName() + " is Blackjack.");
                break;
            } else if (g.player[3].getScore() > 21) {
                g.player[3].busted = true;
                System.out.println(g.player[3].getName() + " is Busted.");
                break;
            }
        }
    }

    public static void wonGame() {
        int count = 0, woner = 0;
        for (int i = 0; i < 4; i++) {
            if (g.keepTrackOfValidScore == g.player[i].getScore()) {
                count++;
                woner = i;
            }
        }
        if (count > 1) {
            System.out.println("PUSH");
        } else if (count == 1 && g.player[woner].busted == false) {
            if (g.player[woner].blackjack == true) {
                System.out.println(g.player[woner].getName() + " won the game and Blackjack. ");
            } else {
                System.out.println(g.player[woner].getName() + " won the game. ");
            }
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        GUI gui = new GUI();
        startGame();
        gui.runGUI(g.dick, g.player[0].getCardWithPlayer(), g.player[1].getCardWithPlayer(), g.player[2].getCardWithPlayer(), g.player[3].getCardWithPlayer());
        playGame(gui);
        dealer(gui);
        wonGame();

    }

}
