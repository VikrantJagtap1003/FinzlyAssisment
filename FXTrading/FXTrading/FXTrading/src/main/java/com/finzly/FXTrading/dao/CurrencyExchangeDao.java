package com.finzly.FXTrading.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.LoggingCacheErrorHandler;
import org.springframework.stereotype.Repository;

import com.finzly.FXTrading.entity.CurrencyExchange;

@Repository
public class CurrencyExchangeDao {

	@Autowired
	SessionFactory sessionFactory;

	Logger logger = LoggerFactory.getLogger(CurrencyExchangeDao.class);

	public String addCurrencyExchange(CurrencyExchange currencyExchange) {
		Session session = sessionFactory.openSession();
		session.save(currencyExchange);
		session.beginTransaction().commit();
		logger.info("currency pair is added" + currencyExchange);
		return "Currency Pair added";

	}

	public Object checkcurrencyPair(String currencyPair) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CurrencyExchange.class);
		criteria.add(Restrictions.eq("currencyPair", currencyPair));
		if (criteria.list().size() != 0) {
			logger.info("currency-pair  list is" + criteria.list());
			return criteria.list();

		} else {
			logger.info("currency-pair  list is empty");
			return null;
		}
	}

	public List<CurrencyExchange> getCurrencyPairRate(String currencyPair) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CurrencyExchange.class);
		criteria.add(Restrictions.eq("currencyPair", currencyPair));
		return criteria.list();

	}

	public List<CurrencyExchange> getAllCurrencyPair() {

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CurrencyExchange.class);
		logger.info("currency-pair  list is" + criteria.list());
		return criteria.list();
	}

	public String updateCurrencyPair(CurrencyExchange currencyExchangerObj) {
		Session session = sessionFactory.openSession();
		session.update(currencyExchangerObj);
		Criteria criteria = session.createCriteria(CurrencyExchange.class);
		logger.info("currency-pair  is updated " + criteria.list());
		return "currency pair updated";
	}
}
