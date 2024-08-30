public class passenger {
     
	static int id=1;
	int passengerId;
	String name;
	int age;
	String birthPreference;
	String alloted;
	int number;
	public  passenger(String name,int age,String birthPreference) {
		this.name=name;
		this.age=age;
		this.birthPreference=birthPreference;
		this.passengerId=id++;
		alloted="";
		number=-1;
		
	}
}
