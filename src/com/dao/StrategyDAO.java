package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.logic.Pair;
import com.pojo.Derivative;
import com.pojo.Strategy;

public interface StrategyDAO {
	List<Strategy> getStrategies(String views);
	List<Derivative> getDerivative();
}
