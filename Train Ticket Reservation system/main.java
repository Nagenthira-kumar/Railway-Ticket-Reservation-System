import java.util.*;

public class RailwayReservationSystem {
	
	public static void  bookTicket(passenger p) {
		booking booker = new booking();
		if(booking.waitingListAval==0) {
			System.out.println("No ticket available");
			return;
		}
		if((p.birthPreference.equals("L")&&booking.lowerBirthAval>0)||
			(p.birthPreference.equals("M")&&booking.middleBirthAval>0)||
			(p.birthPreference.equals("U")&&booking.upperBirthAval>0)){
			
			System.out.println("Prefered birth available");
			
			if(p.birthPreference.equals("L")) {
				System.out.println("Lower birth given");
				booker.bookTicket(p, (booking.lowerBirthPosition.get(0)), "L");
				booking.lowerBirthPosition.remove(0);
				booking.lowerBirthAval--;
				
			}
			else if(p.birthPreference.equals("M")) {
				System.out.println("Middle birth given");
				booker.bookTicket(p, (booking.middleBirthPosition.get(0)), "M");
				booking.middleBirthPosition.remove(0);
				booking.middleBirthAval--;
				
			}
			else if(p.birthPreference.equals("U")) {
				System.out.println("Upper birth given");
				booker.bookTicket(p, (booking.upperBirthPosition.get(0)), "U");
				booking.upperBirthPosition.remove(0);
				booking.upperBirthAval--;
				
			}
		}
		
		else if(booking.lowerBirthAval>0) {
			System.out.println("Lower birth given");
			booker.bookTicket(p, (booking.lowerBirthPosition.get(0)), "L");
			booking.lowerBirthPosition.remove(0);
			booking.lowerBirthAval--;
		}
		else if(booking.middleBirthAval>0) {
			System.out.println("Middle birth given");
			booker.bookTicket(p, (booking.middleBirthPosition.get(0)), "M");
			booking.middleBirthPosition.remove(0);
			booking.middleBirthAval--;
			
		}
		else if(booking.upperBirthAval>0) {
			System.out.println("Upper birth given");
			booker.bookTicket(p, (booking.upperBirthPosition.get(0)), "U");
			booking.upperBirthPosition.remove(0);
			booking.upperBirthAval--;
		}
		else if(booking.RacAval>0) {
			System.out.println("RAC available");
			booker.addToRac(p,booking.racPosition.get(0),"RAC");	
		}
		else if(booking.waitingListAval>0) {
			System.out.println("Waitinglist available");
			booker.addToWaitingList(p, booking.waitingListPosition.get(0), "WAITING LIST");
		}
		
	}
	public static void cancelTicket(int id) {
		booking booker = new booking();
		
		if(!booker.Passengers.containsKey(id))
			System.out.println("passenger detais unknown");
		else
			booker.cancelTicket(id);
		}
	
	

	public static void main(String[] args) {
		
		boolean flag=true;
		while(flag) {
		System.out.println("1.Boook a ticket\n2.cancel a ticket\n3.show availabel ticket\n4.Booked ticket\n5.exit");
		Scanner sc = new Scanner(System.in);
		System.out.println("choose what to perform:");
		int choice = sc.nextInt();
		
		switch (choice) {
		
		case 1:{
			//Boook a ticket
			System.out.println("Enter passenger name,age,birthPreference:");
			String name = sc.next();
			int age = sc.nextInt();
			String birthPreference = sc.next();
			
			passenger p = new passenger(name,age,birthPreference);
			bookTicket(p);
			
		}
			break;
		case 2:{
			//cancel a ticket
			System.out.println("Enter passenger id to cancel");
			int id=sc.nextInt();
			cancelTicket(id);
		}
		break;
		case 3:{
			//show availabel ticket
			booking booker = new booking();
			booker.printAvailabelTicket();
		}
		break;
		case 4:{
			//Booked ticket
			booking booker = new booking();
			booker.showBookedTickets();
		}
		break;
		case 5:{
			//exit
			flag = false;
		}
		break;
		
		}
		
		
		}
		
		
	}

}
