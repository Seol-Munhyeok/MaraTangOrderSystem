package com.example.MaraTangOrderSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Scanner;

@SpringBootApplication
@EntityScan(basePackages = "com.example.MaraTangOrderSystem.model")
public class MaraTangOrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaraTangOrderSystemApplication.class, args);
		// 사용자 입력으로 종료 대기
		Scanner scanner = new Scanner(System.in);
		System.out.println("데이터베이스가 초기화되었습니다. 종료하려면 아무 키나 입력하세요...");
		scanner.nextLine(); // 사용자 입력 대기
		System.out.println("애플리케이션 종료.");
	}

}
