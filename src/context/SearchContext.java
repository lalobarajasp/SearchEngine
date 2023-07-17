package context;

import contract.SearchStrategy;

import java.util.List;
import java.util.Map;

public class SearchContext {
    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public boolean executeStrategy(List<Integer> commonIntegers, List<List<Integer>> searchResults, Map<String, List<Integer>> invertedIndex, List<String> people) {
        searchStrategy.matches(commonIntegers, searchResults, invertedIndex, people);
        return false;
    }

}
