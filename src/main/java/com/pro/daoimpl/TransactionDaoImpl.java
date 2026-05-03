package com.pro.daoimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pro.config.AccountRepository;
import com.pro.config.TransactionRepository;
import com.pro.dao.TransactionDao;
import com.pro.pojo.Account;
import com.pro.pojo.Transaction;

@Component
public class TransactionDaoImpl implements TransactionDao{
	
	
	@Autowired
	private TransactionRepository tRepository;
	
	@Autowired
	private AccountRepository aRepository;
	
	@Override
	public void approveTransaction(int tid){

	    //Transaction tx = hTemplate.get(Transaction.class, tid);
	    //Account acc = hTemplate.get(Account.class, tx.getAccno());
		
		Optional<Transaction>top=tRepository.findById(tid);
		Transaction tx=top.get();
		
		Optional<Account>aop=aRepository.findById(tx.getAccno());
		Account acc=aop.get();

	    if("withdraw".equals(tx.getTtype())){
	        if(acc.getBal() < tx.getTammount()){
	            return; // optional handling
	        }
	        acc.setBal(acc.getBal() - tx.getTammount());
	    }
	    else{
	        acc.setBal(acc.getBal() + tx.getTammount());
	    }

	    tx.setTstatus("done");

	    aRepository.save(acc);
	    tRepository.save(tx);
	}
	@Override
	public void rejectTransaction(int tid){

	    //Transaction tx = hTemplate.get(Transaction.class, tid);
		Optional<Transaction>top=tRepository.findById(tid);
		Transaction tx=top.get();
	    tx.setTstatus("rejected");

	    tRepository.save(tx);
	}
	@Override
	public boolean adminTransaction(int accno, double amount, String type){

	    try{
	        //Account acc = hTemplate.get(Account.class, accno);
	    	Optional<Account>aop=aRepository.findById(accno);
			Account acc=aop.get();
	        if("withdraw".equals(type))
	        {
	            if(acc.getBal() < amount)
	            {
	                return false;
	            }
	            else
	            {
	            	acc.setBal(acc.getBal() - amount);
	            	aRepository.save(acc);
		            Transaction tx = new Transaction();
			        tx.setAccno(accno);
			        tx.setTammount(amount);
			        tx.setTtype("ADMIN_" + type);
			        tx.setTstatus("done");
			        tx.setTdate(new Date().toString());
	
			        tRepository.save(tx);
			        return true;
	            }
	        }
	        else
	        {
	            acc.setBal(acc.getBal() + amount);
	            aRepository.save(acc);
	            Transaction tx = new Transaction();
		        tx.setAccno(accno);
		        tx.setTammount(amount);
		        tx.setTtype("ADMIN_" + type);
		        tx.setTstatus("done");
		        tx.setTdate(new Date().toString());

		        tRepository.save(tx);
		        return true;
	        }

	    }
	    catch(Exception e){
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
	public boolean doTransaction(int accno, double amount, String type){

		
	    //Account acc = hTemplate.get(Account.class, accno);
		Optional<Account>aop=aRepository.findById(accno);
		Account acc=aop.get();
	    Date dt=new Date();
	    String sdt=dt.toString();
	    Transaction tx = new Transaction();
	    //if("frozen".equals(acc.getAccstatus())) return;
	    
	    if(type.equals("deposit"))
	    {
	        acc.setBal(acc.getBal() + amount);
	        tx.setTstatus("Done");
	    } 
	    else 
	    {
	        acc.setBal(acc.getBal() - amount);
	        tx.setTstatus("Done");
	    }

	    aRepository.save(acc);

	    
	    tx.setAccno(accno);
	    tx.setTammount(amount);
	    tx.setTtype(type);
	    tx.setTdate(sdt);

	    tRepository.save(tx);
	    return true;
	}
	@Override
	public List<Account> getAllAccounts() {
	    //return hTemplate.loadAll(Account.class);
		return (List<Account>) aRepository.findAll();
	}
	
	@Override
	public Account getAccountByUser(String accname){
	    //List<Account> list = (List<Account>) hTemplate.findByNamedParam(
	    //	    "from Account where accname = :accname",
	    //	    "accname",
	    //	    accname);
		List<Account> list = aRepository.getAccountByAccName(accname);
	    return list.isEmpty() ? null : list.get(0);
	}
	@Override
	public List<Transaction> getTransactions(int accno){

	    //return (List<Transaction>) hTemplate.findByNamedParam(
	    //    "from Transaction where accno = :accno",
	    //    "accno",
	    //    accno);
		return (List<Transaction>)tRepository.findByAccno(accno);
	}
	@Override
	public Transaction getLastTransaction(int accno){

	    //List<Transaction> list = (List<Transaction>) hTemplate.findByNamedParam(
	    //    "from Transaction where accno = :accno order by tid desc",
	    //    "accno",
	    //    accno);
		List<Transaction>list=tRepository.findByAccno(accno);

	    return list.isEmpty() ? null : list.get(0);
	}
	@Override
	public boolean requestTransaction(int accno, double amount, String type){

	    try{
	        Transaction tx = new Transaction();

	        tx.setAccno(accno);
	        tx.setTammount(amount);
	        tx.setTtype(type);
	        tx.setTstatus("pending");
	        tx.setTdate(new Date().toString());

	        tRepository.save(tx);

	        return true;
	    }
	    catch(Exception e){
	        e.printStackTrace();
	        return false;
	    }
	}
}
