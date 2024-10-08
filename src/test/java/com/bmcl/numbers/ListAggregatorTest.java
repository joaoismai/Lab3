package com.bmcl.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {

    public ListAggregator aggregator;
    public List<Integer> list;
    public List<Integer> list2;

    @BeforeEach
    public void setUp(){
        aggregator = new ListAggregator();
        list = Arrays.asList(1,2,4,2,5);
        list2 = Arrays.asList(-1,-2,-5);
    }
    @Test
    public void maxbug7263() {

        int max = aggregator.max(list2);
        Assertions.assertEquals(-1, max);
    }

    @Test
    public void sum() {
        int sum = aggregator.sum(list);
        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {
        int max = aggregator.max(list);
        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {
        int min = aggregator.min(list);
        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {
        //List<Integer> list = Arrays.asList(1,2,4,2,5);
        class StubListDeduplicator implements GenericListDeduplicator{
            @Override public List<Integer> deduplicate(List<Integer> list) {
                return  Arrays.asList(1, 2, 4, 5);
            }
        }
        ListAggregator aggregator = new ListAggregator();
        StubListDeduplicator deduplicator = new StubListDeduplicator();
        int distinct = aggregator.distinct(list, deduplicator);
        Assertions.assertEquals(4, distinct);
    }
    @Test
    public void max_bug_8726() {
        List<Integer> list = Arrays.asList(1,2,4,2);
        class StubListDeduplicator implements GenericListDeduplicator{
            @Override public List<Integer> deduplicate(List<Integer> list) {
                return  Arrays.asList(1, 2, 4);
            }
        }
        ListAggregator aggregator = new ListAggregator();
        StubListDeduplicator deduplicator = new StubListDeduplicator();
        int distinct = aggregator.distinct(list, deduplicator);
        Assertions.assertEquals(3, distinct);
    }
}
