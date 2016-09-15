package com.roporter.feature.timer;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class myTimer {
	NavigableMap<String, Long> timers = new TreeMap<String, Long>();
	
	public void start(String name) {
		if(timers.get(name) == null) {
			timers.put(name, System.currentTimeMillis());
		}
	}
	public String stop() {
		Entry<String, Long> lastEntry = timers.lastEntry();
		timers.remove(lastEntry.getKey());
		long time = System.currentTimeMillis() - lastEntry.getValue();
		return lastEntry.getKey() + " - " + time + "ms";
	}
}
