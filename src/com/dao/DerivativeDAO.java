package com.dao;

import com.logic.Pair;
import com.pojo.Holding;
import java.util.List;

public interface DerivativeDAO {
	public List<Pair> generatePayoff(List<Holding> holdingList);
}
