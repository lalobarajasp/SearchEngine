package implementations;

import contract.SearchStrategy;

import java.util.*;

public class MatchesAllStrategy implements SearchStrategy {

    public static List<Integer> findCommonIntegers(List<List<Integer>> searchResult) {
        Set<Integer> commonIntegers = new HashSet<>(searchResult.get(0));

        for (List<Integer> list : searchResult) {
            commonIntegers.retainAll(list);
        }

        return new ArrayList<>(commonIntegers);
    }


    @Override
    public void matches(List<Integer> commonIntegers, List<List<Integer>> searchResults, Map<String, List<Integer>> invertedIndex, List<String> people) {
        for(int i =0; i < commonIntegers.size(); i++){
            System.out.println(people.get(commonIntegers.get(i)));
        }
    }
}
