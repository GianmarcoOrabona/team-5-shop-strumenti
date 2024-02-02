package com.learning.team5shopstrumenti.comparator;

import com.learning.team5shopstrumenti.model.Strumento;

import java.util.Comparator;

public class StrumentoDataVenditaComparator implements Comparator<Strumento> {

    @Override
    public int compare(Strumento primoStrumento, Strumento secondoStrumento) {
        return Integer.compare(primoStrumento.topVenditeMese(), secondoStrumento.topVenditeMese());
    }
}
