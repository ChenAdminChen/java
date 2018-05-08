package com.yf.af.data;

import org.springframework.transaction.TransactionStatus;

/**
 * 
 * 
 * 
 * @author xiemingquan
 *
 */
public interface DaoManagement {

	Object getService(String name);

	TransactionStatus startTransaction();

	void commitTransaction(TransactionStatus transStatus);

	void rollbackTransaction(TransactionStatus transStatus);
}