// 

package suman.appdirect.model.event;

//--------------------------------------------------------------------------------------------------------------------------------
public class Payload {
	private Company company; 
	private Account account; 
								
	private Order order; 
	private Notice notice; 
	private User user; 
						

	// --------------------------------------------------------------------------------------------------------------------------------
	public Company getCompany() {
		return company;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setCompany(Company company) {
		this.company = company;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Account getAccount() {
		return account;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setAccount(Account account) {
		this.account = account;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Order getOrder() {
		return order;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setOrder(Order order) {
		this.order = order;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Notice getNotice() {
		return notice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User getUser() {
		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setUser(User user) {
		this.user = user;
	}
}
