package edu.bsu.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WaitTimeTest {
    @BeforeEach
    void setUpData() {
        SingletonDataStore data = SingletonDataStore.getInstance();
        WaitTime time = new WaitTime();
        final List<Party> partyList = data.getPartyList();
        partyList.add(new Party(3, "John", "123-345-1243", time.waitTimeAlgorithm()));
        partyList.add(new Party(3, "Sam", "123-345-4596", time.waitTimeAlgorithm()));
    }

    @Test
    public void testAlgorithm() {
        WaitTime time = new WaitTime();
        int waitTime = time.waitTimeAlgorithm();
        System.out.println(waitTime);
    }
}
