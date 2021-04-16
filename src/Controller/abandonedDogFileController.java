package Controller;

import java.io.File;
import java.io.IOException;
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

import com.sun.glass.ui.Application;

import Dao.abandonedDogDao;
import Dto.abandonedDogDto;





@WebServlet("/filedd") 
public class abandonedDogFileController extends HttpServlet{
	
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	// 업로드한 경로를 취득하기 위해서
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doImageWrite(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doImageWrite(req, resp);
	}
	
	public void doImageWrite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		//확인용문자열
		System.out.println("In doImageWriteController");
		
		
		String param = req.getParameter("param");
		
		
		if(param.equals("writeAf")) {
			String fupload = mConfig.getServletContext().getRealPath("/upload"); //저장 위치
			
			int yourMaxRequestSize = 100 * 1024 * 1024;	// 파일 최대 사이즈 (1 Mb)
			int yourMaxMemorySize = 100 * 1024;			// 메모리 사이즈 (1 Kb)
	
			// 변수 셋팅
			String address = "";
			int danger = 0;
			String phonenum = "";
			String filename = "";
			String mycontent = "";
			String newfilename = "";
			String myid = "";
	
			//////////////////////DB 저장을 위한 셋팅 (이름 받아오기) ///////////////////////////
	
			//넘어온게  multipart인지 검사
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
	
				// FileItem 객채 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				System.out.println("writeAf 1");
	
				// File 셋팅 및 업로드
				factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
				factory.setRepository(new File(fupload)); // 저장소 
				ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
				upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
				System.out.println("writeAf 2");
	
	
				//list에 정보 저장후 꺼내기
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println("writeAf 3");
	
				Iterator<FileItem> it = items.iterator();
				System.out.println("writeAf 4");
	
				
				//데이터 타입 조사
				while(it.hasNext()){
					FileItem item = it.next();
	
					//폼 필드라면
					if(item.isFormField()){	
						
						if(item.getFieldName().equals("address")){
							address = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("oldfile")){
							filename = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("content")){
							mycontent = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("danger")){
							danger = Integer.parseInt(item.getString("utf-8"));
						}
						else if(item.getFieldName().equals("id")){
							myid = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("phonenum")){
							phonenum = item.getString("utf-8");
						}
	
					}				
					//폼 필드가 아니라면 
					else{	
						if(item.getFieldName().equals("fileload")){
	
							//파일위치+파일명
							filename = item.getName(); 
	
							//파일을 다시 업로드 하는 경우
							if(!(filename == null || filename.equals(""))){
								int lastInNum = filename.lastIndexOf(".");
								String exName = filename.substring(lastInNum);
				
								//새로운 파일명 + 확장자
								newfilename = (new Date().getTime()) + ""; 
								newfilename = newfilename + exName;
								
								//파일 업로드
								processUploadFile(item, fupload, newfilename); 
								
								//확인하기 
								System.out.println("id : "+myid);
								System.out.println("content : "+mycontent);
								System.out.println("phonenum : "+phonenum);
								System.out.println("danger : "+danger);
								System.out.println("address : "+address);
	
	
								System.out.println("filename : "+filename);
								System.out.println("newfilename : "+newfilename);
															
				
								}
							} 	
						} //else 
					} // while
				
				
				//DB에 저장
				abandonedDogDao dao = abandonedDogDao.getInstance();
				abandonedDogDto dto = new abandonedDogDto(myid, phonenum, address, mycontent, filename,
						newfilename, danger);
				boolean isS = dao.writeReport(dto);
				if(isS)
					System.out.println("write Success!!");
				
				resp.sendRedirect("index.jsp");
				
				}		
		}
		else if (param.equals("updateAf")){
			String fupload = mConfig.getServletContext().getRealPath("/upload"); //저장 위치
			
			int yourMaxRequestSize = 100 * 1024 * 1024;	// 파일 최대 사이즈 (1 Mb)
			int yourMaxMemorySize = 100 * 1024;			// 메모리 사이즈 (1 Kb)
			
			String yourTempDir = fupload;
			
			// 변수
			String address = "";
			int danger = 0;
			String phonenum = "";
			String oldfilename = "";
			String mycontent = "";
			String oldnewfilename = "";
			String myid = "";
			String sseq="";
			
			//file명 저장
			String filename = "";
			String newfilename = "";
			
			//넘어온게  multipart인지 검사
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
	
				// FileItem 객채 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				System.out.println("updateAf 1");
	
				// File 셋팅 및 업로드
				factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
				factory.setRepository(new File(fupload)); // 저장소 
				ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
				upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
				System.out.println("updateAf 2");
	
	
				//list에 정보 저장후 꺼내기
				List<FileItem> items = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println("updateAf 3");
	
				Iterator<FileItem> it = items.iterator();
				System.out.println("updateAf 4");
	
				
				//데이터 타입 조사
				while(it.hasNext()){
					FileItem item = it.next();
	
					//폼 필드라면
					if(item.isFormField()){	
						
						if(item.getFieldName().equals("address")){
							address = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("oldfile")){
							oldfilename = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("oldnewfile")) {
							oldnewfilename = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("mycontent")){
							mycontent = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("danger")){
							danger = Integer.parseInt(item.getString("utf-8"));
						}
						else if(item.getFieldName().equals("id")){
							myid = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("phonenum")){
							phonenum = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("seq")) {
							sseq = item.getString("utf-8");
						}
	
					}				
					//폼 필드가 아니라면 
					else{	
						if(item.getFieldName().equals("fileload")){
	
							//파일위치+파일명
							filename = item.getName(); 
	
							//파일을 다시 업로드 하는 경우
							if(!(filename == null || filename.equals(""))){
								int lastInNum = filename.lastIndexOf(".");
								String exName = filename.substring(lastInNum);
								
								//새로운 파일명 + 확장자
								newfilename = (new Date().getTime()) + ""; 
								newfilename = newfilename + exName;
								
								//파일 업로드
								processUploadFile(item, fupload, newfilename); 
								
							}
						}
		
					}
					// 변경된 파일이 없으므로 기존의 파일을 저장한다
					if(filename == null || filename.equals("")){
						filename = oldfilename;
						newfilename = oldnewfilename;
						
					}
				}
			}
			abandonedDogDao dao = abandonedDogDao.getInstance();
			
			int seq = Integer.parseInt(sseq);
			System.out.println("myid" + myid);
			System.out.println("phonenum" + phonenum);
			System.out.println("address" + address);
			System.out.println("mycontent" + mycontent);
			System.out.println("filename" + filename);
			System.out.println("newfilename" + newfilename);
			System.out.println("danger" + danger);
			System.out.println("====================");

			


			
			boolean isS = dao.updateReport(seq, new abandonedDogDto(myid, phonenum, address, mycontent, filename, newfilename, danger));
			if(isS) {
				resp.sendRedirect("index.jsp");
			}
			
		}
	}
	
	
	
	
		
	// 파일 업로드용 Method		
	public String processUploadFile(FileItem fileItem, String dir, String filename)throws IOException{

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

			//실제 파일에 업로드 (Byte값 저장)
			try{	
				fileItem.write(uploadFile);		
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
		
		System.out.println("fileUpload success!");
		
		return originalfilename;
	}
	
}