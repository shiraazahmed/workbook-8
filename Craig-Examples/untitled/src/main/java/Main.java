package com.pluralsight;

import com.pluralsight.data.FilmDAO;
import com.pluralsight.model.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(username);
        dataSource.setPassword(password);


        displayAllFilmsScreen(dataSource);
        displayFilmSearchScreen(dataSource);

    }

    private static void displayAllFilmsScreen(BasicDataSource dataSource) {
        FilmDAO filmDAO = new FilmDAO(dataSource);
        List<Film> films = filmDAO.getAll();

        //print header row
        System.out.printf("%-4s %-40s %10s%n", "Id", "Title", "Release Year");
        System.out.println("_________________________________________________________________________________");

        for (Film film:films){
            System.out.printf("%-4d %-40s %10d%n", film.getFilmId(), film.getTitle(), film.getReleaseYear());
        }
    }

    private static void displayFilmSearchScreen(BasicDataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for films that start with: ");
        String searchTerm = scanner.nextLine() + "%";

        FilmDAO filmDAO = new FilmDAO(dataSource);
        List<Film> films = filmDAO.search(searchTerm);

        //print header row
        System.out.printf("%-4s %-40s %10s%n", "Id", "Title", "Release Year");
        System.out.println("_________________________________________________________________________________");

        for (Film film:films){
            System.out.printf("%-4d %-40s %10d%n", film.getFilmId(), film.getTitle(), film.getReleaseYear());
        }
    }


}