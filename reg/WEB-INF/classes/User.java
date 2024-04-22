package com.example.model;

public class User 
{
    private int id;
    private String name;
    private String rollNo;
    private double fees;
    private String status;

    public int getId() 
	{
        return id;
    }

    public void setId(int id) 
	{
        this.id = id;
    }

    public String getName() 
	{
        return name;
    }

    public void setName(String name) 
	{
        this.name = name;
    }

    public String getRollNo() 
	{
        return rollNo;
    }

    public void setRollNo(String rollNo) 
	{
        this.rollNo = rollNo;
    }

    public double getFees() 
	{
        return fees;
    }

    public void setFees(double fees) 
	{
        this.fees = fees;
    }

    public String getStatus() 
	{
        return status;
    }

    public void setStatus(String status)
	{
        this.status = status;
    }
}
