package com.team3.forcemajeure.util;
/*
 *
 */

public class Battle {
    public static void battle(Enemy enemy, Player player, int rollcount){
        int winner = roll(enemy, player, rollcount);
        if (winner < 0){
            System.out.println("you won!!");
            enemy.health = 0;
            enemy.talk();
        }
        else if (winner == 0){
            System.out.println("you tied!!");
        }
        else {
            System.out.println("you lost, come back when you are feeling lucky!!");
        }
    }

    private static int roll(Enemy name, Player player, int rollCount){
        int enemyNumber = name.rollDiceTotalEnemy(rollCount);
        System.out.println("Enemy total " + enemyNumber);
        int playerNumber = player.rollDiceTotalPlayer(rollCount);
        System.out.println("Player total " + playerNumber);
        return (enemyNumber - playerNumber);
    }
}
