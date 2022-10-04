/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author martina
 */
public class Player {

    private String name;
    private int score;
    private Card[] cardWithPlayer = new Card[11];
    public int counter = 0;
    protected boolean blackjack = false;
    protected boolean busted = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Card[] getCardWithPlayer() {
        return cardWithPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCardWithPlayer(Card[] cardWithPlayer) {
        this.cardWithPlayer = cardWithPlayer;
    }

    public void addCard(Card c) {
        cardWithPlayer[counter++] = c;
        score += c.getValue();
    }
}
