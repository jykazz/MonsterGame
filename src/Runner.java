import entities.FantasyCharacter;
import entities.monsters.Goblin;
import entities.monsters.Skeleton;
import entities.players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {
    private static BufferedReader reader;
    private static FantasyCharacter player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру!\n");
        Runner.reader = new BufferedReader(new InputStreamReader(System.in));
        Runner.battle = new Battle();
        System.out.print("Придумайте имя для вашего персонажа: ");
        try {
            Runner.playerCommand(Runner.reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void playerCommand(String string) throws IOException {
        if (Runner.player == null) {
            Runner.player = new Player(string, 100, 20, 20, 0, 0);
            System.out.println(String.format("\nДобро пожаловать, %s!", Runner.player.getName()));
            Runner.showNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                Runner.showNavigation();
                playerCommand(Runner.reader.readLine());
            }
            break;
            case "2": {
                Runner.commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                Runner.playerCommand("2");
                break;
            case "нет": {
                Runner.showNavigation();
                Runner.playerCommand(Runner.reader.readLine());
            }
        }
        Runner.playerCommand(Runner.reader.readLine());
    }

    private static void commitFight() {
        Runner.battle.fight(Runner.player, Runner.createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота. Помимо этого у вас осталось %d едениц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHealthPoints()));
                System.out.println("\nХотите продолжить поход? (да/нет). Если нет, отправляйтесь в город");
                try {
                    Runner.playerCommand(Runner.reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void showNavigation() {
        System.out.println("\nВыберите, куда вы хотите пойти:");
        System.out.println("К торговцу: 1");
        System.out.println("В темный лес: 2");
        System.out.println("Выход: 3");
    }

    private static FantasyCharacter createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Гоблин Жора", 50, 10, 10, 100, 20);
        else return new Skeleton("Скелет Толя", 25, 20, 20, 100, 10);
    }

    interface FightCallback {
        void fightWin();

        void fightLost();
    }
}