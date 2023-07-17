package contract;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    void matches(List<Integer> commonIntegers, List<List<Integer>> searchResults, Map<String, List<Integer>> invertedIndex, List<String> people);
}
