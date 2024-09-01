import java.util.Map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class CountryFoodReport {
  public static void main(String[] args) {

  }
}

final class Functions {

  private final Map<String, Map<String, Integer>> costMap = new HashMap<>();
  private final Map<String, Map<String, Integer>> percentageMap = new HashMap<>();

  public void insertCounryFoodData(String country, Map<String, Integer> arr) throws Exception {
    if (costMap.containsKey(country)) {
      throw new Exception("country data already exist");
    }
    for (var cost : arr.values()) {
      if (cost < 0) {
        throw new Exception("invalid value detected");
      }
    }
    costMap.put(country, arr);
  }

  public void insertFoodPercentageData(String country, Map<String, Integer> arr) throws Exception {
    if (costMap.containsKey(country)) {
      throw new Exception("country data already exist");
    }
    for (var per : arr.values()) {
      if (per < 0 || per > 100) {
        throw new Exception("invalid value detected");
      }
    }
    percentageMap.put(country, arr);
  }

  public Map<String, Integer> searchCountry(String country) {
    return costMap.get(country);
  }

  public List<String> listCountries() {
    List<String> countries = new ArrayList<>();
    for (var key : costMap.keySet()) {
      countries.add(key);
    }
    return countries;
  }

  public int getTotalCostOf(String food) {
    int sum = 0;
    for (var foods : costMap.values()) {
      for (var entry : foods.entrySet()) {
        if (entry.getKey() == food) {
          sum += entry.getValue();
        }
      }
    }
    return sum;
  }

  public Pair<String, Integer> getMinimum() throws Exception {
    String DEFAULT = "default-value";
    Pair<String, Integer> minimumCountry = new Pair<>(DEFAULT, Integer.MAX_VALUE);
    for (var entry : costMap.entrySet()) {
      int sum = 0;
      for (var cost : entry.getValue().values()) {
        sum += cost;
      }
      if (minimumCountry.getValue() >= sum) {
        minimumCountry = new Pair<>(entry.getKey(), sum);
      }
    }
    if (minimumCountry.getKey() == DEFAULT) {
      throw new Exception("No data");
    }
    return minimumCountry;
  }
}