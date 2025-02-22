package com.Service;

public class Student {
	   private String name;
	    private int age;
	    private String email;
	    private String gender;
	    private String phone;
	    private String password;
	    
	  
	    public Student(String name, int age, String email, String gender, String phone,String  password) {
	        this.name = name;
	        this.age = age;
	        this.email = email;
	        this.gender = gender;
	        this.phone = phone;
	        this.password=password;
	    }
	    
	    
	    public String getName() {
	        return name;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    public int getAge() {
	        return age;
	    }
	    
	    public void setAge(int age) {
	        this.age = age;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getGender() {
	        return gender;
	    }
	    
	    public void setGender(String gender) {
	        this.gender = gender;
	    }
	    
	    public String getPhone() {
	        return phone;
	    }
	    
	    public void setPhone(String phone) {
	        this.phone = phone;
	    }
	    public String getPassword() {
	        return password;
	    }
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }

}
