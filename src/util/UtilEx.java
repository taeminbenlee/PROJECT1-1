package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;



public class UtilEx {
	
	public static String processUploadFile(FileItem fileItem, String newfilename, String dir)throws IOException{

		String filename = fileItem.getName();	// 경로 + 파일명
		long sizeInBytes = fileItem.getSize();
		
		if(sizeInBytes > 0){	// 파일이 정상?		// d:\\tmp\\abc.txt d:/tmp/abc.txt 
			
			int idx = filename.lastIndexOf("\\"); 
			if(idx == -1){
				idx = filename.lastIndexOf("/");
			}
			
			filename = filename.substring(idx + 1);
		//	File uploadFile = new File(dir, filename);
			File uploadFile = new File(dir, newfilename); // 새로운 파일 명으로
		
			try{	
				fileItem.write(uploadFile);		// 실제 upload되는 부분
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
		return filename;	// DB에 저장하기 위한 return;
	}
	
	public static String extracTxt(String link) {
		
		int idx = link.lastIndexOf("=");
		if(idx == -1) {
			// 오류메시지
		}
		
		link = link.substring(idx+1);
		
		
		return link;
	
	}//end extracTxt

}//end class
