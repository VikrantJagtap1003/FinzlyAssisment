package com.finzly.FXTrading.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.FXTrading.entity.CurrencyExchange;
import com.finzly.FXTrading.entity.Trade;

@Repository
public class TradeDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public Object checkcurrencyPair(String currencyPair)
	{
		Session session=sessionFactory.openSession();
		Criteria criteria=session.createCriteria(CurrencyExchange.class);
		criteria.add(Restrictions.eq("currencyPair",currencyPair ));
		if(criteria.list().size()!=0)
		{
			return criteria.list();
		}
		else {
			return null;
		}
	}
		
	public void addTrade(Trade trade) {
		Session session=sessionFactory.openSession();
		session.save(trade);
		session.beginTransaction().commit();
	}

	public List<Trade> getBookedTradeList() {
		Session session=sessionFactory.openSession();
		Criteria criteria=session.createCriteria(Trade.class);
		return criteria.list();
	}

}
