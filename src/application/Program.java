package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String path = "G:\\Documentos\\FUMEC\\Ciência da Computação\\Curso\\Java\\20 - Programação funcional e expressões lambda\\in.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();
			
			while(line != null) {
				String fields[] = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salaryCap = sc.nextDouble();
			
			Comparator<String> compEmail = (x,y) -> x.compareTo(y); //não é necessário pq String já tem o compareTo
			
			List<String> listEmails = list.stream()
					.filter(x -> x.getSalary() > salaryCap)
					.map(x -> x.getEmail()).sorted(compEmail) //pode ser feito sem o Comparator como parâmetro por ser String
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than :" + String.format("%.2f", salaryCap));
			listEmails.forEach(System.out::println);
			
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}

}
