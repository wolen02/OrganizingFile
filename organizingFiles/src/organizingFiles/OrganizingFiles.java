package organizingFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public class OrganizingFiles {

	public static void main(String[] args){
		
		try {
			Scanner sc = new Scanner(System.in);
			
			// 분류할 곳의 폴더 경로 입력
			
			System.out.print("분류할 파일이 존재하는 폴더의 경로를 입력해주세요(ex. c:\\Users\\Desktop\\song): ");
			String startPath = sc.nextLine();
			
			System.out.println(startPath);
			File dir = new File(startPath);

			File[] files = dir.listFiles();

			// 분류될 곳의 폴더 경로 입력
			
			System.out.print("분류가 될 폴더의 경로를 입력해주세요(ex. c:\\\\Users\\\\Desktop\\\\song): ");
			String arrivePath = sc.nextLine();
			
			ArrayList<String> genres = new ArrayList<>();
			
			/*장르 폴더 생성(while 문에 true를 직접 넣지 않고 변수로 넣은 이유는 향상된 
			 * for문 앞에 무한 반복문이 있으면 오류가 나기때문에 우회적으로 처리*/ 
			boolean keep = true;
			while(keep) {
				System.out.print("샘플의 장르를 입력해주세요(장르는 하나씩 입력해주시고 다음 단계로 넘어가시려면 1을 눌러주세요): ");
				String genre = sc.nextLine();
				
				if(genre.equals("1")) {
					keep = false;
				}else {
					genres.add(genre);
					File genreFile = new File(arrivePath + "\\" + genre);
					genreFile.mkdir();
				}
			}
			
			
			// 파일 이름 인식 및 분류
			for(File file:files) {
				
				String fileName = file.getName();
				
				for(String genre:genres) {
					// 해당 장르의 이름이 들어있다면 파일 이동
					if(StringUtils.containsIgnoreCase(fileName, genre)) {
						Path start = Paths.get(file.getAbsolutePath());
						Path arrive = Paths.get(arrivePath + "\\" + genre + "\\" + file.getName());
						Files.move(start, arrive, StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}catch(Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		sc.nextLine();

	}

}