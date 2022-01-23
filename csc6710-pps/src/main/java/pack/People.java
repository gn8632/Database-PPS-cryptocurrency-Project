package pack;


public class People {
    
    public People(String email, String firstname, String lastname) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public People(String email, String password, String firstname, String lastname, String address,
			String dateOfBirth) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}
    public People() {}
   
	protected String email;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String address;
    protected String dateOfBirth;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
 
   
}