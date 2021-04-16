package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.RescuedDogDao;
import Dto.RescuedDogDto;


@WebServlet("/rcdFile") 
public class RescuedFileController extends HttpServlet {

	ServletConfig mConfig = null;


	public void init(ServletConfig config) throws ServletException {
		mConfig = config;
	}


	/**
	 *
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In rcdFileController");

		String fupload = mConfig.getServletContext().getRealPath("/upload"); //톰캣 서버에 파일 저장하기

		int yourMaxRequestSize = 100 * 1024 * 1024;	// 파일 최대 사이즈 (1 Mb)
		int yourMaxMemorySize = 100 * 1024;			// 메모리 사이즈 (1 Kb)

		// 변수 셋팅
		String title = "";
		String fileName = "";
		String newfileName = "";
		String content = "";
		String filename = "";
		String sseq = "";
		int seq = 0;

		//////////////////////DB 저장을 위한 셋팅 (이름 받아오기) ///////////////////////////



		//넘어온게  multipart인지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart == true){

			// FileItem 객채 생성
			DiskFileItemFactory factory = new DiskFileItemFactory();
			System.out.println("RcdWriteController 1");

			// File 셋팅 및 업로드
			factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
			factory.setRepository(new File(fupload)); // 저장소 
			ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
			upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
			System.out.println("RcdWriteController 2");


			//list에 정보 저장후 꺼내기
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println("RcdWriteController 3");

			Iterator<FileItem> it = items.iterator();
			System.out.println("RcdWriteController 4");


			//데이터 타입 조사
			while(it.hasNext()){
				FileItem item = it.next();

				//폼 필드라면
				if(item.isFormField()){	

					if(item.getFieldName().equals("title")){
						title = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("oldfile")){
						filename = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("content")){
						content = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("seq")){
						sseq = item.getString("utf-8");
					}

				}

				//폼 필드가 아니라면 
				else{	
					if(item.getFieldName().equals("fileload")){

						//파일위치+파일명
						newfileName = item.getName(); 
						
						//파일을 다시 업로드 하는 경우
						if(!(newfileName == null || newfileName.equals(""))){
							int lastInNum = newfileName.lastIndexOf(".");
							String exName = newfileName.substring(lastInNum);

							//이미지 파일 아니라면
							if(!(exName.equals(".jpg") || exName.equals(".png")  || exName.equals(".gif")  
									|| exName.equals(".jpeg") || exName.equals(".jfif"))) {
								resp.sendRedirect("rescued?param=fail");
								return;
							}

							//새로운 파일명 + 확장자
							newfileName = (new Date().getTime()) + ""; 
							newfileName = newfileName + exName;

							//파일 업로드
							processUploadFile(item, fupload, newfileName); 

							//확인하기 
							System.out.println("title : "+title);
							System.out.println("content : "+content);
							System.out.println("filename : "+fileName);
							System.out.println("newfilename : "+newfileName);
							System.out.println("seq : "+sseq);
						}
					} 	
				} // else

				//변경된 파일이 없다면 newfile을 기존 file로 변경
				if(newfileName == null || newfileName.equals("")){
					newfileName = filename;
				}

			} // while


			//확인하기 
			System.out.println("title : "+title);
			System.out.println("content : "+content);
			System.out.println("filename : "+filename);
			System.out.println("newfilename : "+newfileName);
			System.out.println("seq : "+sseq);

			//Update & Upload 판단 후 DB 저장
			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = new RescuedDogDto(title, content, newfileName);
			boolean isS = false;
			
			//Upload
			if(sseq.equals("")) {
				isS = dao.writeRescued(dto);

			//update	
			} else {
				seq = Integer.parseInt(sseq);
				isS = dao.updateRescued(dto, seq);
			}

			if(isS) {
				System.out.println("writeRescued Success!!");
				resp.sendRedirect("rescued?param=list");
			}
		}
	}



// 파일 업로드용 Method		
public static void processUploadFile(FileItem fileItem, String dir, String filename)throws IOException{

	String originalfilename = fileItem.getName();	// 경로 + 파일명
	long sizeInBytes = fileItem.getSize();          // 파일의 크기

	//파일이 정상인지 확인
	if(sizeInBytes > 0){			// d:\\tmp\\abc.txt 혹은 d:/tmp/abc.txt 
		//파일명만 가져오기
		int idx = originalfilename.lastIndexOf("\\"); 
		if(idx == -1){
			idx = originalfilename.lastIndexOf("/");
		}

		originalfilename = originalfilename.substring(idx + 1);
		//파일 생성
		File uploadFile = new File(dir, filename); //경로, 뉴파일 이름
		System.out.println("파일경로 : " + dir + "/"+ filename);

		//실제 파일에 업로드 (Byte값 저장)
		try{	
			fileItem.write(uploadFile);		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	System.out.println("fileUpload success!");
}




}