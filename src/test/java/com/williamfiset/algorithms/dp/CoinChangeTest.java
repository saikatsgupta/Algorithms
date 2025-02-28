package com.williamfiset.algorithms.dp;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.primitives.Ints;
import com.williamfiset.algorithms.utils.TestUtils;
import java.util.*;
import org.junit.*;

public class CoinChangeTest {

  static final int LOOPS = 100;

  @Test
  public void testCoinChange() {
    for (int i = 1; i < LOOPS; i++) {
      List<Integer> values = TestUtils.randomIntegerList(i, 1, 1000);
      int[] coinValues = Ints.toArray(values);

      int amount = TestUtils.randValue(1, 1000);

      CoinChange.Solution solution1 = CoinChange.coinChange(coinValues, amount);
      int v1 = solution1.minCoins;
      int v2 = CoinChange.coinChangeSpaceEfficient(coinValues, amount);
      int v3 = CoinChange.coinChangeRecursive(coinValues, amount);

      assertThat(v1).isEqualTo(v2);
      assertThat(v2).isEqualTo(v3);
    }
  }

  @Test
  public void testCoinChangeSelectedCoins() {
    for (int i = 1; i < LOOPS; i++) {
      List<Integer> values = TestUtils.randomIntegerList(i, 1, 1000);
      int[] coinValues = Ints.toArray(values);

      int amount = TestUtils.randValue(1, 1000);

      CoinChange.Solution solution = CoinChange.coinChange(coinValues, amount);
      int selectedCoinsSum = 0;
      for (int v : solution.selectedCoins) {
        selectedCoinsSum += v;
      }
      if (solution.minCoins == -1) {
        assertThat(solution.selectedCoins.size()).isEqualTo(0);
      } else {
        // Verify that the size of the selected coins is equal to the optimal solution.
        assertThat(solution.selectedCoins.size()).isEqualTo(solution.minCoins);

        // Further verify that the sum of the selected coins equals the amount we want to make.
        assertThat(selectedCoinsSum).isEqualTo(amount);
      }
    }
  }
}
