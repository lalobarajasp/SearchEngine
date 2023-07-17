package implementations;

import contract.SearchStrategy;

import java.util.*;

public class MatchesAnyStrategy implements SearchStrategy {

    @Override
    public void matches(List<Integer> commonIntegers, List<List<Integer>> searchResults, Map<String, List<Integer>> invertedIndex, List<String> people) {
        Set<Integer> matchedIntegers = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (List<Integer> list : searchResults) {
            matchedIntegers.addAll(list);
        }

        result = new ArrayList<>(matchedIntegers);

        for(int i =0; i < result.size(); i++){
            System.out.println(people.get(result.get(i)));
        }
    }
}
