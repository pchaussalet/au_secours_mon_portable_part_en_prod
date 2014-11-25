package net.chaussalet.bbl.asmppp.mygreatapp.model.status;

import java.util.Map;

import static com.google.common.collect.Maps.newTreeMap;

public class Statistics {
    public long eventsCount = 0;
    public Map<String, Long> eventsCountPerLine = newTreeMap();
}
