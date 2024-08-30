
import java.util.*;

public class booking {
    
	static int lowerBirthAval=1;
	static int upperBirthAval=1;
	static int middleBirthAval=1;
	static int RacAval=1;
	static int waitingListAval=1;
	
	static Queue<Integer> waitingList = new LinkedList<>();
	static Queue<Integer> racList = new LinkedList<>();
	static List<Integer> bookedList = new ArrayList<>();
	
	static List<Integer> lowerBirthPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> middleBirthPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> upperBirthPosition= new ArrayList<>(Arrays.asList(1));
	static List<Integer> racPosition = new ArrayList<>(Arrays.asList(1));
	static List<Integer> waitingListPosition = new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer,passenger> Passengers = new HashMap<>();
	
	//book ticket
	
	public void bookTicket(passenger p,int birthInfo,String allotedBirth) {
		
		p.number=birthInfo;
		p.alloted=allotedBirth;
		Passengers.put(p.passengerId, p);
		bookedList.add(p.passengerId);
		System.out.println("--------------------Ticket Booked Succesfuly");
		
	}
	public void addToRac(passenger p,int RacInfo,String allotedRac) {
		p.number=RacInfo;
		p.alloted=allotedRac;
		Passengers.put(p.passengerId, p);
		racList.add(p.passengerId);
		RacAval--;
		racPosition.remove(0);
		System.out.println("--------------------Added to RAC");
	}
	public void addToWaitingList(passenger p,int waitingListInfo,String allotedWL) {
		p.number=waitingListInfo;
		p.alloted=allotedWL;
		Passengers.put(p.passengerId,p);
		waitingList.add(p.passengerId);
		waitingListAval--;
		waitingListPosition.remove(0);
		System.out.println("------------Added to waiting list ");	
	}
	public void cancelTicket(int id) {
		passenger p = Passengers.get(id);
		Passengers.remove(Integer.valueOf(id));
		bookedList.remove(Integer.valueOf(id));
		
		int positionBooked = p.number;
		
		System.out.println("---------------cancelled succesfully");
		
		if(p.alloted.equals("L")) {
			lowerBirthAval++;
			lowerBirthPosition.add(positionBooked);
		}
		else if(p.alloted.equals("M")){
			middleBirthAval++;
			middleBirthPosition.add(positionBooked);
		}
		else if(p.alloted.equals("U")) {
			upperBirthAval++;
			upperBirthPosition.add(positionBooked);
		}
		
		if(racList.size()>0) {
			passenger racPassenger = Passengers.get(racList.poll());
			int positionRac = racPassenger.number;
			racPosition.add(positionRac);
			racList.remove(Integer.valueOf(racPassenger.passengerId));
			RacAval++;
			
			if(waitingList.size()>0) {
				passenger WLpassenger = Passengers.get(waitingList.poll());
				int positionWl = WLpassenger.number;
				waitingListPosition.add(positionWl);
				waitingList.remove(Integer.valueOf(WLpassenger.passengerId));
				
				WLpassenger.number=racPosition.get(0);
				WLpassenger.alloted="RAC";
				racPosition.remove(0);
				racList.add(WLpassenger.passengerId);
				
				waitingListAval++;
				RacAval--;
			}
			RailwayReservationSystem.bookTicket(racPassenger);
			 
		}
	}
	public void printAvailabelTicket() {
        System.out.println("--------------------------");
		 System.out.println("Available Lower Berths "  + lowerBirthAval);
	        System.out.println("Available Middle Berths "  + middleBirthAval);
	        System.out.println("Available Upper Berths "  + upperBirthAval);
	        System.out.println("Availabel RACs " + RacAval);
	        System.out.println("Available Waiting List " + waitingListAval);
	        System.out.println("--------------------------");
	}
	public void showBookedTickets() {
		if(Passengers.size()==0) {
		  System.out.println("No bookings");
		  return;
		}
		for(passenger p:Passengers.values()) {
			System.out.println("-----------------------------------");
			System.out.println("Passenger Id:" + p.passengerId);
			System.out.println("Name" + p.name);
			System.out.println("Age" + p.age);
			System.out.println("Status" + p.number+p.alloted);
			System.out.println("-----------------------------------");
		}
		
	}
	
}
