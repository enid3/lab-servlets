package by.bsu.soroka.ea.neko.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Comparsion {
    private List<Product> toCompare;
    private List<Integer> ratingsSaved;

    private List<Integer> results;
    private List<Integer> first;
    private List<Integer> second;

    private int stage;

    public void init(List<Product> products) {
        toCompare = new ArrayList<>(products);
        basicInit();
    }

    public Comparsion(){
        toCompare = new ArrayList<>();
        basicInit();
    }
    private void basicInit() {
        if(toCompare != null) {
            ratingsSaved = toCompare
                    .stream()
                    .map(Product::getRating)
                    .collect(Collectors.toCollection(ArrayList::new));

            results = new ArrayList<>();
            List<int[]> pairs = new ArrayList<>();

            for(int i = 0; i < toCompare.size(); ++i){
                for(int j = 0; j < toCompare.size(); ++j){
                    if(i != j){
                        int[] arr = {i, j};
                        pairs.add(arr);
                    }
                }
            }
            Collections.shuffle(pairs);

            first = pairs.stream()
                    .map(arr -> arr[0])
                    .collect(Collectors.toCollection(ArrayList::new));

            second = pairs.stream()
                    .map(arr -> arr[1])
                    .collect(Collectors.toCollection(ArrayList::new));

            results = new ArrayList<>();
            int cmpSize = toCompare.size() * (toCompare.size() - 1);
            for(int i = 0; i < cmpSize; ++i){
                results.add(-1);
            }
        }
    }

    public int getUndoneStage() {
        int i = -1;
        while (++i != results.size() && results.get(i) != -1);
        return i;
    }

    public int chooseFirst() {
        return choose(0);
    }

    public int chooseSecond() {
        return choose(1);
    }

    private int choose(int i) {
        results.set(stage, i);
        return ++stage;
    }

    public boolean isStageValid(int stage) {
        return (stage >= 0 && stage < getSize());
    }

    public boolean isStageDone(int stage){
        return isStageValid(stage) && (results.get(stage) != -1);
    }

    public int getSize(){
        return results.size();
    }

    private List<Integer> getRatings(List<Integer> cmpResult, int size) {
        List<Integer> ratings = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            ratings.add(0);
        }
        for(int i = 0; i < cmpResult.size(); ++i) {
            int res = cmpResult.get(i);
            int rating = ratings.get(cmpResult.get(i));
            ratings.set(res, rating + 1);
        }

        return ratings;

    }
    public Product getCurrentFirst() {
        return toCompare.get(first.get(stage));
    }

    public Product getCurrentSecond() {
        return toCompare.get(second.get(stage));
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void restoreRatings(){
        for(int i = 0; i < toCompare.size(); ++i) {
            toCompare.get(i).setRating(ratingsSaved.get(i));
        }
    }

    public boolean calcRatings() {
        if(getUndoneStage() == getSize()) {
            restoreRatings();
            for(int i = 0; i < results.size(); ++i) {
                int winner = -1;
                int loser = -1;
                switch (results.get(i)){
                    case 0:
                        winner = first.get(i);
                        loser = second.get(i);
                        break;
                    case 1:
                        winner = second.get(i);
                        loser = first.get(i);
                        break;

                }
                toCompare.get(winner).incRating();
                toCompare.get(loser).decRating();
            }
            return true;
        } else {
            return false;
        }
    }
    public List<Product> getToCompare() {
        return toCompare;
    }
    public int getResult(int stage) {
        log.error("{}", results);
        if(isStageValid(stage)) {
            return results.get(stage);
        } else {
           return -1;
        }
    }
}
