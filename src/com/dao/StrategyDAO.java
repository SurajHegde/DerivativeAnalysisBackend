package com.dao;

import java.util.ArrayList;

import java.util.List;

import com.pojo.Holding;
import com.pojo.Strategy;

public interface StrategyDAO {
	List<Strategy> getStrategies(String views);
	List<Holding> getHolding();
}
