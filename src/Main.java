package search;

import context.SearchContext;
import contract.SearchStrategy;
import implementations.MatchesAllStrategy;
import implementations.MatchesAnyStrategy;
import implementations.MatchesNoneStrategy;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//    File
//    Dwight Joseph djo@gmail.com
//    Joseph Webb webb@gmail.com
//    Katie Jacobs

//        Map for Inverted Index and save memory
//        Key(String) ---> Value (List<Integer>)
//        Dwight                [0]
//        Joseph                [0,1]
//        djo@gmail.com         [0]
//        Webb                  [1]
//        webb@gmail.com        [1]
//        katie                 [2]
//        Jacobs                [2]

        List<String> people = new ArrayList<>();
        String filePath = "people.txt";

        File file = new File(filePath);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                people.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String p : people){
            System.out.println(p);
        }

        Scanner scanner = new Scanner(System.in);
        Map<String,List<Integer>> invertedIndex = buildInvertedIndex(people);

        int option;
        do {
            System.out.println("====Menu====");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Select a matching strategy:");
                    String type = scanner.nextLine();

                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();
                    String[] searchWords = query.split(" ");

                    List<List<Integer>> searchResults = new ArrayList<>();
                    for (String word : searchWords) {
                        List<Integer> searchResult = invertedIndex.getOrDefault(word, new ArrayList<>());
                        searchResults.add(searchResult);
                    }

                    SearchContext context = new SearchContext();

                    SearchStrategy matchesAllStrategy = new MatchesAllStrategy();
                    SearchStrategy matchesAnyStrategy = new MatchesAnyStrategy();
                    SearchStrategy matchesNoneStrategy = new MatchesNoneStrategy();

                    context.setSearchStrategy(new MatchesAllStrategy());

                    if(Objects.equals(type, "ALL")){
                        context.setSearchStrategy(matchesAllStrategy);
                        context.executeStrategy(findCommonIntegers(searchResults), searchResults, invertedIndex, people);
                    } else if (Objects.equals(type, "ANY")) {
                       context.setSearchStrategy(matchesAnyStrategy);
                       context.executeStrategy(findCommonIntegers(searchResults), searchResults, invertedIndex, people);
                    } else if (Objects.equals(type, "NONE")) {
                       context.setSearchStrategy(matchesNoneStrategy);
                       context.executeStrategy(findCommonIntegers(searchResults), searchResults, invertedIndex, people);
                    } else {
                        return;
                    }
                    break;
                case 2:
                    printAllPeople(people);
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }while (option != 0);


        scanner.close();


    }

    private static Map<String, List<Integer>> buildInvertedIndex(List<String> people){
        Map<String, List<Integer>> invertedIndex = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for(int i=0; i < people.size(); i++){
            String[] words = people.get(i).split(" ");

            for(String word : words) {
                if(!invertedIndex.containsKey(word)){
                    invertedIndex.put(word,new ArrayList<>());
                }
                invertedIndex.get(word).add(i);
            }
        }
        return invertedIndex;
    }

    private static void printAllPeople(List<String> people){
        for (String person : people){
            System.out.println(person);
        }

    }

    private static void exit(){
        System.out.println("Bye!");
    }

    public static List<Integer> findCommonIntegers(List<List<Integer>> searchResult) {
        Set<Integer> commonIntegers = new HashSet<>(searchResult.get(0));

        for (List<Integer> list : searchResult) {
            commonIntegers.retainAll(list);
        }

        return new ArrayList<>(commonIntegers);
    }


}