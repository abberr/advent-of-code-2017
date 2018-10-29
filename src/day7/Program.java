package day7;

import java.util.ArrayList;

public class Program {
	
	String name;	
	int weight;
	int totalWeight;
	ArrayList<Program> children;
	
	
	public Program(String name, int weight) {
		this.name = name;
		this.weight = weight;
		children = new ArrayList<>();
	}
	
	public Program (String name) {
		this.name = name;
		children = new ArrayList<>();
	}
	
	public void addChild(Program child) {
		children.add(child);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public ArrayList<Program> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		return name + "(" + weight + ")";
	}

	
	
}
