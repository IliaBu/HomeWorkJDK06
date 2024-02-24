package ru.gb_jdk.Game;

import java.util.ArrayList;

public class Presenter {
    private final MontyHall montyHall;
    private final View view;
    private final int iteration;

    public Presenter(int iteration){
        this.montyHall = MontyHall.getMontyHall();
        this.view = new View();
        this.iteration = iteration;
    }

    public void start(){
        for (int i = 0; i < iteration; i++){
            ArrayList<Door> doors = montyHall.getDoors();
            Boolean[] temp = new Boolean[3];
            montyHall.shuffleDoors();
            int firstChoice = montyHall.random();
            int numberFromLeader = montyHall.getDoorWithGoat(firstChoice);
            int j =0;
            while (j < temp.length) {
                if(j != firstChoice && j != numberFromLeader){
                    montyHall.addResult(i, doors.get(j).openDoor());
                }
                j++;
            }
        }
        view.printResult(percentWinsCount(), "Процент побед: ");
        view.printResult(getInfo("Коза"), "Количество выбранных дверей с козой: ");
        view.printResult(getInfo("Автомобиль"), "Количество выбранных дверей с автомобилем: ");
    }

    private int percentWinsCount(){
        int wins = montyHall.getResults().values().stream().filter(e -> e.toString().equalsIgnoreCase("автомобиль")).toList().size();
        int allcounted = montyHall.getResults().size();
        return wins * 100 / allcounted;
    }

    private int getInfo(String param){
        int unit = 0;
        for(String value : montyHall.getResults().values()){
            if(value.equalsIgnoreCase(param))
                unit++;
        }
        return  unit;
    }
}
