package com.gauravrmsc.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class SuperDuperDrive {

  public static void main(String[] args) {
    SpringApplication.run(SuperDuperDrive.class, args);
  }



}
