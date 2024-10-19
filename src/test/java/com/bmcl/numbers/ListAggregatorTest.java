package com.bmcl.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        ListAggregator aggregator = new ListAggregator();
        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        Mockito.when(deduplicator.deduplicate(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 4, 5));
        int distinct = aggregator.distinct(list, deduplicator);
        Assertions.assertEquals(4, distinct);
    }
    @Test
    public void max_bug_8726() {
        List<Integer> list = Arrays.asList(1,2,4,2);
        ListAggregator aggregator = new ListAggregator();
        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        Mockito.when(deduplicator.deduplicate(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 4));
        int distinct = aggregator.distinct(list, deduplicator);
        Assertions.assertEquals(3, distinct);
    }
}
