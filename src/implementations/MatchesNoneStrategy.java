package implementations;

import contract.SearchStrategy;

import java.util.*;

public class MatchesNoneStrategy implements SearchStrategy {

    @Override
    public void matches(List<Integer> commonIntegers, List<List<Integer>> searchResults, Map<String, List<Integer>> invertedIndex, List<String> people) {
        Set<Integer> allMatches = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (List<Integer> matches : searchResults) {
            allMatches.addAll(matches);
        }

        Set<Integer> noneMatches = new HashSet<>();
        for (List<Integer> indices : invertedIndex.values()) {
            noneMatches.addAll(indices);
        }
        noneMatches.removeAll(allMatches);

        result = new ArrayList<>(noneMatches);

        for(int i =0; i < result.size(); i++){
            System.out.println(people.get(result.get(i)));
        }
    }
}
