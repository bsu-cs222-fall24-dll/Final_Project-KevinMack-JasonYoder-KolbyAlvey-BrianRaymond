package edu.bsu.cs;

import java.util.*;

public class WaitlistManager {
    private List<Party> waitlist;

    public WaitlistManager () {
        this.waitlist = new ArrayList<>();
    }

    public void addParty(Party party) {
        waitlist.add(party);
        sortWaitlist();
    }

    public void removeParty(Party party) {
        waitlist.remove(party);
    }

    public List<Party> getWaitlist() {
        return waitlist;
    }

    private void sortWaitlist() {
        waitlist.sort(Comparator.comparingLong(Party::getWaitTime).reversed());
    }


}
