/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author martina
 */
public class Game {

    Player[] player = new Player[4];
    Card[] dick = new Card[52];
    int keepTrackOfValidScore; //<=21

    public void generateDick() {

        int index = 0;
        for (int i = 0; i < 4; i++) {
            int value = 0;
            for (int j = 0; j < 13; j++) {
                if (value >= 10) {
                    value = 10;
                } else {
                    value = j + 1;
                }
                dick[index++] = new Card(i, j, value);
            }
        }
    }

    public Card ramdomSelect() {
        while (true) {
            Random rand = new Random();
            int randChoice = rand.nextInt(52);
            if (dick[randChoice] != null) {
                Card c = new Card(dick[randChoice]);
                dick[randChoice] = null;
                return c;
            }
        }
    }

    public void setInfo() {
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    System.out.print("Enter frist name: ");
                    break;
                case 1:
                    System.out.print("Enter second name: ");
                    break;
                case 2:
                    System.out.print("Enter third name: ");
                    break;
                default:
                    break;
            }

            Scanner in = new Scanner(System.in);
            player[i] = new Player(in.next());
            for (int j = 0; j < 2; j++) {
                player[i].addCard(ramdomSelect());
            }

        }
        player[3] = new Player("Dealer");
        for (int j = 0; j < 2; j++) {
            player[3].addCard(ramdomSelect());
        }
    }

    public int maxScore() {
        keepTrackOfValidScore = 0;
        for (int i = 0; i < 4; i++) {
            if (keepTrackOfValidScore < player[i].getScore() && player[i].getScore() <= 21) {
                keepTrackOfValidScore = player[i].getScore();
            }
        }
        return keepTrackOfValidScore;
    }

}
