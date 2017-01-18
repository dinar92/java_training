package ru.job4j;

public class Calculate {
	public String echo(String str) {
		return String.format("%s", str);
	}
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("Hello world!"));
	}
}
